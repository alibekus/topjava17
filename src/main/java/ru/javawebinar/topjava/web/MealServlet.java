package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.IdGenerator;
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
        String mode = request.getParameter("mode");
        LOG.debug("Redirect to meals. Action is " + mode);
        request.setAttribute("dayCalories", LIMIT_DAY_CALORIES);
        Meal meal = null;
        switch (mode) {
            case "delete":
                mealService.delete(Long.parseLong(request.getParameter("mealId")));
                meals = mealService.getAllSorted();
                excessMeals = MealsUtil.getFilteredWithExcess(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), LIMIT_DAY_CALORIES);
                request.setAttribute("excessMeals", excessMeals);
                response.sendRedirect("meals");
                return;
            case "edit":
                meal = mealService.get(Long.parseLong(request.getParameter("mealId")));
                break;
            case "create":
                long id = IdGenerator.getInstance().generateId();
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime dateTime = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),now.getHour(),now.getMinute());
                meal = new Meal(id,dateTime,"",0);
                break;
            default:
                request.setAttribute("excessMeals", excessMeals);
                request.getRequestDispatcher("WEB-INF/jsp/meals.jsp").forward(request, response);
        }
        request.setAttribute("meal", meal);
        request.setAttribute("mode",mode);
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
        String mode = request.getParameter("mode");
        Meal meal = new Meal(id, dateTime, desc, calories);
        if (mode.equals("edit")) {
            mealService.update(meal);
        } else {
            mealService.save(meal);
        }
        meals = mealService.getAllSorted();
        excessMeals = MealsUtil.getFilteredWithExcess(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), LIMIT_DAY_CALORIES);
        request.setAttribute("excessMeals", excessMeals);
        request.getRequestDispatcher("WEB-INF/jsp/meals.jsp").forward(request, response);
    }
}
