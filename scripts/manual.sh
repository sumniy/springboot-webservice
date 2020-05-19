#!/usr/bin/env bash

# ec2 인스턴스 안에서 수동으로 서버를 구동시키기 위한 스크립트

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)

echo "> $IDLE_PORT 에서 구동중인 애플리케이션 pid 확인"

PID_OF_8081=$(lsof -ti tcp:8081)
PID_OF_8082=$(lsof -ti tcp:8082)

if [ -z ${PID_OF_8081} ]
then
  echo "> 8081 포트에서 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $PID_OF_8081"
  kill -15 ${PID_OF_8081}
  sleep 5
fi

if [ -z ${PID_OF_8082} ]
then
  echo "> 8082 포트에서 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $PID_OF_8082"
  kill -15 ${PID_OF_8082}
  sleep 5
fi



REPOSITORY=/home/ec2-user/app/step3

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 어플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

echo "> $JAR_NAME 를 profile=real1, real2 으로 실행합니다."
nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-real1.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=real1 \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 && nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-real2.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=real2 \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
