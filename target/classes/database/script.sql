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

create table room
(
    id   int                       not null
        primary key,
    name varchar(255) charset utf8 null,
    code varchar(255)              null
);

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
    session_fifteen  tinyint(1)                null,
    constraint student_subject_student_mssv_fk
        foreign key (mssv) references student (mssv)
);

create index student_subject_subject_code_fk
    on student_subject (subject_id);

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


