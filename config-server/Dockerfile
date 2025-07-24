FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/config-server-*.jar app.jar

RUN apk add --no-cache curl

ENTRYPOINT ["java", "-jar", "app.jar"]
