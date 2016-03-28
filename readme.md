#auth

java web用spring mvc, spring security, mybatis

目标用户：小型软件权限认证

防破解建议

- 采用加壳SDK保护重要代码和逻辑
- 重要认证函数改为inline
- 软件中加入随机条件下的认证
- 认证相关代码避免使用dll，如果必须，最好校验散列值

功能：通信流程

```

	客户端每次生成AES密匙对，并随机生成verify字符串，使用RSA送给服务器。服务器采用AES加密返回。客户端需要校验verify字符串是否正确。

	msg + AES key -> RSA public key encode -> Base64 encode -> server -> Base64 decode -> RSA private key decode -> AES key encode -> Base64 encode -> client -> Base64 decode -> EAS decode


```

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


