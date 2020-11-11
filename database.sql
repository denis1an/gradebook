create table "group"
(
    id   integer     not null
        constraint group_pk
            primary key,
    name varchar(20) not null
);

alter table "group"
    owner to user_gradebook;

create table students
(
    id        integer      not null
        constraint students_pk
            primary key,
    firstname varchar(100) not null,
    lastname  varchar(100) not null,
    "groupId" integer      not null
        constraint students_group_id_fk
            references "group"
);

alter table students
    owner to user_gradebook;

create unique index students_id_uindex
    on students (id);

create unique index group_id_uindex
    on "group" (id);

