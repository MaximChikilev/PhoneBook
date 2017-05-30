package com.phonebook.dao;

import java.util.List;

public interface Dao<T> {

    void create(T object);

    void update(T object);

    void delete(long userId);

    List<T> getAll(long userId);

    List<T> getAll(T object);

}
