package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(InMemoryMealRepositoryImpl.this::save);
    }

    @Override
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            int userId = SecurityUtil.authUserId();
            meal.setUserId(userId == 0 ? 1 : userId);
            repository.put(meal.getId(), meal);
            log.info("Meal id: " + meal.getId());
            log.info("User id of meal: " + meal.getUserId());
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        log.info("get {}", id);
        int currentUserId = SecurityUtil.authUserId();
        Meal meal = repository.get(id);
        if (meal.getUserId() == currentUserId) {
            return meal;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("getAll");
        return repository.values().stream().filter(meal -> meal.getUserId() == userId)
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
        log.info("getMealByUserId {}", userId);
        return repository.values().stream().filter(meal -> meal.getUserId() == userId).collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllBetweenDateTime(LocalDateTime start, LocalDateTime end) {
        return repository.values().stream().filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(),start,end)).collect(Collectors.toList());
    }
}

