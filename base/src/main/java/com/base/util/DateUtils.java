package com.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 俞智威
 * on 2015-12-09.
 * 11:07
 * Procedure Explain:时间戳工具
 */

public class DateUtils {

    private static SimpleDateFormat sf = null;

    /**
     * 获取系统时间 (long格式)
     *
     * @return
     */
    public static long getCurrentDate() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取系统时间 格式为："yyyy/MM/dd HH:mm:ss"
     *
     * @param timeType
     * @return
     */
    public static String getCurrentDate(String timeType) {
        Date d = new Date();
        sf = new SimpleDateFormat(timeType);
        return sf.format(d);
    }

    /**
     * 时间戳转换成字符窜
     *
     * @param time
     * @param timeType
     * @return
     */

    public static String getDateToString(long time, String timeType) {
        Date d = new Date(time);
        sf = new SimpleDateFormat(timeType);
        return sf.format(d);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param time
     * @param timeType
     * @return
     */

    public static long getStringToDate(String time, String timeType) {
        sf = new SimpleDateFormat(timeType);
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay, String oldTimeType, String newTimeType) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date date = new SimpleDateFormat(oldTimeType).parse(specifiedDay);
        if (date != null) {
            c.setTime(date);
            int day = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day - 1);
            String dayBefore = new SimpleDateFormat(newTimeType).format(c.getTime());
            return dayBefore;
        } else
            return "";

    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay, String oldTimeType, String newTimeType) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date date = date = new SimpleDateFormat(oldTimeType).parse(specifiedDay);
        if (date != null) {
            c.setTime(date);
            int day = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day + 1);
            String dayAfter = new SimpleDateFormat(newTimeType).format(c.getTime());
            return dayAfter;
        } else
            return "";
    }

    /**
     * 获取当前年份
     */
    public static int getThisYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     */
    public static int getThisMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static String timeIntervalDescription(String dateString, String dateFormat) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.US);
            Date date = df.parse(dateString);
            Date now = new Date(System.currentTimeMillis());
            long delta = (now.getTime() - date.getTime()) / 1000;
            if (delta < 0) {
                return null;
            } else if (delta < 60) {
                return "一分钟内";
            } else if (delta < 60 * 60) {
                return String.format("%d分钟前", (int) delta / 60);
            } else if (delta < 24 * 60 * 60) {
                return String.format("%d小时前", (int) delta / 60 / 60);
            } else if (delta < 3 * 24 * 60 * 60) {
                return String.format("%d天前", (int) delta / 60 / 60 / 24);
            } else if (delta < 365 * 24 * 60 * 60) {
                SimpleDateFormat mdf = new SimpleDateFormat("MM月dd日", Locale.US);
                return mdf.format(date);
            } else {
                return String.format("%d年前", (int) delta / 60 / 60 / 24 / 365);
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public static String friendlyTimeDescription(String dateString, String dateFormat) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.US);
            Date date = df.parse(dateString);

            Calendar current = Calendar.getInstance();

            Calendar today = Calendar.getInstance();    //今天
            today.set(Calendar.YEAR, current.get(Calendar.YEAR));
            today.set(Calendar.MONTH, current.get(Calendar.MONTH));
            today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
            //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);


            Calendar yesterday = Calendar.getInstance();    //昨天
            yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
            yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
            yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
            yesterday.set(Calendar.HOUR_OF_DAY, 0);
            yesterday.set(Calendar.MINUTE, 0);
            yesterday.set(Calendar.SECOND, 0);

            Calendar oneweekago = Calendar.getInstance();    //一周前
            oneweekago.set(Calendar.YEAR, current.get(Calendar.YEAR));
            oneweekago.set(Calendar.MONTH, current.get(Calendar.MONTH));
            oneweekago.set(Calendar.WEEK_OF_YEAR, current.get(Calendar.WEEK_OF_YEAR) - 1);
            oneweekago.set(Calendar.HOUR_OF_DAY, 0);
            oneweekago.set(Calendar.MINUTE, 0);
            oneweekago.set(Calendar.SECOND, 0);

            current.setTime(date);
            //增加1小时，否则为0点0分0秒，current.after(today)会返回false
            current.set(Calendar.HOUR_OF_DAY, 1);

            SimpleDateFormat mdf = new SimpleDateFormat("ah:mm", Locale.US);
            SimpleDateFormat mmdf = new SimpleDateFormat("yyyy-MM-dd ah:mm", Locale.US);

            if (current.after(today)) {
                return mdf.format(date);
            } else if (current.before(today) && current.after(yesterday)) {
                return String.format("昨天 %s", mdf.format(date));
            } else if (current.before(yesterday) && current.after(oneweekago)) {
                return String.format("一周内 %s", mdf.format(date));
            } else {
                return mmdf.format(date);
            }


        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }


    public static String standardTimeDescription(String dateString, String dateFormat) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.US);
            Date date = df.parse(dateString);

            Calendar current = Calendar.getInstance();

            Calendar today = Calendar.getInstance();    //今天
            today.set(Calendar.YEAR, current.get(Calendar.YEAR));
            today.set(Calendar.MONTH, current.get(Calendar.MONTH));
            today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
            //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);

            Calendar tomorrow = Calendar.getInstance();    //昨天
            tomorrow.set(Calendar.YEAR, current.get(Calendar.YEAR));
            tomorrow.set(Calendar.MONTH, current.get(Calendar.MONTH));
            tomorrow.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) + 1);
            tomorrow.set(Calendar.HOUR_OF_DAY, 0);
            tomorrow.set(Calendar.MINUTE, 0);
            tomorrow.set(Calendar.SECOND, 0);


            Calendar yesterday = Calendar.getInstance();    //昨天
            yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
            yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
            yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
            yesterday.set(Calendar.HOUR_OF_DAY, 0);
            yesterday.set(Calendar.MINUTE, 0);
            yesterday.set(Calendar.SECOND, 0);

            current.setTime(date);

            SimpleDateFormat mdf = new SimpleDateFormat("HH:mm", Locale.US);

            if (current.after(today) && current.before(tomorrow)) {
                //今天
                int hourOfDay = current.get(Calendar.HOUR_OF_DAY);
                if (hourOfDay >= 0 && hourOfDay <= 6) {
                    return String.format("凌晨 %s", mdf.format(date));
                } else if (hourOfDay > 6 && hourOfDay <= 11) {
                    return String.format("上午 %s", mdf.format(date));
                } else if (hourOfDay > 11 && hourOfDay <= 17) {
                    return String.format("下午 %s", mdf.format(date));
                } else {
                    return String.format("晚上 %s", mdf.format(date));
                }
            } else if (current.before(today) && current.after(yesterday)) {
                return String.format("昨天%s", mdf.format(date));
            } else {
                return df.format(date);
            }


        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}