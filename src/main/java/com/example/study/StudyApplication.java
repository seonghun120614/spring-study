package com.example.study;

import com.example.study.config.*;
import com.example.study.events.*;
import com.example.study.service.*;
import org.slf4j.*;
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
public class StudyApplication
		//implements CommandLineRunner
{

	/*
	 Spring Logger Facade for Java 의 라이브러리에서
	 각종 Logback, Log4j, Log4j2, java.util.logging, tinylog
	 의 라이브러리를 종합하여 간소화한 형태의 interface 로 제공해줌.

	 지금 이 클래스가 가지고 있는 Logger 를 가져온다는 뜻
	 */
	private static final Logger logger =
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

		SpringApplication springApplication
				= new SpringApplication(StudyApplication.class);

		springApplication.setWebApplicationType(WebApplicationType.NONE);

		/*
		 addListeners 는 가변인자를 받기 때문에 여러개 등록 가능
		 이 방식은 SpringApplication 의 소스코드 변경을 유발하기에
		 Spring.factories 프로퍼티 파일을 사용해 커스텀 리스너 추가 가능
		 */
		springApplication.addListeners(
				new ApplicationContextInitializedEventListener(),
				new ApplicationEnvironmentPreparedEventListener(),
				new ApplicationFailedEventListener(), // 실행 안되는게 맞음
				new ApplicationPreparedEventListener(),
				new ApplicationReadyEventListener(),
				new ApplicationStartedEventListener(),
				new ApplicationStartingEventListener(), // Logging 이 안보이는게 맞음
				new ContextRefreshedEventListener(),
				new WebServerInitializedEventListener() // 웹서버는 아니라서 logging 안보임
		);

		ConfigurableApplicationContext applicationContext =
				springApplication.run(args);

		Environment env = applicationContext.getBean(Environment.class);
		// 값 읽는 방법 - log.info("Timeout : " + env.getProperty("app.timeout"));

		DbConfiguration dbConfiguration = applicationContext.getBean(DbConfiguration.class);

		AppService appService = applicationContext.getBean(AppService.class);

//		logger.info(appService.getAppProperties().toString());
//		logger.info(dbConfiguration.toString());
//		logger.info(env.getProperty("user"));

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

		 Properties 를 소스코드로도 설정가능 함.
		 이는 설정 정보가 나중에 바뀌지 않는 경우 사용할 때 적합하다.
		 즉, dev, test, prod 와 같은 properties 를 만들어 놓으면 좋을 것이다.
		 기본적으로 Map<String, Object> 의 형태로 properties 가 구성된다.
		 Properties properties = new Properties();

		 setProperty 를 사용해 String, Object 로 property 를 넣음
		 properties.setProperty("spring.config.import", "additional-application.properties");
		 properties.setProperty("spring.config.on-not-found", "ignore");

		 설정한 Properties 넣음, 보통 addListeners 보다 먼저 호출
		 Listeners 가 properties 를 의존 할 수도 있기 때문
		 springApplication.setDefaultProperties(properties);
		 */
	}

	/*
	CommandLineRunner 를 통해 ApplicationStartedEvent 의 발행
	직후에 실행되는 메서드, CommandLineRunner 를 implements 해야함.
	 */

	// 1번째 방법 - 제한적임. 오로지 하나의 메서드에서만 실행가능.
//	@Override
//	public void run(String... args) throws Exception {
//		logger.info("StudyApplication CommandLineRunner has executed");
//	}

	/*
	 2번째 방법
	 Bean 에 등록해놓으면 알아서 CommandLineRunner 를 필요로 하는(인자로 취하는)
	 메서드에 주입을 자동으로 하기 때문에 이런 방법이 가능할거라 생각.
	 또한, Bean 으로 등록됐기 때문에 인자로 다른 Bean 으로 등록된 객체를 넣을 수 있음
	 */
//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//			logger.info("CommandLineRunner executed as a bean definition with" + args.length + " arguments.");
//			for (String arg : args) {
//				logger.info("Argument: " + arg);
//			}
//		};
//	}

	/*
	3번째 방법은 다른 클래스를 참고하여야 한다.
	 */

//  이는 초기 event 의 감지는 잘 못하는 경향이 있음 따라서 위와 같이 리스너를 등록하기
//	@EventListener(ApplicationReadyEvent.class)
//	public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
//		System.out.println("Application Ready Event generated at " + new Date(applicationReadyEvent.getTimestamp()));
//	}
}
