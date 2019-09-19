package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            controller = appCtx.getBean(MealRestController.class);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String userId = request.getParameter("user-id");
        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("date-time")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (userId.equals("")) {
            meal.setUserId(SecurityUtil.authUserId());
        } else {
            meal.setUserId(Integer.parseInt(userId));
        }
        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        if (meal.isNew()) {
            controller.create(meal);
        } else {
            controller.update(meal, meal.getId());
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                controller.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        controller.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "filter":
                log.info("getFiltered by date and time");
                String dateFromString = request.getParameter("date-from");
                String dateToString = request.getParameter("date-to");
                String timeFromString = request.getParameter("time-from");
                String timeToString = request.getParameter("time-to");
                log.info("From date: " + dateFromString + " and time: " + timeFromString);
                log.info("To date: " + dateToString + " and time: " + timeToString);
                LocalDate dateFrom = DateTimeUtil.convertToDate(dateFromString);
                LocalDate dateTo = DateTimeUtil.convertToDate(dateToString);
                List<LocalTime> localTimes = DateTimeUtil.convertToTime(timeFromString, timeToString);
                final LocalDateTime dateTimeFrom = LocalDateTime.of(dateFrom,localTimes.get(0));
                final LocalDateTime dateTimeTo = LocalDateTime.of(dateTo, localTimes.get(1));
                request.setAttribute("dateFrom", dateTimeFrom.toLocalDate().toString());
                request.setAttribute("dateTo", dateTimeTo.toLocalDate().toString());
                request.setAttribute("timeFrom", dateTimeFrom.toLocalTime().toString());
                request.setAttribute("timeTo", dateTimeTo.toLocalTime().toString());
                request.setAttribute("meals",
                        MealsUtil.getWithExcess(controller.getAllBetweenDateTime(dateTimeFrom,dateTimeTo),
                                MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        MealsUtil.getWithExcess(controller.getAll(SecurityUtil.authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
