package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsRepoImpl implements MealsRepo {
    Map<Integer, Meal> mealsMap;
    private AtomicInteger count = new AtomicInteger(0);


    public MealsRepoImpl() {
        mealsMap = new ConcurrentHashMap<>();
    }

    @Override
    public void addMeal(Meal meal) {
        meal.id = count.incrementAndGet();
        mealsMap.put(meal.id, meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealsMap.put(meal.id, meal);
    }

    @Override
    public void deleteMeal(int id) {
        mealsMap.remove(id);
    }

    @Override
    public List<Meal> getAllMeals() {
        List<Meal> meals = new ArrayList<>(mealsMap.values());
        return meals;
    }

    @Override
    public Meal getMealById(int userId) {
        return mealsMap.get(userId);
    }
}
