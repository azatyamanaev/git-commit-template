package ru.itis.kpfu.git_commit_template.components;

import java.util.function.Function;

public class TableRowDefinition<T> {

    public String title;
    public Function<T, Object> tableModelRowFunction;

    public TableRowDefinition(String title, Function<T, Object> tableModelRowFunction) {
        this.title = title;
        this.tableModelRowFunction = tableModelRowFunction;
    }
}
