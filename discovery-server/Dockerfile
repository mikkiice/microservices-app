FROM eclipse-temurin:17-jdk-alpine
RUN apk add --no-cache curl
VOLUME /tmp
COPY target/discovery-server-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
