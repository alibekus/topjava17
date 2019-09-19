package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository){
        this.repository = repository;
    }

    @Override
    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        int currentUserId = SecurityUtil.authUserId();
        Meal meal = checkNotFoundWithId(repository.get(id), id);
        if (currentUserId != meal.getUserId()) {
            throw new NotFoundException("The meal is not belong to current user");
        }
        return meal;
    }

    @Override
    public Collection<Meal> getByUserId(int id) throws NotFoundException {
        return repository.getMealByUserId(id);
    }

    @Override
    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

    @Override
    public List<Meal> getAll(int userId) {
        return (List<Meal>) repository.getAll(userId);
    }

    @Override
    public List<Meal> getAllBetweenDateTime(LocalDateTime start, LocalDateTime end) {
        return repository.getAllBetweenDateTime(start,end);
    }
}