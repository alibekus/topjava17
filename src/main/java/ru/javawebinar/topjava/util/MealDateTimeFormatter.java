package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealDateTimeFormatter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static String doMealDateTimeFormat(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}
