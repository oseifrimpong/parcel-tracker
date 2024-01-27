/*
 * Copyright 2019-2029 Decathlon (https://github.com/dktunited)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.hrs.parceltracker.util;

import com.hrs.parceltracker.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;


@Slf4j
public class DateUtil {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYYMMDD = "yyyyMMdd";





    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Date getDayAgoOrAfter(Date date, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }

    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static Date stringToDate(String date, String pattern) {
        if (ValidationUtil.isStringEmpty(date)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            log.warn("Got ParseException when converting date string[{}]", date);
        }
        return null;
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }


    public static Date removeTimeFromDate(Date dateWithTime) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
            return simpleDateFormat.parse(simpleDateFormat.format(dateWithTime));
        } catch (ParseException e) {
            // should never be here
            return dateWithTime;
        }
    }

    public static Date getCurrentDateWithoutTime() {
        Date today = getCurrentJavaDate();
        return removeTimeFromDate(today);
    }

    public static Date getInsDateWithoutTime(Date date, int ins) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, ins);
        Date insDate = cal.getTime();
        return removeTimeFromDate(insDate);
    }

    public static Date getYesterdayWithoutTime() {
        Date today = getCurrentJavaDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();
        return removeTimeFromDate(yesterday);
    }

    public static Date getTomorrowWithoutTime() {
        Date today = getCurrentJavaDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, 1);
        Date yesterday = cal.getTime();
        return removeTimeFromDate(yesterday);
    }

    public static Date getLastSecondDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
        String newDateSting = simpleDateFormat.format(date) + " 23:59:59";

        return stringToDate(newDateSting, YYYY_MM_DD_HH_MM_SS);
    }

    public static LocalDateTime getCurrent() {
        return localToUTC(Instant.now().getEpochSecond() * 1000L);
    }

    public static Date getCurrentJavaDate() {
        return localToUTCJavaDate(Instant.now().getEpochSecond() * 1000L);
    }

    /**
     * get current week,for example 202145
     */
    public static Integer getCurrentYW() {
        return getYWByDate(getCurrentJavaDate());
    }

    public static Integer getWeekNumberOfYear(Integer year) {
        return getWeekFromYW(computeYearAndWeek(combineYearAndWeek(year + 1, 1), -1));
    }

    public static LocalDateTime localToUTC(long localTimeInMillis) {
        /** longCalendar */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /**  */
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        /**  */
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        /** ，UTC */
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** UTC */
        return LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
    }

    public static Date localToUTCJavaDate(long localTimeInMillis) {
        /** longCalendar */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /**  */
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        /**  */
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        /** ，UTC */
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** UTC */
        return new Date(calendar.getTimeInMillis());
    }

    public static Integer combineYearAndWeek(Integer year, Integer week) {
        return year * 100 + week;
    }

    public static Integer getYearFromYW(Integer yearAndWeek) {
        return yearAndWeek / 100;
    }

    public static Integer getWeekFromYW(Integer yearAndWeek) {
        Integer year = getYearFromYW(yearAndWeek);
        return yearAndWeek - year * 100;
    }

    public static Integer computeYearAndWeek(Integer yearAndWeek, Integer ins) {
        if (ins == 0) {
            return yearAndWeek;
        }

        Date firstDayOfWeek = getFirstDayOfWeek(yearAndWeek);
        Calendar cal = Calendar.getInstance();
        cal.setTime(firstDayOfWeek);
        cal.add(Calendar.WEEK_OF_YEAR, ins);

        return getYWByDate(cal.getTime());
    }

    public static Integer getYWByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);

        // 2019-12-31 is 202001 because the first day of 2020 is 2019-12-29
        Date firstDayOfNextYear = getFirstDayOfFirstWeekOfYear(year + 1);
        ValidationUtil.checkObjectNull("firstDayOfNextYear", firstDayOfNextYear);
        if (firstDayOfNextYear.compareTo(date) <= 0) {// NOSONAR NullPointerException has already been checked
            // return first week of next year
            return combineYearAndWeek(year + 1, 1);
        }

        Date firstDayOfYear = getFirstDayOfFirstWeekOfYear(year);
        ValidationUtil.checkObjectNull("firstDayOfYear", firstDayOfYear);
        // 2021-01-01 is 202053 because the first day of 2021 is 2021-01-03
        if (firstDayOfYear.compareTo(date) > 0) {// NOSONAR NullPointerException has already been checked
            // get last week of pre year
            return computeYearAndWeek(combineYearAndWeek(year, 1), -1);
        } else {
            long betweenDays = (date.getTime() - firstDayOfYear.getTime()) / (1000L * 3600L * 24L);
            long weeks = betweenDays / 7L;
            int weeksInt = (int)weeks;
            return combineYearAndWeek(year, 1 + weeksInt);
        }
    }

    // the week number of year in java is different with google calendar
    // here follow google calendar to determine the first day of first week in a
    // year
    public static Date getFirstDayOfFirstWeekOfYear(Integer year) {
        Date firstDayOfYear = stringToDate(year.toString() + "-01-01", YYYY_MM_DD);
        Calendar cal = Calendar.getInstance();

        String firstDayString = null;
        cal.setTime(firstDayOfYear);
        int weekDays = cal.get(Calendar.DAY_OF_WEEK);
        switch (weekDays) {
            case Calendar.SUNDAY -> firstDayString = year + "-01-01";
            case Calendar.MONDAY -> firstDayString = (year - 1) + "-12-31";
            case Calendar.TUESDAY -> firstDayString = (year - 1) + "-12-30";
            case Calendar.WEDNESDAY -> firstDayString = (year - 1) + "-12-29";
            case Calendar.THURSDAY -> firstDayString = year + "-01-04";
            case Calendar.FRIDAY -> firstDayString = year + "-01-03";
            case Calendar.SATURDAY -> firstDayString = year + "-01-02";
            default -> { // NOSONAR
            }
        }

        if (ValidationUtil.isStringNotEmpty(firstDayString)) {
            return stringToDate(firstDayString, YYYY_MM_DD);
        }

        throw new SystemException("It should not happen");

    }

    public static Date getFirstDayOfWeek(Integer yearAndWeek) {
        Date firstDayOfFirstWeekOfYear = getFirstDayOfFirstWeekOfYear(getYearFromYW(yearAndWeek));

        Calendar cal = Calendar.getInstance();
        cal.setTime(firstDayOfFirstWeekOfYear);
        cal.add(Calendar.DAY_OF_YEAR, (getWeekFromYW(yearAndWeek) - 1) * 7);

        return cal.getTime();
    }

    public static Date getLastDayOfWeek(Integer yearAndWeek) {
        Integer nextYW = computeYearAndWeek(yearAndWeek, 1);
        Date firstDayOfNextWeek = getFirstDayOfWeek(nextYW);

        Calendar cal = Calendar.getInstance();
        cal.setTime(firstDayOfNextWeek);
        cal.add(Calendar.SECOND, -1);
        return cal.getTime();
    }

    public static Integer getYMByDate(Date date) {
        return Integer.valueOf(dateToString(date, YYYY_MM));
    }

    public static Date getFirstDayOfMonth(Integer ym) {
        return stringToDate(ym.toString() + "01", YYYYMMDD);
    }

    public static Date getLastDayOfMonth(Integer ym) {
        Date firstDay = getFirstDayOfMonth(ym);
        Calendar cal = Calendar.getInstance();
        cal.setTime(firstDay);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_YEAR, -1);

        return cal.getTime();
    }

    public static long getSecondDifference(Date startDate, Date endDate) {
        // calculate time difference in milliseconds
        long millisecondDifference = endDate.getTime() - startDate.getTime();
        return millisecondDifference / 1000L;
    }

    public static long getDayDifference(Date startDate, Date endDate) {
        // calculate time difference in milliseconds
        long secondDifference = getSecondDifference(startDate, endDate);
        return secondDifference / 60L / 60L / 24L;
    }

    public static Date getFirstDayOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static Date getLastDayOfLastMonth() {
        Date firstDayOfLastMonth = getFirstDayOfLastMonth();
        return getLastDayOfMonth(getYMByDate(firstDayOfLastMonth));
    }

    public static String getMonthName(Date date) {
        return dateToString(date, "yyyy MMM");
    }

    public static boolean isFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int weekDays = cal.get(Calendar.DAY_OF_WEEK);
        return weekDays == Calendar.SUNDAY;
    }

    public static Integer transDotSplitWeekToIntWeek(String dotSplitWeek) {
        if (!dotSplitWeek.matches("^[\\d]{2}\\.[\\d]{4}$")) {
            throw new SystemException("ETL week doesn't match correct format");
        }
        String[] split = dotSplitWeek.split("\\.");
        return Integer.parseInt(split[1] + split[0]);
    }

    public static String transIntWeekToDotSplitWeek(Integer intWeek) {
        return getWeekFromYW(intWeek) + "." + getYearFromYW(intWeek);
    }

    public static List<Integer> generateIntWeekList(Integer startWeek, Integer changeWeek) {
        List<Integer> intWeekList = new ArrayList<>(27);
        intWeekList.add(startWeek);
        if (changeWeek == 0) {
            return intWeekList;
        } else if (changeWeek > 0) {
            for (int i = 0; i < changeWeek; i++) {
                intWeekList.add(computeYearAndWeek(startWeek, 1 + i));
            }
        } else {
            for (int i = 0; i > changeWeek; i--) {
                intWeekList.add(computeYearAndWeek(startWeek, -1 + i));
            }
        }
        return intWeekList;
    }

//    public static List<String> generateDotSplitWeekList(String startWeek, Integer changeWeek) {
//        Integer intStartWeek = transDotSplitWeekToIntWeek(startWeek);
//        List<Integer> intWeekList = generateIntWeekList(intStartWeek, changeWeek);
//        return intWeekList.stream().map(DateUtil::transIntWeekToDotSplitWeek).toList();
//    }

//    public static List<String> generateDotSplitWeekList(Integer startWeek, Integer changeWeek) {
//        List<Integer> intWeekList = generateIntWeekList(startWeek, changeWeek);
//        return intWeekList.stream().map(DateUtil::transIntWeekToDotSplitWeek).toList();
//    }

}

