package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class MealTo {

    private static final long serialVersionUID = 1L;
    private static final AtomicLong ATOMIC_LONG = new AtomicLong();
    private final long id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean excess;

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = ATOMIC_LONG.incrementAndGet();
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

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}