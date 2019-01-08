const Koa = require('koa');
const bodyParser = require('koa-bodyparser');
const controller = require('./config/controller');
const staticFiles = require('./config/static-files');

const app = new Koa();

// 记录URL以及页面执行时间:
app.use(async (ctx, next) => {
    console.log(`Process ${ctx.request.method} ${ctx.request.url}...`);
    let
        start = new Date().getTime(),
        execTime;
    await next();
    execTime = new Date().getTime() - start;
    console.log(`${execTime}ms`);
    ctx.response.set('X-Response-Time', `${execTime}ms`);
});

// 处理静态文件
app.use(staticFiles('/static/', __dirname + '/static'));

// 解析request body
app.use(bodyParser());

// 处理http路由
app.use(controller());

//监听端口
app.listen(80);
console.log('app started at port 80...');
