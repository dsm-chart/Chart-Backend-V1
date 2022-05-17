#!/bin/bash

REPOSITORY=/home/ec2-user/app/travis
PROJECT_NAME=chart

echo "> Build 파일 복사"

cp $REPOSITORY/build/build/libs/*.jar $REPOSITORY/jar/

sudo pkill -9 "java"

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/jar/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
    $JAR_NAME > $REPOSITORY/nohup.out 2>1 &