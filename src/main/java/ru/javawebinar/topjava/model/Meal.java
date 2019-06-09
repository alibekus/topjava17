package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;

public class Meal {
    private static final AtomicLong atomicLong = new AtomicLong();
    private final long id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.id = atomicLong.incrementAndGet();
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
}
