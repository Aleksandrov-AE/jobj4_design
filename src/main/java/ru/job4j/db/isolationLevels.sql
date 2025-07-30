CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name TEXT,
    stock INT
);

INSERT INTO products (name, stock) VALUES
('apple', 10),
('banana', 20),
('carrot', 30);


BEGIN;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SELECT stock FROM products WHERE name = 'banana';
SELECT pg_sleep(10);
UPDATE products SET stock = stock + 1 WHERE name = 'banana';
COMMIT;

BEGIN;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
UPDATE products SET stock = stock + 50 WHERE name = 'banana';
COMMIT;
