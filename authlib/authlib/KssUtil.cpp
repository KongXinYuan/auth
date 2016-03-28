//
//  KssUtil.cpp
//  TestCryptopp
//
//  Created by 李本利 on 3/4/16.
//  Copyright © 2016 李本利. All rights reserved.
//

#include "KssUtil.h"


#include "cryptopp/randpool.h"
#include "cryptopp/rsa.h"
#include "cryptopp/base64.h"
#include <vector>


using namespace std;
using namespace CryptoPP;


//生成密匙对
void RSAUtil::GenerateRSAKey(unsigned int keyLength, string &privKey, string &pubKey)
{
    RandomPool randPool;
    
    RSAES_PKCS1v15_Decryptor priv(randPool, keyLength);
    Base64Encoder privStr(new StringSink(privKey));
    priv.DEREncode(privStr);
    privStr.MessageEnd();
    
    
    RSAES_PKCS1v15_Encryptor pub(priv);
    Base64Encoder pubStr(new StringSink(pubKey));
    pub.DEREncode(pubStr);
    pubStr.MessageEnd();
}


//RSA加密
string RSAUtil::RSAEncryptString( string &pubKey, string &message )
{
    
    
    
    StringSource pubStr(pubKey,true, new Base64Decoder);
    
    RSAES_PKCS1v15_Encryptor pub(pubStr);
    
    RandomPool randPool;
    
    
    long offSet=0;
    long len = message.length();
    string crypt;
    while (len > offSet) {
        string cache;
        if(len - offSet > MAX_ENCRYPT_BLOCK){
            StringSource(message.substr(offSet,MAX_ENCRYPT_BLOCK), true, new PK_EncryptorFilter(randPool, pub, new StringSink(cache)));
            offSet+=MAX_ENCRYPT_BLOCK;
        }else{
            StringSource(message.substr(offSet), true, new PK_EncryptorFilter(randPool, pub, new StringSink(cache)));
            offSet=len;
        }
        crypt+=cache;
    }
    
    string result;
    StringSource(crypt, true, new Base64Encoder(new StringSink(result)));
    return result;
}


//RSA解密
string RSAUtil::RSADecryptString(string &privKey, string &ciphertext)
{
    RandomPool randomPool;
    
    StringSource pubStr(privKey, true, new Base64Decoder);
    
    RSAES_PKCS1v15_Decryptor pub(pubStr);
    
    string rawtext;
    StringSource( ciphertext, true, new Base64Decoder(new StringSink(rawtext)));
    
    string result;
    long offSet=0;
    long len = rawtext.length();
    while (len > offSet) {
        string cache;
        if(len - offSet > MAX_DECRYPT_BLOCK){
            StringSource(rawtext.substr(offSet,MAX_DECRYPT_BLOCK), true, new PK_DecryptorFilter(randomPool, pub, new StringSink(cache)));
            offSet+=MAX_DECRYPT_BLOCK;
        }else{
            StringSource(rawtext.substr(offSet), true, new PK_DecryptorFilter(randomPool, pub, new StringSink(cache)));
            offSet=len;
        }
        result+=cache;
    }
    return result;
    
}




CURL *HttpUtil::getCurl(){
    CURL *curl = curl_easy_init();;
    
    if (!curl) {
        throw "无法创建curl错误";
    }
    
    return curl;
}


//post请求
string HttpUtil::post(string &url, long port, ParamList &param){
    
    string paramStr = assembleParam(param);
    cout << paramStr << endl;
    string recv = post(url, port, paramStr);
    return recv;
}


size_t write_data(void *contents, size_t size, size_t nmemb, void *userp) {
    size_t realsize = size * nmemb;
    ((std::string*)userp)->append((char *)contents, realsize);
    return realsize;
}

string HttpUtil::post(string &url, long port, string &paramStr){
    
    CURL *curl = getCurl();
    
    string recv;
    
    // URL
    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
    //port
    curl_easy_setopt(curl, CURLOPT_PORT, port);
    // POST data
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, paramStr.c_str());
    // 设置为非0表示本次操作为POST
    curl_easy_setopt(curl, CURLOPT_POST, 1);
    // 超时时间
    curl_easy_setopt(curl, CURLOPT_TIMEOUT, 20);
    // 设置回调函数
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_data);
    // 回调函数第四个参数
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &recv);
    //设置为非0在执行时打印请求信息
    curl_easy_setopt(curl, CURLOPT_VERBOSE, 1);
    /* Perform the request, res will get the return code */
    CURLcode res = curl_easy_perform(curl);
    
    /* Check for errors */
    if(res != CURLE_OK){
        throw string("网络连接错误：") + curl_easy_strerror(res);
    }
    
    curl_easy_cleanup(curl);
    return recv;
    
}



//组装参数
string HttpUtil::assembleParam(ParamList &param){
    string dest;
    
    CURL *curl = getCurl();
    for (auto &kv: param) {
        //urlencoded编码
        string k = curl_easy_escape(curl, kv.first.c_str(), 0);
        string v = curl_easy_escape(curl, kv.second.c_str(), 0);
        dest.append(k + '=' + v + '&');
    }
    
    if (0 != dest.length()) {
        dest.pop_back();
    }
    
    return dest;
}

//拆解参数
ParamList HttpUtil::disassembleParam(std::string &src){
    ParamList param;
    unsigned long nend=0,nbegin=0;
    
    while(true){
        ParamItem item;
        
        nend = src.find_first_of("=", nbegin);
        if (-1 == nend) break;
        item.first = src.substr(nbegin, nend-nbegin);
        nend = src.find_first_of("&", nbegin);
        if (-1 == nend) {
            item.second = src.substr(nbegin, src.length()-nbegin);
            break;
        }else{
            item.second = src.substr(nbegin, nend-nbegin);
        }
        param.push_back(item);
    }
    return param;
}







string RandomUtil::getRandomCharAndNumr(int length){
    string str;
    
    for (int i = 0; i < length; i++) {
        
        // 0-9数字
        // 10-35大写字母
        // 36-61小写字母
        int value = rand() % 62;
        if (value <= 9) {
            str += (char) (48 + value);
        } else if (value <= 35) {
            str += (char) (65 + value - 10);
        } else {
            str += (char) (97 + value - 36);
        }
    }
    return str;
}


void TimeUtil::parse(long time, long &day, long &hour, long &minute){
    if (time<=0) {
        day=0;
        hour=0;
        minute=0;
    }
    long tmp = time/(1000*60);
    day=tmp/(24*60);
    tmp-=day*60*24;
    hour=tmp/60;
    minute=tmp-hour*60;
}




