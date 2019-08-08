package com.xinyartech.baselibrary.constant;


/**
 * Created by zjb on 2016/6/12.
 */
public class Constant {
//    public static String HOST_X = "https://paypracticeapi.xinyartech.com/";
    public static String HOST_X = "https://payagentapi.xinyartech.com/";
    public static String HOST = HOST_X+"api/apiForPlatform";
    /**
     * 判断数据是否有改变
     */
    public static int changeControl = 2017;
    public static String source = "";
    public static boolean isLogin = false;
    public static int MainActivityAlive = 0;
    /**
     * 微信appid
     */
    public static String WXAPPID = "wx6346bca675f82935";
    public static String WXSHANGHUHAO = "1520289091";
    /**
     * 微信scrent
     */
    public static String WXSCRENT = "ac8588e12c428fa7d0c6f5c5f3782b44";
    /**
     * qq
     */
    public static String QQ_ID = "1106239952";
    public static String QQ_KEY = "HcA9s2rpKkLO2M5w";
    /**
     * 阿里云推送
     */
    public static String ALI_APP_KEY = "27698905";
    public static String ALI_APP_SECRET = "061ab537a0fa2cbc8c2360f307f7579a";
    /**
     * 更新服务器配置
     */
    public static final String UPDATE_SERVER_URL = "http://version.app.xinyartech.com/apkStorage/getAppInfo";

    public static class Url {
        public static final String LOGIN = HOST + "/auth/login";
        public static final String LOGOUT = HOST + "/auth/logout";
        public static final String RESETSELFPASSWORD = HOST + "/auth/resetSelfPassword";
        public static final String GETSMSFORCHANGEPWD = HOST + "/sms/getSmsForChangePwd";
        public static final String RESETPASSWORDBYSMSCODE = HOST + "/auth/resetPasswordBySmsCode";
        public static final String SLIDE_IMG = HOST + "/image/slide_img";
        public static final String RESETSELFPHONEBYSMSCODE = HOST + "/auth/resetSelfPhoneBySmsCode";
        public static final String GETSMSFORCHANGEPHONE = HOST + "/sms/getSmsForChangePhone";
        public static final String INDEXWITHSIGNED = HOST + "/company/indexWithSigned";
        public static final String INDEXWITHOUTSIGNED = HOST + "/company/indexWithoutSigned";
        public static final String SEARCHCOMPANY = HOST + "/company/searchCompany";
        public static final String REPORTMONTHLIST = HOST + "/company/reportMonthList";
        public static final String REPORTPLATFORMDATA = HOST + "/report/reportPlatformData";
        public static final String REPORTCOMPANYDATA = HOST + "/report/reportCompanyData";
        public static final String DETAIL = HOST + "/company/detail/";
        public static final String SHOPLISTBYCOMPANY = HOST + "/company/shop/shopListByCompany/";
        public static final String CASHIERLISTBYSHOP = HOST + "/company/cashier/cashierListByShop/";
        public static final String ADDIMG = HOST + "/platform/addImg";
        public static final String STORE = HOST + "/company/store";
        public static final String UPLOAD = HOST + "/image/upload";
        public static final String REPORTPLATFORMDATALIST = HOST + "/report/reportPlatformDataList";
        public static final String REPORTCOMPANYDATALIST = HOST + "/report/reportCompanyDataList";
        public static final String ADD_CASHIER = HOST + "/company/cashier/store/";
        public static final String CASHIER_DETAIL = HOST + "/company/cashier/show/";
        public static final String SEMOOR_INDEX = HOST + "/semoor/index/";
        public static final String GETIMG = HOST + "/platform/getImg";
        public static final String CASHIER_UPDATE = HOST + "/company/cashier/update/";
        public static final String SHOP_DETAIL = HOST + "/company/shop/show/";
        public static final String SIGN_INDEX = HOST + "/sign/index/";
        public static final String SIGN_MERCHANTINFO = HOST + "/sign/merchantInfo/";
        public static final String SIGN_BASICINFO = HOST + "/sign/basicInfo/";
        public static final String SIGN_GETMERCHANTINFO = HOST + "/sign/getMerchantInfo/";
        public static final String SIGN_GETBASICINFO = HOST + "/sign/getBasicInfo/";
        public static final String SIGN_REGINFO = HOST + "/sign/regInfo/";
        public static final String GETREGINFO = HOST + "/sign/getRegInfo/";
        public static final String GETBANKINFO = HOST + "/sign/getBankInfo/";
        public static final String XINGYE_BANK = HOST + "/sign/xingye/bank";
        public static final String ALLCITY = HOST + "/sign/xingye/allCity";
        public static final String BANKUNION = HOST + "/sign/xingye/bankunion/";
        public static final String SIGN_BANKINFO = HOST + "/sign/bankInfo/";
        public static final String SIGN_GETIMGINFO = HOST + "/sign/getImgInfo/";
        public static final String SIGN_IMGINFO = HOST + "/sign/imgInfo/";
        public static final String SIGN_GETFIELDFORCHANNELCLIENT = HOST + "/sign/getFieldForChannelClient/";
        public static final String SIGN_SIGNINFO = HOST + "/sign/signInfo/";
        public static final String SIGN_PREVIEW = HOST + "/sign/preview/";
        public static final String SIGN_SUBMIT = HOST + "/sign/submit/";
        public static final String SIGN_ALIPAYQRCODE = HOST + "/sign/aliPayQrCode/";
        public static final String SEMOOR_UPDATE= HOST + "/semoor/update/";
        public static final String SEMOOR_DELETE= HOST + "/semoor/delete/";
        public static final String SEMOOR_STORE= HOST + "/semoor/store/";
        public static final String PLATFORM_NEXTPLATFORMLIST= HOST + "/platform/nextPlatformList";
        public static final String PLATFORM_ADDPLATFORM= HOST + "/platform/addPlatform";
        public static final String PLATFORM_DISABLEPLATFORM= HOST + "/platform/disablePlatform";
        public static final String PLATFORM_PLATFORMDETAIL= HOST + "/platform/platformDetail/";
        public static final String COMPANY_SHOP_STORE= HOST + "/company/shop/store/";
    }

