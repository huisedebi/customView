package com.xinyartech.baselibrary.router;

import android.content.Context;
import androidx.core.app.ActivityOptionsCompat;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

import com.xinyartech.baselibrary.R;

/**
 * 作者: created by zcm on 2018/9/21
 * 修改: modified by zcm on 2018/9/21
 */
public class RouterUtil {

    public static Postcard getPostcard(String url){
        Postcard postcard = ARouter.getInstance().build(url);
        return postcard;
    }
    public static Postcard getPostcardWithTransition(Context context, String url){
        Postcard postcard = ARouter.getInstance().build(url).withOptionsCompat(ActivityOptionsCompat.makeCustomAnimation(context, R.anim.slide_in_right,R.anim.slide_out_left));
        return postcard;
    }
}
