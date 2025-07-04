package com.example.study.dao;


import com.example.study.dao.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.test.context.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void givenCreateUserWhenLoadTheUserThenExpectExistedUser() {
        User user = new User("IU", "1234");

        userRepository.save(user);

        assertEquals("IU", userRepository.findById(1L).orElseThrow(
                () -> new AssertionError("User not found")
        ).getName());
    }

    @Test
    public void givenCreateUserWhenLoadTheUserThenExpectSameUser() {
        User user = new User("Alice", "alice@alice.co.kr"); // 여기선 user id 가 null 이지만
        User savedUser = userRepository.save(user); // 여기서는 user id가 자동 설정됨

        assertEquals(user, savedUser);
    }

    @Test
    public void givenUpdateUserWhenLoadTheUserThenExpectUpdatedUser() {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new AssertionError("User not found"));

        user.setName("Bob");
        userRepository.save(user);

        User foundUser = userRepository.findById(1L).get();

        assertNotEquals("IU", foundUser.getName());
        assertEquals("Bob", foundUser.getName());
    }

    @Test
    public void givenDeleteUserWhenLoadTheUserThenExpectNoUser() {
        long total = userRepository.count();
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new AssertionError("User not found"));

        userRepository.delete(user);
        assertEquals(total-1,userRepository.count());

        userRepository.deleteAll();
        assertEquals(0L, userRepository.count());
    }
}
