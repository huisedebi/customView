package com.xinyartech.baselibrary.view;


import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextClock;

import java.util.Calendar;

/**
 * 作者: created by zcm on 2018/9/29
 * 修改: modified by zcm on 2018/9/29
 * 描述: desc(限时抢购)
 */
public class CustomDigitalClock extends TextClock {

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

    public CustomDigitalClock(Context context) {
        super(context);
        initClock(context);
    }

    public CustomDigitalClock(Context context, AttributeSet attrs) {
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
        if(text.toString().contains("天")){
            super.setText(text, type);
        }
    }


    private SpannableString getHandleTime(String time){
        time = " " + time;
        SpannableString spannableString=new SpannableString(time);
        RadiusBackgroundSpan rbgDay = new RadiusBackgroundSpan(Color.parseColor("#FF606060"),Color.WHITE,getResources().getDisplayMetrics().density*3);
        RadiusBackgroundSpan rbgHour = new RadiusBackgroundSpan(Color.parseColor("#FF606060"),Color.WHITE,getResources().getDisplayMetrics().density*3);
        RadiusBackgroundSpan rbgMinute = new RadiusBackgroundSpan(Color.parseColor("#FF606060"),Color.WHITE,getResources().getDisplayMetrics().density*3);
        RadiusBackgroundSpan rbgSecond = new RadiusBackgroundSpan(Color.parseColor("#FF606060"),Color.WHITE,getResources().getDisplayMetrics().density*3);
        RadiusBackgroundSpan rbgDayUnit = new RadiusBackgroundSpan(Color.TRANSPARENT,0,getResources().getDisplayMetrics().density*3);
        RadiusBackgroundSpan rbgHourUnit = new RadiusBackgroundSpan(Color.TRANSPARENT,0,getResources().getDisplayMetrics().density*3);
        RadiusBackgroundSpan rbgMinutUnit = new RadiusBackgroundSpan(Color.TRANSPARENT,0,getResources().getDisplayMetrics().density*3);
        RadiusBackgroundSpan rbgSecondUnit = new RadiusBackgroundSpan(Color.TRANSPARENT,0,getResources().getDisplayMetrics().density*3);

        int dayIndex = time.indexOf("天");
        int hourIndex = time.indexOf("时");
        int minuteIndex = time.indexOf("分");

        spannableString.setSpan(rbgDay, 1, dayIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(rbgHour, dayIndex+1, hourIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(rbgMinute, hourIndex+1, minuteIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(rbgSecond, minuteIndex+1, time.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(rbgDayUnit, dayIndex, dayIndex+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(rbgHourUnit, hourIndex, hourIndex+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(rbgMinutUnit, minuteIndex, minuteIndex+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(rbgSecondUnit, time.length()-1, time.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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

    public interface ClockListener{
        void timeEnd();
        void remainFiveMinutes();
    }

}

