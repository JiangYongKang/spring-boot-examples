package com.pers.vincent.springboot.util;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * User: vincent
 * Date: 2017/4/12
 * Comment: 字符串工具类
 */
public class StringUtil {

    private static final String CHARSET_NAME = "UTF-8";

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");

    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static final Pattern SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");

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
     * 判断字符串是否相等
     *
     * @param s1
     * @param s2
     * @return 都为空的情况下也按相等计算
     */
    public static boolean isEquals(String s1, String s2) {
        if (s1 == null && s2 == null)
            return true;
        if (s1 == null || s2 == null)
            return false;
        return s1.equals(s2);
    }

    /**
     * 判断字符串是否为数字
     *
     * @param s
     * @return 是数字返回 true，否则返回 false
     */
    public static boolean isNumber(String s) {
        return isNotNull(s) && NUMBER_PATTERN.matcher(s).matches();
    }

    /**
     * 字符串转换 Short 类型
     *
     * @param s
     * @return 不是数字返回 0
     */
    public static Short parseShort(String s) {
        return isNumber(s) ? Short.parseShort(s) : 0;
    }

    /**
     * 字符串转换 Integer 类型
     *
     * @param s
     * @return 不是数字返回 0
     */
    public static Integer parseInteger(String s) {
        return isNumber(s) ? Integer.parseInt(s) : 0;
    }

    /**
     * 字符串转换 Long 类型
     *
     * @param s
     * @return 不是数字返回 0
     */
    public static Long parseLong(String s) {
        return isNumber(s) ? Long.parseLong(s) : 0;
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
