package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.util.MealsUtil.USER_MEALS;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    Logger logger = LoggerFactory.getLogger(MealServiceTest.class.getSimpleName());

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    /*@Before
    public void setUp() throws Exception {
        USER_MEALS.forEach(meal -> service.update(meal,USER_ID));
    }*/

    /*@After
    public void tearDown() throws Exception {
        USER_MEALS.forEach(meal -> service.delete(meal.getId(),USER_ID));
    }*/

    @Test
    public void get() {
        Meal createdMeal = service.create(MEAL1, USER_ID);
        Meal actualMeal;
        if (createdMeal != null) {
            actualMeal = service.get(createdMeal.getId(), USER_ID);
            service.delete(createdMeal.getId(), USER_ID);
        } else {
            actualMeal = MEAL1;
        }
        assertThat(actualMeal).isEqualTo(MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        Meal createdMeal = service.create(MEAL1, USER_ID);
        service.delete(createdMeal.getId(), USER_ID);
        service.get(createdMeal.getId(), USER_ID);
    }

    @Test
    public void getBetweenDates() {
        LocalDate startDate = LocalDate.of(2019, 10, 4);
        LocalDate endDate = LocalDate.of(2019, 3, 5);
        List<Meal> mealsBetweenDates = service.getBetweenDates(startDate, endDate, USER_ID);
        int size = mealsBetweenDates.size();
        if (size > 0) {
            LocalDate meal0Date = mealsBetweenDates.get(0).getDate();
            assertThat(meal0Date).isAfterOrEqualTo(startDate);
            assertThat(meal0Date).isBeforeOrEqualTo(endDate);
            if (size > 1) {
                LocalDate lastMealDate = mealsBetweenDates.get(size - 1).getDate();
                assertThat(lastMealDate).isAfterOrEqualTo(startDate);
                assertThat(lastMealDate).isBeforeOrEqualTo(endDate);
            }
        }
    }

    @Test
    public void getBetweenDateTimes() {
        LocalDateTime startDateTime = LocalDateTime.of(2019, 4, 10, 7, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2019, 3, 5, 22, 0);
        List<Meal> mealsBetweenDateTimes = service.getBetweenDateTimes(startDateTime, endDateTime, SecurityUtil.authUserId());
        for (Meal meal : mealsBetweenDateTimes) {
            assertThat(meal.getDateTime()).isBetween(startDateTime, endDateTime);
        }
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        logger.info(meals.toString());
        assertMatch(meals, USER_MEALS);
    }

    @Test
    public void update() {
        Meal userMeal = service.getAll(USER_ID).get(0);
        logger.info(userMeal.toString());
        Meal updateMeal = new Meal(MealTestData.MEAL1.getDateTime(), MealTestData.MEAL1.getDescription(), MealTestData.MEAL1.getCalories());
        updateMeal.setId(userMeal.getId());
        service.update(updateMeal, USER_ID);
        logger.info(service.get(updateMeal.getId(), USER_ID).toString());
        assertMatch(service.get(updateMeal.getId(), USER_ID), updateMeal);
    }

    @Test
    public void create() {
        Meal createdMeal = service.create(MEAL1, SecurityUtil.authUserId());
        service.delete(createdMeal.getId(), USER_ID);
        logger.info("create({})", SecurityUtil.authUserId());
        logger.info(createdMeal.toString());
        assertThat(MEAL1).isEqualTo(createdMeal);
    }

    @Test(expected = NotFoundException.class)
    public void getAnotherUserMeal() {
        logger.info("getAnotherUserMeal()");
        service.get(100_002, 100_001);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAnotherUserMeal() {
        service.delete(100_002, 100_001);
    }

    @Test(expected = NotFoundException.class)
    public void updateAnotherUserMeal() {
        SecurityUtil.setAuthUserId(100_000);
        service.update(MEAL1, 100_001);
    }
}