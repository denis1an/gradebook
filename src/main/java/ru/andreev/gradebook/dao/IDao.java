package ru.andreev.gradebook.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    Optional<T> findById(Integer id);

    List<T> findAll();

    void save(T t);

    void update(T t);

    void delete(Integer id);

}
