package com.zx.api.utils;

public class MyUtils {
    private static final String sail = "zhouxin";
    public static String getId(String str){
        return MD5Util.MD5(str+sail);
    }
}
