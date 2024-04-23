package ru.kfu.gitcommittemplate;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.actions.ActiveCheckinPanel;
import org.jetbrains.annotations.NotNull;

public class GitCommitTemplateAction extends AnAction {
    private static final String TEMPLATE = "<Номер задачи> <Описание>\nИзменения:\n- ";

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        CheckinProjectPanel checkinPanel = ActiveCheckinPanel.getActivePanel(e.getDataContext());
        if (checkinPanel != null) {
            checkinPanel.setCommitMessage(TEMPLATE);
        }
    }
}
