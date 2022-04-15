FROM openjdk:16
LABEL title="Chart"
LABEL version="0.1"

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar


FROM openjdk:16-alpine3.13
ENTRYPOINT ["java", "-jar", "/app.jar"]

