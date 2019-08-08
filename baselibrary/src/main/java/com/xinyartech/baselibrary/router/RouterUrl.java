package com.xinyartech.baselibrary.router;

/**
 * 作者: created by zcm on 2018/9/21
 * 修改: modified by zcm on 2018/9/21
 */
public interface RouterUrl {
    interface Main {
        String main_home = "/main/home";
        String main_datePicker = "/main/datePicker";
        String main_web = "/main/web";
    }

    interface LoginRegister {
        String login_login = "/login_register/login";
        String login_forget = "/login_register/forget";
    }



}
