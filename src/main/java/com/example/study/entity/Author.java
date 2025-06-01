package com.example.study.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@NoArgsConstructor
@Entity(name = "Author") // JPQL 쿼리에서 사용할 이름임, 단수로 해주는게 좋고, CamelCase 로 해주는게 편함. Java 에서 사용할 애너테이션 스트링 리터럴에 들어갈 것이기 때문
@Table(name = "AUTHORS") // Table 은 대문자로 해당하는 DBMS 의 쿼리문 문법에 맞게 넣음(SCHEMA 참고해서 지정해주면 됨 이는 자바 프로그래밍 문법 명칭과 관련 없음)
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @NotEmpty
    @Column(name = "NAME")
    private String name;

    @NotEmpty
    @Column(name = "BIO")
    private String bio;

    @ManyToMany
    @JoinTable(
            name="AUTHORS_COURSES", // 실제 TABLE 명
            joinColumns = { // 실제 table 의 필드명
                    @JoinColumn(name="AUTHOR_ID", referencedColumnName="ID", nullable=false, insertable=false, updatable=false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name="COURSE_ID", referencedColumnName="ID", nullable=false, insertable=false, updatable=false)
            }
    )
    private Set<Course> courses = new HashSet<>();

    public Author(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }
}