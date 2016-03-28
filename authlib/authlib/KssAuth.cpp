//
//  KssAuth.cpp
//  TestCryptopp
//
//  Created by 李本利 on 3/3/16.
//  Copyright © 2016 李本利. All rights reserved.
//

#include "KssAuth.h"
#include <stdlib.h>
#include "cryptopp/aes.h"
#include <cryptopp/osrng.h>
#include <cryptopp/filters.h>
#include <cryptopp/default.h>
#include "cryptopp/base64.h"

using namespace std;
using namespace CryptoPP;

void KssAuth::initSoft(string url, long port, string softid, string softKey, string pubKey, string pccode){
    this->url = url;
    this->port = port;
    this->softid = softid;
    this->softkey = softKey;
    this->pubKey = pubKey;
    this->pccode=pccode;
    this->inited = true;
}

void KssAuth::setUser(string username, string password){
    this->username = username;
    this->password = password;
}

bool KssAuth::isInited(){
    if (!inited) {
        errMsg = "未初始化";
        return false;
    }
    
    return inited;
}

//登录
bool KssAuth::signin(){
    if (!isInited()) {
        return false;
    }
    
    this->linecode = RandomUtil::getRandomCharAndNumr(10);
    
    //生成参数列表
    Json::FastWriter writer;
    Json::Value param, recv;
    param["method"] = "signin";
    param["username"] = this->username;
    param["password"] = this->password;
    //机器信息生成的编码
    param["pccode"] = this->pccode;
    //运行校验,类似session,每次登录重置
    //每次允许一个点登录
    param["linecode"] = this->linecode;
    
    
    bool result = SafeHttpPost(param, recv);
    cout << writer.write(recv) << endl << endl;
    if(!result)return false;
    
    if(!recv["remain"].isInt() || recv["remain"].asInt() <= 0){
        errMsg = "没有剩余时间";
        return false;
    }
    this->remainTime = recv["remain"].asInt();
    
    return result;
}

//注册
bool KssAuth::signup(std::string username, std::string password, std::string cdkey, long &remainTime){
    if (!isInited()) {
        return false;
    }
    remainTime=0;
    
    //生成参数列表
    Json::FastWriter writer;
    Json::Value param, recv;
    param["method"] = "signup";
    param["username"] = username;
    param["password"] = password;
    param["cdkey"] = cdkey;
    
    bool result = SafeHttpPost(param, recv);
    cout << writer.write(recv) << endl << endl;
    if(!result)return false;
    
    //剩余时间
    if(!recv["remain"].isInt() || recv["remain"].asInt() <= 0){
        errMsg = "没有剩余时间";
        return false;
    }
    remainTime = recv["remain"].asInt();
    return true;
}

bool KssAuth::recharge(std::string username, std::string cdkey, long &remainTime){
    if (!isInited()) {
        return false;
    }
    
    //生成参数列表
    Json::FastWriter writer;
    Json::Value param, recv;
    param["method"] = "recharge";
    param["username"] = username;
    param["cdkey"] = cdkey;
    
    bool result = SafeHttpPost(param, recv);
    cout << writer.write(recv) << endl << endl;
    if(!result)return false;
    
    //剩余时间
    if(!recv["remain"].isInt() || recv["remain"].asInt() <= 0){
        errMsg = "没有剩余时间";
        return false;
    }
    remainTime = recv["remain"].asInt();
    return true;
}


bool KssAuth::valid(){
    if (!isInited()) {
        return false;
    }
    
    //生成参数列表
    Json::FastWriter writer;
    Json::Value param, recv;
    param["method"] = "valid";
    param["username"] = this->username;
    param["linecode"] = this->linecode;
    
    
    bool result = SafeHttpPost(param, recv);
    cout << writer.write(recv) << endl << endl;
    if(!result)return false;
    
    //剩余时间
    if(!recv["remain"].isInt() || recv["remain"].asInt() <= 0){
        errMsg = "没有剩余时间";
        return false;
    }
    remainTime = recv["remain"].asInt();
    return true;
    
}

