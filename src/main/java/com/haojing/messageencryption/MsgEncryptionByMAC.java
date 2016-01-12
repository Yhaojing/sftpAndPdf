package com.haojing.messageencryption;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hnzb on 16/1/12.
 * mac消息认证码算法兼容md5/sha1
 * 含有秘钥的散列函数算法hmac
 */
public class MsgEncryptionByMAC {
    private static String string = "security md";

    public static void HmacMD() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");//初始化keyGenerator
            SecretKey secretKey = keyGenerator.generateKey();//chans秘钥
            byte[] key = secretKey.getEncoded();//获得秘钥
            //或者
//            byte[] key = Hex.decodeHex(new char[]{'a', 'a' ,'a'});//自定义以aaa加密
            //还原秘钥
            SecretKey restoreSecretKet = new SecretKeySpec(key, "HmacMD5");
            //实例化mac
            Mac mac = Mac.getInstance(restoreSecretKet.getAlgorithm());
            //初始化
            mac.init(restoreSecretKet);
            byte[] hmacMd5Bytes = mac.doFinal(string.getBytes());//执行摘要
            System.out.println(Hex.encodeHexString(hmacMd5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
    //bc的实现
    //cc的实现
    public static void main(String[] args) {
        HmacMD();
    }
}
