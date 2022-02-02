create table department (
  id serial primary key,
	name varchar(255)
);

create table emploer (
  id serial primary key,
	name varchar(255),
  department_id int references department(id)
);

insert into department (name) values
('дирекция'),
('тех поддержка'),
('IT'),
('бухгалтерия'),
('hr');

insert into  emploer (name, department_id) values
('Саша', 1),
('Юля', 2),
('Ира', 3),
('Сережа', 2),
('Света', 4),
('Аня', 3),
('Таня', 3),
('Петя', 3),
('Костя', null);

/*2*/
 select *
 from emploer e
 left join department d
 on e.department_id = d.id;

 select *
 from emploer e
 right join department d
 on e.department_id = d.id;

select *
 from emploer e
 full join department d
 on e.department_id = d.id;

 select *
 from emploer e
 cross join department d;

/*3*/
 select *
 from department d
 left join emploer e
 on e.department_id = d.id
 where e.id is null;


/*4*/
 select *
 from emploer e
 left join department d
 on e.department_id = d.id
 where e.id is not null
 and d.id is not null;

 select *
 from emploer e
 right join department d
 on e.department_id = d.id
 where e.id is not null
 and d.id is not null;

/*5*/
create table teens (
  id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into  teens (name, gender) values
('Саша', 'male'),
('Юля', 'female'),
('Ира', 'female'),
('Сережа', 'male'),
('Света', 'female'),
('Аня', 'female'),
('Таня', 'female'),
('Петя', 'male'),
('Костя', 'male');


 select
 tableMale.name as "Мужчина",
 tableFemale.name as "Женщина"
 from
 (select *
 from teens t
 where t.gender='male') tableMale
 cross join (
 select *
 from teens t
 where t.gender='female') tableFemale;
