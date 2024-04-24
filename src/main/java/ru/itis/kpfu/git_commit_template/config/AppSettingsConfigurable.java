package ru.itis.kpfu.git_commit_template.config;

import javax.swing.JComponent;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import ru.itis.kpfu.git_commit_template.models.Argument;
import ru.itis.kpfu.git_commit_template.models.Template;

public class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent settingsComponent;

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "SDK: Application Settings Example";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsComponent.getNamesList();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsComponent = new AppSettingsComponent();
        return settingsComponent.getTabs();
    }

    @Override
    public boolean isModified() {
        return settingsComponent.isModified();
    }

    @Override
    public void apply() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settings.templates.clear();
        for (Template template : settingsComponent.getTemplates()) {
            if (!settings.templates.containsKey(template.getName())) {
                settings.templates.put(template.getName(), template);
            }
        }
        settings.params.clear();
        for (Argument argument : settingsComponent.getParamsModel().rows) {
            if (!settings.params.containsKey(argument.getName())) {
                settings.params.put(argument.getName(), argument.getValue());
            }
        }
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settingsComponent.updateContent(settings);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }
}
