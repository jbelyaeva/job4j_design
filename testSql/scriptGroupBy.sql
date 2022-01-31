create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values
('смартфон xiaomi', 18000.45),
('смартфон samsung', 55000.45),
('смартфон samsung', 120000.45),
('смартфон apple', 150000.45),
('смартфон apple', 72000.45),
('электронная книга pocketBook ',8369.00),
('электронная книга kindle ',12445.00);

insert into people (name) values
('Иван'),
('Сергей'),
('Юля');

insert into devices_people (device_id, people_id) values
(1, 1),
(2, 1),
(6, 1),
(2, 2),
(5, 2),
(4, 3),
(7, 3),
(1, 3);

select
avg(price)
from devices;

select
p.name,
avg(d.price)
from
people as p,
devices as d,
devices_people as dp
where p.id = dp.people_id
and d.id = dp.device_id
group by p.name;

select
p.name,
avg(d.price)
from
people as p,
devices as d,
devices_people as dp
where
p.id = dp.people_id
and d.id = dp.device_id
group by p.name
having avg(d.price)>5000.00;


