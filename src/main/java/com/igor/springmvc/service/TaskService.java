package com.igor.springmvc.service;

import com.igor.springmvc.model.Card;
import com.igor.springmvc.model.Task;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TaskService implements CommonService<Task> {

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> readAll(int id) {
        return entityManager.createNamedQuery("Task.getAll", Task.class).setParameter("cardId", id).getResultList();
    }
    @Transactional()
    @Override
    public Integer update(Task entity, int id) {
        entity.setCard(findCardById(id));
        if(entity.getId() == null) {
            //entity.setTimestamp(LocalDateTime.now());
            entityManager.persist(entity);
        }
        else
            entityManager.merge(entity);
        return entity.getId();
    }

    @Transactional
    public void complete(int id) {
        Task task = findById(id);
        task.setComplete(!task.isComplete());
        entityManager.merge(task);
    }

    private Task findById(int id) {
        return entityManager.find(Task.class, id);
    }

    private Card findCardById(int id) {
        return entityManager.find(Card.class, id);
    }
    @Transactional()
    @Override
    public void delete(int id) {
        entityManager.remove(findById(id));
    }
}