    public static class IntentKey {
        public static final String TYPE = "type";
        public static final String TYPE01 = "type01";
        public static final String POSITION = "position";
        public static final String ID = "id";
        public static final String BEAN = "bean";
        public static final String BEAN1 = "bean1";
        public static final String BEAN2 = "bean2";
        public static final String BEAN3 = "bean3";
        public static final String BEAN4 = "bean4";
        public static final String VALUE = "value";
        public static final String URL = "url";
        public static final String TITLE = "title";
        public static final String LIST = "List";
        public static final String LIST01 = "List01";
        public static final String BIG_IMG_POSITION = "bigImgPosition";
        public static final String BIG_IMG = "bigImg";
        public static final String IS_DEL = "IS_DEL";
        public static final String CanNotUpload = "CanNotUpload";
    }

    public static class Acache {
        public static final String APP = "app";
        public static final String USER_INFO = "userInfo";
        public static final String DEVICE_ID = "DEVICE_ID";
        public static final String USER_NAME = "USER_NAME";
        public static final String USER_PSW = "USER_PSW";
        public static final String shopNum = "shopNum";
        public static final String appInfo = "appInfo";
    }

    public static class EventKey {
        public static final String TEST = "TEST";
        public static final String Tab02 = "Tab02";
        public static final String signedTop = "signedTop";
        public static final String unSignedTop = "unSignedTop";
        public static final String dataRank = "dataRank";
        public static final String addShopUser = "addShopUser";
        public static final String addCompany = "addCompany";
        public static final String delImg = "delImg";
        public static final String signStep = "signStep";
        public static final String signInfo02 = "signInfo02";
        public static final String signInfo03 = "signInfo03";
        public static final String signInfo04 = "signInfo04";
        public static final String signInfo05Commit = "signInfo05Commit";
        public static final String signCommit = "signCommit";
        public static final String signSuccessZhongTou = "signSuccessZhongTou";
        public static final String aliPaySign = "aliPaySign";
        public static final String addSubordinateAgent = "addSubordinateAgent";
        public static final String addShop = "addShop";
    }

    public static class RequestResultCode {
        public static final int OK = 2019;
        public static final int UPLOAD_IDCARD = 2020;
        public static final int UPLOAD_IMG = 2021;
        public static final int CHOOSE_BANK = 2022;
        public static final int CHOOSE_ZHIHANG = 2023;
        public static final int UPLOAD_IMG_JI_GOU = 2024;
        public static final int UPLOAD_IMG_Kai_HU = 2025;
        public static final int UPLOAD_IMG_HEAD_SENCE = 2026;
        public static final int UPLOAD_IMG_DESK = 2027;
        public static final int UPLOAD_IMG_BANK = 2028;
        public static final int UPLOAD_IMG_XIE_YI= 2029;
        public static final int UPLOAD_IMG_Other= 2030;
        public static final int UPLOAD_IDCARD_RZ = 2031;
        public static final int UPLOAD_IMG_RUZHANG= 2032;
        public static final int UPLOAD_IMG_Kai_HU_bank = 2033;
        public static final int UPLOAD_IMG_Hang_Ye = 2034;

    }

}
