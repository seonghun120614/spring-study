package com.example.study.validator;

import jakarta.validation.*;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordRuleValidator.class)
public @interface Password {
    // 기본 메시지 정의
    String message() default "Password do not adhere to the specified rule";
    Class<?>[] groups() default {};
    /*
     검증 대상 객체에 대한 부가적인 메타데이터를 제공하는 Payload를 사용
     어떤 검증 테스트가 실패했는지를 payload 객체로 받게 됨
     */
    Class<? extends Payload>[] payload() default {};
}