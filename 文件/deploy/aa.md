#一个端口多个vue项目 部署

https://www.cnblogs.com/vipzhou/p/9255552.html

####路由配置
```
const RouterConfig = {
    base: '/tb-front/',
    mode: 'history',
    routes: []
};
```
#### vue.config.js配置
```
module.exports = {
    publicPath: '/tb-front/',
    devServer: {
        host: '0.0.0.0',
        ...
    }
}
```
#### nginx配置
```
location /tb-front/ {
    if (!-e $request_filename) {
        rewrite ^/(tb-front)/(.+)$ /$1/index.html last;
        break;
    }
    alias   D:/Server/tb-platform/tb-web/tb-front/;
    index  index.html;
}
#或者
location /tb-front/ {
    try_files $uri $uri/ @rewrites; 
    alias   C:/Users/Lenovo/Desktop/temp/mine-back-pc/;
    index  index.html; 
}
location @rewrites {
    rewrite ^/(tb-front)/(.+)$ /$1/index.html last;
}
```
