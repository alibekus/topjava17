package ru.javawebinar.topjava.service;

import java.util.List;

public interface Service<MT> {

    int size();

    MT get(long id);

    List<MT> getAll();

    List<MT> getAllSorted();

    void save(MT meal);

    void update(MT meal);

    void delete(long id);

    void clear();
}
