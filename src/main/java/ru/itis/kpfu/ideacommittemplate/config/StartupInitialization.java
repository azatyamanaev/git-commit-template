package ru.itis.kpfu.ideacommittemplate.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

public class StartupInitialization implements StartupActivity {

    private static final Logger logger = Logger.getInstance(StartupInitialization.class);

    @Override
    public void runActivity(@NotNull Project project) {
        logger.debug("Running startup initialization");
        ApplicationManager.getApplication().getService(AppSettingsState.class);
        logger.debug("Finished startup initialization");
    }
}
