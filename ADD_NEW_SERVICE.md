# 🧱 Как добавить новый микросервис в проект

## 📁 1. Создай директорию сервиса

```bash
mkdir <service-name>
cd <service-name>
```

Пример:
```bash
mkdir post-service
```

---

## ⚙ 2. Создай Dockerfile

**Пример (`Dockerfile`):**
```Dockerfile
FROM eclipse-temurin:17-jdk-alpine
RUN apk add --no-cache curl
WORKDIR /app
COPY target/service-name-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Заменить `<service-name>` на имя твоего сервиса.

---

## 📦 3. Пропиши конфигурацию `application.properties`

```properties
spring.application.name=<service-name>
server.port=808X
spring.config.import=optional:configserver:http://config-server:8888
```

> Порт подбирай уникальный для каждого сервиса (`8081`, `8082`, и т.д.)

---

## 🔧 4. Добавь сервис в `docker-compose.yml`

```yaml
  <service-name>:
    build: ./<service-name>
    container_name: <service-name>
    ports:
      - "808X:808X"
    depends_on:
      - config-server
    networks:
      - app-network
```

---

## 🧩 5. Добавь сервис в `pom.xml` (если используешь multi-module Maven)

Пример:
```xml
<module>post-service</module>
```

---

## 📚 6. Добавь конфигурационный файл в `config-repo`

Создай файл:
```
config-repo/<service-name>.properties
```

Пример содержимого:
```properties
server.port=808X
spring.datasource.url=jdbc:postgresql://postgres:5432/userdb
spring.datasource.username=postgres
spring.datasource.password=postgres
```

---

## 🚀 7. Собери и запусти

```bash
mvn clean install
docker compose up --build
```

---

## ✅ Готово

Проверь:
```bash
curl http://localhost:808X/actuator/health
```

Если видишь `"status":"UP"` — значит всё 🔥
