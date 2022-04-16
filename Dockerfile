
FROM openjdk:16-alpine3.13
RUN ls -al
ARG JAR_FILE=build/libs/*.jar
RUN mkdir a
RUN ls -al
COPY ${JAR_FILE} a/
EXPOSE 8080
RUN cd a
ENTRYPOINT ["java", "-jar", "app.jar"] 