#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
JAR_FILE="$PROJECT_ROOT/spring-project200.jar"
TIME_NOW=$(date +%c)

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"


# build 파일 복사
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

# jar 파일 실행
nohup java -jar $JAR_FILE --spring.profiles.active=prod --SERVER_ADDRESS=${SERVER_ADDRESS} --SERVER_PORT=${SERVER_PORT} --DB_HOST=${DB_HOST} --DB_PORT=${DB_PORT} --DB_SCHEMA=${DB_SCHEMA} --DB_USERNAME=${DB_USERNAME} --DB_PASSWORD=${DB_PASSWORD} &

CURRENT_PID=$(pgrep -f $JAR_FILE)