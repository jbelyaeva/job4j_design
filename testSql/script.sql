create table student (
  id serial primary key,
	name varchar(255),
	age integer
);

insert into student (name, age) values ('Иван', 20);

update student set name='Ваня';

delete from student;