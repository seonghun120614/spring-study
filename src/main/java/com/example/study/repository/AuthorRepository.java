package com.example.study.repository;

import com.example.study.dto.*;
import com.example.study.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;

public interface AuthorRepository
        extends CrudRepository<Author, Long> {

    @Query(
            "SELECT new com.example.study.dto.AuthorCourseDto(c.id, a.name, c.name, c.description) " +
            "FROM AuthorCourse ac " +
            "JOIN Author a ON a.id = CAST(ac.author_id AS long) " +
            "JOIN Course c ON c.id = CAST(ac.course_id AS long) " +
            "WHERE a.id = ?1"
    )
    Iterable<AuthorCourseDto> getAuthorCourseInfo(long authorId);
}

