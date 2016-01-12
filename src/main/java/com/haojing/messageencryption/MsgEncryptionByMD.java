package com.haojing.messageencryption;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hnzb on 16/1/12.
 * MD5消息摘要算法,摘要长度128由JDK实现,单向加密过程
 * 应用:用户注册,对密码进行摘要算法
 */
public class MsgEncryptionByMD {
    private static String string = "security md";
    public static void jdkMD5() {
        try {
            //MD5加密对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md.digest(string.getBytes());
            //bytes转成16进制
            System.out.println("JDK1:"+Hex.encodeHexString(md5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        jdkMD5();
    }
}
