# 使用说明

该程序为web应用程序，分为前端和后端，后端用于处理数据，前端用于可视化展示数据

# 后端使用步骤

mvn clean

mvn package

nohup java -server -jar target/datadisplay-0.0.1-SNAPSHOT.jar \
--spring.datasource.url="jdbc:mysql://localhost:3306/recruit_analyze?useUnicode=true&characterEncoding=UTF-8&useSSL=true" \
--spring.datasource.username=root \
--spring.datasource.password=123456 \
--spring.redis.host=localhost \
--spring.redis.password=123456
--path=/home > datadisplay.log 2>&1 &

# 前端使用步骤

进入src/main/js目录查看README文档运行
