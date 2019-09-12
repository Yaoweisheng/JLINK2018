#JLINK

[TOC]

##1.<span id="user">用户管理</span>
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
    "name":"SSSS",
    "email":"1027407651@qq.com",
    "password":"QAHlVoUc49w=",
    "phone":"17826859540"
}
```

- **返回示例**

> 

```
{
    "code": "200",
    "msg": "操作成功",
    "data": {
        "userId": 1,
        "name": "SSSS",
        "password": "QAHlVoUc49w=",
        "email": "1027407651@qq.com",
        "phone": "17826859540",
        "createTime": 1568116594000,
        "modifyTime": null,
        "isDelete": 0,
        "isActive": 0
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

> [localhost:8080/jlink/user/login](#)

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

###1.3<span id="user-register">用户更新</span>

- **请求URL**

> [localhost:8080/jlink/user/updateUser](#)

- **请求方式** 

> **PUT**

- **Headers**

> 

```
| :-------- | :--------|
| Content-Type| application/json|
| token | token |
```

- **请求参数**

> 

```
{
    "userId": 7,
    "name": "C",
    "password": "QAHlVoUc49w=",
    "email": "1027407653@qq.com",
    "phone": "17826859541",
    "createTime": 1568167723000,
    "modifyTime": null,
    "isDelete": 0,
    "isActive": 0
}
```

- **返回示例**

> 

```
{
    "code": "200",
    "msg": "操作成功",
    "data": {
        "userId": 7,
        "name": "C",
        "password": "QAHlVoUc49w=",
        "email": "1027407653@qq.com",
        "phone": "17826859541",
        "createTime": 1568167723000,
        "modifyTime": 1568169001972,
        "isDelete": 0,
        "isActive": 0
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

 用户名已存在返回：

> 
```
{
    "code": "400",
    "msg": "用户名已存在！",
    "data": null
}
```


##2<span id="department">部门</span>
###2.1<span id="department-getDepartmentList">获取所有部门</span>
- **请求URL**
>[localhost:8080/jlink/department/getDepartmentList](#)
- **请求方式**
>**GET**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
| token | token|
```
- **请求参数**
>
```
{
    
}
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": [
        {
            "deptId": 2,
            "deptNo": "01",
            "deptName": "A1",
            "deptAddr": "1",
            "createId": 1,
            "createDate": 1567930301000,
            "modifyId": 1,
            "modifyDate": 1567950815000
        },
        ...
    ]
}
```
- **说明**
> 未登录:
```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```

###2.2<span id="department-addDepartment">添加部门</span>
- **请求URL**
>[localhost:8080/jlink/department/addDepartment](#)
- **请求方式**
>**POST**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
| token | token|
```
- **请求参数**
>
```
{
	"deptParentNo":"01",//部门父类ID
	"deptName":"A12",//部门名称
	"deptAddr":"1"//部门地点
}
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": {
        "deptId": 11,
        "deptNo": "0101",
        "deptName": "A12",
        "deptAddr": "",
        "createId": 1,
        "createDate": 1557369022000,
        "modifyId": null,
        "modifyDate": null
    }
}
```
- **说明**
> 未登录:
```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```
> 名称未填写:
```
{
    "code": "400",
    "msg": "必填项未填！",
    "data": null
}
```

###2.3<span id="department-updateDepartment">更新部门</span>
- **请求URL**
>[localhost:8080/jlink/department/updateDepartment](#)
- **请求方式**
>**PUT**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
| token | token|
```
- **请求参数**
>
```
{
	"deptId":4,
	"deptNo":"03",
	"deptName":"C1",
	"deptAddr":"1"
}
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": {
        "deptId": 4,
        "deptNo": "03",
        "deptName": "C1",
        "deptAddr": "1",
        "createId": 1,
        "createDate": 1567930301000,
        "modifyId": 1,
        "modifyDate": 1557369226000
    }
}
```
- **说明**
> 未登录:
```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```
> 名称未填写:
```
{
    "code": "400",
    "msg": "必填项未填！",
    "data": null
}
```

###2.4<span id="department-getPlinenubs">获取所有产线</span>
- **请求URL**
>[localhost:8080/jlink/department/getPlinenubs](#)
- **请求方式**
>**GET**
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
    
}
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": [
        "A1",
        "A12",
        "B1",
        "C1"
    ]
}
```
- **说明**

###2.5<span id="department-getTree">获取部门树</span>
- **请求URL**
>[localhost:8080/jlink/department/getTree](#)
- **请求方式**
>**GET**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
|token | token |
```
- **请求参数**
>
```
{
    
}
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": [
        {
            "id": "01",
            "text": "A1",
            "children": [
                {
                    "id": "0101",
                    "text": "A12",
                    "children": []
                }
            ]
        },
        {
            "id": "02",
            "text": "B1",
            "children": []
        },
        {
            "id": "03",
            "text": "C1",
            "children": []
        }
    ]
}
```
- **说明**
> 未登录:
```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```

##3<span id="menu">菜单</span>
###3.1<span id="menu-getMenuTree">获取菜单树</span>
- **请求URL**
>[localhost:8080/jlink/menu/getMenuTree](#)
- **请求方式**
>**GET**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
|token | token |
```
- **请求参数**
>
```
{
    
}
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": [
        {
            "id": "110",
            "text": "系统管理",
            "children": [
                {
                    "id": "112",
                    "text": "用户管理",
                    "children": []
                },
                {
                    "id": "116",
                    "text": "角色管理",
                    "children": []
                },
                {
                    "id": "117",
                    "text": "发送邮件配置",
                    "children": []
                },
                {
                    "id": "118",
                    "text": "接收邮件用户",
                    "children": []
                }
            ]
        },
        ...
    ]
}
```
- **说明**
> 未登录:
```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```
##4<span id="role">角色</span>
###4.1<span id="role-getRoleList">获取角色列表</span>
- **请求URL**
>[localhost:8080/jlink/role/getRoleList](#)
- **请求方式**
>**GET**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
|token | token |
```
- **请求参数**
>
```
{
}
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": [
        {
            "roleId": 1,
            "name": "管理员",
            "code": "001",
            "createTime": 1557498794000,
            "modifyTime": null,
            "isDelete": 0,
            "isActive": 0
        },
        ...
     ]
}
```
- **说明**
> 未登录:
```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```

