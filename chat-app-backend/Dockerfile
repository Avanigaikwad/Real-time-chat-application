FROM amd64/openjdk:21-jdk-slim

COPY target/chat-app-backend-0.0.1-SNAPSHOT.jar app.jar

COPY .env .env

EXPOSE 8080

ENV PORT=8080

ENTRYPOINT ["java", "-jar", "app.jar"]