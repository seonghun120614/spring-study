package com.example.study.dao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USERS")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100)
    private String name;
    @Column(length = 100, nullable = false)
    private String email;

    // Spring JPA 에서 Blank Constructor 를 쓰기에 들어가 있어야 함
    public User() {}
    public User(@NonNull String name, @NonNull String email) {
        this.name = name;
        this.email = email;
    }
}
