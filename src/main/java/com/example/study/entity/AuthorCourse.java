package com.example.study.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name="AuthorCourse")
@Table(name="AUTHORS_COURSES")
public class AuthorCourse {
    @Id
    @Column(name="AUTHOR_ID")
    private String author_id;

    @Column(name="COURSE_ID")
    private String course_id;
}
