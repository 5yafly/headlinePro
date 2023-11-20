# headlinePro
头条新闻，用于提供前段接口的后端项目；同时也是使用Tomcat搭建服务器的练手项目。
# 一.接口文档

### 1.登录表单提交

​	需求描述：

- 用户输入用户名和密码后向后端提交，后端根据用户名和密码判断登录是否成功

​	uri：

```
user/login
```

​	请求方式：

```
POST
```

​	请求参数：

```json
{
    "username":"zhangsan",  //用户名
    "userPwd":"123456"		//密码
}
```

​	响应示例：

- 登录成功

```json
{
    "code": 200,
    "message": "成功",
    "data": {
        "token": "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_6tWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDcwMDE3MbQ01VEqLU4t8kwBitUCAIrk4wgvAAAA.xqDCaSkr8wvsBwt7TI2TYC_6YFxM3lbHfe2xHsCyfbrfKmyIH_BYqgEmAzUye4sHyYGTPdxVfGedxy5vsRfoyw"
    }
}
```

- 账户密码错误

```json
{
    "code": 501,
    "message": "失败"
}
```

### 2.根据token获取完整用户信息

​	需求描述：

- 客户端发送请求，提交token请求头，后端根据token请求头获取登录用户的详细信息

​	uri：

```
user/getUserInfo
```

​	请求方式：

```
GET
```

​	请求头：

```json
token: ... ...
```

​	响应示例：

- 成功获取

```json
{
    "code": 200,
    "message": "成功",
    "data": {
        "token": "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_6tWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDcwMDE3MbQ01VEqLU4t8kwBitUCAIrk4wgvAAAA.xqDCaSkr8wvsBwt7TI2TYC_6YFxM3lbHfe2xHsCyfbrfKmyIH_BYqgEmAzUye4sHyYGTPdxVfGedxy5vsRfoyw"
    }
}
```

- 获取失败

```json
{
    "code": 501,
    "message": "失败"
}
```

### 3.注册时用户名占用校验

​	需求描述：

- 用户在注册时输入用户名，前端立刻将用户名发送给后端，后端根据用户名查询是否有用户

​	uri：

```
user/checkUsername
```

​	请求方式：

```
POST
```

​	请求参数：

```json
username:zhangsan
```

​	响应示例：

- 用户名校验通过

```json
{
    "code": 200,
    "message": "成功"
}
```

- 用户名占用

```json
{
    "code": 503,
    "message": "账号重复"
}
```

### 4.用户注册

​	需求描述：

- 客户端将新用户信息发送给后端，后端将用户信息存入数据库

​	uri：

```
user/regist
```

​	请求方式：

```
POST
```

​	请求参数：

```json
{
    "username":"wangwu",
    "userPwd":"123456",
    "nickName":"王五"
}
```

​	响应示例：

- 注册成功

```json
{
    "code": 200,
    "message": "成功"
}
```

- 用户名占用

```json
{
    "code": 503,
    "message": "账号重复"
}
```

### 5.查询所有头条分类

​	需求描述：

- 用户根据不同分类，前端发送相应的分页查询信息，后端接收信息并查寻数据响应

​	uri：

```
portal/findNewsPage
```

​	请求方式：

```
get
```

​	请求参数：

```json
无
```

​	响应示例：

```json
{
    "code": 200,
    "message": "成功",
    "data": {
        "pageInfo": {
            "totalSize": 6,
            "totalPage": 2,
            "pageSize": 5,
            "pageData": [
                {
                    "hid": 1,
                    "title": "特色产业激发乡村振兴新活力",
                    "type": 1,
                    "pageViews": 104,
                    "pastHours": 3560,
                    "publisher": 1
                },
                {
                    "hid": 2,
                    "title": "北京连续三天最高温超40℃，6月“炎值”因何爆表？",
                    "type": 1,
                    "pageViews": 2,
                    "pastHours": 3560,
                    "publisher": 1
                },
                {
                    "hid": 3,
                    "title": "今年夏天，极端高温是否会成为常态？",
                    "type": 1,
                    "pageViews": 1,
                    "pastHours": 3560,
                    "publisher": 1
                },
                {
                    "hid": 4,
                    "title": "中央气象台发布今年首个高温橙色预警",
                    "type": 1,
                    "pageViews": 1,
                    "pastHours": 3560,
                    "publisher": 1
                },
                {
                    "hid": 5,
                    "title": "江南水乡 龙舟竞渡",
                    "type": 1,
                    "pageViews": 1,
                    "pastHours": 3560,
                    "publisher": 1
                }
            ],
            "pageNum": 1
        }
    }
}
```

