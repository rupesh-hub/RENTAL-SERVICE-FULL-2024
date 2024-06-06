create table if not exists category
(
    id bigint not null primary key,
    description varchar(255),
    name varchar(255)
);