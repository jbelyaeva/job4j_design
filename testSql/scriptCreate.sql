create table role (
  id serial primary key,
	role varchar(255),
);

create table user (
  id serial primary key,
	name varchar(255),
	sername varchar(255),
	role_id int references role(id)
);

create table rule (
  id serial primary key,
	rule varchar(255),
);

create table role_rule (
  id serial primary key,
	role_id int references role(id),
	rule_id int references rule(id)
);

create table category (
  id serial primary key,
	category varchar(255),
);

create table state (
  id serial primary key,
	state varchar(255),
);

create table item (
  id serial primary key,
	item varchar(255),
	user_id int references user(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table comment (
  id serial primary key,
	comment varchar(255),
	item_id int references item(id)
);

create table attach (
  id serial primary key,
	path varchar(255),
	item_id int references item(id)
);



