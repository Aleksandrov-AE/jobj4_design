create table devices (
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people (
    id   serial primary key,
    name varchar(255)
);

create table devices_people (
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

-- Устройства
insert into devices (name, price) values
('iPhone 14', 999.99),
('Samsung Galaxy S22', 899.99),
('MacBook Pro 14"', 1999.99),
('Dell XPS 13', 1499.99),
('iPad Air', 649.99);

-- Люди
insert into people (name) values
('Alice'),
('Bob'),
('Charlie'),
('Diana');



select avg(price)
from devices


select people.name, avg(devices.price) as avg_price_devices
from devices_people
join devices on devices.id = devices_people.device_id
join people on people_id = devices_people.people_id
group by people.name, people.id;


select people.name, avg(devices.price) as avg_price_devices
from devices_people
join devices on devices.id = devices_people.device_id
join people on people_id = devices_people.people_id
group by people.name, people.id
having avg(devices.price) >5000;