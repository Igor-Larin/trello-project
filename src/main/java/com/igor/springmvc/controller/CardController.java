package com.igor.springmvc.controller;

import com.igor.springmvc.model.Card;
import com.igor.springmvc.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
public class CardController {
    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/users/{userId}/desks/{deskId}/newCard")
    public ResponseEntity<?> createCard(@RequestBody Card card, @PathVariable("deskId") int deskId)
    {
        return cardService.create(card) > 0 ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/{userId}/desks/{deskId}/cards")
    public ResponseEntity<List<Card>> readAllCards(@PathVariable("deskId") int id) {
        List<Card> cards = cardService.readAll(id);
        return cards != null ? new ResponseEntity<>(cards, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{userId}/desks/{deskId}/cards/delete/{cardId}")
    public ResponseEntity<List<Card>> deleteCard(@PathVariable("cardId") int id) {
        cardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
