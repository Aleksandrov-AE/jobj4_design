create table roles (
	id serial primary key,
	nameRole varchar(20) not null
);


create table users (
	id serial primary key,
	name varchar(20) not null,
	role_id int references roles(id)
);



insert into users(name) values ('Alex');
insert into roles(nameRole) values ('User');
insert into roles(nameRole) values ('Guest');
insert into roles(nameRole) values ('Admin');
insert into users(name, role_id) values ('Ivan', 1);
insert into users(name, role_id) values ('Tolik', 2);
insert into users(name, role_id) values ('Ivan246', 3);


select users.name, roles.nameRole
from users join roles on users.role_id = roles.id;