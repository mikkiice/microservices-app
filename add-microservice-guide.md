# 🧱 Универсальный гайд: как добавить новый микросервис в проект

## 📁 1. Создай директорию сервиса

Скопируй существующий сервис (например, `user-service`) и назови его по-новому:

```bash
cp -r user-service new-service
```

---

## 🧾 2. Обнови `pom.xml` сервиса

Задай уникальный `artifactId`, исправь имя и зависимости:

```xml
<artifactId>new-service</artifactId>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

---

## 🧩 3. Добавь модуль в родительский `pom.xml`

```xml
<modules>
    ...
    <module>new-service</module>
</modules>
```

---

## ⚙ 4. Добавь `new-service.properties` в `config-repo`

```properties
spring.application.name=new-service
spring.config.activate.on-profile=default
server.port=8090

spring.datasource.url=jdbc:postgresql://postgres:5432/userdb
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always
```

---

## 🐳 5. Добавь сервис в `docker-compose.yml`

```yaml
  new-service:
    build: ./new-service
    container_name: new-service
    ports:
      - "8090:8090"
    depends_on:
      config-server:
        condition: service_healthy
      postgres:
        condition: service_healthy
      discovery-server:
        condition: service_started
    networks:
      - app-network
    healthcheck:
      test: [ "CMD", "curl", "-f", "http://localhost:8090/actuator/health" ]
      interval: 10s
      timeout: 5s
      retries: 5
      start_period: 15s
    environment:
      - SPRING_APPLICATION_NAME=new-service
      - SPRING_PROFILES_ACTIVE=default
```

---

## 🐧 6. Dockerfile для сервиса

```dockerfile
FROM eclipse-temurin:17-jdk-alpine

RUN apk add --no-cache curl

WORKDIR /app
COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
```

---

## 🚀 7. Собери и запусти проект

```bash
mvn clean package -DskipTests
docker compose up --build
```

---

## ✅ 8. Проверь работоспособность

```bash
curl http://localhost:8090/actuator/health
```

Если видишь `"status":"UP"` — значит всё 🔥
