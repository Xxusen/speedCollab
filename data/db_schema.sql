USE speedCollab_db;
create table user(
    iduser      int             not null    auto_increment,
    name        varchar(50)     not null,
    firstname   varchar(50)     not null,
    email       varchar(255)    not null,
    unique (email),
    primary key (iduser)
);