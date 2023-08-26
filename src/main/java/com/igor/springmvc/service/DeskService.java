package com.igor.springmvc.service;

import com.igor.springmvc.model.Desk;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Repository
public class DeskService implements CommonService<Desk> {
    private final SessionFactory sessionFactory;
    @Autowired
    public DeskService(EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

/*    @Override
    public void create(Desk entity) {

    }*/
    @Transactional(readOnly = true)
    @Override
    public List<Desk> readAll(int id) {

        return sessionFactory.getCurrentSession().createNamedQuery("Desk.getAll", Desk.class).setParameter("userId", id).list();

    }

    @Override
    public void update(Desk entity, int id)
    {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().createNamedQuery("Desk.delete").setParameter("deskId", id);
    }
}
