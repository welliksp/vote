create table if not exists parameter
(
    id         bigint(20)  not null auto_increment,
    value       varchar(50) not null,
    primary key (id)
);