package ru.specialist.spring.dao;

import java.util.List;

public interface AbstractDao<T> {

    //DAO - Data Access Object
    //CRUD - Create Read Update Delete

    void create(T data);

    List<T> getAll();

    T getById(long id);

    void update(T data);

    void delete(long id);


}
