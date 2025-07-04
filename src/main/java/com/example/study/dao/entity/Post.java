package com.example.study.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Table(name = "POSTS")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Post() {}

    public Post(@NonNull LocalDateTime createdAt, @NonNull String title, @NonNull String content) {
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
    }
}
