create table room
(
    id   int                       not null
        primary key,
    name varchar(255) charset utf8 null,
    code varchar(255)              null
);

INSERT INTO `quan-ly-lop-hoc`.room (id, name, code) VALUES (1, 'Phòng học 1', 'PH1');
INSERT INTO `quan-ly-lop-hoc`.room (id, name, code) VALUES (2, 'Phòng học 2', 'PH2');

create table student
(
    mssv     varchar(255) charset utf8 not null,
    username varchar(255) charset utf8 null,
    password varchar(255) charset utf8 null,
    name     varchar(255) charset utf8 null,
    id       int auto_increment
        primary key,
    constraint student_mssv_uindex
        unique (mssv),
    constraint student_username_uindex
        unique (username)
);

INSERT INTO `quan-ly-lop-hoc`.student (mssv, username, password, name, id) VALUES ('SV1', null, '$2a$10$5TLc73yJ4uwvHm1X8fRsSOGYqglLxe6CMU.hKRRdrZh8dJP882qW6', 'Nguyễn Văn Thanh', 15);
INSERT INTO `quan-ly-lop-hoc`.student (mssv, username, password, name, id) VALUES ('SV2', null, '$2a$10$pgGYnTGaNemc9j6Ee6J9dePW.aoeSz/yybK/ppSIFlLjMpaTkN0My', 'Đinh Văn Thuấn', 16);
INSERT INTO `quan-ly-lop-hoc`.student (mssv, username, password, name, id) VALUES ('SV3', null, '$2a$10$Bq1xWSqrtU8i69bVwcMbQ.1Aoz0sF8jk1QupyL.HMgJ6/JKWMfpva', 'Nguyễn Văn Huy', 17);
INSERT INTO `quan-ly-lop-hoc`.student (mssv, username, password, name, id) VALUES ('SV4', null, '$2a$10$BkNnjX46TCw77gS0wmq.ZeAGx8zBZhn72xq5AIOVkN/IscT4xvi7S', 'Nguyễn Văn Hiệp', 18);
INSERT INTO `quan-ly-lop-hoc`.student (mssv, username, password, name, id) VALUES ('SV5', null, '$2a$10$fFcsf.DcdBbOM7KOYPUWAeDEGpXRUI0SLUsi2B8k5HLDeIEYbxn/C', 'Nguyễn Văn Sỹ', 20);
INSERT INTO `quan-ly-lop-hoc`.student (mssv, username, password, name, id) VALUES ('SV6', null, '$2a$10$CYPKcl5Kg.NvFNcWdbHDzuPRgzQht68ygFuCO2VP8LZrDeHTgYRBq', 'Nguyễn Như Quỳnh', 21);
INSERT INTO `quan-ly-lop-hoc`.student (mssv, username, password, name, id) VALUES ('SV7', null, '$2a$10$lNm8iapzcHqrWpA8KrRQf.BU67b8sT6KYvAqsTmPnbZDPGJG9X1NK', 'Nguyễn Văn Bảnh', 23);


create table academic_staff
(
    id       int auto_increment
        primary key,
    username varchar(255) charset utf8 not null,
    password varchar(255) charset utf8 not null,
    email    varchar(255) charset utf8 null,
    name     varchar(255)              null,
    constraint academic_staff_username_uindex
        unique (username)
);

INSERT INTO `quan-ly-lop-hoc`.academic_staff (id, username, password, email, name) VALUES (1, 'admin', '$2a$10$tTDTiyyI2rjlqyuJ8HOOf.ttMppwCc9TlckEqg6bLAYEdTG4jSLfW', null, 'Nguyễn Văn Thanh');

create table subject
(
    code        varchar(255) charset utf8 not null,
    name        varchar(255) charset utf8 null,
    date_start  date                      null,
    date_end    date                      null,
    day_of_week varchar(256)              null,
    time_start  varchar(256)              null,
    time_end    varchar(256)              null,
    id_room     int                       null,
    id          int auto_increment
        primary key,
    constraint subject_code_uindex
        unique (code),
    constraint subject_room_id_fk
        foreign key (id_room) references room (id)
);

