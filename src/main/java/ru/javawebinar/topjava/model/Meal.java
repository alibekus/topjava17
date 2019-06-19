package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal implements Comparable<Meal> {

    private long id;
    private LocalDateTime dateTime;
    private String description;
    private int calories;

    public Meal() {
    }

    public Meal(long id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
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



    public String toString() {
        return "\nMeal:" +
                "\ndateTime=" + dateTime +
                "\ndescription='" + description +
                "\ncalories=" + calories;
    }

    @Override
    public int compareTo(Meal another) {
        return this.compareTo(another);
    }
}
