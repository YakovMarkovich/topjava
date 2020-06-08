package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealsRepo {

    void addMeal(Meal meal);
    void updateMeal(Meal meal);
    void deleteMeal(int id);
    List<Meal> getAllMeals();
    Meal getMealById(int userId);
}
