package com.igor.springmvc.service;

import java.util.List;

public interface CommonService<T> {
    //void create(T entity);
    List<T> readAll(int id);
    void update(T entity, int id);
    void delete(int id);
}
