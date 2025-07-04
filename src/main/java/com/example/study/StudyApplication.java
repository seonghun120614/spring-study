package com.example.study;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.*;
import org.springframework.core.env.*;

@SpringBootApplication
public class StudyApplication {
	private static final Logger logger =
			LoggerFactory.getLogger(StudyApplication.class);

	public static void main(String[] args) {

		SpringApplication springApplication
				= new SpringApplication(StudyApplication.class);

		ConfigurableApplicationContext context = springApplication.run(args);

		Environment env = context.getBean(Environment.class);

		logger.info(env.getProperty("spring.datasource.url"));
	}
}
