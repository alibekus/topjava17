package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MealService implements Service<Meal> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MealService.class.getName());
    private List<Meal> meals;

    public MealService mealStorageInitializer() {
        LOGGER.info("Meals initialization");
        this.meals = new ArrayList<>(new MealStorage().getMeals());
        return this;
    }

    @Override
    public int size() {
        LOGGER.info("Meals size getting");
        return meals.size();
    }

    @Override
    public Meal get(long id) {
        LOGGER.info("Meal of " + id + " getting");
        return meals.stream().filter(mt -> mt.getId() == id).findFirst().get();
    }

    @Override
    public List<Meal> getAllSorted() {
        LOGGER.info("Getting sorted meals");
        return meals.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAll() {
        LOGGER.info("Getting unmodifiable list of meals");
        return Collections.unmodifiableList(meals);
    }

    @Override
    public void save(Meal meal) {
        LOGGER.info("Saving meal " + meal);
        meals.add(meal);
    }

    @Override
    public void update(Meal meal) {
        LOGGER.info("Updating meal: " + meal);
        delete(meal.getId());
        meals.add(meal);
    }

    @Override
    public void delete(long id) {
        LOGGER.info("Deleting meal with id: " + id);
        Meal meal = get(id);
        meals.remove(meal);
    }

    @Override
    public void clear() {
        LOGGER.info("Clearing meals storage");
        meals.clear();
    }
}
