# git-commit-template

Проект представляет из себя плагин для Intellij Idea, позволяющий создавать шаблоны для сообщений коммитов в Git. Он совместим с версиями Intellij Idea Community и Ultimate(2023.2.5+). 

# Требования к использованию

1. Установить OpenJDK 17
2. Задать JAVA_HOME по пути установки JDK 1
3. Клонировать репозиторий(при компиляции с локальной машины)

# Запуск через Intellij Idea

После установки зависимостей, следует произвести следующие шаги:
1. Запустить Intellij Idea и открыть Gradle проект из корня репозитория
2. Настроить использование Java 17 в проекте
- File -> Project Structure -> Project Settings -> Project
- В выпадающем списке SDK выбрать установленную JDK 17
3. Создать конфигурацию плагина для запуска кода
- Run -> Edit Configurations... -> Add -> Gradle
- Задать имя конфигурации(пример Run plugin)
- Указать Gradle проект idea-commit-template
- В текстовом поле Run написать команду runIde
4. Запустить плагин выбрав Run -> Run plugin

# Использование

Инструкция к использованию располагается в папке [https://github.com/azatyamanaev/git-commit-template/usage](usage)
