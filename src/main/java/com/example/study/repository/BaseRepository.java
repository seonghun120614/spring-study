package com.example.study.repository;

import org.springframework.data.repository.*;

/*
 스프링 데이터가 이를 감지에 구현체가 자동으로 만들어지지 않도록 기본 레포지토리에
 @NoRepositoryBean 을 넣는다. 따라서 해당 인터페이스는 프록시 객체가 생성되지
 않게 된다. 또한 CrudRepository 메서드 중에서 사용할 메서드만 선택해서
 BaseRepository 에 동일하게 추가했다. BaseRepository 에 정의된 메서드는
 CrudRepository 에 정의된 메서드와 시그니처가 동일하므로 이 메서드가 호출되면
 스프링 데이터는 런타임에 실제 JPA 구현체의 메서드를 호출한다.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);
    Iterable<T> findAll();
}
