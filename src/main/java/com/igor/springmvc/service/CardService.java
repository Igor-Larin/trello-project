package com.igor.springmvc.service;

import com.igor.springmvc.model.Card;
import com.igor.springmvc.model.Desk;
import com.igor.springmvc.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CardService implements CommonService<Card> {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Card> readAll(int id) {
        return entityManager.createNamedQuery("Card.getAll", Card.class).setParameter("deskId", id).getResultList();
    }

    private Card findById(int id) {
        return entityManager.find(Card.class, id);
    }

    private Desk findDeskById(int id) {
        return entityManager.find(Desk.class, id);
    }

    @Override
    public Integer update(Card entity, int id) {
        entity.setDesk(findDeskById(id));
        if(entity.getId() == null)
            entityManager.persist(entity);
        else
            entityManager.merge(entity);
        return entity.getId();
    }

    @Override
    public void delete(int id) {
        entityManager.remove(findById(id));
    }
}
