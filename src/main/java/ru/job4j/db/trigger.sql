ALTER TABLE products
ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

CREATE OR REPLACE FUNCTION apply_tax_on_insert()
RETURNS TRIGGER AS $$
BEGIN
    -- Обновляем все продукты, добавленные "прямо сейчас" — грубая фильтрация
    UPDATE products
    SET price = ROUND(price * 1.20)
    WHERE created_at >= CURRENT_TIMESTAMP - INTERVAL '1 second';

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

 CREATE TRIGGER trg_apply_tax
 AFTER INSERT ON products
 FOR STATEMENT
 EXECUTE FUNCTION apply_tax_on_insert();

CREATE OR REPLACE FUNCTION apply_tax_row()
RETURNS TRIGGER AS $$
BEGIN
    NEW.price := ROUND(NEW.price * 1.2);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_apply_tax_row
BEFORE INSERT ON products
FOR EACH ROW
EXECUTE FUNCTION apply_tax_row();

CREATE TABLE history_of_price (
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    price INTEGER,
    date  TIMESTAMP
);

CREATE OR REPLACE FUNCTION log_product_price()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO history_of_price (name, price, date)
    VALUES (NEW.name, NEW.price, CURRENT_TIMESTAMP);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_log_price_after_insert
AFTER INSERT ON products
FOR EACH ROW
EXECUTE FUNCTION log_product_price();
