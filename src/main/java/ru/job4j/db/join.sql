create table departments (
	id serial primary key,
	name varchar(50)
);

create table employees(
	id serial primary key,
	name varchar(30) not null,
	depart_id int references departments(id)
)


INSERT INTO departments (name) VALUES
('HR'),
('IT'),
('Marketing'),
('Finance');


INSERT INTO employees (name, depart_id) VALUES
('Alice', 1),
('Bob', 2),
('Charlie', NULL),
('Diana', 3),
('Eve', 2),
('Frank', null);


select e.name, d.name
from employees e
join departments d on e.depart_id = d.id;

select e.name, d.name
from employees e
left join departments d on e.depart_id = d.id;

select e.name, d.name
from employees e
right join departments d on e.depart_id = d.id;

select e.name, d.name
from employees e
full join departments d on e.depart_id = d.id;

select e.name, d.name
from employees e
cross join departments d;

SELECT d.id, d.name
FROM departments d
LEFT JOIN employees e ON d.id = e.depart_id
WHERE e.id IS NULL;

SELECT d.id AS department_id, d.name AS department_name
FROM departments d
LEFT JOIN employees e ON d.id = e.depart_id
WHERE e.id IS NULL;

SELECT d.id AS department_id, d.name AS department_name
FROM employees e
RIGHT JOIN departments d ON e.depart_id = d.id
WHERE e.id IS NULL;

CREATE TABLE teens (
    id serial PRIMARY KEY,
    name VARCHAR(30),
    gender VARCHAR(10)  -- можно 'male' / 'female'
);
INSERT INTO teens (name, gender) VALUES
('Вася', 'male'),
('Петя', 'male'),
('Маша', 'female'),
('Оля', 'female');

SELECT
    t1.name AS boy,
    t2.name AS girl
FROM
    teens t1
CROSS JOIN
    teens t2
WHERE
    t1.gender = 'male'
    AND t2.gender = 'female'
    AND t1.name < t2.name;
