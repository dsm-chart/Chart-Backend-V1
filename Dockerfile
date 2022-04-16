FROM openjdk:16
RUN cd ~/
RUN ls -al
ARG JAR_FILE=build/libs/*.jar
RUN ls -al
COPY ${JAR_FILE} app.jar
FROM openjdk:16-alpine3.13
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]

