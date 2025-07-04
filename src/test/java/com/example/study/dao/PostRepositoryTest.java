package com.example.study.dao;

import com.example.study.dao.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.data.domain.*;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    void readPostWhenLoadThePostThenExpectExistedPost() {
        // given
        Post post1 = new Post(
                LocalDateTime.of(2025, 7, 3, 10, 0),
                "Spring Boot Intro", "Introduction to Spring Boot"
        );
        Post post2 = new Post(
                LocalDateTime.of(2025, 7, 3, 11, 0),
                "JPA Basics", "Learn JPA with Spring"
        );
        Post post3 = new Post(
                LocalDateTime.of(2025, 7, 3, 12, 0),
                "Testing with JUnit", "Unit testing guide"
        );
        Post post4 = new Post(
                LocalDateTime.of(2025, 7, 3, 13, 0),
                "REST API", "Building REST APIs"
        );
        Post post5 = new Post(
                LocalDateTime.of(2025, 7, 3, 14, 0),
                "Spring Security", "Securing applications"
        );
        Post post6 = new Post(
                LocalDateTime.of(2025, 7, 3, 15, 0),
                "Advanced JPA", "Advanced JPA techniques"
        );
        postRepository.saveAll(List.of(post1, post2, post3, post4, post5, post6));
        Pageable pageable = PageRequest.of(0, 5);

        // when
        Page<Post> page = postRepository.findAll(pageable);
        assertEquals(0, page.getNumber());
        assertEquals(5, page.getSize());
        assertEquals(5, page.getNumberOfElements());
        assertEquals(6, page.getTotalElements());

        // then
        List<Post> posts = page.getContent();
        assertTrue(posts.stream().anyMatch(post ->
                        post.getId().equals(1L) && post.getTitle().equals("Spring Boot Intro")),
                "ID가 1이고 제목이 'Spring Boot Intro'인 Post가 존재해야 한다.");
    }
}