
create table student (
  id serial primary key,
	name varchar(255),
	sername varchar(255)
);

create table personal_data (
  id serial primary key,
	snils varchar(255),
	passport_number int
);

create table student_personal_data (
  id serial primary key,
	student_id int references student(id) unique,
	pers_id int references personal_data(id) unique
);
