package com.xinyartech.baselibrary.utils;

import android.text.TextUtils;

/**
 * Created by cuibin on 17/11/16.
 */

public class PasswordStrength {

    public static int calculateStrength(String password) {
        if (TextUtils.isEmpty(password)){
            return 0;
        }
        //当前安全等级
        int currentScore = 0;
        //是否包含大写字母
        boolean sawUpper = false;
        //是否包含小写字母
        boolean sawLower = false;
        //是否包含数字
        boolean sawDigit = false;
        //是否包含特殊字符
        boolean sawSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            //如果不是字母或者数字就是特殊字符，不是空字符
            if (!sawSpecial && !Character.isLetterOrDigit(c)) {
                sawSpecial = true;
            } else {
                //判断是否包含数字
                if (!sawDigit && Character.isDigit(c)) {
                    sawDigit = true;
                } else {
                    //判断是否包含大小写字母
                    if (!sawUpper || !sawLower) {
                        if(Character.isUpperCase(c) && !sawUpper) {
                            sawUpper = true;
                        }
                        if(Character.isLowerCase(c) && !sawLower) {
                            sawLower = true;
                        }
                    }
                }
            }
        }


        /** 大于八位并且有特殊字符 */
        if((password.length() >= 8) && sawSpecial){
            currentScore = 2;
        }
        else{
            /** 小于八位或者仅包含字母或者数字 */
            if((password.length() < 8) || !((sawDigit && (sawLower || sawUpper)))){
                currentScore = 0;
            }
            /** 其他情况 */
            else{
                currentScore = 1;
            }
        }

        switch (currentScore) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            default:
                return 3;
        }
    }

}
