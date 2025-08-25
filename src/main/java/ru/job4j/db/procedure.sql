create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

CREATE OR REPLACE PROCEDURE delete_product_by_id(pid INT)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM products
    WHERE id = pid;
END;
$$;



CALL delete_product_by_id(3);


CREATE OR REPLACE FUNCTION delete_zero_count_products()
RETURNS INTEGER
LANGUAGE plpgsql
AS $$
DECLARE
    deleted_count INT;
BEGIN
    DELETE FROM products
    WHERE count = 0
    RETURNING * INTO deleted_count;

    GET DIAGNOSTICS deleted_count = ROW_COUNT;

    RETURN deleted_count;
END;
$$;


SELECT delete_zero_count_products();
