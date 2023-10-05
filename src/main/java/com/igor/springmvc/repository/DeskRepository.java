package com.igor.springmvc.repository;

import com.igor.springmvc.model.Desk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeskRepository extends CrudRepository<Desk, Integer> {
}
