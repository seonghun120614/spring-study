package com.example.study;

import com.example.study.config.*;
import com.example.study.service.*;
import org.slf4j.*;
import org.slf4j.Logger;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.*;
import org.springframework.context.*;
import org.springframework.core.env.Environment;

/* 루트 패키지에 위치
 *
 * SpringBootApplication ->
 * @EnableAutoConfiguration - jar 파일 기반 애플리케이션 자동 구성
 * @ComponentScan - 스프링 컴포넌트(@Bean, @Component)들 탐색
 * @SpringBootConfiguration - 스프링 부트 애플리케이션 설정
 * 포함
 */
//@EnabledConfigurationProperties("AppProperties.class") - 삭제된 annotation
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class StudyApplication {

	/*
	 Spring Logger Facade for Java 의 라이브러리에서
	 각종 Logback, Log4j, Log4j2, java.util.logging, tinylog
	 의 라이브러리를 종합하여 간소화한 형태의 interface 로 제공해줌
	 */
	private static final Logger log =
			LoggerFactory.getLogger(StudyApplication.class);

	/* run()
	 * class path 기준 ApplicationContext 클래스 인스턴스 생성
	 *
	 * 우선순위 1위인 CommandLinePropertySource 를 등록 후 명령행 인자를 스프링 Property 로 읽음
	 * 		명령행 인자는 CLI 에서의 --를 통한 인자 입력을 얘기
	 * 		java -jar myapp.jar --server.port=8085 --my.custom.property=test
	 * ApplicationContext 를 통한 모든 singleton Bean 로딩
	 * ApplicationRunners 와 CommandRunners 를 실행
	 */
	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext =
				SpringApplication.run(StudyApplication.class, args);
		Environment env = applicationContext.getBean(Environment.class);

		// app.timeout 의 value 를 읽는데 해당 value 는 환경설정으로 설정됨
//		log.info("Timeout : " + env.getProperty("app.timeout"));

		DbConfiguration dbConfiguration = applicationContext.getBean(DbConfiguration.class);

		// EnableConfigurationProperties 를 사용하면 선언한 Properties 들을 Bean 으로 등록
		AppService appService = applicationContext.getBean(AppService.class);
		log.info(appService.getAppProperties().toString());

		/*
		각 프로퍼티들이 전부 모여 Environment 객체에 로딩됨.
		따라서 어플리케이션 클래스에서 Environment 인스턴스에
		접근해서 설정 정보를 읽을 수 있으며 @Value 애너테이션을
		통해 접근 가능

		프로퍼티를 읽어들이는 순위는
		1. SpringApplication
		2. PropertySource
		3. 설정 정보 파일(application.properties)
		4. 운영 체제 환경 변수
		5. 명령행 인자
		 */
		log.info("\n\n");
		log.info(dbConfiguration.toString());
		log.info(env.getProperty("user"));

		/*
		 Properties 를 소스코드로도 설정가능 함.
		 이는 설정 정보가 나중에 바뀌지 않는 경우 사용할 때 적합하다.
		 즉, dev, test, prod 와 같은 properties 를 만들어 놓으면 좋을 것이다.
		 기본적으로 Map<String, Object> 의 형태로 properties 가 구성된다.
		 Properties properties = new Properties();

		 setProperty 를 사용해 String, Object 로 property 를 넣음
		 properties.setProperty("spring.config.import", "additional-application.properties");
		 properties.setProperty("spring.config.on-not-found", "ignore");
		 */

//		SpringApplication springApplication =
//				new SpringApplication(StudyApplication.class);
		// springApplication.setWebApplicationType(WebApplicationType.SERVLET);

		/*
		 * 설정한 Properties 넣음, 보통 addListeners 보다 먼저 호출
		 * Listeners 가 properties 를 의존 할 수도 있기 때문
		 * springApplication.setDefaultProperties(properties);
		 */

		/*
		 * addListeners 는 가변인자를 받기 때문에 여러개 등록 가능
		 * 이 방식은 SpringApplication 의 소스코드 변경을 유발하기에
		 * Spring.factories 프로퍼티 파일을 사용해 커스텀 리스너 추가 가능
		 */
//		springApplication.addListeners(
//				new ApplicationContextInitializedEventListener(),
//				new ApplicationEnvironmentPreparedEventListener(),
//				new ApplicationFailedEventListener(),
//				new ApplicationPreparedEventListener(),
//				new ApplicationReadyEventListener(),
//				new ApplicationStartedEventListener(),
//				new ApplicationStartingEventListener(),
//				new ContextRefreshedEventListener(),
//				new WebServerInitializedEventListener()
//		);
	}

//  이는 초기 event 의 감지는 잘 못하는 경향이 있음 따라서 위와 같이 리스너를 등록하기
//	@EventListener(ApplicationReadyEvent.class)
//	public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
//		System.out.println("Application Ready Event generated at " + new Date(applicationReadyEvent.getTimestamp()));
//	}
}
