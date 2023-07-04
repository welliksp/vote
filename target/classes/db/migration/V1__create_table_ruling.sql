create table if not exists ruling
(
    id         bigint(20)  not null auto_increment,
    name       varchar(50) not null,
    created_at timestamp   not null,
    created_by varchar(50) not null,
    primary key (id)
);