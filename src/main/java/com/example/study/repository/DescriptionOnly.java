package com.example.study.repository;

// [필드명]Only 로 관례적 정의
public interface DescriptionOnly {
    // Projection 에 관심 있는 필드만 getter 로 선언
    String getDescription();
}
