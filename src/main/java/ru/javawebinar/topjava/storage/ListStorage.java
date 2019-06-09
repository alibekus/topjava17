package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class ListStorage implements Storage {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public Meal get(String uuid) {
        return null;
    }

    @Override
    public List<Meal> getAllSorted() {
        return null;
    }

    @Override
    public void save(Meal resume) {

    }

    @Override
    public void update(Meal resume) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public void clear() {

    }
}
