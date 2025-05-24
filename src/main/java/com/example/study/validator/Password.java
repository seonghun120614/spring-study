package com.example.study.validator;

import jakarta.validation.*;

import java.lang.annotation.*;

/*
커스텀 애너테이션 생성

@Target - @Password 를 적용할 대상 범위를 설정, Set 형태의 인자가 들어가며,
    - Field declaration (includes enum constants) -
    Element.FIELD,
    Element.METHOD,
    Element.PARAMETER,
    Element.CONSTRUCTOR,
    Element.LOCAL_VARIABLE,
    Element.ANNOTATION_TYPE,
    Element.PACKAGE,
    Element.TYPE_PARAMETER,
    Element.TYPE_USE,
    Element.MODULE,
    Element.RECORD_COMPONENT
    가 Set 에 들어갈 수 있다.
@Retention - 애너테이션 @Password 가 생명주기 동안 어느 범위 까지 효력을 유지하는지
    정의하는 곳, 인자로는 RUNTIME 을 넣으면 runtime 동안 살아남아 효력을 유지한다.
    RetentionPolicy.SOURCE, RetentionPolicy.CLASS,
    RetentionPolicy.RUNTIME 이 있다.
@Constraint - Bean Validation 의 제약 사항을 포함하는 애너테이션이며,
    validatedBy 속성을 사용해 제약사항이 구현된 클래스를 지정한다.
message() - 유효성 검증에 실패할 때 표시할 메시지
Class<?>[] groups() - 어떤 타입이든 상관 없는 객체를 원소로 가지는 배열 반환하는 메서드
    groups() 는 밸리데이션을 그룹별로 구분해서 적용 가능 -> groups() default {RequiredInfo.class} 와
    같이 groups 를 지정 시 validator.validate(targetObject, RequiredInfo.class) 의
    그룹 단위로 밸리데이션 수행
Class<? extends PayLoad>[] payload() - 메타데이터 전달을 위해 사용
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordRuleValidator.class)
public @interface Password {
    String message() default "Password do not adhere to the specified rule";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
