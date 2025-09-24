package com.arstialmq.javaweb.utils;

public class NumberUtil {


    //Regular expression
    public static boolean isNumeric(String str) {
        return str != null && str.matches("-?\\d+(\\.\\d+)?");
    }

    //nếu cần sử dụng luôn giá trị
//    public static boolean isNumeric(String value) {
//        try {
//            Long number = Long.parseLong(value);
//            return true;
//        }
//        catch (NumberFormatException e) {
//            return false;
//        }
//    }

    //Dùng trong số nguyên

//    public boolean isNumeric(String str) {
//        if(str == null || str.isEmpty()) return false;
//        for (char c : str.toCharArray()) {
//            if (!Character.isDigit(c)) return false;
//        }
//
//        return true;
//    }
}
