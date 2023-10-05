package com.igor.springmvc.mapper;

import com.igor.springmvc.DTO.CardInTaskList;
import com.igor.springmvc.DTO.DeskRequest;
import com.igor.springmvc.DTO.TaskResponse;
import com.igor.springmvc.model.Card;
import com.igor.springmvc.model.Desk;
import com.igor.springmvc.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MapperUtils {
    public Desk toDesk(DeskRequest deskRequest, UserRepository userRepository)
    {
        Desk desk = new Desk();
        if(deskRequest.id() != null)
            desk.setId(deskRequest.id());
        desk.setUsers(deskRequest.users().stream().map(user -> userRepository.findByUsername(user).orElseThrow()).collect(Collectors.toSet()));
        desk.setDescr(deskRequest.descr());
        desk.setName(deskRequest.name());
        return desk;
    }
    public TaskResponse toTaskResponse(Card card) {
        return new TaskResponse(card.getTasks(), card.getDesk().getCards().stream().filter(deskCard -> !deskCard.getId().equals(card.getId())).map(
                deskCard -> new CardInTaskList(deskCard.getId(), deskCard.getName()))
                .collect(Collectors.toSet()));
    }
}
