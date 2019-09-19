package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.UsersUtil;
import ru.javawebinar.topjava.web.user.AbstractUserController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private AbstractUserController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            controller = appCtx.getBean(AdminRestController.class);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("user-id");
        String action = request.getParameter("action");
        SecurityUtil.setAuthUserId(Integer.parseInt(id));
        if (action.equals("user-auth")) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        log.info("after user auth");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Integer caloriesPerDay = Integer.parseInt(request.getParameter("calories-per-day"));
        String[] roleNames = request.getParameterValues("roles");
        log.info("roles: " + Arrays.toString(roleNames));
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            roles.add(Role.valueOf(roleName));
        }
        User user = new User(id.isEmpty() ? null : Integer.valueOf(id), name, email, password, caloriesPerDay, true, roles);
        log.info(user.isNew() ? "Create {}" : "Update {}", user);
        if (user.isNew()) {
            controller.create(user);
        } else {
            controller.update(user, Integer.parseInt(id));
        }
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        String action = request.getParameter("action");
        log.info("action: " + action);
        int userId;
        switch (action == null ? "all" : action) {
            case "create":
            case "update":
                log.info(action);
                final User user = "create".equals(action) ?
                        new User(null, "", "", "", Role.USER) :
                        controller.get(getId(request));
                request.setAttribute("user", user);
                request.getRequestDispatcher("/userForm.jsp").forward(request, response);
                break;
            case "delete":
                userId = getId(request);
                log.info("Get user: " + userId);
                controller.delete(userId);
                response.sendRedirect("users");
                break;
            case "all":
            default:
                log.info("getAll");
                List<User> usersSortedByName = UsersUtil.getUsersSortedByName(controller.getAll());
                log.info(usersSortedByName.toString());
                request.setAttribute("users", usersSortedByName);
                request.getRequestDispatcher("/users.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
