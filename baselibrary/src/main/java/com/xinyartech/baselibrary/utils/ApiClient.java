package com.xinyartech.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xinyartech.baselibrary.bean.Login;
import com.xinyartech.baselibrary.bean.OkObject;
import com.xinyartech.baselibrary.constant.Constant;


/**
 * Created by Administrator on 2017/8/27.
 */
public class ApiClient {
    public final static int OK = 0;
    public final static int OUT = 401;
    //    public final static int FAIL = 0;
    public static String md5Key = "xinya@paymerchant!app@android";

    public interface CallBack {
        void onSuccess(String s);

        void onError();
    }

    public interface UpLoadCallBack {
        void onSuccess(String s);

        void onError();

        void uploadProgress(float progress);
    }

    public static void post(Context context, final OkObject okObject, final CallBack callBack) {
        LogUtil.LogShitou("ApiClient--post", "" + okObject.getUrl());
        LogUtil.LogShitou("ApiClient--发送", "" + okObject.getJson());
        HttpHeaders httpHeaders = okObject.getHttpHeaders();
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
        }
        Login login = (Login) ACacheX.getAsObject(context, Constant.Acache.APP, Constant.Acache.USER_INFO, Login.class);
        if (login != null) {
            httpHeaders.put("Authorization", "Bearer " + login.getToken());
            LogUtil.LogShitou("ApiClient--post Authorization", "" + "Bearer " + login.getToken());
        }
        HashMap<String, String> params = okObject.getParams();
        params.put("device_type", "2");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        List<String> list = new ArrayList<>(Arrays.asList(chars));
        Collections.shuffle(list);
        String random16chars = "";
        for (int i = 0; i < 16; i++) {
            random16chars = random16chars + list.get(i);
        }

        params.put("nonce", random16chars);
        String GuestId = ACacheX.getAsString(context, Constant.Acache.APP, Constant.Acache.DEVICE_ID);
        params.put("uuid", GuestId);
        params.put("app_version", VersionUtils.getCurrVersionName(context));
        params.put("device_name", Build.DEVICE);
        params.put("device_version", String.valueOf(Build.VERSION.SDK_INT));

        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(params.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                // 指定排序器按照降序排列
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        String md5Before = "";
        //排序后
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            if (i == 0) {
                md5Before = id;
            } else {
                md5Before = md5Before + "&" + id;
            }
        }
        md5Before = md5Before + "&key=" + md5Key;
        LogUtil.LogShitou("ApiClient--postmd5Before", "" + md5Before);
        String md5 = MD5Util.getMD5(md5Before).toUpperCase();
        params.put("sign", md5);
        JSONObject jsonObject = new JSONObject(params);
        LogUtil.LogShitou("ApiClient--getJson", "" + jsonObject.toString());

