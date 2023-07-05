create table if not exists vote
(
    id         bigint(20) not null auto_increment,
    ruling_id  bigint(20) not null,
    ruling_name varchar(50) not null ,
    result varchar(3) not null ,
    created_at timestamp  not null,
    primary key (id)
);