create table if not exists categories
(
    id   serial primary key,
    name varchar(255)
);

CREATE TABLE if not exists items
(
    id          serial primary key,
    created     boolean,
    description VARCHAR(255),
    done boolean,
    timecreat timestamp,
    category_id integer,
    user_id integer
);

CREATE TABLE if not exists users
(
    id           serial primary key,
    username     VARCHAR(255)  NOT NULL,
    pass     VARCHAR(255) NOT NULL
);

