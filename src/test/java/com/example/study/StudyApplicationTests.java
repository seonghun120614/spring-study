package com.example.study;

import com.mongodb.*;
import org.apache.tomcat.jdbc.pool.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class StudyApplicationTests {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private MongoTemplate mongoTemplate; // 다양한 MongoDB 연산을 수행할 수 있도록 함, 헬퍼 클래스

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
	public void whenCountAllCoursesThenExpectFiveCourses() throws SQLException {
		ResultSet rs = null;
		long noOfCourses = 0;

		try(PreparedStatement ps =
					// DB session 하나(커넥션 하나) 를 가져옴
					dataSource.getConnection()
							/* JDBC 가 SQL 문을 파싱, 실행 계획을 미리 컴파일 해두는 것
							   바인딩 파라미터를 지원하여 SQL Injection 방지에 효과적 */
							.prepareStatement("SELECT COUNT(1) FROM COURSES")) {

			/*
			 실행하고, 결과를 rs에 저장 ResultSet은 커서(cursor)를 가짐
			 이 시점에 실제 DB 서버에 쿼리가 날라가고 결과를 받아옴
			 */
			rs = ps.executeQuery();

			/*
			 rs의 커서를 통해 순회함.
			 커서는 next를 통해 다음 행으로 순회
			 getInt(1) 이면 1번째 열의 값을 가져오고,
			 해당 행과 열의 값을 가져옴
			 */
			while(rs.next())
				noOfCourses = rs.getInt(1);

			assertThat(noOfCourses).isEqualTo(5L);
		}
		finally {
			if(rs != null)
				rs.close();
		}
	}
}
