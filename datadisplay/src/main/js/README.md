# 运行步骤

1. 修改config文件夹内的dev.env.js文件配置BASE_API项为后端接口ip

2. 如需外网访问，需修改package.json文件内dev启动参数增加--host=0.0.0.0

3. 执行npm install --registry=https://registry.npm.taobao.org安装依赖包

4. npm run dev

5. 运行成功后可用服务器ip+9528端口访问
