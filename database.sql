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


