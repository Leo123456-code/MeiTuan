package com.imooc.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * ClassName: Md5Util
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/29-10:27
 * email 1437665365@qq.com
 */
//确认计算方法Md5
public class Md5Util {

    public static String encodeByMd5(String str) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
    }
}
