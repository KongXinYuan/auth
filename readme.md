#auth

java web用spring mvc, spring security, mybatis

实现结构包括


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


