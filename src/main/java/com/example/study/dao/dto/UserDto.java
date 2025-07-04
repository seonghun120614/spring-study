package com.example.study.dao.dto;

import com.example.study.dao.entity.*;
import lombok.*;

public record UserDto(Integer id, String name, String email) {
    public static UserDto from(User entity) {
        return new UserDto(entity.getId(), entity.getName(), entity.getEmail());
    }

    public User toEntity() {
        return new User(name, email);
    }
}