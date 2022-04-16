RUN ls -al
FROM openjdk:16
RUN ls -al
ARG JAR_FILE=*SNAPSHOT.jar
RUN ls -al
ADD ${JAR_FILE} app.jar
FROM openjdk:16-alpine3.13
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]

