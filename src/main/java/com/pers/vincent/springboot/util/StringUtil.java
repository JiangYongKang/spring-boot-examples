package com.pers.vincent.springboot.util;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * User: vincent
 * Date: 2017/4/12
 * Comment: 字符串工具类
 */
public class StringUtil {

    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 判断是否不为空
     *
     * @param s 需要判断的字符串
     * @return 不为空返回 true 为空返回 false
     */
    public static boolean isNotNull(String s) {
        return s != null && !s.trim().equals("");
    }

    /**
     * 判断是否为空
     *
     * @param s 需要判断的字符串
     * @return 为空返回 true 不为空返回 false
     */
    public static boolean isNull(String s) {
        return s == null || s.trim().equals("");
    }

    /**
     * 将字节数组转换为字符串对象
     *
     * @param bytes 字节数组
     * @return 转换成功返回字符串对象，否则返回 null
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 随机生成一个 32 位的 UUID，已去掉 "-"
     *
     * @return 32 位 UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
