FROM openjdk:16
RUN pwd
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN pwd

FROM openjdk:16-alpine3.13

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]

