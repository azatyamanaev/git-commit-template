package ru.itis.kpfu.git_commit_template.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ComponentManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.itis.kpfu.git_commit_template.models.Template;
import ru.itis.kpfu.git_commit_template.util.SystemHelper;

@State(
        name = "AppSettingsState",
        storages = @Storage("AppSettingsState.xml")
)
public class AppSettingsState implements PersistentStateComponent<AppSettingsState> {

    public LinkedHashMap<String, Template> templates = new LinkedHashMap<>();
    public LinkedHashMap<String, String> localArgs = new LinkedHashMap<>();
    public LinkedHashMap<String, String> sysArgs = new LinkedHashMap<>();

    public static AppSettingsState getInstance() {
        return ApplicationManager.getApplication().getService(AppSettingsState.class);
    }

    public void setSystemArgs() {
        this.sysArgs.clear();
        this.sysArgs.putAll(SystemHelper.getGitSystemVars());

        //TODO: fix NoClassDefFoundError: git4idea/GitUtil
//        GitRepository repository = GitHelper.getGitRepository(project);
//        String branchName = GitHelper.getBranchName(repository);
//        if (branchName != null) {
//            sysArgs.put("branch", branchName);
//        }
    }

    @Override
    public @Nullable AppSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull AppSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
