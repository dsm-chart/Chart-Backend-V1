FROM openjdk:16-alpine3.13
WORKDIR app

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} ./

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./"]