INSERT INTO `quan-ly-lop-hoc`.subject (code, name, date_start, date_end, day_of_week, time_start, time_end, id_room, id) VALUES ('LH1', 'lop hoc 1', '2022-04-24', '2022-04-27', 'Thứ ba', '04:00 AM', '09:00 PM', 1, 1);
INSERT INTO `quan-ly-lop-hoc`.subject (code, name, date_start, date_end, day_of_week, time_start, time_end, id_room, id) VALUES ('LH2', 'lop hoc 2', '2022-04-23', '2022-04-23', 'Thứ tư', '12:00 AM', '12:00 AM', 1, 2);
INSERT INTO `quan-ly-lop-hoc`.subject (code, name, date_start, date_end, day_of_week, time_start, time_end, id_room, id) VALUES ('LH3', 'lop hoc 3', '2022-04-23', '2022-04-23', 'Thứ bảy', '12:00 AM', '12:00 AM', 1, 3);

create table student_subject
(
    mssv             varchar(255) charset utf8 null,
    id               int auto_increment
        primary key,
    subject_id       int                       null,
    session_one      tinyint(1)                null,
    session_two      tinyint(1)                null,
    session_three    tinyint(1)                null,
    session_four     tinyint(1)                null,
    session_five     tinyint(1)                null,
    session_six      tinyint(1)                null,
    session_seven    tinyint(1)                null,
    session_eight    tinyint(1)                null,
    session_nine     tinyint(1)                null,
    session_ten      tinyint(1)                null,
    session_eleven   tinyint(1)                null,
    session_twelve   tinyint(1)                null,
    session_thirteen tinyint(1)                null,
    session_fourteen tinyint(1)                null,
    session_fifteen  tinyint(1)                null
);

create index student_subject_student_mssv_fk
    on student_subject (mssv);

create index student_subject_subject_code_fk
    on student_subject (subject_id);

INSERT INTO `quan-ly-lop-hoc`.student_subject (mssv, id, subject_id, session_one, session_two, session_three, session_four, session_five, session_six, session_seven, session_eight, session_nine, session_ten, session_eleven, session_twelve, session_thirteen, session_fourteen, session_fifteen) VALUES ('SV1', 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0);
INSERT INTO `quan-ly-lop-hoc`.student_subject (mssv, id, subject_id, session_one, session_two, session_three, session_four, session_five, session_six, session_seven, session_eight, session_nine, session_ten, session_eleven, session_twelve, session_thirteen, session_fourteen, session_fifteen) VALUES ('SV2', 7, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `quan-ly-lop-hoc`.student_subject (mssv, id, subject_id, session_one, session_two, session_three, session_four, session_five, session_six, session_seven, session_eight, session_nine, session_ten, session_eleven, session_twelve, session_thirteen, session_fourteen, session_fifteen) VALUES ('SV3', 8, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `quan-ly-lop-hoc`.student_subject (mssv, id, subject_id, session_one, session_two, session_three, session_four, session_five, session_six, session_seven, session_eight, session_nine, session_ten, session_eleven, session_twelve, session_thirteen, session_fourteen, session_fifteen) VALUES ('SV4', 9, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `quan-ly-lop-hoc`.student_subject (mssv, id, subject_id, session_one, session_two, session_three, session_four, session_five, session_six, session_seven, session_eight, session_nine, session_ten, session_eleven, session_twelve, session_thirteen, session_fourteen, session_fifteen) VALUES ('SV1', 10, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `quan-ly-lop-hoc`.student_subject (mssv, id, subject_id, session_one, session_two, session_three, session_four, session_five, session_six, session_seven, session_eight, session_nine, session_ten, session_eleven, session_twelve, session_thirteen, session_fourteen, session_fifteen) VALUES ('SV2', 11, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
