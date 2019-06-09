package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    private static final String BREAKFAST = "Breakfast";
    private static final String LUNCH = "Lunch";
    private static final String DINNER = "Dinner";

    private static final Meal USER_MEAL_1 = new Meal(LocalDateTime.of(2019, Month.APRIL, 3, 8, 34), BREAKFAST, 500);
    private static final Meal USER_MEAL_2 = new Meal(LocalDateTime.of(2019, Month.APRIL, 3, 12, 45), LUNCH, 1000);
    private static final Meal USER_MEAL_3 = new Meal(LocalDateTime.of(2019, Month.APRIL, 3, 19, 20), DINNER, 700);
    private static final Meal USER_MEAL_4 = new Meal(LocalDateTime.of(2019, Month.APRIL, 4, 7, 43), BREAKFAST, 600);
    private static final Meal USER_MEAL_5 = new Meal(LocalDateTime.of(2019, Month.APRIL, 4, 12, 19), LUNCH, 1200);
    private static final Meal USER_MEAL_6 = new Meal(LocalDateTime.of(2019, Month.APRIL, 4, 19, 7), DINNER, 500);
    private static final Meal USER_MEAL_7 = new Meal(LocalDateTime.of(2019, Month.APRIL, 5, 8, 16), BREAKFAST, 400);
    private static final Meal USER_MEAL_8 = new Meal(LocalDateTime.of(2019, Month.APRIL, 5, 12, 50), LUNCH, 1300);
    private static final Meal USER_MEAL_9 = new Meal(LocalDateTime.of(2019, Month.APRIL, 5, 18, 50), DINNER, 700);
    private static final Meal USER_MEAL_10 = new Meal(LocalDateTime.of(2019, Month.APRIL, 6, 8, 42), BREAKFAST, 500);
    private static final Meal USER_MEAL_11 = new Meal(LocalDateTime.of(2019, Month.APRIL, 6, 13, 10), LUNCH, 1000);
    private static final Meal USER_MEAL_12 = new Meal(LocalDateTime.of(2019, Month.APRIL, 6, 19, 30), DINNER, 500);

    private static final List<Meal> meals = new ArrayList<>();

    private void fillMeals(){
        meals.add(USER_MEAL_1);
        meals.add(USER_MEAL_2);
        meals.add(USER_MEAL_3);
        meals.add(USER_MEAL_4);
        meals.add(USER_MEAL_5);
        meals.add(USER_MEAL_6);
        meals.add(USER_MEAL_7);
        meals.add(USER_MEAL_8);
        meals.add(USER_MEAL_9);
        meals.add(USER_MEAL_10);
    }
    
    public List<Meal> getMeals(){
        fillMeals();
        return meals;
    }

    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!");
    }
}
