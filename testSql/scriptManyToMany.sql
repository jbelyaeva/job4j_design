create table course (
  id serial primary key,
	title varchar(255),
	description varchar(255)
);

create table student (
  id serial primary key,
	name varchar(255),
	sername varchar(255)
);

create table student_course (
  id serial primary key,
	student_id int references student(id),
	course_id int references course(id)
);
