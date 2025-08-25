CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers (first_name, last_name, age, country) VALUES
('John', 'Doe', 28, 'USA'),
('Maria', 'Garcia', 34, 'Spain'),
('Ivan', 'Petrov', 41, 'Russia'),
('Akira', 'Tanaka', 25, 'Japan'),
('Ann', 'Schmidt', 30, 'Germany'),
('Claire', 'Dubois', 38, 'France');

select first_name, last_name, age
from customers
where age = (select min(age) from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

select last_name,first_name
from customers
where id not in (select customer_id from orders);