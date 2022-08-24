create table account
(
    id      serial
        constraint account_pk
            primary key,
    email   varchar(100) not null,
    name    varchar(50),
    role    varchar(50)  not null,
    picture varchar(256)
);

create unique index account_email_uindex
    on account (email);