package ru.itis.kpfu.ideacommittemplate;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.CommitMessageI;
import com.intellij.openapi.vcs.VcsDataKeys;
import com.intellij.openapi.vcs.ui.Refreshable;
import org.jetbrains.annotations.NotNull;
import ru.itis.kpfu.ideacommittemplate.components.TemplateSelectDialog;

public class GitCommitTemplateAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        DataContext dc = e.getDataContext();
        Project project = CommonDataKeys.PROJECT.getData(dc);
        Refreshable panel = CheckinProjectPanel.PANEL_KEY.getData(dc);
        final CommitMessageI commitMessageI =
                (panel instanceof CommitMessageI) ? (CommitMessageI) panel : VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(dc);

        TemplateSelectDialog templateSelectDialog = new TemplateSelectDialog(project, commitMessageI);
        templateSelectDialog.show();
    }
}
