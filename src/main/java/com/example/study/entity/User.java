package com.example.study.entity;


import com.example.study.validator.*;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    @Password // 커스텀 애너테이션 사용
    private String password;
}
