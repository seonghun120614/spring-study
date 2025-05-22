package com.example.study;


import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

@Order(2)
@Component
public class MyCommandLineRunner2 implements CommandLineRunner {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(String... args) throws Exception {
        logger.info("Second MyCommandRunner2 execution");
    }
}
