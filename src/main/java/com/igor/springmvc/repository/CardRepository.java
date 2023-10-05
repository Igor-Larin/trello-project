package com.igor.springmvc.repository;

import com.igor.springmvc.model.Card;
import com.igor.springmvc.model.Desk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
    Optional<List<Card>> findAllByDesk(Desk desk);
}
