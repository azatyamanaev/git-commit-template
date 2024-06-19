package ru.itis.kpfu.git_commit_template.config;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.intellij.ui.CollectionListModel;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.FormBuilder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.itis.kpfu.git_commit_template.components.ArgumentTableModel;
import ru.itis.kpfu.git_commit_template.models.Argument;
import ru.itis.kpfu.git_commit_template.models.Template;
import ru.itis.kpfu.git_commit_template.util.SystemHelper;

@Getter
@Setter
public class AppSettingsComponent {

    private final JBTabbedPane tabs;
    private final JPanel templatesPanel;
    private JBList<Template> namesList;
    private JBTextField name;
    private JBTextArea textArea;
    private int selectedIndex;

    private final JPanel localArgsPanel;
    private JBTable localArgsTable;
    private ArgumentTableModel localArgsModel;

    private final JPanel systemArgsPanel;
    private JBTable systemArgsTable;
    private ArgumentTableModel systemArgsModel;

    private final AppSettingsState settingsState;
    private boolean modified = false;

    public AppSettingsComponent() {

        this.settingsState = AppSettingsState.getInstance();
        this.settingsState.refreshSystemArgs();

        tabs = new JBTabbedPane();
        templatesPanel = FormBuilder.createFormBuilder()
                .addComponent(createTemplateList(settingsState
                        .templates.values().stream().toList()), 0)
                .addComponentFillVertically(new JPanel(), 1)
                .getPanel();

        localArgsModel = new ArgumentTableModel();
        localArgsPanel = new JPanel(new BorderLayout());
        localArgsPanel.add(createLocalArgsPanel(SystemHelper.getArgumentList(settingsState.localArgs)), BorderLayout.CENTER);

        systemArgsModel = new ArgumentTableModel();
        systemArgsPanel = new JPanel(new BorderLayout());
        systemArgsPanel.add(createSystemArgsPanel(SystemHelper.getArgumentList(settingsState.sysArgs)), BorderLayout.CENTER);

        tabs.add("templates", templatesPanel);
        tabs.add("local args", localArgsPanel);
        tabs.add("system args", systemArgsPanel);
    }

