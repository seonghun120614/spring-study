package com.example.study.runner;

import com.example.study.entity.*;
import jakarta.validation.*;
import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

//@Order(3)
//@Component
public class CourseCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Logger logger = LoggerFactory.getLogger(getClass());

        Course course = new Course();
        course.setId(1);
        course.setRating(0);

        // Validator 객체 획득
        Validator validator =
                Validation.buildDefaultValidatorFactory().getValidator();

        // 위반사항이 있으면 Set 에 저장하기 위해 선언 ConstraintViolation 이 위반 사항 객체
        Set<ConstraintViolation<Course>> violations =
                validator.validate(course);

        // 모든 제약 조건을 검사한 것을 for 문 돌려 위반한 사항에 대한 error 메시지 출력
        violations.forEach(courseConstraintViolation ->
                logger.error("A constraint violation has occured. Violation details: [{}].",
                        courseConstraintViolation)
        );
    }
}
