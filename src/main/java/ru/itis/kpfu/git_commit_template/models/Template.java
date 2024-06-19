package ru.itis.kpfu.git_commit_template.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.kpfu.git_commit_template.config.AppSettingsState;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    private String name;
    private String content;

    public String fillContent(AppSettingsState settingsState) {
        String res = content;
        for (Map.Entry<String, String> entry : settingsState.localArgs.entrySet()) {
            res = res.replace("$*" + entry.getKey(), entry.getKey() + " - " + entry.getValue());
        }
        for (Map.Entry<String, String> entry : settingsState.sysArgs.entrySet()) {
            res = res.replace("$&" + entry.getKey(), entry.getKey() + " - " + entry.getValue());
        }
        return res;
    }

    @Override
    public String toString() {
        return name;
    }
}
