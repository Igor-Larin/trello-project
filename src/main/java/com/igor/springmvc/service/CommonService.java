package com.igor.springmvc.service;

import com.igor.springmvc.model.User;

import java.util.List;

public interface CommonService<T> {
    int create(T entity);
    List<T> readAll(int id);
    void update(T entity);
    void delete(int id);
}
