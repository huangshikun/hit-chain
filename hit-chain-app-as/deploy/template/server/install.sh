now=`date "+%Y%m%d%H%M%S"`
finalname="${appType}-"$now
if [ ! -d ./backup ];then
mkdir ./backup &>/dev/null
fi
if [ -d ./app ];then
cp -R ./app ./backup/$finalname &>/dev/null
fi

rm -rf ./app
cp -a tmp/app ./
chmod -R 755 ./app