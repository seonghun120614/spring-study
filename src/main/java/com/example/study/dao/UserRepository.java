package com.example.study.dao;

import com.example.study.dao.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> { }
