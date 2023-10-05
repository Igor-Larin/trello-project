package com.igor.springmvc.DTO;

import java.util.Set;

public record DeskRequest(Integer id, String name, String descr, Set<String> users) {
}
