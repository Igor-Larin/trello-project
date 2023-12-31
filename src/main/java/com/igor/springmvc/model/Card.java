package com.igor.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cards")
public class Card implements BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Integer id;
    @Column(name = "card_name")
    private String name;
    @Column(name = "card_descr")
    private String descr;
    @JsonIgnore
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

    public void setId(Integer cardId) {
        this.id = cardId;
    }

    public Integer getId() {
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
