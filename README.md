# 简介

本项目是一个数据爬取和分析项目，主要对智联招聘网站的招聘数据进行数据爬取并分析。

# 流程

1. 用python脚本爬取智联招聘网站的数据

2. 定时统计招聘数据，并写入mysql主从

3. 对mysql数据进行分析，并在web上进行可视化展示

# 环境

- JDK 1.8
- mysql 5.7
- docker CE 18.09.0
- Python 3.6.1
- Centos 7
- Atlas-2.2.1
- nodeJs 11.6.0
- webpack 3.6.0
- vue 2.5.21
- echarts 4.2.0-rc.2

# 功能介绍

1. 目前只实现了统计全国主要城市内，Java, Python, Android, Web前端, PHP, 算法工程师, 人工智能, 大数据职位的招聘数据。

2. 中国地图展示各城市职位数

3. 柱状图比较各城市各职位薪资差距

4. 柱状图展示各职位的工作经验的薪资差距

5. 柱状图展示各职位的学历需求

6. 词云展示各职位的福利标签

# 架构

![架构介绍](https://github.com/aiurmaple/RecruitAnalyze/raw/master/images/framwork.png)

# 目前进度

- python爬取脚本完成

- 集群环境搭建完成

- datatransform（定时统计程序）架构搭建完成

- datadispaly（web可视化展示程序）前端页面编写完成
