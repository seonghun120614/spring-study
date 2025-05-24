package com.example.study.runner;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

//@Order(1) // CommandLineRunner 가 여러개 있을 때 실행 순서를 정의 가능하다.
//@Component // 이렇게 정의하면 코드가 간단해지게 된다.
class MyCommandLineRunner implements CommandLineRunner {

    // 이미 구현되어 있는(메모리에 적재되어 있는 인스턴스) 것을 가져옴
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(String... args) throws Exception {
        logger.info("MyCommandLineRunner executed as a Spring Component");
    }
}
