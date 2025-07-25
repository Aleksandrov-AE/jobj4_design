create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);


INSERT INTO fauna (name, avg_age, discovery_date) VALUES
('Африканский слон',           60, '1797-03-21'),
('Амурский тигр',              15, '1844-11-10'),
('Снежный барс',               18, '1775-06-15'),
('Галапагосская черепаха',    100, '1535-03-10'),
('Коала',                      13, '1816-08-24'),
('Большая белая акула',        30, '1758-05-01'),
('Императорский пингвин',      20, '1844-12-25'),
('Гренландский кит',          200, '1767-04-13'),
('Платинумовый паук',           3, '1901-09-09'),
('Красная панда',              10, '1825-10-12');

 select *
 from fauna
 where name like '%fish%';

 select *
 from fauna
 where avg_age between 10000 and 21000;

 SELECT *
 FROM fauna
 WHERE discovery_date IS NULL;

SELECT *
FROM fauna
WHERE discovery_date < '1950-01-01';
