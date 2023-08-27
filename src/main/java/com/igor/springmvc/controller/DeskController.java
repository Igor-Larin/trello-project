package com.igor.springmvc.controller;

import com.igor.springmvc.model.Card;
import com.igor.springmvc.model.Desk;
import com.igor.springmvc.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
public class DeskController {
    private DeskService deskService;
    @Autowired
    public void setDeskService(DeskService deskService) {
        this.deskService = deskService;
    }

    @PostMapping("/users/{userId}/newDesk")
    public ResponseEntity<Integer> createDesk(@RequestBody Desk desk, @PathVariable("userId") int userId)
    {
        return new ResponseEntity<>(deskService.update(desk, userId), HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/desks")
    public ResponseEntity<List<Desk>> readAllDesks(@PathVariable("userId") int id) {
        List<Desk> desks = deskService.readAll(id);
        return desks != null ? new ResponseEntity<>(desks, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{userId}/desks/delete/{deskId}")
    public ResponseEntity<?> deleteDesk(@PathVariable("deskId") int id) {
        deskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("users/{userId}/desks/update")
    public ResponseEntity<?> updateDesk(@RequestBody Desk desk, @PathVariable("userId") int id) {
        deskService.update(desk, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
