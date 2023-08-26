package com.igor.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cards")
@NamedQueries({
        @NamedQuery(name = "Card.getAll", query = "FROM Card WHERE desk.id=:deskId"),
        @NamedQuery(name = "Card.delete", query = "DELETE Card WHERE id=:cardId")}
)
public class Card implements BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int id;
    @Column(name = "card_name")
    private String name;
    @Column(name = "card_descr")
    private String descr;
    @ManyToOne()
    @JoinColumn(name = "desk_id")
    private Desk desk;
    @JsonIgnore
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setId(int cardId) {
        this.id = cardId;
    }

    public int getId() {
        return id;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }

    public void setName(String cardName) {
        this.name = cardName;
    }

    public String getName() {
        return name;
    }
}
