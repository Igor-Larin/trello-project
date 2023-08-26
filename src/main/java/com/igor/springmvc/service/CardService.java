package com.igor.springmvc.service;

import com.igor.springmvc.model.Card;
import jakarta.persistence.EntityManagerFactory;
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
    private final SessionFactory sessionFactory;
    @Autowired
    public CardService(EntityManagerFactory factory) {
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Card> readAll(int id) {
        return sessionFactory.getCurrentSession().createNamedQuery("Card.getAll", Card.class).setParameter("deskId", id).list();
    }

    @Override
    public void update(Card entity, int id) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().createNamedQuery("Card.delete").setParameter("cardId", id);
    }
}
