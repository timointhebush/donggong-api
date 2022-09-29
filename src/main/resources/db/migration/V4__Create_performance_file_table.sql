create table if not exists performance_file
(
    id             serial
        constraint performance_file_pk
            primary key,
    performance_id int
        constraint performance_file_performance_id_fk
            references performance
            on delete cascade,
    key            varchar(100) not null,
    name           text         not null,
    type           varchar(64)  not null,
    file_size      int          not null,
    file_path      text         not null,
    created_at     timestamp default now(),
    updated_at     timestamp default now()
);

create unique index performance_file_key_uindex
    on performance_file (key);