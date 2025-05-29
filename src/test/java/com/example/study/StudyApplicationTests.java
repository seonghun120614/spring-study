package com.example.study;

import com.example.study.entity.*;
import com.example.study.repository.*;
import com.mongodb.*;
import org.apache.tomcat.jdbc.pool.*;
import org.assertj.core.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class StudyApplicationTests {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private MongoTemplate mongoTemplate; // 다양한 MongoDB 연산을 수행할 수 있도록 함, 헬퍼 클래스
	@Autowired
	private CourseRepository courseRepository;

	@Test
	public void givenDatasourceAvailableWhenAccessDetailsThenExpectDetails()
			throws SQLException {
		assertThat(dataSource.getClass().getName()).isEqualTo("org.apache.tomcat.jdbc.pool.DataSource");
		assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo("H2");
	}

	@Test
	public void givenObjectAvailableWhenSaveToCollectionThenExpectValue() {
		// given - Manning 이 key 이며, Spring Boot in Practice 가 값인 Document 를 생성함
		DBObject object = BasicDBObjectBuilder.start()
				.add("Manning", "Spring Boot in Practice").get();

		// when - collection 이 이름인 몽고DB 컬렉션에 저장한다.;
		mongoTemplate.save(object, "collection");

		// then - collection 에서 Manning 의 키로 조회하여 그 값이 Spring Boot in Practice 인지 판정한다.
		assertThat(mongoTemplate.findAll(DBObject.class, "collection"))
				.extracting("Manning")
				.containsOnly("Spring Boot in Practice");
	}

	@Test
	public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {
		Course course = new Course(
				"Rapid Spring Boot Application Development",
				"Spring",
				4,
				"Spring Boot gives all the power of the Spring Framework without all of the complexities");
		courseRepository.save(course);
		course.setRating(5);
		Course savedCourse = courseRepository.save(course);

		assertThat(courseRepository.findById(savedCourse.getId())
				.get().getRating()).isEqualTo(5);
	}

	@Test
	public void givenUpdateCourseWhenLoadTheCourseThenExpectUpdatedCourse() {
		Course course = new Course(
				"Rapid Spring Boot Application Development",
				"Spring",
				4,
				"Spring Boot gives all the power of the Spring Framework without all the complexities"
		);
		courseRepository.save(course); // DB에 저장한 상태
		course.setRating(5); // 메모리에서 수정
		Course savedCourse = courseRepository.save(course); // DB에 다시 저장

		assertThat(courseRepository.findById(savedCourse.getId())
				.get().getRating()).isEqualTo(5); // 수정이 되었는지 봄
	}

	@Test
	public void givenDeleteCourseWhenLoadTheCourseThenExpectNoCourse() {
		Course course =
				new Course("Rapid Spring Boot Application Development",
						"Spring",
						4,
						"Spring Boot gives all the power of the Spring Framework without all of the complexities");

		Course savedCourse = courseRepository.save(course);

		/*
		그냥 isEqualTo 하면 객체의 저장된 고유 ID 를 통해 비교하기 때문에
		save 로 생성된 savedCourse 는 값은 같지만 다른 참조를 가지는 새로운
		인스턴스이다. 즉, hashcode 가 서로 다르다.
		따라서 entity 를 정의한 곳에서 equals 를 구현해야 isEqualTo 가
		실제 값들이 같은지 판단이 가능하다. 여기서 Lombok 은 @Data 애너테이션
		으로 하위 @Getter, @Setter, @ToString, @EqualsAndHashCode,
		를 전부 포함하는 애너테이션이므로 바꿔준다. @Data로 바꿔주자
		 */
		assertThat(courseRepository.findById(savedCourse.getId())
				.get()).isEqualTo(course);

		courseRepository.delete(course);
		assertThat(courseRepository.findById(savedCourse.getId())
				.isPresent()).isFalse();
	}

//	@Test
//	@Sql("classpath:data.sql")
//	public void whenCountAllCoursesThenExpectFiveCourses() throws SQLException {
//		ResultSet rs = null;
//		long noOfCourses = 0;
//
//		/*
//		try의 인자는 자동으로 리소스를 닫아줌 Java7 부터 도입
//		@AutoClosable 을 구현한 객체만 try-with-resources 가능
//		 */
//		try(PreparedStatement ps =
//					// DB session 하나(커넥션 하나) 를 가져옴
//					dataSource.getConnection()
//							/* JDBC 가 SQL 문을 파싱, 실행 계획을 미리 컴파일 해두는 것
//							   바인딩 파라미터를 지원하여 SQL Injection 방지에 효과적 */
//							.prepareStatement("SELECT COUNT(1) FROM COURSES")) {
//
//			/*
//			 실행하고, 결과를 rs에 저장 ResultSet 은 커서(cursor)를 가짐
//			 이 시점에 실제 DB 서버에 쿼리가 날라가고 결과를 받아옴
//			 */
//			rs = ps.executeQuery();
//
//			/*
//			 rs의 커서를 통해 순회함.
//			 커서는 next를 통해 다음 행으로 순회
//			 getInt(1) 이면 1번째 열의 값을 가져오고,
//			 해당 행과 열의 값을 가져옴
//			 */
//			while(rs.next())
//				noOfCourses = rs.getInt(1);
//
//			assertThat(noOfCourses).isEqualTo(5L);
//		}
//		finally {
//			if(rs != null)
//				rs.close();
//		}
//	}
	@Test
	public void givenCreateCourseWhenFindAllCourseRepository() {
		Course course = new Course(
				"Rapid Spring Boot Application Development",
				"Spring",
				4,
				"Spring Boot gives all the power of the Spring Framework without all of the complexities"
		);
		courseRepository.save(course);

		assertThat(Collections.singletonList(courseRepository.findAll()).size()).isEqualTo(1);
	}

	/**
	 * data 가 sql 에 작성되어 있기 때문에 ddl-auto 를 none 으로 해주어야 함
	 * 안그럼 0개임
	 */
	@Test
	void givenDataAvailableWhenLoadFirstPageThenGetFiveRecords() {
		// PageRequest.of 를 통해 페이지 번호, 한 페이지에 나타날 데이터 개수를 지정 할 수 있음.
		// 반환된 객체는 Pageable 로 반환 여기서 next, previousOrFirst 를 통해 다음 페이지
		// 이전 페이지로 갈 수 있음
		Pageable pageable = PageRequest.of(0, 5);

		// findAll 에 pageable 을 넣으면 해당 페이지의 데이터를 전부 조회
		assertThat(courseRepository.findAll(pageable)).hasSize(5);
		assertThat(pageable.getPageNumber()).isEqualTo(0);

		Pageable nextPageable = pageable.next();
		assertThat(courseRepository.findAll(nextPageable)).hasSize(4);
		assertThat(nextPageable.getPageNumber()).isEqualTo(1);

		Pageable last = pageable.previousOrFirst();
		assertThat(courseRepository.findAll(last)).hasSize(5);
		assertThat(last.getPageNumber()).isEqualTo(0);

		Pageable first = pageable.previousOrFirst();
		assertThat(courseRepository.findAll(first)).hasSize(5);
		assertThat(first.getPageNumber()).isEqualTo(0);
	}

	/**
	 * org.springframework.data.domain 에 있는
	 * Sort 를 사용하여 Pagination 을 다르게 바꿀 수도 있다.
	 */
	@Test
	public void givenDataAvailableWhenSortsFirstPageThenGetSortedData() {
		// 여러개의 order 를 집어넣는 것도 가능
		Pageable pageable = PageRequest.of(0, 5, Sort.by(
				Sort.Order.asc("Name"))
		);

		/*
		  Condition 은 추상 조건 표현 객체로 matches 를 구현해야 함.
		  is(Condition), has(Condition)과 같은 메서드랑 함께 사용되고,
		  더 가독성을 높이게 해준다.
 		 */
		Condition<Course> sortedFirstCourseCondition = new Condition<>() {
			@Override
			public boolean matches(Course course) {
				return course.getId() == 4
						&& course.getName().equals("Fully Reactive: Spring, Kotlin, and JavaFX Playing Together");
			}
		};

		// has 사용
		assertThat(courseRepository.findAll(pageable)).first()
				.has(sortedFirstCourseCondition);
	}

	/*
	 Sort 에는 descending, ascending 등 다양한 메소드가 있기 때문에 적절히 정렬시켜서 사용하면 된다.
	 and, reverse, enum 으로는 Direction 인 ASC, DESC 를 가진다.
	 */
	@Test
	public void givenDataAvailableWhenApplyCustomSortThenGetSortedResult() {
		Pageable customSortPageable = PageRequest.of(0, 5,
				Sort.by("Rating").descending()
						.and(Sort.by("Name")));

		Condition customSortFirstCourseCondition = new Condition<Course>() {
			@Override
			public boolean matches(Course course) {
				return course.getId() == 5
						&& course.getName().equals("Getting Started with Spring Cloud Kubernetes");
			}
		};

		assertThat(courseRepository.findAll(customSortPageable)).first()
				.has(customSortFirstCourseCondition);
	}

	@Test
	public void givenCoursesCreatedWhenLoadCoursesBySpringCategoryThenExpectedThreeCourses() {
		assertThat(courseRepository
				.findAllByCategoryAndRating("Spring", 4)).hasSize(2);
	}
}