### 6.查看新闻详情

​	需求描述：

- 用户点击新闻详情，前端发送新闻id，获取新闻详细信息，并更新浏览量

​	uri：

```
portal/showHeadlineDetail
```

​	请求方式：

```
POST
```

​	请求参数：

```json
hid=1
```

​	响应示例：

```json
{
    "code": 200,
    "message": "成功",
    "data": {
        "headline": {
            "hid": 1,
            "title": "特色产业激发乡村振兴新活力",
            "article": "推进中国式现代化，必须全面推进乡村振兴。习近平总书记指出，产业振兴是乡村振兴的重中之重，也是实际工作的切入点。近日，记者走进乡村一线，看到各地以特色产业为抓手，拓展产业链发展产业集群，一二三产业融合发展，培育乡村振兴新动能。\n\n　　这个端午，广东茂名高州市根子镇柏桥村的荔枝迎来了丰收。今年4月，习近平总书记来到柏桥村考察调研。总书记走进荔枝种植园，了解当地发展特色种植产业和文旅产业等情况，并同现场技术人员亲切交流。",
            "type": 1,
            "typeName": "新闻",
            "pageViews": 105,
            "pastHours": 3560,
            "publisher": 1,
            "author": "张三"
        }
    }
}
```

### 7.提交发布新闻

​	需求描述：

- 用户在客户端输入发布新闻后，前端发送请求，后端需要进行登录验证，通过后后端再将数据存入数据库

​	uri：

```
headline/publish
```

​	请求方式：

```
POST
```

​	请求头

```
token: ... ...
```

​	请求参数：

```json
{
    "title":"标题",
    "article":"内容",
    "type":"1"
}
```

​	响应示例：

- 发布成功

```json
{
    "code": 200,
    "message": "成功"
}
```

- ​	失去登录状态发布失败

```json
{
    "code": 502,
    "message": "未登录"
}
```

### 8.修改回显

​	需求描述：

- 用户在进入修改页面时，前端向后端发送请求，服务器先检验当前登录是否过期，未过期获取当前新闻信息，过期响应登录过期的信息

​	uri：

```
headline/findHeadlineByHid
```

​	请求方式：

```
POST
```

​	请求头

```
token: ... ...		
```

​	请求参数：

```json
hid=1
```

​	响应示例：

- 查询成功

```json
{
    "code": 200,
    "message": "成功",
    "data": {
        "headline": {
            "hid": 1,
            "title": "特色产业激发乡村振兴新活力",
            "article": "推进中国式现代化，必须全面推进乡村振兴。习近平总书记指出，产业振兴是乡村振兴的重中之重，也是实际工作的切入点。近日，记者走进乡村一线，看到各地以特色产业为抓手，拓展产业链发展产业集群，一二三产业融合发展，培育乡村振兴新动能。\n\n　　这个端午，广东茂名高州市根子镇柏桥村的荔枝迎来了丰收。今年4月，习近平总书记来到柏桥村考察调研。总书记走进荔枝种植园，了解当地发展特色种植产业和文旅产业等情况，并同现场技术人员亲切交流。",
            "type": 1,
            "publisher": 1,
            "pageViews": 105,
            "createTime": "2023-06-25 09:26:20",
            "updateTime": "2023-06-25 09:26:20",
            "isDeleted": 0
        }
    }
}
```

- ​	失去登录状态无法修改

```json
{
    "code": 502,
    "message": "未登录"
}
```

### 9.提交修改

​	需求描述：

- 用户在修改完成后点击保存，前端发送请求，服务器先检验当前登录是否过期，未过期修改数据库，过期响应登录过期的信息

​	uri：

```
headline/update
```

​	请求方式：

```
POST
```

​	请求头

```
token: ... ...
```

​	请求参数：

```json
{
    "hid":"19",
    "title":"标题",
    "article":"内容",
    "type":"1"
}
```

​	响应示例：

- 查询成功

```json
{
    "code": 200,
    "message": "成功",
}
```

- ​	失去登录状态无法修改

```json
{
    "code": 502,
    "message": "未登录"
}
```

### 10.删除新闻

​	需求描述：

- 前端将要删除的新闻id发送给后端，服务器先检验当前登录是否过期，未过期直接删除，过期响应登录过期的信息

​	uri：

```
headline/removeByHid
```

​	请求方式：

```
POST
```

​	请求头

```
token: ... ...
```

​	请求参数：

```json
hid=1
```

​	响应示例：

- 查询成功

```json
{
    "code": 200,
    "message": "成功",
}
```

- ​	失去登录状态无法修改

```json
{
    "code": 502,
    "message": "未登录"
}
```

