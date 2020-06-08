package ru.javawebinar.topjava.web;

import com.sun.org.glassfish.gmbal.Description;
import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsRepo;
import ru.javawebinar.topjava.dao.MealsRepoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static String INSERT = "/meals.jsp";
    private static String EDIT = "/mealsEdit.jsp";
    private static String LIST_MEALS = "/listMeals.jsp";
    private MealsRepo repository;

    public MealServlet() {
        super();
        repository = new MealsRepoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        ServletContext context = request.getServletContext();
        context.setAttribute("mealId", request.getParameter("mealId"));
        if (action != null && action.equalsIgnoreCase("delete")) {
            forward = LIST_MEALS;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            repository.deleteMeal(mealId);
            request.setAttribute("meals", createAllMealToList(repository.getAllMeals()));
        } else if (action != null && action.equalsIgnoreCase("edit")) {
            forward = EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = repository.getMealById(mealId);
            log.debug("55" + meal);
            request.setAttribute("meal", meal);
        } else if (action == null || action.equalsIgnoreCase("listUser")) {
            forward = LIST_MEALS;
            request.setAttribute("meals", createAllMealToList(repository.getAllMeals()));
        } else {
            forward = INSERT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (getServletContext().getAttribute("mealId") == null) {
            Meal meal = createNewMeal(request);
            repository.addMeal(meal);
        } else {
            int id = Integer.valueOf(String.valueOf(getServletContext().getAttribute("mealId")));
            Meal meal = mealUpdation(request, id);
            repository.updateMeal(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEALS);
        request.setAttribute("meals", createAllMealToList(repository.getAllMeals()));
        view.forward(request, response);
    }

    private List<MealTo> createAllMealToList(List<Meal> allMeals) {
        return MealsUtil.filteredByStreams(allMeals, LocalTime.MIN, LocalTime.MAX, 2000);
    }

    private Meal mealUpdation(HttpServletRequest request, int id) {
        LocalDateTime ld;
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        if (request.getParameter("dateMeal") != "") {
            ld = LocalDateTime.parse(request.getParameter("dateMeal"), ft);
        } else
            ld = repository.getMealById(Integer.valueOf(String.valueOf(getServletContext().getAttribute("mealId")))).getDateTime();
        String description;
        if (request.getParameter("description") != "") {
            description = request.getParameter("description");
        } else
            description = repository.getMealById(Integer.valueOf(String.valueOf(getServletContext().getAttribute("mealId")))).getDescription();
        int calories;
        if (request.getParameter("calories") != "") {
            calories = Integer.valueOf(request.getParameter("calories"));
        } else
            calories = repository.getMealById(Integer.valueOf(String.valueOf(getServletContext().getAttribute("mealId")))).getCalories();
        Meal meal = new Meal(ld, description, calories);
        meal.id = id;
        return meal;
    }

    private Meal createNewMeal(HttpServletRequest request) {
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime ld = LocalDateTime.parse(request.getParameter("dateMeal"), ft);
        String description = request.getParameter("description");
        int calories = Integer.valueOf(request.getParameter("calories"));
        Meal meal = new Meal(ld, description, calories);
        return meal;
    }

}
