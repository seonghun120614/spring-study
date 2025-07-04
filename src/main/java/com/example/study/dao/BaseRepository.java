package com.example.study.dao;

import org.springframework.data.repository.*;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);
    Iterable<T> findAll();
}
