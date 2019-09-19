package ru.javawebinar.topjava.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class DateTimeUtil {

    private static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static boolean isBetween(LocalDateTime ldt, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ldt.compareTo(startDateTime) >= 0 && ldt.compareTo(endDateTime) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime toLocalDateTime(String dateString, String timeString) {
        logger.info("Date: " + dateString);
        logger.info("Time: " + timeString);
        try {
            int year = Integer.parseInt(dateString.substring(0, 4));
            int month = Integer.parseInt(dateString.substring(5, 7));
            int day = Integer.parseInt(dateString.substring(8, 10));
            int hour = Integer.parseInt(timeString.substring(0, 2));
            int minute = Integer.parseInt(timeString.substring(3, 5));
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (NullPointerException exc) {
            logger.info("Date or time is null!");
            return null;
        }
    }

    public static LocalDate convertToDate(String dateString) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        if (dateString.length() != 0) {
            String yearString = dateString.substring(0, 4);
            if (yearString.length() != 0) {
                year = Integer.parseInt(dateString.substring(0, 4));
            }
            String monthString = dateString.substring(5, 7);
            if (monthString.length() != 0) {
                month = Integer.parseInt(monthString);
            }
            String dayString = dateString.substring(8, 10);
            if (dayString.length() != 0) {
                day = Integer.parseInt(dayString);
            }
        }
        return LocalDate.of(year,month,day);
    }

    public static List<LocalTime> convertToTime(String timeFromString, String timeToString) {
        int hourFrom = 0;
        int minFrom = 0;
        int hourTo = 23;
        int minTo = 59;
        LocalTime timeFrom = LocalTime.of(0,0);
        LocalTime timeTo = LocalTime.of(23,59);
        logger.info("time from: " + timeFromString);
        logger.info("time to: " + timeToString);
        if (timeFromString.length() != 0) {
            String hourFromString = timeFromString.substring(0, 2);
            if (hourFromString.length() != 0) {
                hourFrom = Integer.parseInt(hourFromString);
            }
            String minFromString = timeFromString.substring(3, 5);
            if (minFromString.length() != 0) {
                minFrom = Integer.parseInt(minFromString);
            }
        }
        timeFrom = LocalTime.of(hourFrom,minFrom);
        if (timeToString.length() != 0) {
            String hourToString = timeToString.substring(0, 2);
            if (hourToString.length() != 0) {
                hourTo = Integer.parseInt(hourToString);
            }
            String minToString = timeToString.substring(3, 5);
            if (minToString.length() != 0) {
                minTo = Integer.parseInt(minToString);
            }
        }
        timeTo = LocalTime.of(hourTo,minTo);
        return Arrays.asList(timeFrom,timeTo);
    }
}
