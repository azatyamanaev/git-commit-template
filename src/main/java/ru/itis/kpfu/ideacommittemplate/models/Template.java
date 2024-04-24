package ru.itis.kpfu.ideacommittemplate.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    private String name;
    private String content;

    public String fillContent() {
        return content;
    }

    @Override
    public String toString() {
        return name;
    }
}