###4.2<span id="role-addRole">添加角色</span>
- **请求URL**
>[localhost:8080/jlink/role/addRole](#)
- **请求方式**
>**POST**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
|token | token |
```
- **请求参数**
>
```
{
	"name":"管理员",
	"code":"001"
}
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": {
            "roleId": 1,
            "name": "管理员",
            "code": "001",
            "createTime": 1557498794000,
            "modifyTime": null,
            "isDelete": 0,
            "isActive": 0
        }
}
```
- **说明**
> 未登录:
```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```
> 角色已存在：
```
{
    "code": "400",
    "msg": "角色已存在！",
    "data": null
}
```

###4.3<span id="role-deleteRole">删除角色</span>
- **请求URL**
>[localhost:8080/jlink/role/deleteRole](#)
- **请求方式**
>**DELETE**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
|token | token |
```
- **请求参数**
>
```
roleId=4
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": null
}
```
- **说明**
> 未登录:
```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```


##5<span id="roleMenu">角色菜单</span>
###5.1<span id="roleMenu-addRoleMenu">角色绑定菜单</span>
- **请求URL**
>[localhost:8080/jlink/roleMenu/addRoleMenu](#)
- **请求方式**
>**POST**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
|token | token |
```
- **请求参数**
>
```
{
	"roleId":1,
	"menuIds":["120", "122"]
}
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": [
        "120",
        "122"
    ]
}
```
- **说明**
> 未登录:
```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```

###5.2<span id="roleMenu-getMenusByRoleId">根据角色获取菜单</span>
- **请求URL**
>[localhost:8080/jlink/roleMenu/getMenusByRoleId](#)
- **请求方式**
>**GET**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
|token | token |
```
- **请求参数**
>
```
{
	"roleId":1
}
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": [
        "120",
        "122"
    ]
}
```
- **说明**

```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```


##6<span id="roleDept">角色部门</span>
###5.1<span id="roleDept-addRoleDept">角色绑定部门</span>
- **请求URL**
>[localhost:8080/jlink/roleDept/addRoleDept](#)
- **请求方式**
>**POST**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
|token | token |
```
- **请求参数**
>
```
{
	"roleId":1,
	"deptNos":["01", "0101"]
}
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": [
        "01",
        "0101"
    ]
}
```
- **说明**
> 未登录:
```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```

###6.2<span id="roleDept-getDeptsByRoleId">根据角色获取部门</span>
- **请求URL**
>[localhost:8080/jlink/roleDept/getDeptsByRoleId](#)
- **请求方式**
>**GET**
- **Headers**
>
```
| :-------- | :--------|
| Content-Type| application/json|
|token | token |
```
- **请求参数**
>
```
roleId = 1
```

- **返回示例**
>
```
{
    "code": "200",
    "msg": "操作成功",
    "data": [
        "01",
        "0101"
    ]
}
```
- **说明**

```
{
    "code": "403",
    "data": null,
    "msg": "请重新登录"
}
```
