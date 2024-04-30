# Использование

# Настройки

Настройка плагина может быть осуществлена по пути File -> Settings -> Tools -> Git Commit Templates
1. На вкладке templates можно добавить новые шаблоны(название и сам шаблон)
  ![Screenshot 2024-04-30 141910](https://github.com/azatyamanaev/git-commit-template/assets/49018424/47c1b549-b3c4-45f9-a415-73771c41f732)

2. На вкладке local args можно добавить переменные, которые будут вставлены в шаблоны при включении $* + имя_переменной в шаблон
  ![Screenshot 2024-04-30 141922](https://github.com/azatyamanaev/git-commit-template/assets/49018424/3f3f35b5-aa05-4f24-90fb-8da53878aa48)

3. На вкладке system args отображен список переменных окружения, которые можно вставить в шаблон при включении $& + имя_переменной в шаблон. 
  ![Screenshot 2024-04-30 141929](https://github.com/azatyamanaev/git-commit-template/assets/49018424/31fdac02-ea78-4259-8778-8b07fb2a966a)

4. По умолчанию в списке присутствуют значения OS и COMPUTERNAME(на Windows). Добавление в этот список новых переменных осуществляется через определение переменной окружения, в названии которой есть приставка GITT_.
  ![Screenshot 2024-04-30 142932](https://github.com/azatyamanaev/git-commit-template/assets/49018424/ba39ae98-e2a4-443f-9adb-be4f43029632)


# Вставка шаблона

1.При написании сообщения коммита будет доступна кнопка Insert Commit Message
  ![Screenshot 2024-04-30 142008](https://github.com/azatyamanaev/git-commit-template/assets/49018424/7d9caf54-18bf-425a-9954-f25815a948f4)

2. При нажатии на нее откроется окно со списком указанных в настройках шаблонов
  ![Screenshot 2024-04-30 142712](https://github.com/azatyamanaev/git-commit-template/assets/49018424/591718e5-582f-4617-94c5-42ecfb3e96af)

4. При выборе одного из шаблонов и нажатия Select шаблон будет вставлен как сообщение коммита
  ![Screenshot 2024-04-30 142103](https://github.com/azatyamanaev/git-commit-template/assets/49018424/e3effcd9-d3f1-4858-81e5-46151e6ad920)


