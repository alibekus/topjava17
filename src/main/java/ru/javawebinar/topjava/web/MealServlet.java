package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);
    private MealService mealService;
    private List<MealTo> excessMeals;
    private List<Meal> meals;
    private static final int LIMIT_DAY_CALORIES = 2000;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealService = new MealService().mealStorageInitializer();
        meals = mealService.getAll();
        excessMeals = MealsUtil.getFilteredWithExcess(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), LIMIT_DAY_CALORIES);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        LOG.debug("Redirect to meals. Action is " + action);
        request.setAttribute("dayCalories", LIMIT_DAY_CALORIES);
        if (action == null) {
            request.setAttribute("excessMeals", excessMeals);
            request.getRequestDispatcher("WEB-INF/jsp/meals.jsp").forward(request, response);
            return;
        }
        Meal meal;
        switch (action) {
            case "delete":
                mealService.delete(Long.parseLong(request.getParameter("mealId")));
                meals = mealService.getAllSorted();
                excessMeals = MealsUtil.getFilteredWithExcess(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), LIMIT_DAY_CALORIES);
                request.setAttribute("excessMeals", excessMeals);
                request.getRequestDispatcher("WEB-INF/jsp/meals.jsp").forward(request, response);
                return;
            case "edit":
                meal = mealService.get(Long.parseLong(request.getParameter("mealId")));
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("WEB-INF/jsp/meal.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("dayCalories", LIMIT_DAY_CALORIES);
        long id = Long.parseLong(request.getParameter("mealId"));
        String desc = request.getParameter("desc");
        LocalDate date = LocalDate.parse(request.getParameter("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime time = LocalTime.parse(request.getParameter("time"), DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        int calories = Integer.parseInt(request.getParameter("cals"));
        Meal meal = new Meal(id, dateTime, desc, calories);
        mealService.update(meal);
        meals = mealService.getAllSorted();
        excessMeals = MealsUtil.getFilteredWithExcess(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        request.setAttribute("excessMeals", excessMeals);
        request.getRequestDispatcher("WEB-INF/jsp/meals.jsp").forward(request, response);
    }
}
