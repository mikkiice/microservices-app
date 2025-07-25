Шаг,Описание
1,"Создай папку нового сервиса в корне проекта, например `new-service`. Скопируй туда структуру из любого рабочего сервиса (например, `user-service`)."
2,"Переименуй главный класс и пакет под новый сервис (например, `NewServiceApplication.java`)."
3,"Обнови `pom.xml` сервиса: задай уникальный `<artifactId>`, укажи зависимости (`spring-boot-starter-web`, `lombok`, `eureka-client` и т.д.)."
4,Добавь сервис в `<modules>` родительского `pom.xml` как `<module>new-service</module>`.
5,"Создай файл `new-service.properties` в `config-repo`, где пропиши `spring.application.name`, `server.port`, `datasource`, `jpa` и т.д."
6,"Добавь сервис в `docker-compose.yml`: укажи `build`, `container_name`, `depends_on`, `ports`, `environment`, `healthcheck`. Убедись, что `curl` есть в образе."
7,Обнови `Dockerfile` сервиса: добавь `RUN apk add --no-cache curl` сразу после `FROM eclipse-temurin:17-jdk-alpine`.
8,"Убедись, что в `application.properties` есть `spring.config.import=optional:configserver:http://config-server:8888`. Либо задай это в `environment` контейнера."
9,Собери проект: `mvn clean package -DskipTests`. Потом пересобери контейнеры: `docker compose up --build`.
10,"Проверь, что сервис зарегистрировался в Eureka (`http://localhost:8761`) и стал healthy. Проверь `curl http://localhost:<порт>/actuator/health`."
