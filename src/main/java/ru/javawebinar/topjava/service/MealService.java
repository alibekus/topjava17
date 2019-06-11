package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MealService implements Service<Meal> {

    private List<Meal> meals;

    public MealService mealStorageInitializer() {
        this.meals = new ArrayList<>(new MealStorage().getMeals());
        return this;
    }

    @Override
    public int size() {
        return meals.size();
    }

    @Override
    public Meal get(long id) {
        return meals.stream().filter(mt -> mt.getId() == id).findFirst().get();
    }

    @Override
    public List<Meal> getAllSorted() {
        return meals.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public void save(Meal meal) {
        meals.add(meal);
    }

    @Override
    public void update(Meal meal) {
        delete(meal.getId());
        meals.add(meal);
    }

    @Override
    public void delete(long id) {
        Meal meal = get(id);
        meals.remove(meal);
    }

    @Override
    public void clear() {
        meals.clear();
    }
}
