CREATE TABLE tasks
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    summary VARCHAR(256) NOT NULL,
    description TEXT,
    status VARCHAR(256) NOT NULL
);

CREATE TABLE comment
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    tasks_id BIGINT REFERENCES tasks(id), -- FOREIGN KEY --
    tasks_order BIGINT NOT NULL,
    content TEXT,
    writer VARCHAR(50) NOT NULL
);

CREATE TABLE users
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    loginId VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at timestamp NOT NULL default now()
);