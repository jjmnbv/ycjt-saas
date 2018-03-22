package com.beitu.saas.risk.helpers;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 时间工具类
 */
public class TimeUtils {

    public static final String FORMAT_YMDA = "yyyy-MM-dd";


    /**
     * 获取年龄
     *
     * @param birthDay
     * @return
     */
    public static Integer getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }

        return age;
    }

    public static Integer getBirthDayYearByIdentityNo(String identityNo) {
        Integer year = 0;
        if (StringUtils.isNotEmpty(identityNo)) {
            Integer length = identityNo.length();
            year = Integer.parseInt(length == 18 ? identityNo.substring(8, 10) : identityNo.substring(6, 8));
        }

        return year;
    }

    public static String getZodiac(Date birthday) {

        String zodiacs[] = {"水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
                "天蝎座", "射手座", "魔羯座"};
        int[] constellationEdgeDay = {20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return zodiacs[month];
        }

        return zodiacs[11];
    }

    /**
     * 按照一定格式将时间转换成字符串格式
     * yyyy-MM-dd HH:mm:ss
     *
     * @param pattern 字符串格式默认yyyy/MM/dd
     * @param date    时间
     * @return
     */
    public static String toString(String pattern, Date date) {
        return new SimpleDateFormat(StringUtils.isEmpty(pattern) ? "yyyy/MM/dd" : pattern).format(date == null ? new Date() : date);
    }



    public static String toDateString(String pattern, Date date) {

        if(null==date){return "";}

        return new SimpleDateFormat(StringUtils.isEmpty(pattern) ? "yyyy/MM/dd" : pattern).format(date);
    }


    public static String getDateFormat(String date) {
        return toString("yyyy-MM-dd", getDateByStr(date, "yyyy-MM-dd"));
    }

    /**
     * 获取指定日期的前一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy/MM/dd").format(c
                .getTime());
        return dayBefore;
    }

    public static Date getDate(Date date) {
        String time = toString(FORMAT_YMDA, date);

        return getDateByStr(time, FORMAT_YMDA);
    }

    public static Date getDate(String pattern, Date date) {
        String time = toString(pattern, date);

        return getDateByStr(time, pattern);
    }

    /**
     * 时间字符串转换为date类型
     *
     * @param date    字符串时间
     * @param pattern 字符串格式
     * @return
     */
    public static Date getDateByStr(String date, String pattern) {
        if (StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(pattern)) {
            try {
                return new SimpleDateFormat(pattern).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 时间转换进秒转换为（hh:mm:ss）格式
     *
     * @param seconds 时间（单位秒）
     * @return
     */
    public static String secToTime(long seconds) {
        String timeStr = ConstStrings.EMPTY;
        long hour = 0l;
        long minute = 0l;
        long second = 0l;
        if (seconds <= 0) {
            return "00:00:00";
        } else {
            minute = seconds / 60;
            if (minute < 60) {
                second = seconds % 60;
                timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                minute = minute % 60;
                second = seconds - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String secToTime(int seconds) {
        return secToTime((long) seconds);
    }

    public static String unitFormat(int i) {
        return unitFormat((long) i);
    }

    public static String unitFormat(long i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + i;
        } else {
            retStr = "" + i;
        }
        return retStr;
    }

    /**
     * 转换（hh:mm:ss）的时间为秒数
     *
     * @param timeStr
     */
    public static int timeStrToSec(String timeStr) {
        if (StringUtils.isEmpty(timeStr)) {
            return 0;
        }
        Integer sec = 0;
        int timeStep = 1;
        String[] times = timeStr.split(":");
        int len = times.length;
        for (int i = 0; i < len; i++) {
            sec += (Integer.valueOf(times[len - i - 1]) * timeStep);
            timeStep = (i + 1) * 60;
        }
        return sec;
    }

    /**
     * @param date
     * @return
     */
    public static Date getDateEnd(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.withTime(23, 59, 59, 999).toDate();
    }

    /**
     * 获取指定日期前后天数的最迟时间
     *
     * @param date
     * @param delta 前后日期差
     * @return
     */
    public static Date ceiling(Date date, int delta) {
        if (date == null) {
            return null;
        }
        return new DateTime(getDateEnd(date)).plusDays(delta).toDate();
    }

    public static Date ceiling(Date date) {
        if (date == null) {
            return null;
        }
        return getDateEnd(date);
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return cal;
    }

    /**
     * 获取距离今天前后几天的最迟时间
     *
     * @param delta
     * @return
     */
    public static Date ceiling(int delta) {
        return ceiling(new Date(), delta);
    }

    /**
     * 获取指定日期前后天数的最早时间
     *
     * @param date
     * @param delta 前后天数差
     * @return
     */
    public static Date floor(Date date, int delta) {
        return new DateTime(getStartDateOfDay(date)).plusDays(delta).toDate();
    }

    /**
     * 获取最早时间
     *
     * @param
     * @return
     */
    public static Date getStartDateOfDay(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.withTime(0, 0, 0, 0).toDate();
    }

    /**
     * 获取与当天前后几天的最早时间
     *
     * @param delta
     * @return
     */
    public static Date floor(int delta) {
        return floor(new Date(), delta);
    }

    /**
     * 把时间清零，获得当前日期的清零时间
     *
     * @param date
     * @return
     */
    public static Date floor(Date date) {
        return getStartDateOfDay(date);
    }

    /**
     * 获取与指定日期前后几天的最早时间
     *
     * @param delta
     * @return
     */
    public static Date floorer(Date date, int delta) {
        return floor(date, delta);
    }


    /**
     * 获取是星期几,用0,1,3，4，5，6，7表示
     */
    public static int getWeekOfDate(Date date) {
        return (new DateTime(date).getDayOfWeek());
    }

    /**
     * 比较两个日期是否为在同一月
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameMoth(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return Boolean.FALSE;
        }
        DateTime d1 = new DateTime(date1);
        DateTime d2 = new DateTime(date2);
        if (d1.monthOfYear().get() == d2.monthOfYear().get()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断两日期是否为同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return Boolean.FALSE;
        }
        DateTime d1 = new DateTime(date1);
        DateTime d2 = new DateTime(date2);
        if (d1.dayOfMonth().get() == d2.dayOfMonth().get()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 获取当前时间前后几天
     *
     * @param delta 前后天数，支持正负数
     * @return
     */
    public static Date appointed(int delta) {
        Calendar cal = getCalendar(new Date());
        if (delta != 0) {
            cal.add(Calendar.DATE, delta);
        }
        return cal.getTime();
    }

    /**
     * 获取当前时间前后几个月
     *
     * @param month
     * @return
     */
    public static Date getDateByMonth(Date date, int month) {
        Calendar calendar = getCalendar(date);
        calendar.add(Calendar.MONTH, month);
        Date lastDate = calendar.getTime();
        return lastDate;
    }

    /**
     * 获取
     *
     * @param statisDate
     * @return
     */
    public static Date getStatisDate(Date statisDate) {
        return new DateTime(statisDate == null ? new Date() : statisDate).plusDays(-1).withTime(0, 0, 0, 0).toDate();
    }

    /**
     * 把时间date是时分秒归零
     *
     * @param date
     * @return
     */
    public static Date toDayTime(Date date) {
        if (date == null) {
            date = new Date();
        }
        return new DateTime(date).withTime(0, 0, 0, 0).toDate();
    }

    /**
     * 判断指定时间是否是周末
     *
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        int index = new DateTime(date).getDayOfWeek();
        if (index == 6 || index == 7) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断指定时间是否是周末
     *
     * @param
     * @return
     */
    public static String getYearMonthDay(DateTime dt) {
        String m = "";
        String d = "";

        int year = dt.getYear();
        int month = dt.getMonthOfYear();
        int day = dt.getDayOfMonth();
        if (month < 10) {
            m = "0" + month;

        } else {
            m = String.valueOf(month);
        }
        if (day < 10) {
            d = "0" + day;
        } else {
            d = String.valueOf(day);
        }
        return year + m + d;

    }

    /**
     * @param date
     * @param pattern
     * @param locale
     * @return Date
     * @Description: 根据方言和模式解析日期字符串
     * @author yaojf
     * @date 2015年5月20日 下午4:16:19
     */
    public static Date getDateByStrAndLocale(String date, String pattern,
                                             Locale locale) {
        if (StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(pattern)
                && locale != null) {
            try {
                return new SimpleDateFormat(pattern, locale).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param date
     * @param pattern
     * @param locale
     * @return String
     * @Description: 根据方言和模式解析日期
     * @author yaojf
     * @date 2015年5月21日 上午11:23:29
     */
    public static String getStrByDateAndLocale(Date date, String pattern,
                                               Locale locale) {
        if (date != null && StringUtils.isNotEmpty(pattern)
                && locale != null) {
            DateFormat dateFormat = new SimpleDateFormat(pattern, locale);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return dateFormat.format(date);
        }
        return null;
    }

    public static boolean hourCompareForLess(String a, String b) {
        if (HHmmToMinute(a) < HHmmToMinute(b)) {
            return true;
        }
        return false;
    }

    /**
     * 将时间格式为hh:mm的字符串转换成分钟数
     *
     * @param time
     * @return
     */
    public static int HHmmToMinute(String time) {
        if (StringUtils.isNotEmpty(time)) {
            String[] strs = time.split(":");
            if (strs.length == 2) {
                return Integer.valueOf(strs[0]) * 60 + Integer.valueOf(strs[1]);
            }
        }
        return 0;
    }


    public static boolean check(Date date1, Date date2) {
        boolean result = false;

        if (date1.getTime() > date2.getTime()) {
            result = true;
        } else if (date1.getTime() < date2.getTime()) {
            result = false;

        }
        return result;

    }

    public static long diff(Date date1, Date date2) {
        long result = 0;
        result = date2.getTime() - date1.getTime();
        return result;

    }

    public static Integer diffDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        long result = date2.getTime() - date1.getTime();
        Integer days = Integer.parseInt((result / 1000 / 60 / 60 / 24) + "");
        return days;
    }

    public static Date transferLongToDate(Long millSec) {
        Date date = new Date(millSec);
        return date;
    }

    /**
     * 获取当前时间的几个小时前的时间
     *
     * @param num
     * @return
     */
    public static String getHourAgoTime(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - num);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    /**
     * 获取当前时间的几分钟前的时间
     *
     * @param num
     * @return
     */
    public static String getMINUTEAgoTime(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - num);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    public static Date yesterday() {
        return appointed(-1);
    }

    public static String yesterdayToString(String pattern) {
        return toString(pattern, yesterday());
    }

    public static boolean checkMonthFirstDay() {
        Calendar c = Calendar.getInstance();
        int today = c.get(c.DAY_OF_MONTH);
        // 判断是不是本月的第一天
        if (today == 1) {
            return true;

        } else {
            return false;
        }
    }

    public static String changDateFormat(String pattern, String date) {
        return toString(pattern, getDateByStr(date, "yyyy-MM-dd"));
    }

    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List lDate = new ArrayList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);//把结束时间加入集合
        return lDate;
    }

    public static List<String> getDatesStringListBetweenTwoDate(Date beginDate, Date endDate) {
        List<String> datesString = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        datesString.add(TimeUtils.toString("yyyy-MM-dd", beginDate));
        cal.setTime(beginDate);
        while (true) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            if (endDate.after(cal.getTime())) {
                datesString.add(toString("yyyy-MM-dd", cal.getTime()));
            } else {
                break;
            }
        }
        datesString.add(TimeUtils.toString("yyyy-MM-dd", endDate));
        return datesString;
    }


    public static String getDatesStringBetweenTwoDate(String beginDate, String endDate) {
        String res = "";
        List<String> datesStringList = getDatesStringListBetweenTwoDate(getDateByStr(beginDate, "yyyy-MM-dd"), getDateByStr(endDate, "yyyy-MM-dd"));
        if (CollectionUtils.isNotEmpty(datesStringList)) {
            for (String date : datesStringList) {
                res += "'" + date + "',";
            }

            res = res.substring(0, res.length() - 1);
        }

        if (res.equals("")) {
            res = "'"+toString("yyyy-MM-dd", new Date())+"'";
        }

        return res;
    }
}