        OkGo.<String>post(okObject.getUrl())
                .tag(context)
                .headers(httpHeaders)
                .upJson(jsonObject.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                        LogUtil.LogShitou("ApiClient--onErrorbody", "" + okObject.getUrl());
                        LogUtil.LogShitou("ApiClient--onErrorcode", "" + response.code());
                        LogUtil.LogShitou("ApiClient--onErrormessage", "" + response.message());
                        LogUtil.LogShitou("ApiClient--onErrorgetException", "" + response.getException().toString());
                    }
                });
    }

    public static void put(Context context, final OkObject okObject, final CallBack callBack) {
        LogUtil.LogShitou("ApiClient--post", "" + okObject.getUrl());
        LogUtil.LogShitou("ApiClient--发送", "" + okObject.getJson());
        HttpHeaders httpHeaders = okObject.getHttpHeaders();
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
        }
        Login login = (Login) ACacheX.getAsObject(context, Constant.Acache.APP, Constant.Acache.USER_INFO, Login.class);
        if (login != null) {
            httpHeaders.put("Authorization", "Bearer " + login.getToken());
        }
        HashMap<String, String> params = okObject.getParams();
        params.put("device_type", "2");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        List<String> list = new ArrayList<>(Arrays.asList(chars));
        Collections.shuffle(list);
        String random16chars = "";
        for (int i = 0; i < 16; i++) {
            random16chars = random16chars + list.get(i);
        }
        LogUtil.LogShitou("ApiClient--post", "" + random16chars);

        params.put("nonce", random16chars);
        String GuestId = ACacheX.getAsString(context, Constant.Acache.APP, Constant.Acache.DEVICE_ID);
        params.put("uuid", GuestId);
        params.put("app_version", VersionUtils.getCurrVersionName(context));
        params.put("device_name", Build.DEVICE);
        params.put("device_version", String.valueOf(Build.VERSION.SDK_INT));

        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(params.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                // 指定排序器按照降序排列
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        String md5Before = "";
        //排序后
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            if (i == 0) {
                md5Before = id;
            } else {
                md5Before = md5Before + "&" + id;
            }
        }
        md5Before = md5Before + "&key=" + md5Key;
        LogUtil.LogShitou("ApiClient--postmd5Before", "" + md5Before);
        String md5 = MD5Util.getMD5(md5Before).toUpperCase();
        params.put("sign", md5);
        JSONObject jsonObject = new JSONObject(params);
        LogUtil.LogShitou("ApiClient--getJson", "" + jsonObject.toString());

        OkGo.<String>put(okObject.getUrl())
                .tag(context)
                .headers(httpHeaders)
                .upJson(jsonObject.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                        LogUtil.LogShitou("ApiClient--onErrorbody", "" + okObject.getUrl());
                        LogUtil.LogShitou("ApiClient--onErrorcode", "" + response.code());
                        LogUtil.LogShitou("ApiClient--onErrormessage", "" + response.message());
                        LogUtil.LogShitou("ApiClient--onErrorgetException", "" + response.getException().toString());
                    }
                });
    }


    public static void get(Context context, OkObject okObject, final CallBack callBack) {
        LogUtil.LogShitou("ApiClient--get", "" + okObject.getUrl());
        LogUtil.LogShitou("ApiClient--发送", "" + okObject.getJson());
        HttpHeaders httpHeaders = okObject.getHttpHeaders();
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
        }
        Login login = (Login) ACacheX.getAsObject(context, Constant.Acache.APP, Constant.Acache.USER_INFO, Login.class);
        if (login != null) {
            httpHeaders.put("Authorization", "Bearer " + login.getToken());
            LogUtil.LogShitou("ApiClient--get Authorization", "" + "Bearer " + login.getToken());
        }
        HashMap<String, String> params = okObject.getParams();
        params.put("device_type", "2");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        List<String> list = new ArrayList<>(Arrays.asList(chars));
        Collections.shuffle(list);
        String random16chars = "";
        for (int i = 0; i < 16; i++) {
            random16chars = random16chars + list.get(i);
        }

        params.put("nonce", random16chars);
        String GuestId = ACacheX.getAsString(context, Constant.Acache.APP, Constant.Acache.DEVICE_ID);
        params.put("uuid", GuestId);
        params.put("app_version", VersionUtils.getCurrVersionName(context));
        params.put("device_name", Build.DEVICE);
        params.put("device_version", String.valueOf(Build.VERSION.SDK_INT));

        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(params.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                // 指定排序器按照降序排列
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        String md5Before = "";
        //排序后
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            if (i == 0) {
                md5Before = id;
            } else {
                md5Before = md5Before + "&" + id;
            }
        }
        String url = okObject.getUrl();
        url = url + "?" + md5Before;
        md5Before = md5Before + "&key=" + md5Key;
        LogUtil.LogShitou("ApiClient--getmd5Before", "" + md5Before);
        String md5 = MD5Util.getMD5(md5Before).toUpperCase();
        params.put("sign", md5);
        LogUtil.LogShitou("ApiClient--getmd5", "" + md5);
        url = url + "&sign=" + md5;
        LogUtil.LogShitou("ApiClient--get", "" + url);
        String finalUrl = url;
        OkGo.<String>get(url)
                .headers(httpHeaders)
                .tag(context)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                        LogUtil.LogShitou("ApiClient--onError", "" + finalUrl);
                        LogUtil.LogShitou("ApiClient--onErrorcode", "" + response.code());
                        LogUtil.LogShitou("ApiClient--onErrormessage", "" + response.message());
                        LogUtil.LogShitou("ApiClient--onErrorgetException", "" + response.getException().toString());
                    }
                });

    }

    /**
     * bean必须序列化
     *
     * @param context
     * @param okObject
     * @param callBack
     */
    public static void getAcache(Context context, OkObject okObject, final CallBack callBack) {
        LogUtil.LogShitou("ApiClient--get", "" + okObject.getUrl());
        LogUtil.LogShitou("ApiClient--发送", "" + okObject.getJson());
        HttpHeaders httpHeaders = okObject.getHttpHeaders();
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
        }
        Login login = (Login) ACacheX.getAsObject(context, Constant.Acache.APP, Constant.Acache.USER_INFO, Login.class);
        if (login != null) {
            httpHeaders.put("Authorization", "Bearer " + login.getToken());
            LogUtil.LogShitou("ApiClient--get Authorization", "" + "Bearer " + login.getToken());
        }
        HashMap<String, String> params = okObject.getParams();
        params.put("device_type", "2");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        List<String> list = new ArrayList<>(Arrays.asList(chars));
        Collections.shuffle(list);
        String random16chars = "";
        for (int i = 0; i < 16; i++) {
            random16chars = random16chars + list.get(i);
        }

        params.put("nonce", random16chars);
        String GuestId = ACacheX.getAsString(context, Constant.Acache.APP, Constant.Acache.DEVICE_ID);
        params.put("uuid", GuestId);
        params.put("app_version", VersionUtils.getCurrVersionName(context));
        params.put("device_name", Build.DEVICE);
        params.put("device_version", String.valueOf(Build.VERSION.SDK_INT));

        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(params.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                // 指定排序器按照降序排列
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        String md5Before = "";
        //排序后
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            if (i == 0) {
                md5Before = id;
            } else {
                md5Before = md5Before + "&" + id;
            }
        }
        String url = okObject.getUrl();
        url = url + "?" + md5Before;
        md5Before = md5Before + "&key=" + md5Key;
        LogUtil.LogShitou("ApiClient--getmd5Before", "" + md5Before);
        String md5 = MD5Util.getMD5(md5Before).toUpperCase();
        params.put("sign", md5);
        LogUtil.LogShitou("ApiClient--getmd5", "" + md5);
        url = url + "&sign=" + md5;
        LogUtil.LogShitou("ApiClient--get", "" + url);
        String finalUrl = url;

        OkGo.<String>get(url)
                .headers(httpHeaders)
                .cacheKey(okObject.getCacheKey())
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .cacheTime(60L * 60L * 1000L * 24L)
                .tag(context)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        LogUtil.LogShitou("ApiClient--onCacheSuccess", "取缓存");
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                        LogUtil.LogShitou("ApiClient--onError", "" + finalUrl);
                        LogUtil.LogShitou("ApiClient--onErrorcode", "" + response.code());
                        LogUtil.LogShitou("ApiClient--onErrormessage", "" + response.message());
                        LogUtil.LogShitou("ApiClient--onErrorgetException", "" + response.getException().toString());
                    }
                });
    }

    public static void postNormal(Context context, final OkObject okObject, final CallBack callBack) {
        LogUtil.LogShitou("ApiClient--post", "" + okObject.getUrl());
        LogUtil.LogShitou("ApiClient--发送", "" + okObject.getJson());
        OkGo.<String>post(okObject.getUrl())
                .tag(context)
                .params(okObject.getParams())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                        LogUtil.LogShitou("ApiClient--onErrorbody", "" + okObject.getUrl());
                        LogUtil.LogShitou("ApiClient--onErrorcode", "" + response.code());
                        LogUtil.LogShitou("ApiClient--onErrormessage", "" + response.message());
                        LogUtil.LogShitou("ApiClient--onErrorgetException", "" + response.getException().toString());
                    }
                });
    }

    public static void postJson(Context context, String url, String json, final CallBack callBack) {
        LogUtil.LogShitou("ApiClient--发送", "" + json);
        OkGo.<String>post(url)
                .tag(context)
                .upJson(json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                    }
                });
    }

    /**
     * des： 上传文件
     * author： ZhangJieBo
     * date： 2017/11/8 0008 上午 11:40
     */
    public static void upFiles(Context context, OkObject okObject, List<File> files, final UpLoadCallBack callBack) {
        LogUtil.LogShitou("ApiClient--post", "" + okObject.getUrl());
        LogUtil.LogShitou("ApiClient--发送", "" + okObject.getJson());
        HttpHeaders httpHeaders = okObject.getHttpHeaders();
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
        }
        Login login = (Login) ACacheX.getAsObject(context, Constant.Acache.APP, Constant.Acache.USER_INFO, Login.class);
        if (login != null) {
            httpHeaders.put("Authorization", "Bearer " + login.getToken());
            LogUtil.LogShitou("ApiClient--upFile Authorization", "" + "Bearer " + login.getToken());
        }
        HashMap<String, String> params = okObject.getParams();
        params.put("device_type", "2");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        List<String> list = new ArrayList<>(Arrays.asList(chars));
        Collections.shuffle(list);
        String random16chars = "";
        for (int i = 0; i < 16; i++) {
            random16chars = random16chars + list.get(i);
        }

        params.put("nonce", random16chars);
        String GuestId = ACacheX.getAsString(context, Constant.Acache.APP, Constant.Acache.DEVICE_ID);
        params.put("uuid", GuestId);
        params.put("app_version", VersionUtils.getCurrVersionName(context));
        params.put("device_name", Build.DEVICE);
        params.put("device_version", String.valueOf(Build.VERSION.SDK_INT));

        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(params.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                // 指定排序器按照降序排列
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        String md5Before = "";
        //排序后
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            if (i == 0) {
                md5Before = id;
            } else {
                md5Before = md5Before + "&" + id;
            }
        }
        md5Before = md5Before + "&key=" + md5Key;
        LogUtil.LogShitou("ApiClient--postmd5Before", "" + md5Before);
        String md5 = MD5Util.getMD5(md5Before).toUpperCase();
        params.put("sign", md5);
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(okObject.getUrl())
                .tag(context)
                .headers(httpHeaders)
