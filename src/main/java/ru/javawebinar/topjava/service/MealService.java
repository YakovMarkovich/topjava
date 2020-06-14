package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;
import java.util.ArrayList;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public MealTo create(MealTo mealTo, Integer userId) {
        Meal meal = createMeal(mealTo);
        Meal mealFrom =  repository.save(meal, userId);
        MealTo newMealTwo =  createMealTo(mealFrom);
        return newMealTwo;
    }

    public void delete(int id, Integer userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public MealTo get(int id) {
        Meal meal = checkNotFoundWithId(repository.get(id), id);
        return createMealTo(meal);
    }

    public Collection<MealTo> getAll(Integer userId) {
        Collection<Meal> meals = repository.getAll(userId);
        Collection<MealTo> mealsTo = new ArrayList<>();
        for(Meal m : meals)
            mealsTo.add(createMealTo(m));
        return mealsTo;
    }

    public void update(MealTo mealTo, Integer userId) {
        Meal meal = createMeal(mealTo);
        if(meal.getUserId()!=userId){
            throw new NotFoundException("User id not correspond meal user id");
        }
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    private Meal createMeal(MealTo mealTo) {
        Meal meal = new Meal(mealTo.getId(), mealTo.getDateTime(),
                mealTo.getDescription(), mealTo.getCalories());
        meal.setUserId(mealTo.getUserId());
        return meal;
    }

    private MealTo createMealTo(Meal meal) {
        MealTo mealTo =  new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(),
                meal.getCalories(),
                repository.getAll(meal.getUserId())
                        .stream()
                        .filter(e -> e.getDateTime().toLocalDate().equals(meal.getDate()))
                        .mapToInt(c -> c.getCalories())
                        .sum() > SecurityUtil.authUserCaloriesPerDay());
        mealTo.setUserId(meal.getUserId());
        return mealTo;
    }

}