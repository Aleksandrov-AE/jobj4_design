
INSERT INTO roles (name) VALUES
    ('admin'),
    ('manager'),
    ('user');


INSERT INTO users (userName, role_id) VALUES
    ('Иванов', 1),    -- admin
    ('Петров', 2),    -- manager
    ('Сидоров', 3);   -- user


INSERT INTO rules (nameRule) VALUES
    ('view_all'),
    ('edit'),
    ('delete'),
    ('create');


INSERT INTO rolesRules (role_id, rule_id) VALUES
    (1, 1), (1, 2), (1, 3), (1, 4),   -- admin: все права
    (2, 1), (2, 2), (2, 4),           -- manager: просмотр, редактирование, создание
    (3, 1), (3, 4);                   -- user: просмотр, создание

INSERT INTO categories (categorie) VALUES
    ('Техническая поддержка'),
    ('Бухгалтерия'),
    ('Жалоба');


INSERT INTO states (state) VALUES
    ('Открыта'),
    ('В работе'),
    ('Закрыта');


INSERT INTO items (item, user_id, category_id, state_id) VALUES
    ('Не работает принтер', 1, 1, 1),   -- Иванов, техподдержка, открыта
    ('Ошибка в отчёте', 2, 2, 2),       -- Петров, бухгалтерия, в работе
    ('Грубое общение', 3, 3, 1);        -- Сидоров, жалоба, открыта


INSERT INTO comments (text, item_id) VALUES
    ('Проверьте кабель питания.', 1),
    ('Отправил задачу в отдел ИТ.', 1),
    ('Уточните дату отчёта.', 2),
    ('Жалоба на охрану.', 3);


INSERT INTO attachs (attach, item_id) VALUES
    ('scan_error.png', 1),
    ('report_error.docx', 2),
    ('audio.mp3', 3);
