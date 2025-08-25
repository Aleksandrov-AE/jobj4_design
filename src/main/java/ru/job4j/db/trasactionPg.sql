
BEGIN;
INSERT INTO products (name, stock) VALUES ('grape', 50);
SAVEPOINT sp1;
UPDATE products SET stock = 999 WHERE name = 'apple';
ROLLBACK TO SAVEPOINT sp1;
UPDATE products SET stock = 777 WHERE name = 'banana';
COMMIT;
