### auth

java web用spring mvc, spring security, mybatis

目标用户：小型软件权限认证

防破解建议

- 采用加壳SDK保护重要代码和逻辑
- 重要认证函数改为inline
- 软件中加入随机条件下的认证
- 认证相关代码避免使用dll，如果必须，最好校验散列值

通信流程
	
- 客户端每次生成AES密匙key和iv，并随机生成verify字符串，使用RSA送给服务器。
- 服务器采用key和iv作为参数进行AES加密返回。
- 客户端采用AES的密匙key和iv解密,并校验verify字符串是否正确。

即

- msg + AES key -> RSA public key encode -> Base64 encode -> server
- server -> Base64 decode -> RSA private key decode -> AES key encode -> Base64 encode -> client
- client -> Base64 decode -> EAS decode

### web打包安装

修改spring.profiles

	/auth/src/webapp/WEB-INF/web.xml
	<context-param>
		<param-name>spring.profiles.default</param-name>
		<!-- 生产环境需要使用production profile -->
		<param-value>production</param-value>
		<!-- <param-value>development</param-value> -->
	</context-param>

修改数据库信息
	
	下面两个文件和上述的配置绑定
	/auth/src/main/resources/application.production.properties
	/auth/src/main/resources/application.development.properties
	jdbc.driver=com.mysql.jdbc.Driver
	jdbc.url=jdbc:mysql://rdss74pb4a7fvckl0pqxy.mysql.rds.aliyuncs.com:3306/r27q822zoqb3024v?useUnicode=true&characterEncoding=utf-8
	jdbc.username=kent
	jdbc.password=kent123

在项目下打包

- mvn package -Dmaven.test.skip=ture
- 将target目录下的auth.war放在tomcat对应目录下
- 应当部署在域名下(auth.xuguruogu.com)而不是子目录下(auth.xuguruogu.com/auth)

### 使用步奏
- 软件管理->软件列表->添加
- 软件管理->卡类售价设置->添加
- 注册卡管理->添加注册卡->生成注册卡
- 后台账号->账号列表->添加代理
- 后台账号->账号列表->后侧编辑图标->设置代理的权限，代理价格和余额


### 软件接入指南
- 参考authlib设置RSA公匙(添加软件时生成)
- 开始时验证signin
- 运行时valid验证是否到期以及合法


样例参考 <http://auth.xuguruogu.com/>
	
	默认作者
	用户名：root
	密码：admin
	示例中下属两个账号：
	一级代理：虚谷若谷
	密码：kent
	一级代理：kent
	密码：kent123
	代理：test
	密码：test

#authlib

采用c++的客户端lib

提供如下函数：

- signup:注册
- signin:登录
- recharge:充值
- valid:校验合法
- changePassword:修改密码



```
示例代码

    KssAuth &auth = KssAuth::GetInstance();
    
    init();
    
    //signup();
    //recharge();
    string username = "虚谷若谷";
    string password = "123456";
    string newPassword = "12345600";
    auth.changePassword(username, password, newPassword);
    sleep(5);
    auth.changePassword(username, newPassword, password);
    sleep(5);
    signin();
    auth.valid();
    sleep(5);
    auth.valid();

```

#图片

![](https://github.com/xuguruogu/auth/blob/master/img/login.png)

![](https://github.com/xuguruogu/auth/blob/master/img/admin.png)

![](https://github.com/xuguruogu/auth/blob/master/img/cdkey.png)

![](https://github.com/xuguruogu/auth/blob/master/img/addcdkey.png)

![](https://github.com/xuguruogu/auth/blob/master/img/user.png)

![](https://github.com/xuguruogu/auth/blob/master/img/statistic.png)

![](https://github.com/xuguruogu/auth/blob/master/img/soft.png)


