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
}
