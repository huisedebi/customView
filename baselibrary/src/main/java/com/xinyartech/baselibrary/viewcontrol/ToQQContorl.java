package com.xinyartech.baselibrary.viewcontrol;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.xinyartech.baselibrary.constant.Constant;
import com.xinyartech.baselibrary.router.RouterUrl;
import com.xinyartech.baselibrary.router.RouterUtil;
import com.xinyartech.baselibrary.utils.ToastUtils;
import com.xinyartech.baselibrary.utils.Tools;


/**
 * Created by zhangjiebo on 2018/11/12/012.
 *
 * @author ZhangJieBo
 */
public class ToQQContorl {
    public static void toQQ(Context context, String QQ) {
        boolean qqClientAvailable = Tools.isQQClientAvailable(context);
        if (qqClientAvailable) {
            if (TextUtils.isEmpty(QQ)) {
                ToastUtils.showToast(context,"该店铺暂无QQ客服");
                return;
            }
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + QQ;
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } else {
            RouterUtil.getPostcardWithTransition(context, RouterUrl.Main.main_web)
                    .withString(Constant.IntentKey.TITLE, "客服QQ")
                    .withString(Constant.IntentKey.URL, "file:///android_asset/qidianQQ.html")
                    .navigation();
        }
    }
}
