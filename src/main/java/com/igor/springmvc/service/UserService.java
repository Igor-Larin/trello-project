package com.igor.springmvc.service;

import com.igor.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements CommonService<User> {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create(User entity) {
        String sql = "INSERT INTO users (name, email) VALUES(:userName, :userEmail)";
        Map<String, Object> params = new HashMap<>();
        params.put("userName", entity.getName());
        params.put("userEmail", entity.getEmail());
        return jdbcTemplate.update(sql, params);
    }

    public List<User> readAll(int id) {
        String sql = "SELECT id, name, email FROM USERS";
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setId(rs.getInt("id"));
            return user;
        });
    }

    @Override
    public Integer update(User entity, int id) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }
}
