package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.IdGenerator;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealStorage {

    private static final String BREAKFAST = "Breakfast";
    private static final String LUNCH = "Lunch";
    private static final String DINNER = "Dinner";
    private static final IdGenerator idGenerator = IdGenerator.getInstance();

    private final List<Meal> meals = Arrays.asList(

            new Meal(idGenerator.generateId(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), BREAKFAST, 500),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), LUNCH, 1000),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), DINNER, 500),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), BREAKFAST, 1000),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), LUNCH, 500),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), DINNER, 510),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 3, 8, 34), BREAKFAST, 500),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 3, 12, 45), LUNCH, 1000),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 3, 19, 20), DINNER, 700),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 4, 7, 43), BREAKFAST, 600),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 4, 12, 19), LUNCH, 1200),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 4, 19, 7), DINNER, 500),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 5, 8, 16), BREAKFAST, 400),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 5, 12, 50), LUNCH, 1300),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 5, 18, 50), DINNER, 700),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 6, 8, 42), BREAKFAST, 500),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 6, 13, 10), LUNCH, 1000),
            new Meal(idGenerator.generateId(), LocalDateTime.of(2019, Month.APRIL, 6, 19, 30), DINNER, 500)
    );

    public List<Meal> getMeals() {
        return meals;
    }
}
