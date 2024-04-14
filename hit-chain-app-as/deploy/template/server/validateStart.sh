#!/bin/bash
i=60
while [[ $i -gt 0 ]];do
	check_start=`ss -anplt | grep ${app_server_port}`
	if [ -n "$check_start" ]
	then
		break
	fi
	((i = i - 1))
	sleep 2
done
if [[ $i -gt 0 ]]
then
    echo 0
else
	echo "start failed" 1>&2
fi


