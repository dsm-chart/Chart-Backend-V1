cp $REPOSITORY/zip/*.jar $REPOSITORY/

CURRENT_PID=$(pgrep -fl $PROJECT_NAME | grep java | awk '{print $1}')

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi


JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)


chmod +x $JAR_NAME

nohup java -jar \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
