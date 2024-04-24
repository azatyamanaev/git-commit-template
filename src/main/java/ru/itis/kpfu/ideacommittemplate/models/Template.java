package ru.itis.kpfu.ideacommittemplate.models;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.kpfu.ideacommittemplate.config.AppSettingsState;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    private String name;
    private String content;

    public String fillContent(AppSettingsState settingsState) {
        String res = new String(content);
        for (Map.Entry<String, String> entry : settingsState.params.entrySet()) {
            res = res.replace("$" + entry.getKey(), entry.getKey() + " - " + entry.getValue());
        }
        return res;
    }

    @Override
    public String toString() {
        return name;
    }
}
