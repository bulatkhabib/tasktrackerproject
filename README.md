Для запуска необходима IDEA, поменять username и password на ваши от PostgreSQL в файле application.properties в папке /src/main/resources. 
Там же, в строчке spring.datasource.url=jdbc:postgresql://localhost:5432/tasktracker нужно указать существующую на вашем устройстве БД вместо tasktracker,
либо создать у себя БД с таким названием. Также в строчке pring.jpa.hibernate.ddl-auto=update изменить на create для автоматического создания всех таблиц. 
После первого запуска изменить на обратно на update.

Гайд по созданию postgresql: https://www.youtube.com/watch?v=IbVPbF7HTL4

Также нужно выбрать в правых вкладках Database и создать БД. Там указать свой логин и пароль от PostgreSQL.


Стек технической реализации проекта: Язык разработки Swift; Работа с сетью: REST (URLSession, Codable, Alamofire); Архитектура: MVP; Поддержка iOS 11+; Использование KeychainAccess, CoreData; Использование iOS Coordinator pattern; Использование TableView и StackView; 
Работа c CocoaPods;

Для того чтобы приложение работало, нужен рабочий бэк. Серверная часть для этого приложения написан мной. Проверяем что установлен PostgresSql, а так же сборщик проекта Gradle

Настройка бэка: Натсройка swift проекта: Пулим к себе код pod install Пробуем запустить. Вначале, нужно зарегистрироваться, после чего можете пользоваться приложением.
