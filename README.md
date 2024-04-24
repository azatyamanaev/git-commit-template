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


# Настройки 

Настройка плагина может быть осуществлена по пути File -> Settings -> Tools -> Git Commit Templates
1. На вкладке templates можно добавить новые шаблоны(название и сам шаблон)
![templates](https://github.com/azatyamanaev/git-commit-template/assets/49018424/d173e4d1-6fd1-422a-aeef-97557b606cdd)

3. На вкладке params можно добавить переменные, которые будут вставлены в шаблоны при включение $ + имя_переменной в шаблон
![params](https://github.com/azatyamanaev/git-commit-template/assets/49018424/16e7f496-c522-40e1-8cb4-d64225d07ef1)


# Вставка шаблона

1.При написании сообщения коммита будет доступна кнопка Insert Commit Message
![commit message](https://github.com/azatyamanaev/git-commit-template/assets/49018424/3ac75d42-6615-48db-a433-e3a1d64beff6)

2. При нажатии на нее откроется окно со списком указанных в настройках шаблонов
![choose template](https://github.com/azatyamanaev/git-commit-template/assets/49018424/95a8fa20-940c-4d4b-9546-60f0de6a09c0)

3. При выборе одного из шаблонов и нажатия Select шаблон будет вставлен как сообщение коммита
![inserted template](https://github.com/azatyamanaev/git-commit-template/assets/49018424/252dc9e3-b4af-4129-9f32-013de5f54429)

