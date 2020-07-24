```
http://ifeve.com/oauth2-tutorial-all/   
```

```$xslt
https://github.com/chengjiansheng/cjs-oauth2-sso-demo
```

```$xslt
oauth2

http://www.ruanyifeng.com/blog/2019/04/oauth-grant-types.html 四种令牌
http://www.ruanyifeng.com/blog/2019/04/github-oauth.html demo向github申请令牌获取数据

1:问题
https://blog.csdn.net/taotaojs/article/details/85126140

#获取code
http://localhost:8080/oauth/authorize?response_type=code&client_id=client1&redirect_uri=http://www.baidu.com
#获取token
http://localhost:8080/oauth/token?client_id=client1&client_secret=123456&grant_type=authorization_code&redirect_uri=http://www.baidu.com&code=XUDEKB
#刷新token
http://localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=6d3a556f-377e-4be5-80bf-84e50390b557&client_id=client1&client_secret=123456

{
    "access_token": "000a1f0e-e5ad-42ed-b064-4abd7fd19e79",
    "token_type": "bearer",
    "refresh_token": "1db24c9d-32c2-42f1-a6bb-0eb4f84e4cd5",
    "expires_in": 36001,
    "scope": "test"
}

{
    "access_token": "e878f63e-2c1d-4db9-b155-032efd913dfa",
    "token_type": "bearer",
    "refresh_token": "1db24c9d-32c2-42f1-a6bb-0eb4f84e4cd5",
    "expires_in": 43199,
    "scope": "test"
}
认证
会话
who对what进行how操作
rbac Role-Based Access Control

```