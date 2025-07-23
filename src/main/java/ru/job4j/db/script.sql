CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    hire_date DATE,
    is_active BOOLEAN
);

INSERT INTO employee (name, age, hire_date, is_active)
VALUES ('Пётр Иванов', 30, '2023-01-15', TRUE);

UPDATE employee
SET age = 31, is_active = FALSE
WHERE name = 'Пётр Иван';

DELETE FROM employee
WHERE name = 'Пётр Иван';
