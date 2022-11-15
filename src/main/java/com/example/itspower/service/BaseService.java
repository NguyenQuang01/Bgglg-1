package com.example.itspower.service;

import java.util.List;

public interface BaseService<T, ID> {

    List<T> getAll();

    T getById(ID id);

    T creatOrUpdate(ID id, T t);

    boolean deleteById(ID id);
}
