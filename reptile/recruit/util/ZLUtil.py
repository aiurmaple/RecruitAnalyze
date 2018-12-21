#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'tzj'
import os
import logging
import logging.config
from datetime import datetime, timedelta


# 设置日志格式
def set_log_conf():
    abs_path = os.path.abspath(".")
    file_path = abs_path[0:abs_path.rfind("recruit")] + "recruit" + os.sep + "logging.conf"
    logging.config.fileConfig(file_path)
    return logging.getLogger('cycleRent')


logger = set_log_conf()


# 创建目录
def create_dir(dir_str):
    if not os.path.exists(dir_str):
        logger.debug("创建了目录" + dir_str)
        os.makedirs(dir_str)


# 获得从当前时间到明日的具体秒数
def get_sleep_seconds():
    dateNow = datetime.now()
    # 获得明日日期
    dataN = dateNow + timedelta(days=1)
    dataNStr = dataN.strftime('%Y%m%d')
    dataNTime = datetime.strptime(dataNStr + '000000', '%Y%m%d%H%M%S')
    # 计算差异时间
    dataC = dataNTime - dateNow
    return dataC.seconds


# 执行命令
def exe_cmd(cmd):
    result_str = os.popen(cmd).read()
    return result_str


# 判断传入时间是否大于前一天
def is_new_time(createTime):
    nowDay = datetime.now()
    oneDay = timedelta(days=1)
    yestoday = nowDay - oneDay
    createTimeData = datetime.strptime(createTime, '%Y-%m-%d %H:%M:%S')
    dataC = createTimeData - yestoday
    if dataC.days > 0:
        return True
    else:
        return False
