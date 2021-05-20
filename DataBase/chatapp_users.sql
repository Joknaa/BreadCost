create table users
(
    ID_USER   int auto_increment
        primary key,
    LOGIN     varchar(25) not null,
    PASSWORD  varchar(25) not null,
    ID_GRP    int         not null,
    STATUS    int         not null,
    LAST_CONN datetime    not null
)
    collate = utf8mb4_bin;

INSERT INTO chatapp.users (ID_USER, LOGIN, PASSWORD, ID_GRP, STATUS, LAST_CONN) VALUES (2, 'oussama', '12345', 1, 0, '2021-05-06 07:21:21');
INSERT INTO chatapp.users (ID_USER, LOGIN, PASSWORD, ID_GRP, STATUS, LAST_CONN) VALUES (14, 'med', '123', 1, 0, '2021-05-08 20:47:56');
INSERT INTO chatapp.users (ID_USER, LOGIN, PASSWORD, ID_GRP, STATUS, LAST_CONN) VALUES (12, 'niaa', 'niaa', 1, 0, '2021-05-06 11:17:41');
INSERT INTO chatapp.users (ID_USER, LOGIN, PASSWORD, ID_GRP, STATUS, LAST_CONN) VALUES (13, 'ousmar', 'ousmar', 1, 0, '2021-05-08 11:22:11');
INSERT INTO chatapp.users (ID_USER, LOGIN, PASSWORD, ID_GRP, STATUS, LAST_CONN) VALUES (22, 'oknaa', 'oknaa', 1, 0, '2021-05-15 13:40:33');
INSERT INTO chatapp.users (ID_USER, LOGIN, PASSWORD, ID_GRP, STATUS, LAST_CONN) VALUES (23, 'ok', 'ok', 1, 0, '2021-05-20 20:20:22');
INSERT INTO chatapp.users (ID_USER, LOGIN, PASSWORD, ID_GRP, STATUS, LAST_CONN) VALUES (24, 'okk', 'okkk', 1, 0, '2021-05-20 20:55:33');