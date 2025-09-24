package com.arstialmq.javaweb.utils;

public class StringUtil {
    public static boolean checkString(String data) {
        if (data != null & !"".equals(data)) {
            return true;
        }
        return false;
    }
}
