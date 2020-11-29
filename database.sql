create table groups
(
	id serial not null
		constraint groups_pk
			primary key,
	name varchar(50)
);

create table students
(
	id serial not null
		constraint students_pk
			primary key,
	firstname varchar(100) not null,
	lastname varchar(100) not null,
	"groupId" integer not null
		constraint students_groups_id_fk
			references groups
);

create table tasks
(
    id          serial      not null
        constraint tasks_pk
            primary key,
    name        varchar(30) not null,
    mark        varchar(10) not null,
    "studentId" integer
        constraint tasks_students_id_fk
            references students
);

alter table tasks
    owner to user_gradebook;

create unique index tasks_id_uindex
    on tasks (id);

insert into groups (name) values
('Group 1'),
('Group 2'),
('Group 3');

insert into students (firstname, lastname, "groupId") values
('Anna', 'Green', 1),
('Aleksandr', 'Brown', 1),
('Martha', 'Thomas', 2),
('Jack', 'Adams', 2),
('Harry', 'Murphy', 2),
('Sophie', 'Wilson', 1),
('George', 'Miller', 3),
('Jack', 'Evans', 3);

insert into tasks (name, mark, studentId) values
('lab1', '5',7),
('lab1', '4',8),
('lab2', '4',7),
('lab2', '4',8);




