create table messages
(
    ID_MESSAGE  int auto_increment
        primary key,
    ID_RECIEVER varchar(200) not null,
    ID_SENDER   varchar(200) not null,
    ID_GRP      int          not null,
    MSG_TEXT    varchar(200) not null,
    DATETIME    datetime     not null,
    NAME        varchar(200) not null,
    PATH        varchar(200) not null,
    DATA        blob         not null
)
    charset = utf8;

INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (1, 'med', 'ousmar', 0, 'holaa chico', '2021-06-05 13:08:00', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (2, 'ousmar', 'med', 0, 'a feen', '2021-06-05 13:08:04', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (3, 'ousmar', 'med', 0, 'j', '2021-06-05 13:44:40', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (4, 'med', 'ousmar', 0, 'j', '2021-06-05 13:44:47', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (5, 'ousmar', 'med', 0, 'm', '2021-06-05 14:12:42', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (6, 'med', 'ousmar', 0, 'n', '2021-06-05 14:12:45', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (7, 'med', 'ousmar', 0, 'n', '2021-06-05 14:15:25', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (8, 'ousmar', 'med', 0, 'm', '2021-06-05 14:16:38', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (9, 'med', 'ousmar', 0, 'n', '2021-06-05 14:19:04', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (32, 'ousmar', 'ousmar', 1, 'hola', '2021-06-05 18:45:14', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (11, 'med', 'ousmar', 0, 'n', '2021-06-05 14:19:17', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (12, 'ousmar', 'med', 0, 'n', '2021-06-05 14:20:41', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (13, 'med', 'ousmar', 0, 'm', '2021-06-05 14:22:10', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (14, 'ousmar', 'med', 0, 'j', '2021-06-05 14:24:00', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (15, 'med', 'ousmar', 0, 'h', '2021-06-05 14:24:05', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (16, 'med', 'ousmar', 0, 'n', '2021-06-05 17:15:31', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (17, 'ousmar', 'med', 0, 'a', '2021-06-05 17:15:37', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (18, 'med', 'ousmar', 0, 'o', '2021-06-05 17:15:43', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (19, 'med', 'ousmar', 0, 'heyy', '2021-06-05 17:28:09', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (20, 'ousmar', 'med', 0, 'holaa', '2021-06-05 17:28:12', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (21, 'med', 'ousmar', 0, 'cava !', '2021-06-05 17:28:17', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (22, 'ousmar', 'med', 0, 'hamdulilaah wby?', '2021-06-05 17:28:24', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (23, 'ousmar', 'med', 0, 'hamdulilah', '2021-06-05 17:30:28', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (24, 'med', 'ousmar', 0, 'viva', '2021-06-05 17:30:31', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (25, 'med', 'ousmar', 0, 'heyy', '2021-06-05 17:37:32', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (26, 'ousmar', 'med', 0, 'holaa', '2021-06-05 17:37:39', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (27, 'med', 'ousmar', 0, 'tfq', '2021-06-05 17:37:42', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (28, 'ousmar', 'med', 0, 'kanleab', '2021-06-05 17:38:05', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (29, 'med', 'ousmar', 0, 'heho', '2021-06-05 17:52:23', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (30, 'ousmar', 'med', 0, 'what ?', '2021-06-05 17:52:28', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (31, 'med', 'ousmar', 0, 'bye !', '2021-06-05 17:52:35', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (41, 'GRP1', 'ousmar99', 1, 'hey ?', '2021-06-05 19:14:39', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (40, 'GRP1', 'ousmar', 1, 'hola', '2021-06-05 19:14:35', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (39, 'GRP1', 'ousmaR99', 1, 'a feen', '2021-06-05 19:07:22', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (38, 'GRP1', 'med', 1, 'hla', '2021-06-05 19:07:00', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (42, 'GRP1', 'med', 1, 'j', '2021-06-05 19:23:30', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (43, 'GRP1', 'ousmar99', 1, 'h', '2021-06-05 19:23:37', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (44, 'GRP1', 'ousmar99', 1, 'n', '2021-06-05 19:26:37', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (45, 'GRP1', 'ousmar99', 1, 'k', '2021-06-05 19:26:41', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (46, 'GRP1', 'ousmar', 1, ',', '2021-06-05 19:29:04', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (47, 'GRP1', 'med', 1, 'j', '2021-06-05 19:29:06', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (48, 'GRP1', 'ousmar', 1, 'n', '2021-06-05 19:44:05', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (49, 'GRP1', 'med', 1, 'n', '2021-06-05 19:52:36', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (50, 'GRP1', 'med', 1, 'm', '2021-06-05 19:52:43', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (51, 'GRP1', 'ousmar', 1, 'n', '2021-06-05 19:53:02', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (52, 'GRP1', 'med', 1, 'n', '2021-06-05 19:54:28', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (53, 'GRP1', 'med', 1, 'n', '2021-06-05 19:54:30', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (54, 'GRP1', 'med', 1, 'm', '2021-06-05 19:54:32', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (55, 'GRP1', 'med', 1, 'm', '2021-06-05 22:19:16', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (56, 'GRP1', 'med', 1, 'ka', '2021-06-05 22:19:20', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (57, 'GRP1', 'med', 1, 'skjd', '2021-06-05 22:19:21', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (58, 'GRP1', 'ousmar99', 1, 'a fen', '2021-06-05 22:19:26', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (59, 'GRP1', 'ousmar', 1, 'cava !', '2021-06-05 22:19:32', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (60, 'GRP1', 'med', 1, 'gyug ky', '2021-06-05 22:22:20', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (61, 'GRP1', 'ousmar99', 1, 'hey', '2021-06-05 22:22:30', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (62, 'GRP1', 'ousmar', 1, 'n', '2021-06-05 22:22:37', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (63, 'GRP1', 'med', 1, 'l', '2021-06-05 22:25:39', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (64, 'GRP1', 'med', 1, 'm', '2021-06-05 22:32:59', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (65, 'GRP1', 'med', 1, '$charge_old_msgs', '2021-06-05 22:35:59', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (66, 'GRP1', 'med', 1, 'hey', '2021-06-05 22:36:05', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (67, 'GRP1', 'ousmar99', 1, '$charge_old_msgs', '2021-06-05 22:36:22', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (68, 'GRP1', 'med', 1, 'kjjk', '2021-06-05 22:38:20', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (69, 'GRP1', 'med', 1, '$charge_old_msgs', '2021-06-05 22:38:26', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (70, 'GRP1', 'ousmar', 1, '$charge_old_msgs', '2021-06-05 22:38:36', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (71, 'GRP1', 'ousmar', 1, 'jkl', '2021-06-05 22:39:40', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (72, 'GRP1', 'ousmar99', 1, 'kjhjk', '2021-06-05 22:39:42', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (73, 'GRP1', 'ousmar99', 1, '$charge_old_msgs', '2021-06-05 22:39:45', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (84, 'med', 'ousmar', 0, '', '2021-06-06 12:57:25', '', 'default.jpg', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (80, 'ousmar', 'med', 0, 'chicos', '2021-06-06 02:43:12', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (81, 'med', 'ousmar', 0, 'chicito', '2021-06-06 02:43:19', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (83, 'med', 'ousmar', 0, '', '2021-06-06 12:51:20', '', 'oudirihaka -CAMELEON- TOTO.mp3', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (85, 'med', 'ousmar', 0, '', '2021-06-06 13:12:40', '', 'GI2_groupes_Liens.xlsx', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (86, 'ousmar', 'med', 0, 'jk', '2021-06-06 13:12:49', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (87, 'med', 'ousmar', 0, 'safii', '2021-06-06 13:13:01', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (88, 'ousmar', 'med', 0, 'vivvaa', '2021-06-06 13:13:05', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (89, 'ousmar', 'med', 0, '', '2021-06-06 13:15:02', '', 'projetgenielogiciel.sql', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (90, 'med', 'ousmar', 0, '', '2021-06-06 13:17:53', '', 'fileTP1.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (91, 'med', 'ousmar', 0, '', '2021-06-06 13:40:58', '', 'TP SERVEUR TCP.pdf', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (92, 'med', 'ousmar', 0, '', '2021-06-06 13:43:17', '', 'TP Client TCP.pdf', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (93, 'GRP1', 'med', 1, '$charge_old_messages$', '2021-06-06 15:17:34', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (262, 'med', 'ousmar', 0, 'hamdulilaah', '2021-06-09 15:59:40', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (261, 'ousmar', 'med', 0, 'cava !', '2021-06-09 15:59:36', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (260, 'ousmar', 'med', 0, 'holaa', '2021-06-09 15:59:34', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (259, 'med', 'ousmar', 0, 'heey', '2021-06-09 15:59:30', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (263, 'ousmar99', 'ousmar', 0, 'hi', '2021-06-10 12:45:49', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (264, 'med', 'ousmar', 0, 'hehehe', '2021-06-10 12:57:09', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (265, 'ousmar', 'med', 0, 'hohoho', '2021-06-10 12:57:16', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (266, 'med', 'ousmar', 0, '', '2021-06-10 13:03:49', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (267, 'med', 'ousmar', 0, '', '2021-06-10 13:05:43', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (268, 'med', 'ousmar', 0, 'hhehe', '2021-06-10 13:10:15', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (269, 'ousmar', 'med', 0, '', '2021-06-10 13:10:24', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (270, 'ousmar', 'med', 0, '', '2021-06-10 13:13:36', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (271, 'med', 'ousmar', 0, '', '2021-06-10 13:16:57', '', 'Dataset2.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (272, 'ousmar', 'med', 0, '', '2021-06-10 13:20:01', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (273, 'ousmar', 'med', 0, 'hehe', '2021-06-10 13:33:00', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (274, 'ousmar', 'med', 0, 'hehe', '2021-06-10 13:34:46', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (275, 'ousmar', 'med', 0, 'hehe', '2021-06-10 13:34:50', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (276, 'med', 'ousmar', 0, '', '2021-06-10 13:35:00', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (277, 'med', 'ousmar', 0, 'hh', '2021-06-10 13:37:57', '', '', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (278, 'ousmar', 'med', 0, '', '2021-06-10 13:38:17', '', 'Dataset2.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (279, 'med', 'ousmar', 0, '', '2021-06-10 13:43:43', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (280, 'med', 'ousmar', 0, '', '2021-06-10 13:50:33', '', 'Dataset2.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (281, 'ousmar', 'med', 0, '', '2021-06-10 13:52:53', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (282, 'med', 'ousmar', 0, '', '2021-06-10 13:58:13', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (283, 'med', 'ousmar', 0, '', '2021-06-10 14:01:11', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (284, 'ousmar', 'ousmar99', 0, '', '2021-06-10 14:02:48', '', 'FileTest.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (285, 'ousmar99', 'ousmar', 0, '', '2021-06-10 14:04:23', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (286, 'ousmar', 'med', 0, '', '2021-06-10 14:05:35', '', 'Dataset.txt', 0x);
INSERT INTO chat_db.messages (ID_MESSAGE, ID_RECIEVER, ID_SENDER, ID_GRP, MSG_TEXT, DATETIME, NAME, PATH, DATA) VALUES (287, 'med', 'ousmar', 0, '', '2021-06-10 14:06:25', '', 'Dataset2.txt', 0x);