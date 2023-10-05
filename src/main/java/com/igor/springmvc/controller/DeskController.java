package com.igor.springmvc.controller;

import com.igor.springmvc.DTO.DeskRequest;
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

    @PostMapping("/users/newDesk")
    public ResponseEntity<Integer> createDesk(@RequestBody DeskRequest deskRequest)
    {
        Integer deskId = deskService.saveDesk(deskRequest);
        return new ResponseEntity<>(deskId, HttpStatus.CREATED);
    }

    @GetMapping("/users/{username}/desks")
    public ResponseEntity<List<Desk>> readAllDesks(@PathVariable("username") String username) {
        List<Desk> desks = deskService.readAll(username);
        return desks != null ? new ResponseEntity<>(desks, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/desks/delete/{deskId}")
    public ResponseEntity<?> deleteDesk(@PathVariable("deskId") int id) {
        deskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("users/desks/update")
    public ResponseEntity<?> updateDesk(@RequestBody DeskRequest deskRequest) {
        deskService.updateDesk(deskRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
