package com.igor.springmvc.controller;

import com.igor.springmvc.model.Desk;
import com.igor.springmvc.model.User;
import com.igor.springmvc.service.DeskService;
import com.igor.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody User user)
    {
        return userService.create(user) > 0 ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<User>> readAllUsers(@PathVariable("id") int id) {
        List<User> users = userService.readAll(id);
        return users != null ? new ResponseEntity<>(users, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
