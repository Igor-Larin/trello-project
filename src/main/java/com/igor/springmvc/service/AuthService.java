package com.igor.springmvc.service;

import com.igor.springmvc.model.Card;
import com.igor.springmvc.model.Desk;
import com.igor.springmvc.model.Task;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    boolean userHas(Task task) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return task.getCard().getDesk().getUsers().stream().anyMatch(user -> user.getUsername().equals(username));
    }
    boolean userHas(Card card) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return card.getDesk().getUsers().stream().anyMatch(user -> user.getUsername().equals(username));
    }
    boolean userHas(Desk desk) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return desk.getUsers().stream().anyMatch(user -> user.getUsername().equals(username));
    }
}
