#!/bin/bash
caller_path=$(pwd)
app_path=$(cd "$(dirname "$0")"; pwd;)
cd $caller_path
cd $app_path
pwd
pidFile="${systemType}-${appType}.pid"

# 检查停止状态函数
function check_stop()
{
    # 轮询次数
    local count=10

    while [[ $count -gt 0 ]]
    do
        # 间隔2s
        sleep 2
        ret=`ps axu | grep java | grep -w $PID |grep -v grep| awk '{printf $2}'`
        if [ -z "$ret" ]
        then
            break
        fi
        ((count = count - 1))
    done

    if [[ $count -gt 0 ]]
    then
        # 进程不存在
        echo 0
    else
        # 进程存在检查超时
        echo 1
    fi
    return $?

}

# 如果存在文件中文件中找，否则ps命令查找
if [ -f "$pidFile" ]
then
    PID=$(cat ${pidFile})
else
    PID=`ps axu | grep java | grep -w ${systemType}-${appType} | grep -v grep | awk {'print $2'}`
fi

if [ "-$PID" = "-" ]
then
    echo "no process success!"
else
    # 先优雅停机
    kill $PID
    # 检查停止状态
    ret=$(check_stop)
    if [[ $ret -eq 0 ]]
    then
        # 进程停止，删除pid文件和pid临时文件
        echo "kill $PID process success!"
    else
        # 强制停止
        kill -9 $PID
        # 进程停止，删除pid文件和pid临时文件
        echo "kill -9 $PID process"
    fi
fi