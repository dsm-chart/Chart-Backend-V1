FROM openjdk:16
WORKDIR /home/runner/work/Chart-Backend-V1/Chart-Backend-V1

ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN pwd

FROM openjdk:16-alpine3.13

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]

