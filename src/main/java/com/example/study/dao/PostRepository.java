package com.example.study.dao;

import com.example.study.dao.entity.*;
import jakarta.transaction.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long>, CrudRepository<Post, Long> {
    @Query("SELECT p FROM POSTS p WHERE p.content LIKE %:keyword%")
    Iterable<Post> search(@Param("keyword") String keyword);

    @Modifying
    @Transactional
    @Query("UPDATE POSTS p set p.content=:content WHERE p.id=:id")
    int updateCourseRatingByName(@Param("id") Long id, @Param("content") String content);
}
