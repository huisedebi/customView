package com.xinyartech.baselibrary.utils;

import android.content.Context;
import android.text.TextUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zjb on 2016/4/6.
 */
public class StringUtil {
    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
        String telRegex = "[1][1234567890]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    /**
     * 密码必须大于6位，且由字母及数字组合
     *
     * @param password
     * @return
     */
    public static boolean isPassword(String password, int length) {
        String pass = "^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{" + length + ",}$";
        if (TextUtils.isEmpty(password)) {
            return false;
        } else {
            return password.matches(pass);
        }
    }

    /**
     * 8-20个字符，必须由字母,数字,特殊符号组合
     *
     * @param password
     * @return
     */
    public static boolean isPasswordX(String password) {
        String pass = "^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^*]+$";
        if (TextUtils.isEmpty(password)) {
            return false;
        } else {
            return password.matches(pass);
        }
    }

    /**
     * 用户名（4-20个字符,以字母开头.(a-z,0-9,中划线)）
     */
    public static boolean isUName(String username) {
        String pass = "^[a-zA-z][a-zA-Z0-9-]{3,20}$";
        if (TextUtils.isEmpty(username)) {
            return false;
        } else {
            return username.matches(pass);
        }
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证合法字符
     *
     * @return
     */
    public static boolean checkCharacter(String character) {
        boolean flag = false;
        try {
            String check = "[a-zA-Z0-9_]{4,16}";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(character);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 是否是车牌
     *
     * @param carId
     * @return
     */
    public static boolean isCarID(String carId) {
        String telRegex = "[\\u4e00-\\u9fa5|WJ]{1}[A-Z0-9]{6}";
        if (TextUtils.isEmpty(carId)) {
            return false;
        } else {
            return carId.matches(telRegex);
        }
    }

    private static Pattern p = Pattern.compile("[\u4e00-\u9fa5]*");

    /**
     * 验证姓名
     */
    public static boolean checkChineseName(String name) {
        boolean flag = false;
        try {
            Matcher matcher = p.matcher(name);
            flag = matcher.matches();
            if (name.length() < 2 || name.length() > 15) {
                flag = false;
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 提取出城市或者县
     *
     * @param city
     * @param district
     * @return
     */
    public static String extractLocation(final String city, final String district) {
        return district.contains("县") ? district.substring(0, district.length() - 1) : city.substring(0, city.length() - 1);
    }

    /**
     * 判断小数点后2位的数字的正则表达式
     */
    private static Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");


    /**
     * 金额验证
     */
    public static boolean isAmount(String str) {
        Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * des： 秒数转换时分秒
     * author： ZhangJieBo
     * date： 2017/9/26 0026 下午 8:08
     */
    public static String TimeFormat(int progress) {
        int hour = progress / 3600;
        int min = progress % 3600 / 60;
        int sec = progress % 60;
        //设置整数的输出格式：  %02d  d代表int  2代码位数    0代表位数不够时前面补0
        String time = String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec);
        return time;
    }

    /**
     * des： 毫秒数转换天时分秒
     * author： ZhangJieBo
     * date： 2017/9/26 0026 下午 8:08
     */
    public static String TimeFormatX(long progress) {
        int year = (int) (progress / (60 * 60 * 1000 * 24 * 365));
        if (year > 0) {
//         return year+"年前";
            return "1月前";
        }
        int month = (int) (progress % (60 * 60 * 1000 * 24 * 365) / (60 * 60 * 1000 * 24 * 30));
        if (month > 0) {
//            return month+"月前";
            return "1月前";
        }
        int day = (int) (progress % (60 * 60 * 1000 * 24 * 30) / (60 * 60 * 1000 * 24));
        if (day > 0) {
            return day + "天前";
        }
        int hour = (int) (progress % (60 * 1000 * 60 * 24) / (60 * 60 * 1000));
        if (hour > 0) {
            return hour + "小时前";
        }
        int min = (int) (progress % (60 * 1000 * 60) / (60 * 1000));
        if (min > 0) {
            return min + "分钟前";
        }
        int sec = (int) (progress % (60 * 1000) / 1000);
        if (sec > 0) {
            return "刚刚";
        }
        return "刚刚";
    }


    /**
     * des： 隐藏电话号码
     * author： ZhangJieBo
     * date： 2017/10/19 0019 下午 2:05
     */
    public static String hidePhone(String phone) {
        String substring01 = phone.substring(0, 3);
        String substring02 = phone.substring(7, phone.length());
        return substring01 + "****" + substring02;
    }

    /**
     * 获取加密token
     *
     * @param jiaMi
     * @return
     */
    public static String apiMD5(String jiaMi) {
        String token = "";
        String md5 = MD5Util.getMD5(jiaMi);
        token = token + md5.substring(0, 3);
        token = token + md5.substring(8, 11);
        token = token + md5.substring(16, 19);
        token = token + md5.substring(24, 27);
        return token;
    }

    public static String getIP(Context context) {

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 提取字符串中的 数字 带小数点 ，没有就返回""
     *
     * @param money
     * @return
     */
    public static String getMoney(String money) {
        Pattern pattern = Pattern.compile("(\\d+\\.\\d+)");
        Matcher m = pattern.matcher(money);
        if (m.find()) {
            money = m.group(1) == null ? "" : m.group(1);
        } else {
            pattern = Pattern.compile("(\\d+)");
            m = pattern.matcher(money);
            if (m.find()) {
                money = m.group(1) == null ? "" : m.group(1);
            } else {
                money = "";
            }
        }

        return money;
    }
}
