# 简介

本项目是一个数据爬取和分析项目，主要对智联招聘网站的招聘数据进行数据爬取并分析。

# 流程

1. 用python脚本爬取智联招聘网站的数据

2. 定时统计招聘数据，并写入mysql

3. 对mysql数据进行分析，并在web上进行可视化展示

# 环境

- JDK 1.8
- maven 3.6.0
- mysql 5.7
- redis 5.0.3
- 360 Atlas 2.2.1
- Python 3.6.7
- Centos 7
- nodeJs 11.6.0

# 功能介绍

主要统计IT类岗位Java, Python, Android, Web前端, PHP, 算法工程师, 人工智能, 大数据职位的招聘数据。

1. 中国地图展示各城市职位数,统计实时职位数和每日职位增数

2. 比较各招聘季度各城市各职位类型平均薪资差距

3. 比较各职位类型工作经验的平均薪资差距

4. 比较各职位类型学历需求

5. 词云展示各职位的福利标签

# 使用步骤

1. 进入reptile/recruit目录用python3执行抓取脚本ZLCrawler.py，会生成一个data文件夹，抓取数据放置在这里

2. 用linux的crontab定时程序每日定时执行脚本ZLCrawlerAdd.py，会生成一个dataAdd文件夹，该文件夹存放每日增量数据

3. 部署mysql，360 Atlas，导入sql文件夹的recruit_analyze.sql到mysql中，Atlas的分表配置可参照conf文件夹内的Atlas.cnf

3. 抓取一定量数据后，进入datatransform目录执行数据传输插件，具体使用看该文件夹下的README

4. 进入datadisplay目录编译并启动可视化web程序，具体使用请看该文件夹下的README

5. web程序启动完成后，输入localhost:9528即可访问



