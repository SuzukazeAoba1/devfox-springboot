CREATE TABLE users
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    loginId VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at timestamp NOT NULL default now()
);

CREATE TABLE tasks
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    summary VARCHAR(256) NOT NULL,
    description TEXT,
    status VARCHAR(256) NOT NULL,
    loginId VARCHAR(50) NOT NULL,
    nickname VARCHAR(50) NOT NULL
);

CREATE TABLE comments
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    tasks_id BIGINT NOt NULL,
    tasks_order BIGINT NOT NULL,
    content TEXT,
    loginId VARCHAR(50) NOT NULL,
    nickname VARCHAR(50) NOT NULL
);

