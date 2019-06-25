package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(SecurityUtil.authUserId());
            repository.put(meal.getId(), meal);
            System.out.println(meal.getId());
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        int currentUserId = SecurityUtil.authUserId();
        Meal meal = repository.get(id);
        if (meal.getUserId() == currentUserId) {
            return meal;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values().stream().filter(meal ->
                meal.getUserId() == SecurityUtil.authUserId())
                .sorted((meal1, meal2) -> {
                    switch (meal1.getDate().compareTo(meal2.getDate())) {
                        case -1:
                            return 1;
                        case 1:
                            return -1;
                        default:
                            return 0;
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getMealByUserId(int userId) {
        return repository.values().stream().filter(meal -> meal.getUserId() == userId).collect(Collectors.toList());
    }
}

