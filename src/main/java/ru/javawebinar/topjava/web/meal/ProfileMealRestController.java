package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;


@Controller
public class ProfileMealRestController extends MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    Integer userId = SecurityUtil.authUserId();

    @Override
    public Collection<MealTo> getAll()
    {
        return service.getAll(userId);
    }

    @Override
    public MealTo get(int id) {
        return service.get(id);
    }

    @Override
    public MealTo create(MealTo mealTo) {
        MealTo updated =  service.create(mealTo, userId);
        return updated;
    }

    @Override
    public void delete(int id) {
        service.delete(id, userId);
    }

    @Override
    public void update(MealTo mealTo, Integer usId) {
        System.out.println("Meal to from profile" + mealTo.getUserId() + mealTo);
        super.update(mealTo, userId);
    }

}