
FROM openjdk:16-alpine3.13
RUN ls -al
ARG JAR_FILE=build/libs/*.jar
RUN mkdir a
RUN ls -al
COPY ${JAR_FILE} a/
EXPOSE 8080
RUN ls -al
RUN cd a
RUN ls -al
ENTRYPOINT ["java", "-jar", "app.jar"] 