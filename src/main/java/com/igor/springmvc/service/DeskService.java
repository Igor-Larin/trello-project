package com.igor.springmvc.service;

import com.igor.springmvc.model.Desk;
import com.igor.springmvc.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Repository
public class DeskService implements CommonService<Desk> {
    @PersistenceContext
    private EntityManager entityManager;

/*    @Override
    public void create(Desk entity) {

    }*/
    @Transactional(readOnly = true)
    @Override
    public List<Desk> readAll(int id) {
        return entityManager.createNamedQuery("Desk.getAll", Desk.class).setParameter("userId", id).getResultList();
    }

    @Override
    public Integer update(Desk entity, int id)
    {
        entity.setUser(findUserById(id));
        if (entity.getId() == null) {
            System.out.println("in persist");
            entityManager.persist(entity);
        }
        else {
            System.out.println("in merge");
            entityManager.merge(entity);
        }
        System.out.println(entity.getId());
        return entity.getId();
    }

    private Desk findById(int id) {
        return entityManager.find(Desk.class, id);
    }

    private User findUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void delete(int id) {
        System.out.println("Deleting the desk");
        //entityManager.createNamedQuery("Desk.delete").setParameter("deskId", id);
        entityManager.remove(findById(id));
    }
}
