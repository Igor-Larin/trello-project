package com.igor.springmvc.service;

import com.igor.springmvc.DTO.DeskRequest;
import com.igor.springmvc.model.Card;
import com.igor.springmvc.model.Desk;
import com.igor.springmvc.repository.CardRepository;
import com.igor.springmvc.repository.DeskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CardService {
    private CardRepository cardRepository;
    private DeskRepository deskRepository;
    private AuthService authService;
    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
    @Autowired
    public void setDeskRepository(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }
    @Autowired
    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional(readOnly = true)
    public List<Card> readAll(int id) throws Exception {
        Desk desk = deskRepository.findById(id).orElseThrow();
        if(authService.userHas(desk))
            return desk.getCards().stream().toList();
        else
            throw new Exception("Попытка неавторизованного доступа");
    }

    public void updateCard(Card newCard) {
        Card oldCard = cardRepository.findById(newCard.getId()).orElseThrow();
        if(!oldCard.getName().equals(newCard.getName()))
            oldCard.setName(newCard.getName());
        if(!oldCard.getDescr().equals(newCard.getDescr()))
            oldCard.setDescr(newCard.getDescr());
    }

    public Integer saveCard(Card card, Integer deskId)
    {
        Desk desk = deskRepository.findById(deskId).orElseThrow();
        Card createdCard = cardRepository.save(card);
        createdCard.setDesk(desk);
        return createdCard.getId();
    }

    public void delete(int id) {
        cardRepository.deleteById(id);
    }
}
