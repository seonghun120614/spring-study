package com.example.study.repository;

import com.example.study.entity.*;
import org.springframework.stereotype.*;

// save, findAll 메서드만 사용 가능
@Repository
public interface CustomizedCourseRepository
        extends BaseRepository<Course, Long> { }
