package ru.kfu.gitcommittemplate;

import com.intellij.vcs.commit.CommitWorkflowListener;
import com.intellij.vcs.commit.CommitSession;
import org.jetbrains.annotations.NotNull;

public class GitCommitTemplateListener implements CommitWorkflowListener {

    private static final String TEMPLATE = "<Номер задачи> <Описание>\nИзменения:\n- ";

    @Override
    public void commitStarted(@NotNull CommitSession session) {
        if (session.getCommitMessage().isEmpty()) {
            session.setCommitMessage(TEMPLATE);
        }
    }

    @Override
    public void commitFinished(boolean success) {
        // Вы можете добавить действия после успешного коммита
    }
}