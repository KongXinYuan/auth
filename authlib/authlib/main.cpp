//
//  main.cpp
//  TestCryptopp
//
//  Created by 李本利 on 3/3/16.
//  Copyright © 2016 李本利. All rights reserved.
//

#include <stdio.h>
#include <iostream>
#include "KssUtil.h"
#include "KssAuth.h"
#include "cryptopp/aes.h"
#include <cryptopp/osrng.h>
#include <cryptopp/filters.h>
#include <cryptopp/default.h>
#include "cryptopp/base64.h"
#include <unistd.h>

using namespace std;
using namespace CryptoPP;


void testRSA();
void init();
void signup();
void signin();
void recharge();


int main(int argc, const char * argv[])
{
    
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
    
    
    return 0;
}

void init(){
    KssAuth &auth = KssAuth::GetInstance();
    
    string url = "http://localhost/api/v1/user.json";
    
    long port = 8080;
    
    string pubKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkQlQvPsiKuUVLb9Ka/7DLa2qaChf2Md+WwF69bypwncVrfFOC4evmMGGO6UU97XMECUr3CBAY2QjHzf+ubMKT0eMRIPz6iWW37AwFGCLoD48PfY5xYNNzdzKMoTg4BJHgwKxVHXFW2Qlg9/8So8IcpXvL3+FNVEiGuwlczltCCwIDAQAB";
    
    string softid = "5";
    
    string softKey = "yV3QQVpNq7H8hwuhGTdCfXlj";
    
    string pccode = "pccode";
    
    auth.initSoft(url, port, softid, softKey, pubKey, pccode);
}

void signup(){
    KssAuth &auth = KssAuth::GetInstance();
    
    string username = "虚谷若谷";
    string password = "123456";
    string cdkey = "7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy";
    
    long remain;
    auth.signup(username, password, cdkey, remain);
    
    long day;
    long hour;
    long minute;
    
    TimeUtil::parse(remain, day, hour, minute);
    
    cout << "remain" << remain << endl
    << "day:\t" << day << endl
    << "hour:\t" << hour << endl
    << "minute:\t" << minute << endl;
}

void recharge(){
    KssAuth &auth = KssAuth::GetInstance();
    
    string username = "虚谷若谷";
    string cdkey = "7kkkXoFDNQqlVlPD3cZRPLwS4kVikSfd";
    
    long remain;
    auth.recharge(username, cdkey, remain);
    
    long day;
    long hour;
    long minute;
    TimeUtil::parse(remain, day, hour, minute);
    
    cout << "remain" << remain << endl
    << "day:\t" << day << endl
    << "hour:\t" << hour << endl
    << "minute:\t" << minute << endl;
    
}

void signin(){
    KssAuth &auth = KssAuth::GetInstance();
    
    string username = "虚谷若谷";
    string password = "123456";
    string cdkey = "7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy";
    
    auth.setUser(username, password);
    auth.signin();
    long remain = auth.getRemainTime();
    
    
    long day;
    long hour;
    long minute;
    
    TimeUtil::parse(remain, day, hour, minute);
    
    cout << "remain" << remain << endl
    << "day:\t" << day << endl
    << "hour:\t" << hour << endl
    << "minute:\t" << minute << endl;
}



void testRSA(){
    
    string privKey,pubKey;
    
    privKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKRCVC8+yIq5RUtv0pr/sMtrapoKF/Yx35bAXr1vKnCdxWt8U4Lh6+YwYY7pRT3tcwQJSvcIEBjZCMfN/65swpPR4xEg/PqJZbfsDAUYIugPjw99jnFg03N3MoyhODgEkeDArFUdcVbZCWD3/xKjwhyle8vf4U1USIa7CVzOW0ILAgMBAAECgYAGu2kz3oDdnqZGZzjcfWpDjA18brl4r6aYSR4Y6Xt1ziGPPDM4BAZlEsqMzua1mQvDuJXH9h6ixhJkDf9SqMZ4s1Jl4OALyPT8fbKCCBCW/Vsj0r85AZ4lJVdcOIrNZFYZhRDykEnxH7v07oemapD3Yztq7r5aVcCrAlJBdqPt4QJBAPuBJ4hw/tNFFNVE+51r86qpMEs6JT6JHrbGyYQug+opJKscFUX0IBnjK550vehE8qN5tNa27sWshsOvnoLin8kCQQCnMfP1Yo5kR2KYTXLWJvAD3GtPxoBIfq2g67Vl8nRUBjLrM6TxLKNuCJlguneYSI7Ta606OPG0QZMHbrtZooUzAkBpdgH2w7MHMkuHFnMP0smNQX6/vLULYQIhEIBFzh+AnBoFKjR9bd3cHRcYTcmoUOkRxaIf+vtqpmqoOccVCSSJAkAHz8esMVyhxswOfZ/d7ZfNNmE1KvsXaAPIvvqMttVEj72VS6ZrNkC0hWZVJKEt1kYGDsPbMonoeB/cewn52CQNAkEA5F8jvfLwlwl/lnqzkUz7i7dREGzo9Q5Opc0fQ28FZubIlafauDyiGaMgddfut4slt4lYsrqxeWRXeOClYx5whQ==";
    pubKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkQlQvPsiKuUVLb9Ka/7DLa2qaChf2Md+WwF69bypwncVrfFOC4evmMGGO6UU97XMECUr3CBAY2QjHzf+ubMKT0eMRIPz6iWW37AwFGCLoD48PfY5xYNNzdzKMoTg4BJHgwKxVHXFW2Qlg9/8So8IcpXvL3+FNVEiGuwlczltCCwIDAQAB";
    
    //RSAUtil::GenerateRSAKey( 1024, privKey, pubKey);
    //cout << privKey << endl << endl << pubKey << endl << endl;
    
    string message = "hello world!7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy7kkkSoz4e5h6XGXaNCEHvpJA9yDdGIOy";
    
    string encryptedText = RSAUtil::RSAEncryptString( pubKey, message );
    string decryptedText = RSAUtil::RSADecryptString( privKey, encryptedText);
    
    cout << "length:" << encryptedText.length() << endl;
    cout << encryptedText << endl << endl << decryptedText << endl << endl;
}


void testAES(){
    
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
    
    cout << keystr << endl;
    cout << ivstr << endl;
    
    
    byte nkey[AES::DEFAULT_KEYLENGTH];
    byte niv[AES::BLOCKSIZE];
    
    
    StringSource(keystr, true, new Base64Decoder(new ArraySink(nkey,AES::DEFAULT_KEYLENGTH)));
    StringSource(ivstr, true, new Base64Decoder(new ArraySink(niv,AES::DEFAULT_KEYLENGTH)));
    
    
    
    CBC_Mode<AES>::Encryption aesEncryptor(nkey, AES::DEFAULT_KEYLENGTH, niv);
    string cipher;
    string plainText="123456";
    StringSource(plainText, true, new StreamTransformationFilter(aesEncryptor,new StringSink(cipher)));
    
    
    string recover;
    CBC_Mode<AES>::Decryption aesDecryptor(key, AES::DEFAULT_KEYLENGTH, iv);
    StringSource(cipher, true,new StreamTransformationFilter(aesDecryptor,new StringSink(recover)));
}

