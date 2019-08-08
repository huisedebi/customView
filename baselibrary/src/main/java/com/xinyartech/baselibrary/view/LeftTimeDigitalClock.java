package com.xinyartech.baselibrary.view;


import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextClock;

import java.util.Calendar;

import com.xinyartech.baselibrary.utils.LogUtil;

/**
 * 作者: created by zcm on 2018/9/29
 * 修改: modified by zcm on 2018/9/29
 * 描述: desc(限时抢购)
 */
public class LeftTimeDigitalClock extends TextClock {

    Calendar mCalendar;
    private final static String m12 = "h:mm aa";
    private final static String m24 = "k:mm";
    private FormatChangeObserver mFormatChangeObserver;

    private Runnable mTicker;
    private Handler mHandler;
    private long endTime;
    private ClockListener mClockListener;

    private boolean mTickerStopped = false;

    @SuppressWarnings("unused")
    private String mFormat;

    public LeftTimeDigitalClock(Context context) {
        super(context);
        initClock(context);
    }

    public LeftTimeDigitalClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClock(context);
    }

    private void initClock(Context context) {

        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }

        mFormatChangeObserver = new FormatChangeObserver();
        getContext().getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, mFormatChangeObserver);

        setFormat();
    }

    @Override
    protected void onAttachedToWindow() {
        mTickerStopped = false;
        super.onAttachedToWindow();
        mHandler = new Handler();
        SpannableString defaultTime = getHandleTime("00天00时00分00秒");
        setTextSize(12);
        setTextColor(Color.parseColor("#FF666666"));
        setText(defaultTime);
        invalidate();
        /**
         * requests a tick on the next hard-second boundary
         */
        mTicker = new Runnable() {
            @Override
            public void run() {
                if (mTickerStopped) {
                    return;
                }
                long currentTime = System.currentTimeMillis();
                if (currentTime / 1000 == endTime / 1000 - 5 * 60) {
                    mClockListener.remainFiveMinutes();
                }
                long distanceTime = endTime - currentTime;
                distanceTime /= 1000;
                SpannableString defaultTime = getHandleTime("00天00时00分00秒");
                if (distanceTime == 0) {
                    setText(defaultTime);
                    onDetachedFromWindow();
                    mClockListener.timeEnd();
                } else if (distanceTime < 0) {
                    setText(defaultTime);
                } else {
                    setText(getHandleTime(dealTime(distanceTime)));
                }
                invalidate();
                long now = SystemClock.uptimeMillis();
                long next = now + (1000 - now % 1000);
                mHandler.postAtTime(mTicker, next);
            }
        };
        mTicker.run();
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text.toString().contains("天")) {
            super.setText(text, type);
        }
    }

    String start;
    String end;

    /**
     * 设置前后内容
     */
    public void setContent(String start, String end) {
        this.start = start;
        this.end = end;
        LogUtil.LogShitou("LeftTimeDigitalClock--setContent", "start" + start);
    }

    private SpannableString getHandleTime(String time) {
        if (TextUtils.isEmpty(start)) {
            start = "";
        }
        if (TextUtils.isEmpty(end)) {
            end = "";
        }
        int timeLength = time.length();
        time = start + time + end;
        SpannableString spannableString = new SpannableString(time);
        RadiusBackgroundSpan rbgDay = new RadiusBackgroundSpan(Color.TRANSPARENT, Color.parseColor("#FFFF5500"), getResources().getDisplayMetrics().density * 3);
        LogUtil.LogShitou("LeftTimeDigitalClock--getHandleTime", "start" + start);
        spannableString.setSpan(rbgDay, start.length(), start.length() + timeLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * deal time string
     *
     * @param time
     * @return
     */
    public static String dealTime(long time) {
        StringBuffer returnString = new StringBuffer();
        long day = time / (24 * 60 * 60);
        long hours = (time % (24 * 60 * 60)) / (60 * 60);
        long minutes = ((time % (24 * 60 * 60)) % (60 * 60)) / 60;
        long second = ((time % (24 * 60 * 60)) % (60 * 60)) % 60;
        String dayStr = timeStrFormat(String.valueOf(day));
        String hoursStr = timeStrFormat(String.valueOf(hours));
        String minutesStr = timeStrFormat(String.valueOf(minutes));
        String secondStr = timeStrFormat(String.valueOf(second));
        returnString.append(dayStr).append("天").append(hoursStr).append("时").append(minutesStr).append("分").append(secondStr).append("秒");
        //returnString.append(hoursStr).append(":").append(minutesStr).append(":").append(secondStr);
        return returnString.toString();
    }

    /**
     * format time
     *
     * @param timeStr
     * @return
     */
    private static String timeStrFormat(String timeStr) {
        switch (timeStr.length()) {
            case 1:
                timeStr = "0" + timeStr;
                break;
        }
        return timeStr;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTickerStopped = true;
    }

    /**
     * Clock end time from now on.
     *
     * @param endTime
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * Pulls 12/24 mode from system settings
     */
    private boolean get24HourMode() {
        return android.text.format.DateFormat.is24HourFormat(getContext());
    }

    private void setFormat() {
        if (get24HourMode()) {
            mFormat = m24;
        } else {
            mFormat = m12;
        }
    }

    private class FormatChangeObserver extends ContentObserver {
        public FormatChangeObserver() {
            super(new Handler());
        }

        @Override
        public void onChange(boolean selfChange) {
            setFormat();
        }
    }

    public void setClockListener(ClockListener clockListener) {
        this.mClockListener = clockListener;
    }

    public interface ClockListener {
        void timeEnd();

        void remainFiveMinutes();
    }

}

