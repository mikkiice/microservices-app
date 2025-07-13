# 📦 Добавление нового микросервиса в систему

Полный чеклист, что нужно сделать при добавлении нового микросервиса.

---

## ✅ 1. Создай папку сервиса

Пример:
```
/notification-service
```

---

## ✅ 2. Создай `pom.xml` в новом сервисе

```xml
<project>
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>com.example</groupId>
    <artifactId>microservices-app</artifactId>
    <version>1.0.0</version>
  </parent>

  <artifactId>notification-service</artifactId>

  <dependencies>
    <!-- Веб -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Actuator -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <!-- Spring Cloud Config -->
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
  </dependencies>
</project>
```

---

## ✅ 3. Добавь сервис в `docker-compose.yml`

```yaml
  notification-service:
    build: ./notification-service
    container_name: notification-service
    restart: always
    ports:
      - "8085:8085"
    depends_on:
      config-server:
        condition: service_healthy
      postgres:
        condition: service_started
    networks:
      - app-network
    healthcheck:
      test: [ "CMD", "curl", "-f", "http://localhost:8085/actuator/health" ]
      interval: 10s
      timeout: 5s
      retries: 5
```

---

## ✅ 4. Добавь модуль в корневой `pom.xml`

```xml
<modules>
    <module>user-service</module>
    <module>api-gateway</module>
    <module>config-server</module>
    <module>notification-service</module>
</modules>
```

---

## ✅ 5. Добавь конфиг-файл в `config-repo/`

📄 `config-repo/notification-service.properties`:

```properties
spring.config.activate.on-profile=default
server.port=8085
spring.application.name=notification-service

# другие свойства
jwt.secret=секретина1234567890
```

---

## ✅ 6. Пересобери и запусти

```bash
mvn clean install -DskipTests
docker compose up --build
```

---

## ✅ 7. (Если нужно) Добавь маршрут в Gateway

📄 `config-repo/api-gateway.properties`:

```properties
spring.cloud.gateway.routes[2].id=notification-service
spring.cloud.gateway.routes[2].uri=http://notification-service:8085
spring.cloud.gateway.routes[2].predicates[0]=Path=/notify/**
```

---

## 🧠 Быстрый итог:

| Что                     | Где                         |
|------------------------|-----------------------------|
| 📁 Папка с кодом       | `/new-service`              |
| 📄 `pom.xml`            | в папке сервиса             |
| 📦 `<module>`          | в корневом `pom.xml`        |
| 🐳 `build:` секция     | в `docker-compose.yml`      |
| ⚙️ `.properties` конфиг | в `config-repo/`            |
| 🌐 Gateway route        | в `api-gateway.properties`  |
| 🔐 JWT ключ            | в конфиге сервиса           |
| 🧪 Healthcheck          | в `docker-compose.yml`      |
