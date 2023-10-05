package com.igor.springmvc.repository;

import com.igor.springmvc.model.Card;
import com.igor.springmvc.model.Desk;
import com.igor.springmvc.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    Optional<List<Task>> findAllByCard(Card card);
}
