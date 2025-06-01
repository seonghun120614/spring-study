package com.example.study.dto;

import lombok.*;

/*
 AuthorCourseDto 를 통해
 id, authorName, courseName, description 의 projection 을 정의,
 이는 interface 의 반환 값으로 설정하여 projection 을 수행하도록 할 수 있음
 */
public record AuthorCourseDto (
        long id,
        String authorName,
        String courseName,
        String description
) {}