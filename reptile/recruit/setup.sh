#!/usr/bin/env bash

#添加定时任务
set -e

PATH=/home/RecruitAnalyze/reptile/recruit/crontab

echo "30  2  *  *  * sh $PATH/reptile.sh" >> /etc/crontab
