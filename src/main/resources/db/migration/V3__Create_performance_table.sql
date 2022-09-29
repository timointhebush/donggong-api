create table if not exists performance
(
    id                serial
        constraint performance_pk
            primary key,
    host_id           int
        constraint performance_account_id_fk
            references account
            on update cascade on delete cascade,
    title             varchar(256) not null,
    organization_name varchar(64)  not null,
    contact           varchar(64)  not null,
    location          varchar(128) not null,
    date              timestamp    not null,
    total_ticket      int          not null,
    remaining_ticket  int,
    ticket_price      int          not null,
    bank_account_info varchar(64),
    description       text         not null,
    created_at        timestamp default now(),
    updated_at        timestamp default now()
);