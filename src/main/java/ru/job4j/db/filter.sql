product(id, name, type_id, expired_date, price)

type(id, name)

SELECT product.*
FROM product
JOIN type ON type.id = product.type_id
WHERE type.name = 'СЫР';

SELECT product.*
FROM product
JOIN type ON type.id = product.type_id
where product.name like '%мороженое%';

SELECT *
FROM product
WHERE expired_date < CURRENT_DATE;

SELECT *
FROM product
WHERE price = (
    SELECT MAX(price)
    FROM product
);

SELECT type.name as имя_типа, count(*) as Количество
FROM product
JOIN type ON type.id = product.type_id
group by type.name;

SELECT product.*
FROM product
JOIN type ON type.id = product.type_id
WHERE type.name = 'СЫР' and type.name = 'МОЛОКО';

SELECT type.name as имя_типа, count(*) as Количество
FROM product
JOIN type ON type.id = product.type_id
group by type.name
HAVING COUNT(*) < 10;

SELECT product.*, type.name
from product
JOIN type ON type.id = product.type_id