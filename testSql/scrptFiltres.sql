create table type (
  id serial primary key,
	name varchar(255)
);

create table product (
  id serial primary key,
	name varchar(255),
  type_id int references type(id),
  expired_date date,
  price float
);

insert into type (name) values
('Сыр'),
('Молоко'),
('Торт'),
('Йогурт'),
('Мороженое');

insert into product (name, type_id, expired_date, price) values
('Мороженое Услада', 5, '2023-01-15', 56.00),
('Сыр Масдаам', 1, '2024-01-15', 250.00),
('Сыр Гауда', 1, '2022-01-15', 150.00),
('Сыр Моцарелла', 1, '2022-08-15', 320.00),
('Сыр Сулугуни', 1, '2022-08-15', 120.00),
('Сыр Бри', 1, '2022-08-15', 500.00),
('Сыр Российский', 1, '2022-12-15', 260.00),
('Домик у деревне', 2, '2022-02-10', 75.00),
('Простоквашино', 2, '2022-02-13', 65.00),
('Артемьевская ферма', 2, '2022-02-12', 79.00),
('Йогрут питьевой Активиа с черникой', 4, '2022-03-12', 112.00),
('Йогрут питьевой Активиа с малиной', 4, '2022-03-10', 112.00),
('Йогрут питьевой Активиа с ежевикой', 4, '2022-03-11', 112.00),
('Йогрут питьевой Активиа натуральный', 4, '2022-03-01', 112.00),
('Йогрут питьевой Активиа с малиной и злаками', 4, '2022-03-12', 115.00),
('Йогрут питьевой Активиа с тропическими фроуктами', 4, '2022-02-15', 120.00),
('Йогрут питьевой Активиа с манго', 4, '2022-02-19', 115.00),
('Йогрут питьевой Активиа с манго и маракуйей', 4, '2022-03-03', 120.00),
('Йогрут питьевой Активиа со злаками', 4, '2022-03-12', 112.00),
('Йогрут питьевой Активиа с лесными ягодами', 4, '2022-03-12', 115.00),
('Йогрут питьевой Активиа с морошкой', 4, '2022-03-12', 115.00),
('Торт Чародейка', 3, '2022-02-15', 225.00);

select
p.name
from product as p
inner join type as t
on p.type_id=t.id
and t.name = 'Сыр';

select
p.name
from product as p
inner join type as t
on p.type_id=t.id
and p.name like '%Мороженое%';

select
p.name,
p.expired_date
from product as p
inner join type as t
on p.type_id=t.id
and p.expired_date< current_date;

select
p.name,
p.price
from product as p,
(select max(price) as price from product) maxresult
where p.price= maxresult.price;

select
t.name as "имя_типа",
count(p.*) as "количество"
from product as p,
     type as t
where p.type_id=t.id
group by t.name;

select
t.name as "имя_типа",
p.name as "продукт"
from product as p,
     type as t
where p.type_id=t.id
and( t.name='Сыр'
or t.name='Молоко')
group by t.name, p.name;

select
t.name as "имя_типа",
count(p.*) as "количество"
from product as p,
     type as t
where p.type_id=t.id
group by t.name
having count(p.*)<10;

select
p.name as "продукт",
t.name as "имя_типа"
from product as p,
     type as t
where p.type_id=t.id
group by t.name, p.name
order by p.name;