create table roles (
                       id serial primary key,
                       name varchar(20) NOT NULL
);

create table users (
                       id serial primary key,
                       userName varchar(20) not null,
                       role_id int not null,
                       foreign key(role_id) references roles(id)
);

create table rules (
                       id serial primary key,
                       nameRule varchar(20) NOT NULL
);

create table rolesRules (
                            role_id int not null,
                            rule_id int not null,
                            primary key(role_id, rule_id),
                            foreign key(role_id) references roles(id),
                            foreign key(rule_id) references rules(id)
);

create table items (
                       id serial primary key,
                       item varchar(30) not null,
                       id_user int not null,
                       id_categorie int not null,
                       id_state int not null,
                       foreign key (id_state) references states(id),
                       foreign key(id_categorie) references categories(id),
                       foreign key(id_user) references users(id)
);

create table comments(
                         id serial primary key,
                         text varchar(50) not null,
                         id_item int not null,
                         foreign key(id_item) references items(id)
    );

create table attachs (
                         id serial primary key,
                         attach varchar(50) not null,
                         id_item int not null,
                         foreign key(id_item) references items(id)
    );

create table categories (
                            id serial primary key,
                            categorie varchar(50) not null
);

create table states (
                        id serial primary key,
                        state varchar(30) not null
);