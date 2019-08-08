package com.xinyartech.baselibrary.utils;

import android.util.Log;

/**
 * des： 日志打印辅助类
 * author： ZhangJieBo
 * date： 2018/11/1 14:38
 */
public class LogUtil {
  
    //可以全局控制是否打印log日志  
    public static boolean isPrintLog = true;

    private static int LOG_MAXLENGTH = 2000;
  
    public static void LogShitou(String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;  
            int end = LOG_MAXLENGTH;  
            for (int i = 0; i < 100; i++) {  
                if (strLength > end) {  
                    Log.e("shitou___" + i, msg.substring(start, end));
                    start = end;  
                    end = end + LOG_MAXLENGTH;  
                } else {  
                    Log.e("shitou___" + i, msg.substring(start, strLength));
                    break;  
                }  
            }  
        }  
    }  
  
    public static void LogShitou(String type, String msg) {
  
        if (isPrintLog) {  
  
            int strLength = msg.length();  
            int start = 0;  
            int end = LOG_MAXLENGTH;  
            for (int i = 0; i < 100; i++) {  
                if (strLength > end) {  
                    Log.e(type + "-" + i, msg.substring(start, end));
                    start = end;  
                    end = end + LOG_MAXLENGTH;  
                } else {  
                    Log.e(type + "-" + i, msg.substring(start, strLength));
                    break;  
                }  
            }  
        }  
    }  
  
}  

