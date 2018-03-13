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

CREATE TABLE Sellers
(
    id int auto_increment PRIMARY KEY ,
    userName VARCHAR(255) not null,
    emailId VARCHAR(255) not null,
    password VARCHAR(255) not NULL,
    isEnabled TINYINT(1) DEFAULT '1' null,
    CONSTRAINT Sellers_id_uindex
    UNIQUE (id),
    CONSTRAINT Sellers_emailId_uindex
    UNIQUE (emailId)
)
    ENGINE =InnoDB
;

create table Items
(
  id int auto_increment
    primary key,
  name varchar(255) not null,
  itemPrice float not null,
  description varchar(1000) null,
  sellerId int null,
  offerId int null,
  subSubSubCategoryId int null,
  url varchar(255) null,
  brand varchar(255) null,
  height float null,
  width float null,
  quantity int null,
  constraint Items_id_uindex
  unique (id)
)
  engine=InnoDB
;

