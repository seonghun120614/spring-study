INSERT INTO USERS (name, email)
SELECT 'Alice', 'alice@example.com';

INSERT INTO POSTS (time, title, content)
VALUES (NOW(), 'Spring Boot Intro', 'This is the content of the post.');