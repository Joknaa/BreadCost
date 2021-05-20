create table messages
(
    ID_MESSAGE int auto_increment
        primary key,
    RECEIVER   varchar(25) not null,
    SENDER     varchar(25) not null,
    ID_GRP     int         not null,
    MSG_TEXT   text        not null,
    DATETIME   datetime    not null,
    NAME       varchar(50) not null,
    PATH       varchar(50) not null,
    DATA       varchar(50) not null
)
    collate = utf8mb4_bin;

INSERT INTO chatapp.messages (ID_MESSAGE, RECEIVER, SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (335, 'oknaa', 'niaa', 0, 'heey oknaa m niaa', '2021-05-20 19:36:10', '', '', '');
INSERT INTO chatapp.messages (ID_MESSAGE, RECEIVER, SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (336, 'niaa', 'oknaa', 0, 'heeyy niaa m oknaa', '2021-05-20 19:36:24', '', '', '');
INSERT INTO chatapp.messages (ID_MESSAGE, RECEIVER, SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (337, 'oknaa', 'niaa', 0, 'rr', '2021-05-20 19:36:38', '', '', '');
INSERT INTO chatapp.messages (ID_MESSAGE, RECEIVER, SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (338, 'niaa', 'oknaa', 0, 'hey niaa m oknaa', '2021-05-20 19:36:47', '', '', '');