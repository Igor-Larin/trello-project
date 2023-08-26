package com.igor.springmvc.model;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Entity
@Table(name = "tasks")
@NamedQueries({
        @NamedQuery(name = "Task.getAll", query = "FROM Task WHERE card.id=:cardId"),
        @NamedQuery(name = "Task.delete", query = "DELETE Task WHERE id=:taskId")}
)
public class Task implements BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int id;
    @Column(name = "iscomplete")
    private boolean isComplete;
    @Column(name = "task_text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isComplete() {
        return isComplete;
    }

}
