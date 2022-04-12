FROM openjdk:16
LABEL title="Chart"
LABEL version="0.1"
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENV dbUserName=${DB_USERNAME} \
RUN echo $dbUserName

ENV dbPassword=${DB_PASSWORD} \
    dbPath=${DB_PATH} \
    githubCilentKey=${GITHUB_CILENT_KEY} \
    githubSecretKey=${GITHUB_SECRET_KEY} \
    jwtSecretKey=${JWT_SECRET_KEY} \
    accessExpiredAt=${ACCESS_TOKEN_EXPIRED_AT} \
    refreshExpiredAt=${REFRESH_TOKEN_EXPIRED_AT} \
    jwtMapKey=${JWT_MAP_KEY} \
    neisApiKey=${NEIS_API_KEY} \

FROM openjdk:16-alpine3.13
ENTRYPOINT ["java", "-jar", "/app.jar"]