//                .upJson(jsonObject.toString())
                .params(params)
                .addFileParams("img_url", files)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                        LogUtil.LogShitou("ApiClient--onErrorbody", "" + okObject.getUrl());
                        LogUtil.LogShitou("ApiClient--onErrorcode", "" + response.code());
                        LogUtil.LogShitou("ApiClient--onErrormessage", "" + response.message());
                        LogUtil.LogShitou("ApiClient--onErrorgetException", "" + response.getException().toString());
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        callBack.uploadProgress(progress.fraction * 100);
                    }
                });
    }

    /**
     * des： 上传文件
     * author： ZhangJieBo
     * date： 2017/11/8 0008 上午 11:40
     */
    public static void upFile(Context context, OkObject okObject, File files, final UpLoadCallBack callBack) {
        LogUtil.LogShitou("ApiClient--post", "" + okObject.getUrl());
        LogUtil.LogShitou("ApiClient--发送", "" + okObject.getJson());
        HttpHeaders httpHeaders = okObject.getHttpHeaders();
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
        }
        Login login = (Login) ACacheX.getAsObject(context, Constant.Acache.APP, Constant.Acache.USER_INFO, Login.class);
        if (login != null) {
            httpHeaders.put("Authorization", "Bearer " + login.getToken());
            LogUtil.LogShitou("ApiClient--upFile Authorization", "" + "Bearer " + login.getToken());
        }
        HashMap<String, String> params = okObject.getParams();
        params.put("device_type", "2");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        List<String> list = new ArrayList<>(Arrays.asList(chars));
        Collections.shuffle(list);
        String random16chars = "";
        for (int i = 0; i < 16; i++) {
            random16chars = random16chars + list.get(i);
        }

        params.put("nonce", random16chars);
        String GuestId = ACacheX.getAsString(context, Constant.Acache.APP, Constant.Acache.DEVICE_ID);
        params.put("uuid", GuestId);
        params.put("app_version", VersionUtils.getCurrVersionName(context));
        params.put("device_name", Build.DEVICE);
        params.put("device_version", String.valueOf(Build.VERSION.SDK_INT));

        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(params.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                // 指定排序器按照降序排列
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        String md5Before = "";
        //排序后
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            if (i == 0) {
                md5Before = id;
            } else {
                md5Before = md5Before + "&" + id;
            }
        }
        md5Before = md5Before + "&key=" + md5Key;
        LogUtil.LogShitou("ApiClient--postmd5Before", "" + md5Before);
        String md5 = MD5Util.getMD5(md5Before).toUpperCase();
        params.put("sign", md5);
        JSONObject jsonObject = new JSONObject(params);
        LogUtil.LogShitou("ApiClient--getJson", "" + jsonObject.toString());
        OkGo.<String>post(okObject.getUrl())
                .tag(context)
                .headers(httpHeaders)
                .params(params)
                .params("img_url", files)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callBack.onSuccess(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callBack.onError();
                        LogUtil.LogShitou("ApiClient--onErrorbody", "" + response.body());
                        LogUtil.LogShitou("ApiClient--onErrorcode", "" + response.code());
                        LogUtil.LogShitou("ApiClient--onErrormessage", "" + response.message());
                        LogUtil.LogShitou("ApiClient--onErrorgetException", "" + response.getException().toString());
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        callBack.uploadProgress(progress.fraction * 100);
                    }
                });
    }

    public static void cancleAll() {
        OkGo.getInstance().cancelAll();
    }

    public static void cancleThisPage(Context context) {
        OkGo.getInstance().cancelTag(context);
    }

    /**
     * 下载文件
     *
     * @param context
     * @param url
     */
    public static void downLoadFile(final Context context, String url, String dir, String fileName, final CallBack callBack) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                OkGo.<File>get(url)
                        .tag(context)
                        .execute(new FileCallback(dir, fileName) {
                            @Override
                            public void onSuccess(Response<File> response) {
                                callBack.onSuccess(response.body().toString());
                            }

                            @Override
                            public void onError(Response<File> response) {
                                super.onError(response);
                                LogUtil.LogShitou("ApiClient--onErrorbody", "" + response.body());
                                LogUtil.LogShitou("ApiClient--onErrorcode", "" + response.code());
                                LogUtil.LogShitou("ApiClient--onErrormessage", "" + response.message());
                                LogUtil.LogShitou("ApiClient--onErrorgetException", "" + response.getException().toString());
                                callBack.onError();
                            }
                        });
            } catch (Exception e) {
            }
        } else {
            Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 下载文件
     *
     * @param context
     * @param url
     */
    public static void downLoadImg(final Context context, String url, String dir, final String fileName, final CallBack callBack) throws Exception {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            final String filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + dir;
            OkGo.<File>get(url)
                    .tag(context)
                    .execute(new FileCallback(filePath, fileName) {
                        @Override
                        public void onSuccess(Response<File> response) {
                            LogUtil.LogShitou("ApiClient--onSuccess", "" + response.body().toString());
                            callBack.onSuccess(response.body().toString());
                            // 最后通知图库更新
                            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                    Uri.fromFile(new File(filePath, fileName))));
                        }

                        @Override
                        public void onError(Response<File> response) {
                            super.onError(response);
                            LogUtil.LogShitou("ApiClient--onErrorbody", "" + response.body());
                            LogUtil.LogShitou("ApiClient--onErrorcode", "" + response.code());
                            LogUtil.LogShitou("ApiClient--onErrormessage", "" + response.message());
                            LogUtil.LogShitou("ApiClient--onErrorgetException", "" + response.getException().toString());
                            callBack.onError();
                        }
                    });
        } else {
            Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }

    }

}
