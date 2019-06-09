package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    int size();

    Meal get(String uuid);

    List<Meal> getAllSorted();

    void save(Meal resume);

    void update(Meal resume);

    void delete(String uuid);

    void clear();
}
