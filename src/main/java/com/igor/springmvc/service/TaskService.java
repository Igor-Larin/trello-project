package com.igor.springmvc.service;

import com.igor.springmvc.DTO.TaskResponse;
import com.igor.springmvc.mapper.MapperUtils;
import com.igor.springmvc.model.Card;
import com.igor.springmvc.model.Task;
import com.igor.springmvc.repository.CardRepository;
import com.igor.springmvc.repository.TaskRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService {
    private CardRepository cardRepository;
    private TaskRepository taskRepository;
    private AuthService authService;
    private MapperUtils mapperUtils;
    @Autowired
    public void setMapperUtils(MapperUtils mapperUtils) {
        this.mapperUtils = mapperUtils;
    }
    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
    @Autowired
    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public TaskResponse readAll(int id) throws Exception {
        Card card = cardRepository.findById(id).orElseThrow();
        if(authService.userHas(card)) {
            return mapperUtils.toTaskResponse(card);
        }
        else
            throw new Exception("Попытка неавторизованного доступа");
    }

    public Integer update(Task entity, int id) {
        Card card = cardRepository.findById(id).orElseThrow();
        entity.setCard(card);
        taskRepository.save(entity);
        return entity.getId();
    }

    public void complete(int id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setComplete(!task.isComplete());
        taskRepository.save(task);
    }

    public void delete(int id) {
        taskRepository.delete(taskRepository.findById(id).orElseThrow());
    }

    public void changeCard(Integer taskId, Integer cardId) throws Exception {
        System.out.println(cardId);
        Card card = cardRepository.findById(cardId).orElseThrow(Exception::new);
        Task task = taskRepository.findById(taskId).orElseThrow(Exception::new);
        task.setCard(card);
    }
}
