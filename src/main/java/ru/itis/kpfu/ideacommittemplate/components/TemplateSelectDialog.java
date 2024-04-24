package ru.itis.kpfu.ideacommittemplate.components;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.Popup;

import com.github.weisj.jsvg.D;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.messages.MessageDialog;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vcs.CommitMessageI;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBUI;
import com.thaiopensource.relaxng.edit.Component;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.itis.kpfu.ideacommittemplate.GitCommitTemplateAction;
import ru.itis.kpfu.ideacommittemplate.config.AppSettingsState;
import ru.itis.kpfu.ideacommittemplate.models.Template;

public class TemplateSelectDialog extends DialogWrapper {

    private JPanel centerPanel;
    private JBList<Template> namesList;
    private int selectedIndex;
    private CommitMessageI commitMessageI;
    private final JBPopupFactory popupFactory = JBPopupFactory.getInstance();

    @Getter
    private final Project project;


    public TemplateSelectDialog(
            @Nullable Project project,
            @Nullable CommitMessageI commitMessageI) {
        super(project);
        this.project = project;
        this.commitMessageI = commitMessageI;

        super.setTitle("Saved templates");
        super.setOKButtonText("Select");
        super.init();
    }




    @Override
    public JComponent createCenterPanel() {
        centerPanel = new JPanel();

        namesList = new JBList<>(new CollectionListModel<>(
                AppSettingsState.getInstance().templates.values().stream().toList()));

        namesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        namesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
                    selectedIndex = namesList.locationToIndex(e.getPoint());
                    namesList.setSelectedIndex(selectedIndex);
                }
            }
        });
        namesList.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = namesList.locationToIndex(e.getPoint());
                if (index > -1) {
                    namesList.setToolTipText(namesList.getModel().getElementAt(index).getContent());
                }
            }
        });

        namesList.setMinimumSize(new Dimension(200, 480));

        JBScrollPane namesPane = new JBScrollPane(namesList);
        namesPane.setPreferredSize(new Dimension(200, 480));

        centerPanel.add(namesPane);
        centerPanel.setPreferredSize(new Dimension(200, 500));

        return centerPanel;
    }

    @Override
    protected void doOKAction() {
        if (commitMessageI != null && project != null) {
            commitMessageI.setCommitMessage(namesList.getModel().getElementAt(selectedIndex).fillContent(AppSettingsState.getInstance()));
        }
        super.close(OK_EXIT_CODE);
    }

    @Override
    public void doCancelAction() {
        super.doCancelAction();
    }
}
