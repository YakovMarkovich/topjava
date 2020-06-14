package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import java.util.Collection;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;


public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public MealService service;

    public Collection<MealTo> getAll() {
        log.info("getAll");
        return service.getAll(null);
    }

    public MealTo get(int id) {
        log.info("get {}" + id);
        return service.get(0);
    }

    public MealTo create(MealTo mealTo) {
        log.info("create {}", mealTo);
        checkNew(mealTo);
        return service.create(mealTo, null);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, null);
    }

    public void update(MealTo mealTo, Integer userId) {
        log.info("update {}", mealTo);
        service.update(mealTo, userId);
    }

}