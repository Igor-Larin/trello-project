package com.igor.springmvc.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "desks")
public class Desk implements BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desk_id")
    private int id;
    @Column(name = "desk_name")
    private String name;
    @Column(name = "desk_descr")
    private String descr;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "desk", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Card> cards = new HashSet<>();

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setName(String deskName) {
        this.name = deskName;
    }

    public String getName() {
        return name;
    }
}
