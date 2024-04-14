#!/bin/sh
caller_path=$(pwd)
app_path=$(cd "$(dirname "$0")"; pwd;)
cd $caller_path
cd $app_path
pwd
nohup java -DAppPID -XX:+HeapDumpOnOutOfMemoryError ${jvm_args} -jar ${systemType}-${appType}-*.jar ${log4j2_args} jfile=config/application.properties >/dev/null 2>&1 &