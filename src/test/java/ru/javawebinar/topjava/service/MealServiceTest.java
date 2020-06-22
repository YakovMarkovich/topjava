package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static ru.javawebinar.topjava.MealsTestData.*;
import static org.junit.Assert.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private MealRepository repository;

    @Test
    public void create() throws Exception {
        Meal newMeal = getNewMeal();
        Meal created = service.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertEquals(newMeal, created);
        assertEquals(newMeal, service.get(newId, USER_ID));
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL4.getId(), USER_ID);
        assertEquals(MEAL4, meal);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(MEAL1.getId(), USER_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL4.getId(), USER_ID);
        assertNull(repository.get(MEAL4.getId(), USER_ID));
    }

    @Test
    public void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL1.getId(), USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> all = service.getBetweenInclusive(LocalDate.of(1, Month.JANUARY, 2015),
                LocalDate.of(1, Month.JANUARY, 2015), ADMIN_ID);
        assertMatchMeal(all, MEAL1, MEAL2);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatchMeal(all, MEAL3, MEAL4, MEAL5, MEAL6, MEAL7, MEAL8, MEAL9);
    }

    @Test
    public void update() {
        Meal updated = getUpdatedMeal();
        service.update(updated, USER_ID);
        assertEquals(service.get(MEAL3.getId(),USER_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        Meal updated = getUpdatedMeal();
        assertThrows(NotFoundException.class, () -> service.update(updated, ADMIN_ID));
    }

}