bool KssAuth::changePassword(std::string username, std::string password, std::string newPassword){
    if (!isInited()) {
        return false;
    }
    
    //生成参数列表
    Json::FastWriter writer;
    Json::Value param, recv;
    param["method"] = "changepwd";
    param["username"] = username;
    param["password"] = password;
    param["newPassword"] = newPassword;
    
    return SafeHttpPost(param, recv);
}

//获取剩余时间
long KssAuth::getRemainTime(){
    if (!isInited()) {
        return -1;
    }
    return this->remainTime;
}



bool KssAuth::SafeHttpPost(Json::Value &param, Json::Value &recv){
    
    //json 类
    Json::FastWriter writer;
    Json::Reader reader;
    
    //添加随机数字用于验证
    param["verify"] = RandomUtil::getRandomCharAndNumr(10);
    
    
    
    // generate the key
    AutoSeededRandomPool rnd;
    byte key[AES::DEFAULT_KEYLENGTH];
    rnd.GenerateBlock( key, AES::DEFAULT_KEYLENGTH);
    
    // Generate a random IV
    byte iv[AES::BLOCKSIZE];
    rnd.GenerateBlock(iv, AES::BLOCKSIZE);
    
    Base64Encoder base64;
    
    string keystr;
    StringSource(key,AES::DEFAULT_KEYLENGTH, true, new Base64Encoder(new StringSink(keystr)));
    string ivstr;
    StringSource(iv,AES::DEFAULT_KEYLENGTH, true, new Base64Encoder(new StringSink(ivstr)));
    
    param["key"] = keystr;
    param["iv"] = ivstr;
    param["len"] = AES::DEFAULT_KEYLENGTH;
    
    //RSA加密
    string jsonReq = writer.write(param);
    string encryptedText = RSAUtil::RSAEncryptString(this->pubKey, jsonReq);
    
    //生成请求参数
    ParamList req;
    req.push_back(*new ParamItem("softid", softid));
    req.push_back(*new ParamItem("softkey", softkey));
    req.push_back(*new ParamItem("req", encryptedText));
    
    //http请求
    HttpUtil &http = HttpUtil::GetInstance();
    string recvRaw;
    try {
        recvRaw = http.post(url, port, req);
        cout << recvRaw << endl << endl;
    } catch (string e) {
        errMsg = e;
        return false;
    }
    
    //解析服务器返回数据
    Json::Value recvJson;
    if (!reader.parse(recvRaw, recvJson)){
        errMsg = "解析服务器数据错误";
        return false;
    }
    
    //解析json
    //是否有错
    if(recvJson["hasError"].isBool() && true == recvJson["hasError"].asBool()){
        if (recvJson["error"].isString()) {
            errMsg = recvJson["error"].asString();
        }else{
            errMsg = "未知错误";
        }
        return false;
    }
    
    //校验success
    if(!recvJson["success"].isBool()||!recvJson["success"].asBool()){
        errMsg="未知错误";
        return false;
    }
    
    //是否有msg
    if(!recvJson["msg"].isString()){
        errMsg = "没有返回结果";
        return false;
    }
    
    //获取msg,解密
    string msg = recvJson["msg"].asString();
    
    
    //解密
    string recvPlain;
    CBC_Mode<AES>::Decryption aesDecryptor(key, AES::DEFAULT_KEYLENGTH, iv);
    StringSource(msg, true,new Base64Decoder(new StreamTransformationFilter(aesDecryptor,new StringSink(recvPlain))));
    
    
    //解析
    reader.parse(recvPlain, recv);
    
    if(!recv["verify"].isString() || 0 != param["verify"].asString().compare(recv["verify"].asString())){
        errMsg = "服务器校验码错误";
        return false;
    }
    
    
    return true;
}








