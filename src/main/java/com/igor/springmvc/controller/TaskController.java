package com.igor.springmvc.controller;

import com.igor.springmvc.DTO.TaskResponse;
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
    @PostMapping("/cards/{cardId}/newTask")
    public ResponseEntity<Integer> createTask(@RequestBody Task task, @PathVariable("cardId") int id)
    {
        return new ResponseEntity<>(taskService.update(task, id), HttpStatus.CREATED);
    }

    @GetMapping("/cards/{cardId}/tasks")
    public ResponseEntity<TaskResponse> readAllTasks(@PathVariable("cardId") int id) {
        try {
            TaskResponse taskResponse = taskService.readAll(id);
            return new ResponseEntity<>(taskResponse, HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/tasks/delete/{taskId}")
    public ResponseEntity<List<Card>> deleteTask(@PathVariable("taskId") int id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cards/{cardId}/tasks/update")
    public ResponseEntity<?> updateDesk(@RequestBody Task task, @PathVariable("cardId") int id) {
        taskService.update(task, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/complete")
    public ResponseEntity<?> completeTask(@RequestBody int id) {
        System.out.println(id);
        taskService.complete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/newcard/{taskId}/{newCardId}")
    public ResponseEntity<?> changeCard(@PathVariable("taskId") Integer taskId, @PathVariable("newCardId") Integer newCardID) {
        try {
            taskService.changeCard(taskId, newCardID);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
