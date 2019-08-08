package com.xinyartech.baselibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by Mr.Jude on 2016/1/6.
 */
public class DpUtils {
    /**
     * This method converts dp unit toGeRen equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need toGeRen convert into pixels
     * @param context Context toGeRen get resources and device specific display metrics
     * @return A float value toGeRen represent px equivalent toGeRen dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    /**
     * This method converts device specific pixels toGeRen density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need toGeRen convert into db
     * @param context Context toGeRen get resources and device specific display metrics
     * @return A float value toGeRen represent dp equivalent toGeRen px value
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}
