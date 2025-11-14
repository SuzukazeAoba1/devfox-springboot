INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot1', 'Spring Boot1 test', 'DONE', 'admin', 'admin');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot2', 'Spring Boot2 test', 'DOING', 'admin', 'admin');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot3', 'Spring Boot3 test', 'DONE', 'admin', 'admin');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot4', 'Spring Boot4 test', 'DOING', 'admin', 'admin');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot5', 'Spring Boot5 test', 'DONE', 'admin', 'admin');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot6', 'Spring Boot6 test', 'DOING', 'test', 'test');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot7', 'Spring Boot7 test', 'DONE', 'test', 'test');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot8', 'Spring Boot8 test', 'DOING', 'test', 'test');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot9', 'Spring Boot9 test', 'DOING', 'test', 'test');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot10', 'Spring Boot10 test', 'DONE', 'test', 'test');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot11', 'Spring Boot11 test', 'DOING', 'test', 'test');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot12', 'Spring Boot12 test', 'DONE', 'test', 'test');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot13', 'Spring Boot13 test', 'DOING', 'test', 'test');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot14', 'Spring Boot14 test', 'DOING', 'test', 'test');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot15', 'Spring Boot15 test', 'DONE', 'test', 'test');
INSERT INTO tasks (summary, description, status, loginId, nickname) VALUES ('Spring Boot16', 'Spring Boot16 test', 'DOING', 'test', 'test');

INSERT INTO comments (tasks_id, tasks_order, content, loginId, nickname) VALUES ('1', '1', 'comment test', 'admin', 'admin');
INSERT INTO comments (tasks_id, tasks_order, content, loginId, nickname) VALUES ('1', '2', 'comment test', 'test', 'test');

-- パスワード　: 1q2w3e4r
INSERT INTO users (loginId, password, nickname, role) VALUES ('admin', '$2a$10$ChHflbw8YPjPL/IQ2Sd..ujcIpzTw1MPpJJzVkXxmsQm.UQAoR7gy', 'admin', 'ADMIN');
INSERT INTO users (loginId, password, nickname, role) VALUES ('test', '$2a$10$ChHflbw8YPjPL/IQ2Sd..ujcIpzTw1MPpJJzVkXxmsQm.UQAoR7gy', 'test', 'USER');