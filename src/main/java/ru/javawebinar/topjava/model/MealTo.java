package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.MealDateTimeFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MealTo {

    private final long id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean excess;

    public MealTo(long id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public String getDateTimeFormatted(){
        return MealDateTimeFormatter.doMealDateTimeFormat(dateTime);
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "\nMealTo:" +
                "\ndateTime=" + dateTime +
                "\ndescription='" + description +
                "\ncalories=" + calories +
                "\nexcess=" + excess;
    }
}