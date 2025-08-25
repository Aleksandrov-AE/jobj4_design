BEGIN;

DECLARE products_cursor SCROLL CURSOR FOR
SELECT * FROM products;


FETCH LAST FROM products_cursor;

MOVE BACKWARD 15 FROM products_cursor;
FETCH FORWARD 0 FROM products_cursor;

MOVE BACKWARD 8 FROM products_cursor;
FETCH FORWARD 0 FROM products_cursor;

MOVE BACKWARD 5 FROM products_cursor;
FETCH FORWARD 0 FROM products_cursor;

MOVE BACKWARD 1 FROM products_cursor;
FETCH FORWARD 0 FROM products_cursor;


CLOSE products_cursor;
COMMIT;
