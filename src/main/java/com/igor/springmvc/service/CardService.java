package com.igor.springmvc.service;

import com.igor.springmvc.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardService implements CommonService<Card> {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int create(Card entity) {
        String sql = "INSERT INTO cards (card_name, card_descr, desk_id) VALUES(:cardName, :cardDescr, :deskId)";
        Map<String, Object> params = new HashMap<>();
        params.put("cardName", entity.getName());
        params.put("cardDescr", entity.getDescr());
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Card> readAll(int id) {
        String sql = "SELECT card_id, card_name, card_descr FROM cards WHERE desk_id=:deskId";
        Map<String, Object> params = new HashMap<>();
        params.put("deskId", id);
        return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
            Card card = new Card();
            card.setName(rs.getString("card_name"));
            card.setDescr(rs.getString("card_descr"));
            card.setId(rs.getInt("card_id"));
            return card;
        });
    }

    @Override
    public void update(Card entity) {

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM cards WHERE card_id=:cardId";
        Map<String, Object> params = new HashMap<>();
        params.put("cardId", id);
        jdbcTemplate.update(sql, params);
    }
}
