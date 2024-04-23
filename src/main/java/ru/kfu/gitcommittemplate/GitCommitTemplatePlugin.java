package ru.kfu.gitcommittemplate;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class GitCommitTemplatePlugin implements ApplicationComponent {

    @Override
    public void initComponent() {
        // Инициализация слушателей или действий плагина
        System.out.println("MyPlugin initialized");
    }

    @Override
    public void disposeComponent() {
        // Освобождение ресурсов, если это необходимо
        System.out.println("MyPlugin disposed");
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "MyPlugin";
    }
}