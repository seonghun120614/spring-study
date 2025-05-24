package com.example.study.validator;

import jakarta.validation.*;
import org.passay.*;

import java.util.*;

/*
ConstraintValidator 를 상속 받아 구현
isValid() 를 구현해야함.

ConstraintValidator 는 Password, String
두 타입 인자를 가지며,
첫 번째 타입 인자는 커스텀 밸리데이터 로직을 적용하게 해주는 '애너테이션'
두 번째 타입 인자는 커스텀 애너테이션을 적용해야 하는 '데이터 타입' 선언

따라서 @Password 가 유효성 검증 애너테이션, 유효성 검증 대상은 String
이 되게 된다.
 */
public class PasswordRuleValidator implements ConstraintValidator<Password, String> {

    private static final int MIN_COMPLEX_RULES = 2;
    private static final int MAX_REPETITIVE_CHARS = 3;
    private static final int MIN_SPECIAL_CASE_CHARS = 1;
    private static final int MIN_UPPER_CASE_CHARS = 1;
    private static final int MIN_LOWER_CASE_CHARS = 1;
    private static final int MIN_DIGIT_CASE_CHARS = 1;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        List<Rule> passwordRules = new ArrayList<>();
        passwordRules.add(new LengthRule(8, 30));
        CharacterCharacteristicsRule characterCharacteristicsRule =
                new CharacterCharacteristicsRule(
                        MIN_COMPLEX_RULES,
                        new CharacterRule(EnglishCharacterData.Special, MIN_SPECIAL_CASE_CHARS),
                        new CharacterRule(EnglishCharacterData.UpperCase, MIN_UPPER_CASE_CHARS),
                        new CharacterRule(EnglishCharacterData.LowerCase, MIN_LOWER_CASE_CHARS),
                        new CharacterRule(EnglishCharacterData.Digit, MIN_DIGIT_CASE_CHARS)
                );

        passwordRules.add(characterCharacteristicsRule);
        passwordRules.add(new RepeatCharacterRegexRule(MAX_REPETITIVE_CHARS));

        PasswordValidator passwordValidator = new PasswordValidator(passwordRules);
        PasswordData passwordData = new PasswordData(password);
        RuleResult ruleResult = passwordValidator.validate(passwordData);

        // t / f 반환
        return ruleResult.isValid();
    }
}
