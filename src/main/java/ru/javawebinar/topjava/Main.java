package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getSimpleName());

    private static final String BREAKFAST = "Breakfast";
    private static final String LUNCH = "Lunch";
    private static final String DINNER = "Dinner";

    private static final UserMeal USER_MEAL_1 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 3, 8, 34), BREAKFAST, 500);
    private static final UserMeal USER_MEAL_2 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 3, 12, 45), LUNCH, 1000);
    private static final UserMeal USER_MEAL_3 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 3, 19, 20), DINNER, 700);
    private static final UserMeal USER_MEAL_4 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 4, 7, 43), BREAKFAST, 600);
    private static final UserMeal USER_MEAL_5 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 4, 12, 19), LUNCH, 1200);
    private static final UserMeal USER_MEAL_6 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 4, 19, 7), DINNER, 500);
    private static final UserMeal USER_MEAL_7 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 5, 8, 16), BREAKFAST, 400);
    private static final UserMeal USER_MEAL_8 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 5, 12, 50), LUNCH, 1300);
    private static final UserMeal USER_MEAL_9 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 5, 18, 50), DINNER, 700);
    private static final UserMeal USER_MEAL_10 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 6, 8, 42), BREAKFAST, 500);
    private static final UserMeal USER_MEAL_11 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 6, 13, 10), LUNCH, 1000);
    private static final UserMeal USER_MEAL_12 = new UserMeal(LocalDateTime.of(2019, Month.APRIL, 6, 19, 30), DINNER, 500);

    private static List<UserMeal> userMeals = new ArrayList<>();

    public static void main(String[] args) {
        LOG.info("User meals with exceed\n");
        userMeals.add(USER_MEAL_1);
        userMeals.add(USER_MEAL_2);
        userMeals.add(USER_MEAL_3);
        userMeals.add(USER_MEAL_4);
        userMeals.add(USER_MEAL_5);
        userMeals.add(USER_MEAL_6);
        userMeals.add(USER_MEAL_7);
        userMeals.add(USER_MEAL_8);
        userMeals.add(USER_MEAL_9);
        userMeals.add(USER_MEAL_10);
        userMeals.add(USER_MEAL_11);
        userMeals.add(USER_MEAL_12);
        List<UserMealWithExceed> userMealWithExceeds
                = UserMealsUtil.getFilteredWithExceeded(userMeals, LocalTime.of(8, 0), LocalTime.of(19, 0), 2000);
        for (UserMealWithExceed mealWithExceed : userMealWithExceeds) {
            LOG.info(mealWithExceed.toString());
        }
    }
}
