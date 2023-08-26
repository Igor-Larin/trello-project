package com.igor.springmvc.service;

import com.igor.springmvc.model.Task;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService implements CommonService<Task> {
    private SessionFactory sessionFactory;

    public TaskService(EntityManagerFactory factory) {
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Task> readAll(int id) {
        return sessionFactory.getCurrentSession().createNamedQuery("Task.getAll", Task.class).setParameter("cardId", id).list();
    }
    @Transactional()
    @Override
    public void update(Task entity, int id) {
        sessionFactory.getCurrentSession().persist(entity);
    }
    @Transactional()
    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().createNamedQuery("Task.delete").setParameter("taskId", id);
    }
}
