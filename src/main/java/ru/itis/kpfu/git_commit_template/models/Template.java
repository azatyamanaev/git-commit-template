package ru.itis.kpfu.git_commit_template.models;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.kpfu.git_commit_template.config.AppSettingsState;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    private String name;
    private String content;

    public String fillContent(AppSettingsState settingsState) {
        String res = new String(content);
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
