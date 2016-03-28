//
//  KssUtil.h
//  TestCryptopp
//
//  Created by 李本利 on 3/4/16.
//  Copyright © 2016 李本利. All rights reserved.
//

#ifndef RSAUtil_h
#define RSAUtil_h

#include <stdio.h>
#include <iostream>
#include <list>

#include <curl/curl.h>

typedef std::pair<std::string, std::string> ParamItem;
typedef std::list<ParamItem> ParamList;

#define MAX_ENCRYPT_BLOCK 117
#define MAX_DECRYPT_BLOCK 128

class RSAUtil{
    
public:
    static void GenerateRSAKey(unsigned int keyLength, std::string &privKey, std::string &pubKey);
    static std::string RSAEncryptString( std::string &pubKey, std::string &message );
    static std::string RSADecryptString(std::string &pubKey, std::string &ciphertext);
    
    
};




class HttpUtil{
private:
    //构造函数是私有的
    HttpUtil(){
        curl_global_init(CURL_GLOBAL_ALL);
    }
    HttpUtil(const HttpUtil &);
    HttpUtil & operator = (const HttpUtil &);
private:
    CURL *getCurl();
    //
    void closeCurl(CURL *curl);
public:
    ~HttpUtil(){
        curl_global_cleanup();
    }
    
    static HttpUtil & GetInstance()
    {
        //局部静态变量
        static HttpUtil instance;
        return instance;
    }
    //post请求
    std::string post(std::string &url, long port, ParamList &param);
    std::string post(std::string &url, long port, std::string &param);
    
    //组装参数
    std::string assembleParam(ParamList &param);
    //拆解参数
    ParamList disassembleParam(std::string &src);
    
};



class RandomUtil{
    
public:
    static std::string getRandomCharAndNumr(int length);
};


class TimeUtil{
public:
    static void parse(long time, long &day, long &hour, long &minute);
};


#endif /* RSAUtil_hpp */
