create table course (
  id serial primary key,
	title varchar(255),
	description varchar(255),
	published boolean,
	created_at date
);

create table lesson (
  id serial primary key,
	title varchar(255),
	description varchar(255),
	published boolean,
	created_at date,
  course_id int references course(id)
);

insert into course (title, description, published, created_at) values
('математика','обучение математики 1 год',true,'2021-09-01'),
('математика','обучение математики 2 год',true,'2021-10-01'),
('английский','уровень intermediat',true,'2021-09-10'),
('физика','вводной курс',false,'2022-01-12'),
('физика','обучение физики 1 год',true,'2022-01-12');

insert into lesson (title, description, published, created_at, course_id) values
('урок1','счет до 10', true,'2021-09-02', 1),
('урок2','счет до 20', true,'2021-09-02', 1),
('урок3','счет до 100',false,'2021-09-03', 1),
('урок1','умножение на 2',true,'2021-10-02', 2),
('урок2','умножение на 3',true,'2021-10-02', 2),
('урок3','умножение на 4',false,'2021-10-03', 2),
('урок1','present simple',true,'2021-09-15', 3),
('урок2','past simple',false,'2021-09-20', 3),
('урок1','наука физика',true,'2022-01-12', 4),
('урок1','не знаю что',false,'2022-01-15', 5);


select
c.title,
l.title,
l.description
from course as c
inner join lesson as l
on c.id=l.course_id;

select
c.title as "Название курса",
l.title as "Название урока",
l.description as "Описание"
from course as c
inner join lesson as l
on c.id=l.course_id;

select
c.title,
l.title,
l.description
from course as c
inner join lesson as l
on c.id=l.course_id
and l.published=false;