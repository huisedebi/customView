package com.xinyartech.baselibrary.application;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyerFeedbackManager;
import com.xinyartech.baselibrary.bean.AppInfoBean;
import com.xinyartech.baselibrary.bean.ConfigParam;
import com.xinyartech.baselibrary.constant.Constant;
import com.xinyartech.baselibrary.swipeback.SimpleSwipeBackDelegate;
import com.xinyartech.baselibrary.swipeback.SwipeBackHelper;
import com.xinyartech.baselibrary.swipeback.SwipeOptions;
import com.xinyartech.baselibrary.utils.ACacheX;
import com.xinyartech.baselibrary.utils.ApiClient;
import com.xinyartech.baselibrary.utils.LogUtil;
import com.xinyartech.baselibrary.utils.gson.GenericType;
import com.xinyartech.baselibrary.utils.gson.GsonUtils;
import com.xinyartech.baselibrary.utils.gson.HttpResult;
import com.xinyartech.baselibrary.utils.gson.HttpResultGsonDeserializer;
import com.xinyartech.baselibrary.utils.gson.IntegerGsonDeserializer;
import com.xinyartech.baselibrary.valueFinal.ConfigParamValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 初始化
 *
 * @author Administrator
 * @date 2015/12/31
 */
public class MyApplication extends MultiDexApplication {
    private static Context context;
    private List<Activity> activityList = new LinkedList<Activity>();
    private static MyApplication instance;
    private Activity activity;

    private ActivityLifecycleCallbacks lifecycleCallback = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (!"com.tbruyelle.rxpermissions.ShadowActivity".equals(activity.getClass().getName())) {
                MyApplication.this.activity = activity;
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {
            if (!"com.tbruyelle.rxpermissions.ShadowActivity".equals(activity.getClass().getName())) {
                MyApplication.this.activity = activity;
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            if (!"com.tbruyelle.rxpermissions.ShadowActivity".equals(activity.getClass().getName())) {
                MyApplication.this.activity = activity;
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

    protected static class SingletonHolder {
        public static MyApplication INSTANCE;
    }

    public MyApplication() {
        registerActivityLifecycleCallbacks(lifecycleCallback);
        SingletonHolder.INSTANCE = this;
    }


    @Override
    public void onCreate() {
        context = this.getApplicationContext();
        super.onCreate();
        GsonUtils.setSingletonInstance(
                new GsonBuilder()
                        .registerTypeAdapter(HttpResult.class, new HttpResultGsonDeserializer())
                        .registerTypeAdapter(Integer.class, new IntegerGsonDeserializer())
                        .registerTypeAdapter(int.class, new IntegerGsonDeserializer())
                        .create());
        //取出配置
        AppInfoBean appInfoBean = (AppInfoBean) ACacheX.getAsObject(this, Constant.Acache.appInfo, Constant.Acache.appInfo, AppInfoBean.class);
        if (appInfoBean != null) {
            String params = appInfoBean.getData().getProject().getParams();
            if (!TextUtils.isEmpty(params)) {
                initConfig(params);
            }
        }

        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        OkGo.getInstance().init(this);
        //启动 Pgyer 检测 Crash 功能；
        PgyCrashManager.register();
        //采用摇一摇弹出 Activity 方式
        new PgyerFeedbackManager.PgyerFeedbackBuilder()
                .setDisplayType(PgyerFeedbackManager.TYPE.DIALOG_TYPE)
                .builder()
                .register();

        List<String> exclude = new ArrayList<>();
//        exclude.add(CaptureActivity.class.getSimpleName());
        SwipeOptions options = SwipeOptions.builder()
                .exclude(exclude) // 排除不需要侧滑的类
                // 以下默认值
//            .isWeChatStyle(true)  //  是否是微信滑动返回样式
//            .isTrackingLeftEdge(true)     // 是否仅仅跟踪左侧边缘的滑动返回
//            .isShadowAlphaGradient(true)     // 阴影区域的透明度是否根据滑动的距离渐变
//            .isNavigationBarOverlap(false)     // 底部导航条是否悬浮在内容上
//            .isNeedShowShadow(true) // 否显示滑动返回的阴影效果
//            .shadowResId() // 阴影资源 id
                .build();
        SwipeBackHelper.init(this, null, options, new SimpleSwipeBackDelegate() {
            @Override
            public void onSwipeBackLayoutExecuted(Activity activity) {

            }

//            @Override
//            public void onSwipeBackLayoutCancel() {
//                super.onSwipeBackLayoutCancel();
//            }
//
//            @Override
//            public void onSwipeBackLayoutSlide(float slideOffset) {
//                super.onSwipeBackLayoutSlide(slideOffset);
//            }
        });
    }

    public void initConfig(String params) {
        LogUtil.LogShitou("MyApplication--initConfig", "" + params);
        List<ConfigParam> configParamList = GsonUtils.parseToGenericObject(params, new GenericType<List<ConfigParam>>() {
        });
        for (int i = 0; i < configParamList.size(); i++) {
            if (TextUtils.equals(configParamList.get(i).getParamKey(), ConfigParamValue.HOST_X)) {
                Constant.HOST_X = configParamList.get(i).getParamValue();
                Constant.HOST = Constant.HOST_X + "api/apiForPlatform";
                break;
            }
        }
        for (int i = 0; i < configParamList.size(); i++) {
            if (TextUtils.equals(configParamList.get(i).getParamKey(), ConfigParamValue.ALI_APP_KEY)) {
                Constant.ALI_APP_KEY = configParamList.get(i).getParamValue();
                break;
            }
        }
        for (int i = 0; i < configParamList.size(); i++) {
            if (TextUtils.equals(configParamList.get(i).getParamKey(), ConfigParamValue.ALI_APP_SECRET)) {
                Constant.ALI_APP_SECRET = configParamList.get(i).getParamValue();
                break;
            }
        }
        for (int i = 0; i < configParamList.size(); i++) {
            if (TextUtils.equals(configParamList.get(i).getParamKey(), ConfigParamValue.md5Key)) {
                ApiClient.md5Key = configParamList.get(i).getParamValue();
                break;
            }
        }
    }


    public static Context getContext() {
        return context;
    }

    /**
     * 单例模式中获取唯一的MyApplication实例
     */
    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    /**
     * 添加Activity到容器中
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 遍历所有Activity并finish
     */
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    @Override
    public void onLowMemory() {
        System.gc();
        System.runFinalization();
        System.gc();
        super.onLowMemory();
    }

    public Activity getCurrentActivity() {
        return activity;
    }

    public static MyApplication get() {
        return SingletonHolder.INSTANCE;
    }


}
