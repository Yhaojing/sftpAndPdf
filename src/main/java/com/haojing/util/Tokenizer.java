package com.haojing.util;

import java.util.StringTokenizer;

/**
 * Created by hnzb on 16/1/7.
 */
public class Tokenizer {
    public static void main(String[] args) {
        String str = "测试字 符串 haha";
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        while(stringTokenizer.hasMoreTokens()) {
            System.out.println(stringTokenizer.nextToken());
        }
    }
}