    public JPanel createTemplateList(List<Template> templates) {

        namesList = new JBList<>(new CollectionListModel<>(templates));

        namesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        namesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
                    selectedIndex = namesList.locationToIndex(e.getPoint());
                    namesList.setSelectedIndex(selectedIndex);
                    Template value = namesList.getModel().getElementAt(selectedIndex);
                    name.setEnabled(true);
                    name.setText(value.getName());
                    textArea.setEnabled(true);
                    textArea.setText(value.getContent());
                }
            }
        });

        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(namesList);
        decorator.setAddAction(a -> {
            ((CollectionListModel<Template>) namesList.getModel()).add(new Template("Unnamed", ""));
            modified = true;
        });
        decorator.setRemoveAction(a -> {
            ((CollectionListModel<Template>) namesList.getModel()).remove(selectedIndex);
            name.setText("");
            textArea.setText("");
            modified = true;
        });

        JBScrollPane namesPane = new JBScrollPane(namesList);
        namesPane.setMinimumSize(new Dimension(50, 342));

        JPanel namesContainer = FormBuilder.createFormBuilder()
                .addComponent(decorator.createPanel(), 1)
                .addComponent(namesPane, 2)
                .getPanel();

        textArea = new JBTextArea();
        textArea.setText("");
        textArea.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                modified = true;
            }
        });
        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                Template selected = namesList.getModel().getElementAt(selectedIndex);
                selected.setContent(textArea.getText());
                ((CollectionListModel<Template>) namesList.getModel()).setElementAt(selected, selectedIndex);
            }
        });

        JBScrollPane templatePane = new JBScrollPane(textArea);
        templatePane.setMinimumSize(new Dimension(100, 370));

        name = new JBTextField();
        name.setText("");
        name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                modified = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                modified = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                modified = true;
            }
        });
        name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (name.getText().isBlank()) {
                    name.setText("Unnamed");
                }

                Template selected = namesList.getModel().getElementAt(selectedIndex);
                selected.setName(name.getText());
                ((CollectionListModel<Template>) namesList.getModel()).setElementAt(selected, selectedIndex);
            }
        });
        JPanel templateContainer = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Name:"), name, 1, false)
                .addComponent(templatePane)
                .getPanel();

        JBSplitter splitter = new JBSplitter();
        splitter.setProportion(0.3f);
        splitter.setFirstComponent(namesContainer);
        splitter.setSecondComponent(templateContainer);
        splitter.setPreferredSize(new Dimension(700, 342));
        splitter.setDividerWidth(2);

        return splitter;
    }

    public JPanel createLocalArgsPanel(List<Argument> arguments) {
        localArgsTable = new JBTable(localArgsModel);
        ArgumentTableModel.updateTable(localArgsModel, arguments);
        localArgsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        localArgsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        localArgsTable.setCellSelectionEnabled(true);
        localArgsTable.setIntercellSpacing(new Dimension(5, 0));

        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(localArgsTable);
        decorator.setAddAction(a -> localArgsModel.rows.add(new Argument("argument", "value")));
        decorator.setRemoveAction(a -> localArgsModel.rows.remove(localArgsTable.getSelectedRow()));

        JBScrollPane paramsPane = new JBScrollPane(localArgsTable);
        paramsPane.setVisible(true);


        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(decorator.createPanel(), BorderLayout.NORTH);
        panel.add(paramsPane, BorderLayout.CENTER);
        return panel;
    }

    public JPanel createSystemArgsPanel(List<Argument> arguments) {
        systemArgsTable = new JBTable(systemArgsModel);
        ArgumentTableModel.updateTable(systemArgsModel, arguments);
        systemArgsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        systemArgsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        systemArgsTable.setCellSelectionEnabled(true);
        systemArgsTable.setIntercellSpacing(new Dimension(5, 0));

        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(systemArgsTable);
        decorator.setAddAction(a -> {
            Map<String, String> vars = System.getenv();
            var keys = vars.keySet().stream()
                    .filter(key -> !settingsState.sysArgs.containsKey(key))
                    .toArray();
            // Create a dialog with a list of entries
            String selectedValue = (String) JOptionPane.showInputDialog(
                    null,
                    "Select an entry:",
                    "Entry Selection",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    keys,
                    keys[0]
            );

            if (selectedValue != null) {
                String val = vars.get(selectedValue);
                settingsState.sysArgs.put(selectedValue, val);
                systemArgsModel.rows.add(new Argument(selectedValue, val));
            }
        });
        decorator.setRemoveAction(a -> settingsState.sysArgs.remove(systemArgsModel.rows.remove(systemArgsTable.getSelectedRow()).getName()));

        JBScrollPane paramsPane = new JBScrollPane(systemArgsTable);
        paramsPane.setPreferredSize(new Dimension(700, 370));

        return FormBuilder.createFormBuilder()
                .addComponent(decorator.createPanel(), 1)
                .addComponent(paramsPane, 2)
                .getPanel();
    }

    public void updateContent(AppSettingsState settings) {
        name.setText("");
        textArea.setText("");
        namesList.clearSelection();
        ((CollectionListModel<Template>) namesList.getModel()).removeAll();
        ((CollectionListModel<Template>) namesList.getModel()).addAll(0, settings.templates.values().stream().toList());
        namesList.setSelectedIndex(0);
        Template selection = namesList.getModel().getElementAt(0);
        name.setText(selection.getName());
        textArea.setText(selection.getContent());

        ArgumentTableModel.updateTable(localArgsModel, SystemHelper.getArgumentList(settings.localArgs));
        ArgumentTableModel.updateTable(systemArgsModel, SystemHelper.getArgumentList(settings.sysArgs));
    }

    public List<Template> getTemplates() {
        List<Template> templates = new ArrayList<>();
        for (int i = 0; i < namesList.getItemsCount(); i++) {
            templates.add(namesList.getModel().getElementAt(i));
        }
        return templates;
    }
}
