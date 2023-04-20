

INSERT INTO ROLES (id, name) VALUES (1, 'ADMIN');
INSERT INTO ROLES (id, name) VALUES (2, 'USER');

INSERT INTO USERS (id, username, password, first_name, last_name, email, address) VALUES (1, 'admin', '$2a$12$BGDYXrP4TTLhe58I/4G2QuUXJiEnLW8CG46uyu3D2kyVWqp1jgCU6', 'Admin', 'lastname', 'admin@petminder.com', 'office');


INSERT INTO USER_ROLES (user_id, role_id) VALUES (1, 1);
