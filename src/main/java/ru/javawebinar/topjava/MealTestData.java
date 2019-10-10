package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final Meal MEAL1 = new Meal(LocalDateTime.of(2019, 3, 5, 7, 45), "Breakfast", 555);
    public static final Meal MEAL2 = new Meal(LocalDateTime.of(2019, 3, 5, 13, 5), "Lunch", 1234);
    public static final Meal MEAL3 = new Meal(LocalDateTime.of(2019, 3, 5, 20, 10), "Dinner", 876);

    public static final Meal MEAL4 = new Meal(LocalDateTime.of(2019, 3, 5, 8, 0), "Завтрак", 850);
    public static final Meal MEAL5 = new Meal(LocalDateTime.of(2019, 3, 5, 13, 30), "Обед", 1230);
    public static final Meal MEAL6 = new Meal(LocalDateTime.of(2019, 3, 5, 19, 30), "Ужин", 940);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id").isEqualTo(expected);
    }
}
