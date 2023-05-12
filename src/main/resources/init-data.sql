INSERT INTO roles (id, role) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');
INSERT INTO users (id, name, password) VALUES (1, 'admin', 'admin');
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);