package com.example.study.repository;

import com.example.study.entity.*;
import jakarta.transaction.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import java.util.*;

//
/**
 * save, findAll 메서드 상속.
 * 다른 기본 메서드 들은 못사용
 *
 * 또한 필요시
 * 커스텀 쿼리 메서드를 추가 가능
 */
@Repository
public interface CourseRepository
        extends PagingAndSortingRepository<Course, Long> {
    // 첫번째 파라미터를 가르킴
    @Query("select c from Course c where c.category=?1")
    Iterable<Course> findAllByCategory(String category);
    // @Param 을 통해 @Query 에서 해당 인자의 명을 :를 통해 사용할 수 있음
    @Query("select c from Course c where c.category:=category and c.rating>:rating")
    Iterable<Course> findAllByCategoryAndRatingGreaterThan(
            @Param("category") String category,
            @Param("rating") int rating
    );
    // ?1 은 첫번째 파라미터를 가르킴, nativeQuery 를 true로 설정하면
    // JPQL 이 아닌 네이티브 쿼리문을 사용가능
    @Query(value = "select * from COURSE where rating=?1", nativeQuery = true)
    Iterable<Course> findAllByRating(int rating);
    // UPDATE, DELETE, INSERT 등 DDL 문에 전부 사용해야한다.
    @Modifying // @Query 에 정의된 메서드가 조회가 아닌 수정 작업을 수행한다는 것을 알림
    @Transactional // 데이터의 변경이 발생하기에 Transactional 하게 수행하도록 애너테이션을 붙인다
    @Query("update Course c set c.rating=:rating where c.name=:name")
    int updateCourseRatingByName(
            @Param("rating") int rating,
            @Param("name") String name
    ); // 반환 타입이 int, Integer 이면 변경된 행의 개수를 반환하며, void 또한 가능하다.
    Iterable<Course> findAllByCategoryAndRating(String category, int rating);
    Optional<Course> findById(long id);
    Course save(Course course);
    Iterable<Course> findAll();
    void delete(Course course);
}
