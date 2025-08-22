
CREATE TABLE car_bodies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


CREATE TABLE car_engines (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


CREATE TABLE car_transmissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    body_id INT REFERENCES car_bodies(id),
    engine_id INT REFERENCES car_engines(id),
    transmission_id INT REFERENCES car_transmissions(id)
);

-- Кузова
INSERT INTO car_bodies (name) VALUES
('Седан'),
('Хэтчбек'),
('Универсал'),
('Купе'),
('Кроссовер');

-- Двигатели
INSERT INTO car_engines (name) VALUES
('Бензин 1.6'),
('Бензин 2.0'),
('Дизель 1.9'),
('Электро'),
('Гибрид');

-- Трансмиссии
INSERT INTO car_transmissions (name) VALUES
('Механика'),
('Автомат'),
('Робот'),
('Вариатор');

INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
('Toyota Corolla',         1, 1, 2), -- седан, бензин 1.6, автомат
('Volkswagen Golf',        2, 3, 1), -- хэтчбек, дизель, механика
('Tesla Model 5',          1, 4, 2), -- седан, электро, автомат
('Ford Focus Wagon',       3, 2, 1), -- универсал, бензин 2.0, механика
('Nissan Juke',            5, 1, 4), -- кроссовер, бензин 1.6, вариатор
('Honda CR-V Hybrid',      5, 5, 2); -- кроссовер, гибрид, автомат
('lada cross',     null, null, null);



select c.id, c.name as car_name, cb.name as body_name,ce.name as engine_name,ct.name as transmission_name
from cars c
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id;

SELECT b.id, b.name
FROM car_bodies b
LEFT JOIN cars c ON b.id = c.body_id
WHERE c.id IS NULL;

SELECT e.id, e.name
FROM car_engines e
LEFT JOIN cars c ON e.id = c.engine_id
WHERE c.id IS NULL;

SELECT t.id, t.name
FROM car_transmissions t
LEFT JOIN cars c ON t.id = c.engine_id
WHERE t.id IS NULL;