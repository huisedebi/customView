package com.xinyartech.baselibrary.bean;

/**
 * Created by zhangjiebo on 2019/4/12.
 *
 * @author ZhangJieBo
 */
public class Login {

    /**
     * company_name : 信雅测试商户
     * expires_in : 1209600
     * merchant_sn : 170705950000003
     * status : ok
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vcGF5dGVzdGFwaS54aW55YXJ0ZWNoLmNvbS9hcGkvYXV0aC9sb2dpbiIsImlhdCI6MTU1NTQ5MTk0OCwiZXhwIjoxNTU2NzAxNTQ4LCJuYmYiOjE1NTU0OTE5NDgsImp0aSI6Imt4MW5MOWlRQmZlY05HbzciLCJzdWIiOjEwMDExLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.wuZNBDbPd-_d0PZUUEjfAPj8y6RLdgXR3tvj6MoaFAA
     * user_info : {"avatar":"","mobile":"15959266702","user_email":"","user_nickname":""}
     */

    private String company_name;
    private String expires_in;
    private String merchant_sn;
    private String contact_mobile;
    private String status;
    private String token;
    private UserInfoBean user_info;

    public String getContact_mobile() {
        return contact_mobile;
    }

    public void setContact_mobile(String contact_mobile) {
        this.contact_mobile = contact_mobile;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getMerchant_sn() {
        return merchant_sn;
    }

    public void setMerchant_sn(String merchant_sn) {
        this.merchant_sn = merchant_sn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public static class UserInfoBean {
        /**
         * avatar :
         * mobile : 15959266702
         * user_email :
         * user_nickname :
         */

        private String avatar;
        private String mobile;
        private String user_email;
        private String user_nickname;
        private String user_id;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }
    }
}
