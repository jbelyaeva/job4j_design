
create table body (
  id serial primary key,
	name varchar(255)
);

create table motor (
  id serial primary key,
	name varchar(255)
);

create table transmission (
  id serial primary key,
	name varchar(255)
);

create table car (
  id serial primary key,
	name varchar(255),
  body_id int references body(id),
  motor_id int references motor(id),
  transmission_id int references transmission(id)
);

insert into body (name) values
('сидан'),
('хечбек'),
('универсал'),
('кабриолет'),
('джип');

insert into motor (name) values
('бензиновый'),
('дизельный'),
('электрический');

insert into transmission (name) values
('автоматическая'),
('механическая'),
('роботизированная');

insert into  car (name, body_id, motor_id, transmission_id ) values
('Машина1', 1, 1, 1),
('Машина2', 2, 1, 1),
('Машина3', 3, 1, 1),
('Машина4', 4, 1, 1),
('Машина5', 1, 2, 1),
('Машина6', 1, 3, 1),
('Машина7', 4, 1, 2),
('Машина8', 1, 2, 2),
('Машина9', 1, 1, 1);

select
 c.id,
 c.name as car,
 b.name as body,
 m.name as motor,
 t.name as transmission
 from car c
 left join body b
 on c.body_id = b.id
 left join motor m
 on c.motor_id = m.id
 left join transmission t
 on c.transmission_id = t.id

 select
 b.name as body
 from car c
 right join body b
 on c.body_id = b.id
 where c.name is null;

 select
 m.name as motor
 from car c
 right join motor m
 on c.motor_id = m.id
 where c.name is null;

 select
 t.name as transmission
 from car c
 right join transmission t
 on c.transmission_id = t.id
 where c.name is null;
