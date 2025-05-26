package com.example.study.repository;

import com.example.study.entity.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    // 비어 있어도 무상관
}
