//
//  KssAuth.h
//  TestCryptopp
//
//  Created by 李本利 on 3/3/16.
//  Copyright © 2016 李本利. All rights reserved.
//

#ifndef KssAuth_h
#define KssAuth_h

#include <stdio.h>
#include <iostream>
#include <map>
#include "KssUtil.h"
#include "json/json.h"


class KssAuth
{
private:
    //构造函数是私有的
    KssAuth()
    {
    }
    KssAuth(const KssAuth &);
    KssAuth & operator = (const KssAuth &);
    
    long port;
    std::string url, softid, softkey, pubKey;
    //机器码，运行校验码
    std::string pccode, linecode;
    std::string username, password;
    std::string errMsg;
    long remainTime=-1;
    bool inited = false;
private:
    bool isInited();
    bool SafeHttpPost(Json::Value &param, Json::Value &recv);
public:
    ~KssAuth()
    {
    }
    static KssAuth & GetInstance()
    {
        //局部静态变量
        static KssAuth instance;
        return instance;
    }
    //初始化软件信息
    void initSoft(std::string url, long port, std::string softid, std::string softkey,std::string pubKey,std::string pccode);
    //设置用户名密码
    void setUser(std::string username, std::string password);
    //获取上次错误信息
    std::string getLastError(){return GetInstance().errMsg;}
    //登录
    bool signin();
    //注册 返回剩余时长
    bool signup(std::string username, std::string password, std::string cdkey, long &remainTime);
    //校验
    bool valid();
    //剩余时长 毫秒计算
    long getRemainTime();
    //注册 返回剩余时长
    bool recharge(std::string username, std::string cdkey, long &remainTime);
    //修改密码
    bool changePassword(std::string username, std::string password, std::string newPassword);
};





#endif /* KssAuth_hpp */
