# 使用说明

本插件用于将数据爬取脚本数据传输到mysql中

mvn clean

mvn package

nohup java -jar target/datatransform-0.0.1-SNAPSHOT.jar \

--spring.datasource.url="jdbc:mysql://localhost:3306/recruit_analyze?useUnicode=true&characterEncoding=UTF-8&useSSL=true" \

--spring.datasource.username="root" \

--spring.datasource.password="123456" \

--path=/home > datatransform.log &

# 参数说明

spring.datasource.url:mysql地址

spring.datasource.username:mysql用户名

spring.datasource.password:mysql密码

path:数据爬取脚本生成数据文件夹
