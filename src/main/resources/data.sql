--INSERT INTO COURSES(ID, NAME, CATEGORY, RATING, DESCRIPTION)
--VALUES
--    (1, 'Rapid Spring Boot Application Development', 'Spring', 4, 'Spring Boot gives all the power of the Spring Framework without all of the complexities'),
--    (2, 'Getting Started with Spring Security DSL', 'Spring', 3, 'Learn Spring Security DSL in easy steps'),
--    (3, 'Scalable, Cloud Negative Data Applicaitons', 'Spring', 4, 'Manage Cloud based applications with Spring boot'),
--    (4, 'Fully Reactive: Spring, Kotlin, and JavaFX Playing Together', 'Spring', 3, 'Unleash the power of Reactive Spring with Kotlin and Spring Boot'),
--    (5, 'Getting Started with Spring Cloud Kubernetes', 'Spring', 5, 'Master Spring Boot application deployment with Kubernetes'),
--    (6, 'Hello', 'spring', 3, 'test'),
--    (7, 'Hello', 'spring', 3, 'test'),
--    (8, 'Hello', 'spring', 3, 'test'),S
--    (9, 'Hello', 'spring', 3, 'test');

INSERT INTO COURSES(ID, NAME, CATEGORY, RATING, DESCRIPTION)
VALUES
    (1, 'Rapid Spring Boot Application Development', 'Spring', 4, 'Spring Boot gives all the power of the Spring Framework without all of the complexity'),
    (2, 'Getting Started with Spring Security DSL', 'Spring', 5, 'Learn Spring Security DSL in easy steps'),
    (3, 'Getting Started with Spring Cloud Kubernetes', 'Python', 3, 'Master Spring Boot application deployment with Kubernetes');

INSERT INTO AUTHORS(ID, NAME, BIO)
VALUES
    (1, 'John Doe', 'Author of several Spring Boot courses'),
    (2, 'Steve Muller', 'Author of several popular Spring and Python courses');

INSERT INTO AUTHORS_COURSES(AUTHOR_ID, COURSE_ID)
VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 2),
    (2, 3);