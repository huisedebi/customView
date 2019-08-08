package com.zjb.home.boxingtu.view.datepicker;

import java.io.Serializable;

public class DateEntity implements Serializable {

    private static final long serialVersionUID = -6053739977785155088L;
    /** 年 */
    public int year;
    /** 月 */
    public int month;
    /** 日 */
    public int day;
    /** 星期 */
    public String weekDay;
    /** 是否为当前日期 */
    public boolean isNowDate;
    /** 是否为本月日期 */
    public boolean isSelfMonthDate;
}