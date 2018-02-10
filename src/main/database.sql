create table Categories
(
    id int auto_increment
        primary key,
    name varchar(255) null,
    displayName varchar(255) null,
    isEnabled tinyint(1) default '1' null,
    constraint Category_id_uindex
    unique (id)
)
    engine=InnoDB
;

create table Users
(
    id int auto_increment
        primary key,
    userName varchar(255) not null,
    emailId varchar(255) not null,
    password varchar(255) not null,
    isEnabled tinyint(1) default '1' not null,
    constraint Users_id_uindex
    unique (id),
    constraint users_emailId_uindex
    unique (emailId)
)
    engine=InnoDB
;

