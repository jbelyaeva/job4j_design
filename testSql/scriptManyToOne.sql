create table course (
  id serial primary key,
	title varchar(255),
	description varchar(255)
);

create table lesson (
  id serial primary key,
	title varchar(255),
	description varchar(255),
	createdAt timestamp with time zone,
  course_id int references course(id)
);
