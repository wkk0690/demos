package com.example.demo.util;

/**
 * @author wkk
 * @create 2020/07/09
 * @desc
 */
public class StringUtil {

    public static String lastPart(String src, char part) {
        int pos = src.lastIndexOf(part);
        return pos == -1 ? src : src.substring(pos + 1);
    }

    public static String lastPart(String src) {
        return lastPart(src, '.');
    }
}
