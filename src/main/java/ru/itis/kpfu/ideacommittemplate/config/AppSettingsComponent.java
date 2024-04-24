package ru.itis.kpfu.ideacommittemplate.config;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
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
import ru.itis.kpfu.ideacommittemplate.components.ArgumentTableModel;
import ru.itis.kpfu.ideacommittemplate.models.Argument;
import ru.itis.kpfu.ideacommittemplate.models.Template;

@Getter
@Setter
public class AppSettingsComponent {

    private final JBTabbedPane tabs;
    private final JPanel templatesPanel;
    private JBList<Template> namesList;
    private JBTextField name;
    private JBTextArea textArea;
    private int selectedIndex;

    private final JPanel paramsPanel;
    private JBTable paramsTable;
    private ArgumentTableModel paramsModel;


    private boolean modified = false;

    public AppSettingsComponent() {

        AppSettingsState settingsState = AppSettingsState.getInstance();
        tabs = new JBTabbedPane();
        templatesPanel = FormBuilder.createFormBuilder()
                                    .addComponent(createTemplateList(settingsState
                                            .templates.values().stream().toList()), 1)
                                    .addComponentFillVertically(new JPanel(), 0)
                                    .getPanel();

        paramsModel = new ArgumentTableModel();
        paramsPanel = FormBuilder.createFormBuilder()
                .addComponent(createArgPanel(settingsState.getArgumentList()))
                                 .addComponentFillVertically(new JPanel(), 0)
                                 .getPanel();

        tabs.add("templates", templatesPanel);
        tabs.add("parameters", paramsPanel);
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
                    Template value = (Template) namesList.getModel().getElementAt(selectedIndex);
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
                Template selected = (Template) namesList.getModel().getElementAt(selectedIndex);
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

                Template selected = (Template) namesList.getModel().getElementAt(selectedIndex);
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

    public JPanel createArgPanel(List<Argument> arguments) {
        paramsTable = new JBTable(paramsModel);
        ArgumentTableModel.updateTable(paramsModel, arguments);
        paramsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        paramsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        paramsTable.setCellSelectionEnabled(true);
        paramsTable.setIntercellSpacing(new Dimension(5, 0));

        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(paramsTable);
        decorator.setAddAction(a -> {
            paramsModel.rows.add(new Argument("argument", "value"));
        });
        decorator.setRemoveAction(a -> {
            paramsModel.rows.remove(paramsTable.getSelectedRow());
        });


        JBScrollPane paramsPane = new JBScrollPane(paramsTable);
        paramsPane.setPreferredSize(new Dimension(700, 370));

        return FormBuilder.createFormBuilder()
                          .addComponent(decorator.createPanel(), 1)
                          .addComponentFillVertically(paramsPane, 2)
                          .getPanel();
    }

    public void updateContent(AppSettingsState settings) {
        name.setText("");
        textArea.setText("");
        namesList.clearSelection();
        ((CollectionListModel<Template>) namesList.getModel()).removeAll();
        ((CollectionListModel<Template>) namesList.getModel()).addAll(0, settings.templates.values().stream().toList());
        namesList.setSelectedIndex(0);
        Template selection = (Template) namesList.getModel().getElementAt(0);
        name.setText(selection.getName());
        textArea.setText(selection.getContent());

        ArgumentTableModel.updateTable(paramsModel, settings.getArgumentList());
    }

    public List<Template> getTemplates() {
        List<Template> templates = new ArrayList<>();
        for (int i = 0; i < namesList.getItemsCount(); i++) {
            templates.add((Template) namesList.getModel().getElementAt(i));
        }
        return templates;
    }
}
