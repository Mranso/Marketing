package com.control.marketing.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * description:
 * author: yuantian
 * date: 2017/7/5
 * version: 2.0.0
 */

public class TimeUtils {

    public static String getDateString(long milliseconds) {
        return getDateTimeString(milliseconds, "yyyy-MM-dd HH:mm:ss");
    }

    private static String getDateTimeString(long milliseconds, String format) {
        Date date = new Date(milliseconds);
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        return formatter.format(date);
    }
}
