# Использование

# Настройки

Настройка плагина может быть осуществлена по пути File -> Settings -> Tools -> Git Commit Templates
1. На вкладке templates можно добавить новые шаблоны(название и сам шаблон)
  ![Screenshot 2024-04-30 141910](https://github.com/azatyamanaev/git-commit-template/assets/49018424/47c1b549-b3c4-45f9-a415-73771c41f732)

2. На вкладке local args можно добавить переменные, которые будут вставлены в шаблоны при включении $* + имя_переменной в шаблон
  ![Screenshot 2024-04-30 141922](https://github.com/azatyamanaev/git-commit-template/assets/49018424/3f3f35b5-aa05-4f24-90fb-8da53878aa48)

3. На вкладке system args отображен список переменных окружения, которые можно вставить в шаблон при включении $& + имя_переменной в шаблон. По умолчанию в списке присутствуют значения OS и COMPUTERNAME(на Windows). Добавление в этот список новых переменных осуществляется через определение переменной окружения, в названии которой есть приставка GITT_.
  ![Screenshot 2024-04-30 141929](https://github.com/azatyamanaev/git-commit-template/assets/49018424/31fdac02-ea78-4259-8778-8b07fb2a966a)

# Вставка шаблона

1.При написании сообщения коммита будет доступна кнопка Insert Commit Message
  ![Screenshot 2024-04-30 142008](https://github.com/azatyamanaev/git-commit-template/assets/49018424/60d17d5b-0d54-4b0a-a3c1-beb8a5bbc9a6)

2. При нажатии на нее откроется окно со списком указанных в настройках шаблонов
  ![Screenshot 2024-04-30 142712](https://github.com/azatyamanaev/git-commit-template/assets/49018424/4df66904-b85e-47ca-ae50-0af711d550cc)

3. При выборе одного из шаблонов и нажатия Select шаблон будет вставлен как сообщение коммита
  ![Screenshot 2024-04-30 142103](https://github.com/azatyamanaev/git-commit-template/assets/49018424/88398c78-5c9f-4946-8f7d-d3ee0cec582e)

