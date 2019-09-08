#JLINK

[TOC]

###1.1<span id="user-register">用户注册</span>

- **请求URL**

> [localhost:8080/jlink/user/register](#)

- **请求方式** 

> **POST**

- **Headers**

> 

```
    | :-------- | :--------|
    | Content-Type| application/json|
```

- **请求参数**

> 

```
    {
    	"roleId":"1",//用户类型ID
    	"username":"17826859546",//用户名
    	"email":"1027407651@qq.com",//邮箱
    	"password":"QAHlVoUc49w="//密码,基于BASE64编码
    }
```

- **返回示例**

> 

```
{
    "code": "200",
    "msg": "操作成功",
    "data": {
        "userId": 15,
        "username": "17826859546",
        "password": "QAHlVoUc49w=",
        "phone": "17826859546",
        "name": null,
        "email": "1027407651@qq.com",
        "address": null,
        "status": 0,
        "roleId": 1,
        "createTime": 1558333359474
    }
}

```

- **说明**

存在参数未填写返回：

> 

```
{
    "code": "400",
    "msg": "必填项未填！",
    "data": null
}

```

 用户已存在返回：

> 
```
{
    "code": "400",
    "msg": "用户已存在！",
    "data": null
}
```

###1.2<span id="user-login">用户登录</span>

- **请求URL**

> [localhost:8080/green/user/login](#)

- **请求方式** 

> **POST**

- **Headers**

> 

```
    | :-------- | :--------|
    | Content-Type| application/json|

```

- **请求参数**

> 

```
    {
     	"username":"17826859545",//用户名
     	"password":"QAHlVoUc49w="//密码,基于BASE64编码加密
     }

```

- **返回示例**

> 

```
{
    "code": "200",
    "msg": "操作成功",
    "data": {
        "user": {
            "userId": 1,
            "username": "17826859554",
            "password": "QAHlVoUc49w=",
            "phone": "17826859554",
            "name": null,
            "email": "1027407651@qq.com",
            "address": null,
            "status": 0,
            "roleId": 1,
            "createTime": 1558500603000
        },
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIn0.EEcXC_phGwZfYbv-NoAQ8zxZg39S_jD4Cv8bdFhAn0w"
    }
}

```

- **说明**

>密码错误返回：
```
{
    "code": "400",
    "msg": "密码错误！",
    "data": null
}
```
> 用户不存在返回：
```
{
   "code": "400",
   "msg": "用户不存在！",
   "data": null
}
```

