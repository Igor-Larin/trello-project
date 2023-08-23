package com.igor.springmvc.service;

import com.igor.springmvc.model.Desk;
import com.igor.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeskService implements CommonService<Desk> {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Desk entity) {
        String sql = "INSERT INTO desks (desk_name, desk_descr, user_id) VALUES(:deskName, :deskDescr, :userId)";
        Map<String, Object> params = new HashMap<>();
        params.put("deskName", entity.getName());
        params.put("deskDescr", entity.getDescr());
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Desk> readAll(int id) {
        String sql = "SELECT desk_id, desk_name, desk_descr FROM desks WHERE user_id=:userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", id);
        return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
            Desk desk = new Desk();
            desk.setName(rs.getString("desk_name"));
            desk.setDescr(rs.getString("desk_descr"));
            desk.setId(rs.getInt("desk_id"));
            return desk;
        });
    }

    @Override
    public void update(Desk entity) {

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM desks WHERE desk_id=:deskId";
        Map<String, Object> params = new HashMap<>();
        params.put("deskId", id);
        jdbcTemplate.update(sql, params);
    }
}
