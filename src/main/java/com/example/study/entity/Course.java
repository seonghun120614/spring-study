package com.example.study.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

/*
비즈니스 엔티티는 record로 구현하기에 적절치 않음
record 자체는 불변성(final)을 지니기 때문에 생명주기의 관리에
불편하기 때문, 생명 주기 동안은 비즈니스 엔티티는 수정과
삭제가 일어날 수 있기에 수정 가능해야 한다. 또한
메서드 확장이 어렵기 때문에 제한적이다.

필드에 붙여 사용할 수 있는 hibernate validator 의
애너테이션은 더 많이 있다.
 - @NotBlank
 - @NotEmpty
 - @NotNull
 - @Min(value=)
 - @Max(value=)
 - @Pattern(regex=, flags)
 - @Size(min=, max=)
 - @Email
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "COURSES")
public class Course {
        @Id
        @Column(name = "ID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "NAME")
        @NotEmpty
        private String name;

        @Column(name = "CATEGORY")
        @NotEmpty
        private String category;

        @Min(value = 1, message = "A course should have a minimum of 1 rating")
        @Max(value = 5, message = "A course should have a maximum of 5 rating")
        @Column(name = "RATING")
        int rating;

        @Column(name = "DESCRIPTION")
        @NotEmpty
        String description;

        public Course(String name, String category, int rating, String description) {
                this.name = name;
                this.category = category;
                this.rating = rating;
                this.description = description;
        }
}