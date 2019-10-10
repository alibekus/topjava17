package ru.javawebinar.topjava.util;

import org.springframework.lang.Nullable;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsUtil {
    public static final List<Meal> USER_MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2019, Month.MARCH, 5, 8, 0), "Завтрак", 850),
            new Meal(LocalDateTime.of(2019, Month.MARCH, 5, 13, 30), "Обед", 1230),
            new Meal(LocalDateTime.of(2019, Month.MARCH, 5, 19, 30), "Ужин", 940),
            new Meal(LocalDateTime.of(2019, Month.APRIL, 10, 7, 40), "Завтрак", 630),
            new Meal(LocalDateTime.of(2019, Month.APRIL, 10, 13, 10), "Обед", 1530),
            new Meal(LocalDateTime.of(2019, Month.APRIL, 10, 19, 25), "Ужин", 375)
    );

    public static final List<Meal> ADMIN_MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2019, Month.MARCH, 5, 7, 30), "Завтрак", 740),
            new Meal(LocalDateTime.of(2019, Month.MARCH, 5, 12, 30), "Обед", 1160),
            new Meal(LocalDateTime.of(2019, Month.MARCH, 5, 18, 45), "Ужин", 670),
            new Meal(LocalDateTime.of(2019, Month.APRIL, 10, 7, 35), "Завтрак", 475),
            new Meal(LocalDateTime.of(2019, Month.APRIL, 10, 13, 5), "Обед", 1310),
            new Meal(LocalDateTime.of(2019, Month.APRIL, 10, 19, 45), "Ужин", 590)
    );

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static List<MealTo> getWithExcess(Collection<Meal> meals, int caloriesPerDay) {
        return getFilteredWithExcess(meals, caloriesPerDay, meal -> true);
    }

    public static List<MealTo> getFilteredWithExcess(Collection<Meal> meals, int caloriesPerDay, @Nullable LocalTime startTime, @Nullable LocalTime endTime) {
        return getFilteredWithExcess(meals, caloriesPerDay, meal -> Util.isBetween(meal.getTime(), startTime, endTime));
    }

    private static List<MealTo> getFilteredWithExcess(Collection<Meal> meals, int caloriesPerDay, Predicate<Meal> filter) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(filter)
                .map(meal -> createWithExcess(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(toList());
    }

    private static MealTo createWithExcess(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}