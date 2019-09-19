package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    private final Logger logger = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<Meal> getAll(int userId) {
        logger.info("getAll meal");
        return service.getAll(userId);
    }

    public Meal get(int id) {
        logger.info("get meal {} ", id);
        return service.get(id);
    }

    public Meal create(Meal meal) {
        logger.info("create meal {}", meal);
        checkNew(meal);
        return service.create(meal);
    }

    public void delete(int id) {
        logger.info("delete meal {}", id);
        service.delete(id);
    }

    public void update(Meal meal, int id) {
        logger.info("update meal {}", id);
        assureIdConsistent(meal, id);
        service.update(meal);
    }

    public List<Meal> getByUserId(int id) {
        logger.info("get meal by user id = " + id);
        return service.getByUserId(id).stream().collect(Collectors.toList());
    }

    public List<Meal> getAllBetweenDateTime(LocalDateTime start, LocalDateTime end) {
        logger.info("getAllBetweenDateTime between " + start + " and " + end);
        return service.getAllBetweenDateTime(start,end);
    }
}