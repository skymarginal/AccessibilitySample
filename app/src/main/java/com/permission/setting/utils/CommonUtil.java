package com.permission.setting.utils;

import android.os.Build;

/**
 * Created by 你的样子 on 2017/12/9.
 * 常用工具类
 * @author gerile
 */
public class CommonUtil {

    /**
     * 获取品牌
     */
    public static String getBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取机型
     */
    public static String getModel(){
        return Build.MODEL;
    }

    /**
     * 获取用户设备的系统版本 6.0 7.0
     */
    public static String getOSVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取设备SDK 23,24,25
     */
    public static String getSDKVersion() {
        return android.os.Build.VERSION.SDK;
    }

    /**
     * 英文字母大写转小写
     */
    public static String letterTurn2Lowercase(String str) {
        StringBuffer sb = new StringBuffer();
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

}
