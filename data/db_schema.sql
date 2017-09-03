USE speedCollab_db;
create table user(
    id      int             not null    auto_increment,
    name        varchar(50)     not null,
    firstname   varchar(50)     not null,
    email       varchar(255)    not null,
	password	varchar(255)	not null,
    unique (email),
    primary key (id)
);

insert into user values(default, 'adan', 'hussen','xusen@protonmail.com', 'redone' );
insert into user values(default, 'admin', '-', 'admin@speedCollab', 'password');