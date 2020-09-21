package com.brl.sc.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 说明：日期处理
 */
public class DateUtil {

    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat sdfMM = new SimpleDateFormat("MM");
    private final static SimpleDateFormat sdfDd = new SimpleDateFormat("dd");
    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
    private final static SimpleDateFormat sdfTimesSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private final static SimpleDateFormat sdfMMdd = new SimpleDateFormat("MM.dd");
    private final static SimpleDateFormat sdfMM_dd = new SimpleDateFormat("MM-dd");

    private final static String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    private final static String yyyyMMddHH = "yyyyMMddHH";
    private final static String yyyyMMdd = "yyyy-MM-dd";


    /**
     * 获取秒的时间戳
     *
     * @return
     */
    public static Long getTimestampForSecond(Date date) {
        return date.getTime() / 1000;
    }
    /**
     * 获取当前时间+30分钟的时间
     *
     * @return
     */
    public static int getTimeThirty() {
        Date now = new Date();
        long time = 30*60*1000;//30分钟
        Date afterDate = new Date(now .getTime() + time);//30分钟后的时间
        System.out.println();
        long time1 = afterDate.getTime() / 1000;
        int ii = (int)time1;
        return ii;
    }
    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getSdfTimes() {
        return sdfTimes.format(new Date());
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    public static String getYear(Date date) {
        return sdfYear.format(date);
    }
    /**
     * 获取YYYY格式
     * @return
     */
    public static String getMM(Date date) {
        return sdfMM.format(date);
    }

    /**
     * 获取YYYY格式
     * @return
     */
    public static String getDd(Date date) {
        return sdfDd.format(date);
    }


    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    public static String getTime(Date date) {
        return sdfTime.format(date);
    }

    public static String getSdfMMdd(Date date) {
        return sdfMMdd.format(date);
    }

    public static String getSdfMM_dd(Date date) {
        return sdfMM_dd.format(date);
    }

    public static Date convertDate(String parttern, String dateString) {
        SimpleDateFormat df = new SimpleDateFormat(parttern);
        try {
            return df.parse(dateString);
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description: TODO(日期比较 ， 如果s > = e 返回true 否则返回false)
     * @author fh
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        boolean b = fomatDate(s).getTime() >= fomatDate(e).getTime();
        return b;
    }

    /**
     * 获取当前时间的int格式
     *
     * @return
     */
    public static int findDate() {
        long i = System.currentTimeMillis() / 1000;
        int ii = (int)i;
        return ii;
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date fomatDate(String date, String format) {
        DateFormat fmt = new SimpleDateFormat(format);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String fomatDate(Date date) {
        return DateUtil.sdfDay.format(date);
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDateToYyyyMMddHHmmss(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            return false; // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
        }
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            return 0;    // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);
        return day;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Date date) {
        return date2Str(date, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 把时间根据时、分、秒转换为时间段
     *
     * @param StrDate
     */
    public static String getTimes(String StrDate) {
        String resultTimes = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now;
        try {
            now = new Date();
            Date date = df.parse(StrDate);
            long times = now.getTime() - date.getTime();
            long day = times / (24 * 60 * 60 * 1000);
            long hour = (times / (60 * 60 * 1000) - day * 24);
            long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

            StringBuffer sb = new StringBuffer();
            //sb.append("发表于：");
            if (hour > 0) {
                sb.append(hour + "小时前");
            } else if (min > 0) {
                sb.append(min + "分钟前");
            } else {
                sb.append(sec + "秒前");
            }
            resultTimes = sb.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultTimes;
    }

    /**
     * 按照参数format的格式，日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }

    public static String getThreeMonthsAgo() {
        return DateUtil.sdfDay.format(getMonthAgo(-3));
    }

    public static String getOneMonthsAgo() {
        return DateUtil.sdfDay.format(getMonthAgo(-1));
    }

    public static String getSixMonthsAgo() {
        return DateUtil.sdfDay.format(getMonthAgo(-6));
    }

    private static Date getMonthAgo(int num) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, num);
        return calendar.getTime();
    }

    public static String getOneYearAgo() {
        return DateUtil.sdfDay.format(getYearAgo(-1));
    }

    public static String getThreeYearAgo() {
        return DateUtil.sdfDay.format(getYearAgo(-3));
    }

    private static Date getYearAgo(int num) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, num);
        return calendar.getTime();
    }

    public static int yearCompare(Date fromDate, Date toDate) {
        Calendar from = Calendar.getInstance();
        from.setTime(fromDate);
        Calendar to = Calendar.getInstance();
        to.setTime(toDate);

        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);

        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear - fromYear;
        int month = toMonth - fromMonth;
        int day = toDay - fromDay;
        return year;
    }

    /*
     * @param date
     * @param rule  例 ：yyyy.MM.dd HH.mm.ss
     * 自定义规则
     * */
    public static String sdfTimeByCustom(Date date, String rule) {
        SimpleDateFormat sdf = new SimpleDateFormat(rule);
        return sdf.format(date);
    }

    /*获取星期几*/
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    /*
     * 获取当前天的起始时间
     */
    public static Date getStartTime(Date date) {
        Calendar day = new GregorianCalendar();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        return day.getTime();
    }

    /*
     * 获取当前天的结束时间
     */
    public static Date getEndTime(Date date) {
        Calendar day = new GregorianCalendar();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 23);
        day.set(Calendar.MINUTE, 59);
        day.set(Calendar.SECOND, 59);
        day.set(Calendar.MILLISECOND, 999);
        return day.getTime();
    }

    public static Date getPreMinute(Integer integer) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, integer);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前时间的10位时间戳
     * */
    public static Integer getNowTimestamp(){
        return Long.valueOf(Instant.now().getEpochSecond()).intValue();
    }

    /**
     * 获取当天结束（24点）的剩余秒数
     */
    public static Integer getRemainSecondsOneDay() {
        Date currentDate = new Date();
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }
    /**
     * UTC 转 CST 时间戳
     * @return CST timestamp
     * */
    public static long utcToCstTimestamp(String utcStr){
        LocalDateTime localDateTime = ZonedDateTime.parse(utcStr).toLocalDateTime();
        return localDateTime.plusHours(8).toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 获取当前时间，格式 yyyy-MM-dd HH:mm:ss
     * */
    public static String getDateTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(yyyyMMddHHmmss);
        return dateTimeFormatter.format(LocalDateTime.now());
    }
    /**
     * 获取当前时间，格式 yyyy-MM-dd HH:mm:ss
     * */
    public static String getDateTime4yyyyMMdd(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(yyyyMMdd);
        return dateTimeFormatter.format(LocalDateTime.now());
    }
    /**
     * 获取当前时间，格式 yyyy-MM-dd HH:mm:ss
     * */
    public static String getDateTime4yyyyMMddHH(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(yyyyMMddHH);
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    public static String getMillisecondNo(){
        return sdfTimesSSS.format(new Date());
    }

    public static void main(String[] args) {
        System.err.println(getSdfMM_dd(new Date()));
    }

}
