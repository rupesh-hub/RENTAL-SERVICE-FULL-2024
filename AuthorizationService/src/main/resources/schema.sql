create table _authentication_methods
(
    id                    bigint not null,
    authentication_method varchar(255),
    client_id             bigint,
    primary key (id)
);

create table _client_token_settings
(
    id              bigint  not null,
    access_tokenttl integer not null,
    token_type      varchar(255),
    client_id       bigint,
    primary key (id)
);

create table _clients
(
    id             bigint not null,
    authentication varchar(255),
    client_id      varchar(255),
    secret         varchar(255),
    primary key (id)
);

create table _grant_types
(
    id         bigint not null,
    grant_type varchar(255),
    client_id  bigint,
    primary key (id)
);

create table _redirect_uris
(
    id        bigint not null,
    url       varchar(255),
    client_id bigint,
    primary key (id)
);

create table _roles
(
    id      bigint not null,
    role    varchar(255),
    user_id bigint,
    primary key (id)
);

create table _scopes
(
    id        bigint not null,
    scope     varchar(255),
    client_id bigint,
    primary key (id)
);

create table _users
(
    id       bigint not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);

alter table if exists _client_token_settings
    drop constraint if exists UKlgj5e28gqtqt2vp5opegff43p;

alter table if exists _client_token_settings
    add constraint UKlgj5e28gqtqt2vp5opegff43p unique (client_id);

create sequence _authentication_method_seq_generator start with 1 increment by 1;

create sequence _client_seq_generator start with 1 increment by 1;
create sequence _client_token_setting_seq_generator start with 1 increment by 1;
create sequence _grant_type_seq_generator start with 1 increment by 1;
create sequence _redirect_uri_seq_generator start with 1 increment by 1;
create sequence _role_seq_generator start with 1 increment by 1;
create sequence _scope_seq_generator start with 1 increment by 1;
create sequence _user_seq_generator start with 1 increment by 1;


alter table if exists _authentication_methods
    add constraint FKqnl6q73hcqix69vtxbs8r26jt
        foreign key (client_id)
            references _clients;

alter table if exists _client_token_settings
    add constraint FK8vdvvf16afk87vj4nmoclbr9x
        foreign key (client_id)
            references _clients;

alter table if exists _grant_types
    add constraint FKejomvy1695b4g2lyb6xckr2u0
        foreign key (client_id)
            references _clients;

alter table if exists _redirect_uris
    add constraint FKsuksxdye8ndj41vfnk8fyffj3
        foreign key (client_id)
            references _clients;

alter table if exists _roles
    add constraint FKpnlbdbw72x7e9spoavwdv2qby
        foreign key (user_id)
            references _users;

alter table if exists _scopes
    add constraint FKop6n6xhios3uo9b8vc9a0ap7j
        foreign key (client_id)
            references _clients;




