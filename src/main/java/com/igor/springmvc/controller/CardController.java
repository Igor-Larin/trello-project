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

    @PostMapping("/desks/{deskId}/newCard")
    public ResponseEntity<Integer> createCard(@RequestBody Card card, @PathVariable("deskId") int deskId)
    {
        return new ResponseEntity<>(cardService.saveCard(card, deskId), HttpStatus.CREATED);
    }

    @GetMapping("/desks/{deskId}/cards")
    public ResponseEntity<List<Card>> readAllCards(@PathVariable("deskId") int id) {
        try {
            List<Card> cards = cardService.readAll(id);
            return cards != null ? new ResponseEntity<>(cards, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/cards/delete/{cardId}")
    public ResponseEntity<List<Card>> deleteCard(@PathVariable("cardId") int id) {
        cardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cards/update")
    public ResponseEntity<?> updateDesk(@RequestBody Card card) {
        cardService.updateCard(card);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
