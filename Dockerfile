FROM openjdk:16
RUN pwd
WORKDIR /home/runner/work/Chart-Backend-V1/Chart-Backend-V1
RUN pwd
ARG JAR_FILE=./build/libs/*.jar
RUN pwd
COPY ${JAR_FILE} app.jar


FROM openjdk:16-alpine3.13

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]

