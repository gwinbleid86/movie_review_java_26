CREATE TABLE IF not EXISTS users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(50),
    password VARCHAR(50)
    );

INSERT INTO users (name, password)
VALUES ('John Doe', '123456'),('Jane Smith', '123456aaa');