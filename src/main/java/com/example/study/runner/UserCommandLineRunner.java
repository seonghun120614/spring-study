package com.example.study.runner;

import com.example.study.entity.*;
import jakarta.validation.*;
import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

//@Order(4)
//@Component
public class UserCommandLineRunner implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private Set<ConstraintViolation<User>> violations;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User("sbip01", "sbip");
        violations = validator.validate(user1);
        logger.error("Password for user1 do not adhere to the password policy");
        violations.forEach(constraintViolation -> logger.error(
                "violation details: [{}]",
                constraintViolation.getMessage()
        ));

        User user2 = new User("sbip2", "Sbip01$4UDfg");
        violations = validator.validate(user2);
        if (violations.isEmpty())
            logger.info("Password for user2 adhere to the password policy");

        User user3 = new User("sbip3", "Sbip01$4UDfgggg");
        violations = validator.validate(user3);
        logger.error("Password for user3 violates maximum repetitive rule");
        violations.forEach(constraintViolation -> logger.error(
                "violation details: [{}]",
                constraintViolation.getMessage()
        ));

        User user4 = new User("sbip4", "Sbip014UDfgggg");
        violations = validator.validate(user4);
        logger.error("Password for user4 violates special character rule");
        violations.forEach(constraintViolation -> logger.error(
                "violation details: [{}]",
                constraintViolation.getMessage()
        ));
    }
}
