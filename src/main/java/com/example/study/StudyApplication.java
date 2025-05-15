package com.example.study;

import com.example.study.events.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* 루트 패키지에 위치
 *
 * SpringBootApplication ->
 * @EnableAutoConfiguration - jar 파일 기반 애플리케이션 자동 구성
 * @ComponentScan - 스프링 컴포넌트(@Bean, @Component)들 탐색
 * @SpringBootConfiguration - 스프링 부트 애플리케이션 설정
 * 포함
 */
@SpringBootApplication
public class StudyApplication {

	/* run()
	 * class path 기준 ApplicationContext 클래스 인스턴스 생성
	 *
	 * 우선순위 1위인 CommandLinePropertySource 를 등록 후 명령행 인자를 스프링 프로퍼티로 읽음
	 * 		명령행 인자는 CLI 에서의 --를 통한 인자 입력을 얘기
	 * 		java -jar myapp.jar --server.port=8085 --my.custom.property=test
	 * ApplicationContext 를 통한 모든 singleton Bean 로딩
	 * ApplicationRunners 와 CommandRunners 를 실행
	 */
	public static void main(String[] args) {
		// SpringApplication.run(StudyApplication.class, args);
		SpringApplication springApplication =
				new SpringApplication(StudyApplication.class);

		springApplication.setWebApplicationType(WebApplicationType.REACTIVE);

		/* addListeners 는 가변인자를 받기 때문에 여러개 등록 가능
		 * 이 방식은 SpringApplication 의 소스코드 변경을 유발하기에
		 * Spring.factories 프로퍼티 파일을 사용해 커스텀 리스너 추가 가능
		 */
		springApplication.addListeners(
//				new ApplicationStartingEventListner()
				new ApplicationContextInitializedEventListener(),
				new ApplicationEnvironmentPreparedEventListener(),
				new ApplicationFailedEventListener(),
				new ApplicationPreparedEventListener(),
				new ApplicationReadyEventListener(),
				new ApplicationStartedEventListener(),
				new ApplicationStartingEventListener(),
				new ContextRefreshedEventListener(),
				new WebServerInitializedEventListener()
		);

		springApplication.run(args);
	}

//  이는 초기 event 의 감지는 잘 못하는 경향이 있음 따라서 위와 같이 리스너를 등록하기
//	@EventListener(ApplicationReadyEvent.class)
//	public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
//		System.out.println("Application Ready Event generated at " + new Date(applicationReadyEvent.getTimestamp()));
//	}
}
