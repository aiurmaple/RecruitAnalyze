#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'tzj'
import json
import os
import time
from datetime import datetime
from itertools import product, count
from multiprocessing import Pool, Value
from urllib.parse import urlencode

import requests

from config.ZLConfig import *
from util.ZLUtil import logger, create_dir

jobNumber = None


def get_data_path():
    abs_path = os.path.abspath(".")
    file_path = abs_path + os.sep + "data" + os.sep + datetime.now().strftime('%Y%m%d') + os.sep
    return file_path


def init(args):
    global jobNumber
    jobNumber = args


# 下载页面
def download(url):
    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0'}
    response = requests.get(url, headers=headers)
    code = response.status_code
    if code != 200:
        logger.error("Failed to crawle the page:" + url)
        raise RuntimeError('crawleError')
    return response.json()


# 获取数据
def get_content(results, keyword, address):
    dir_path = get_data_path()
    filename = dir_path + keyword + "_" + address + ".json"
    with open(str(filename), 'a', encoding='utf-8') as rData:
        for result in results:
            global jobNumber
            rData.write(json.dumps(result, ensure_ascii=False))
            with jobNumber.get_lock():
                jobNumber.value += 1
        rData.write("\n")
    # 休息一秒，防止反爬虫
    time.sleep(1)


# 第一次抓取
def search(args):
    base_url = 'https://fe-api.zhaopin.com/c/i/sou?'
    number_list = count(0, 60)
    pre_first_num = ""
    for page_start in number_list:
        paras = {
            'start': page_start,
            'pageSize': 60,
            'cityId': args[0],
            'kw': args[1],
            'workExperience': -1,
            'education': -1,
            'companyType': -1,
            'employmentType': -1,
            'jobWelfareTag': -1,
            'kt': 3,
            '_v': 0.79909260,
            'x-zp-page-request-id': 'fe117335f2e048ee82cded2bbd8abb1d-1542281153274-13557'
        }
        url = base_url + urlencode(paras)
        logger.debug(url)
        json = download(url)
        results = json["data"]["results"]
        # 判断是否重复，重复停止线程
        if len(results) == 0:
            logger.debug("抓取数据为0，停止线程")
            return
        first_result = results[0]
        cur_first_num = first_result["number"]
        if pre_first_num == cur_first_num:
            logger.debug("抓取数据页重复，停止线程")
            return
        pre_first_num = cur_first_num
        get_content(results, args[1], args[0])


# 主程序入口
if __name__ == '__main__':
    # 创建文件夹
    create_dir(get_data_path())
    # 第一次抓取
    logger.info("开始第一次抓取")
    jobNumber = Value('i', 0)
    start = time.time()
    args = product(ADDRESS, KEYWORDS)
    pool = Pool(initializer=init, initargs=(jobNumber,))
    i = pool.map_async(search, args)
    i.wait()
    end = time.time()
    logger.info("共计用时：" + str(end - start) + "s")
    logger.info("本次抓取统计的职位数为：" + str(jobNumber.value))
    logger.info("本次抓取时间为：" + datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
