# Использование

# Настройки

Настройка плагина может быть осуществлена по пути File -> Settings -> Tools -> Git Commit Templates
1. На вкладке templates можно добавить новые шаблоны(название и сам шаблон)
  ![Templates tab](../images/settings_templates_tab.png)

2. На вкладке local args можно добавить переменные, которые будут вставлены в шаблоны при включении $* + имя_переменной в шаблон
  ![Local args tab](../images/settings_local_args_tab.png)

3. На вкладке system args отображен список переменных окружения, которые можно вставить в шаблон при включении $& + имя_переменной в шаблон.
  ![System args tab](../images/settings_sys_args_tab.png)

4. Добавление в этот список новых переменных осуществляется через кнопку `Add` и выбора переменной среды из списка
  ![Adding a system variable](../images/adding_system_variable.png)


# Вставка шаблона

1.При написании сообщения коммита будет доступна кнопка Insert Commit Message (для неё так же доступен хоткей)
  ![Commit window](../images/commit_window.png)

2. При нажатии на нее откроется окно со списком указанных в настройках шаблонов
  ![Commit templates window(2)](../images/commit_templates_window.png)

3. При выборе одного из шаблонов и нажатия Select шаблон будет вставлен как сообщение коммита
  ![Commit insert template](../images/commit_insert_template.png)

4. При выборе одного из шаблонов и активирования чекбокса Add branch name шаблон будет вставлен 
   как сообщение коммита и название ветки будет добавлено в начало коммита
  
  ![Commit insert template with prefix(2)](../images/commit_insert_template_with_prefix.png)

