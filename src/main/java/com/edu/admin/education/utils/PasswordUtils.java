package com.edu.admin.education.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author mengqa
 * @create 2018-05-22 16:47
 **/
public class PasswordUtils {

    /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String str){
        //确定计算方法
        return DigestUtils.md5Hex(str);
    }
}
