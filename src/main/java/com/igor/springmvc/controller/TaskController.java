package com.igor.springmvc.controller;

import com.igor.springmvc.model.Card;
import com.igor.springmvc.model.Task;
import com.igor.springmvc.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
public class TaskController {
    private TaskService taskService;
    @Autowired
    public void setDeskService(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping("/users/{userId}/newTask")
    public ResponseEntity<?> createTask(@RequestBody Task task)
    {
        //taskService.update(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/desks/{deskId}/cards/{cardId}/tasks")
    public ResponseEntity<List<Task>> readAllTasks(@PathVariable("cardId") int id) {
        List<Task> tasks = taskService.readAll(id);
        return tasks != null ? new ResponseEntity<>(tasks, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{userId}/desks/{deskId}/cards/{cardId}/tasks/delete/{taskId}")
    public ResponseEntity<List<Card>> deleteTask(@PathVariable("taskId") int id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
