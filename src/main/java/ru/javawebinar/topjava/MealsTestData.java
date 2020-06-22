package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

public class MealsTestData {

    public static final Meal MEAL1 = new Meal(ADMIN_ID, LocalDateTime.of(2015, Month.JANUARY, 6, 14, 00), "Админ ланч",510);
    public static final Meal MEAL2 = new Meal(ADMIN_ID, LocalDateTime.of(2015, Month.JANUARY, 6, 21, 00), "Админ ужин",1500);
    public static final Meal MEAL3 = new Meal(USER_ID, LocalDateTime.of(2015, Month.JANUARY, 30, 10, 00), "Завтрак",1500);
    public static final Meal MEAL4 = new Meal(USER_ID, LocalDateTime.of(2015, Month.JANUARY, 30, 13, 00), "Обед",1000);
    public static final Meal MEAL5 = new Meal(USER_ID, LocalDateTime.of(2015, Month.JANUARY, 31, 20, 00), "Ужин",1500);
    public static final Meal MEAL6 = new Meal(USER_ID, LocalDateTime.of(2015, Month.JANUARY, 31, 00, 00), "Еда на граничное значение",100);
    public static final Meal MEAL7 = new Meal(USER_ID, LocalDateTime.of(2015, Month.JANUARY, 31, 10, 00), "Завтрак",1000);
    public static final Meal MEAL8 = new Meal(USER_ID, LocalDateTime.of(2015, Month.JANUARY, 31, 13, 00), "Обед",500);
    public static final Meal MEAL9 = new Meal(USER_ID, LocalDateTime.of(2015, Month.JANUARY, 11, 20, 00), "Ужин",410);

    public static Meal getNewMeal() {
        return new Meal(null, LocalDateTime.now(), "newMeal", 500);
    }

    public static Meal getUpdatedMeal() {
        Meal updated = new Meal(MEAL3);
        updated.setDescription("Updated Brealkfast");
        updated.setCalories(1001);
        return updated;
    }

    public static void assertMatchMeal(Iterable<Meal> actual, Meal... expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
