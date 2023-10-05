package com.igor.springmvc.DTO;

import com.igor.springmvc.model.Task;

import java.util.Set;

public record TaskResponse(Set<Task> tasks, Set<CardInTaskList> cards) {
}
