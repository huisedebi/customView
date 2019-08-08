package com.xinyartech.baselibrary.router;

/**
 * 作者: created by zcm on 2018/9/21
 * 修改: modified by zcm on 2018/9/21
 */
public interface RouterUrl {
    interface Main {
        String main_home = "/main/home";
        String main_welcome = "/main/welcome";
        String main_web = "/main/web";
        String main_search = "/main/search";
    }

    interface LoginRegister {
        String login_login = "/login_register/login";
        String login_forget = "/login_register/forget";
    }

    interface Set {
        String set_set = "/set/set";
        String set_editLoginPsw = "/set/editLoginPsw";
        String set_editPhone = "/set/editPhone";
    }

    interface Signing {
        String Signing_type = "/Signing/type";
        String Signing_step = "/Signing/step";
        String Signing_info = "/Signing/info";
        String Signing_ali = "/Signing/ali";
        String Signing_selectBank = "/Signing/selectBank";
        String Signing_searchZhiHang = "/Signing/searchZhiHang";
        String Signing_imageSingle = "/Signing/imageSingle";
        String Signing_imageIdCard = "/Signing/imageIdCard";
        String Signing_headAndScene = "/Signing/headAndScene";
        String Signing_preview = "/Signing/preview";
    }

    interface Shop {
        String shop_detail_false = "/shop/detail_false";
        String company_add = "/company/add";
        String shop_detail_true = "/shop/detail_true";
        String shop_shopInfo = "/shop/shopInfo";
        String shop_addUser = "/shop/addUser";
        String shop_userDetail = "/shop/userDetail";
        String shop_data = "/shop/data";
        String shop_add = "/shop/add";
    }

    interface SubordinateAgents {
        String Subordinate_Agents = "/Subordinate/Agents";
        String Subordinate_AddAgents = "/Subordinate/AddAgents";
    }

}
