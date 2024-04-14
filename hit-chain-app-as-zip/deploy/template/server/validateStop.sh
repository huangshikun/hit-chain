#!/bin/bash
i=10
while [[ $i -gt 0 ]];do
	sleep 1
	check_start=`ps axu | grep java | grep ${systemType}-${appType} |grep -v grep| awk '{printf $2}'`
	if [ -z "$check_start" ]
	then
		break
	fi
	((i = i - 1))
done
if [[ $i -gt 0 ]]
then
    echo 0
else
	echo echo "No stopping!" 1>&2
fi
