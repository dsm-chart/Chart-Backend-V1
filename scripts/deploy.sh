cp $REPOSITORY/zip/*.jar $REPOSITORY/


JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

chmod +x $JAR_NAME

nohup java -jar \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
