FROM openjdk:16
ARG JAR_FILE=*SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
FROM openjdk:16-alpine3.13
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]

