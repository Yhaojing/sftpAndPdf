package com.haojing.messageencryption;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hnzb on 16/1/12.
 * SHA消息摘要算法,安全散列算法固定长度摘要算法,MD5的继承者
 * SHA1
 * 应用场景:消息传递
 * 1)加入约定的Key
 * 2)加入时间戳
 * 3)排序
 * msg: 原始信息+key+时间戳
 */
public class MsgEncryptionSHA {
    private static String string = "security md";
    public static void jdkSHA1() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(string.getBytes());
            System.out.println(Hex.encodeHexString(messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 可以使用bc/cc 进行加密
     * cc 其实就是对jdksha 的一种封装
     * DigestUtils.sha1Hex(string)
     * DigestUtils.sha1Hex(string.getBytes())
     */
}
