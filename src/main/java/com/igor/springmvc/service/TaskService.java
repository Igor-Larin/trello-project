package com.igor.springmvc.service;

import com.igor.springmvc.model.Card;
import com.igor.springmvc.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService implements CommonService<Task> {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int create(Task entity) {
        String sql = "INSERT INTO tasks (task_text, iscomplete, card_id) VALUES(:taskText, :isComplete, :cardId)";
        Map<String, Object> params = new HashMap<>();
        params.put("taskText", entity.getText());
        params.put("isComplete", entity.isComplete());
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Task> readAll(int id) {
        String sql = "SELECT task_id, task_text, iscomplete FROM tasks WHERE card_id=:cardId";
        Map<String, Object> params = new HashMap<>();
        params.put("cardId", id);
        return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
            Task task = new Task();
            task.setText(rs.getString("task_text"));
            task.setComplete(rs.getBoolean("iscomplete"));
            return task;
        });
    }

    @Override
    public void update(Task entity) {

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM tasks WHERE task_id=:taskId";
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", id);
        jdbcTemplate.update(sql, params);
    }
}
