package ru.itis.kpfu.ideacommittemplate.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JComponent;

import io.ktor.util.pipeline.Pipeline;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import ru.itis.kpfu.ideacommittemplate.models.Template;

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
        return settingsComponent.getPanel();
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
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settingsComponent.updateContent(settings.templates.values().stream().toList());
    }

    private void updatePipelines(AppSettingsState settings) {
        LinkedHashMap<Integer, LinkedHashMap<Integer, Pipeline>> map = new LinkedHashMap<>();

//        settings.projects.keySet().forEach(id -> {
//            map.put(id, (LinkedHashMap<Integer, Pipeline>) Utils.map(GitlabAPI.getProjectPipelines(id)));
//        });
//        settings.pipelines = map;

    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }
}
