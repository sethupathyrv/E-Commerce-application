CREATE TABLE cart
(
  id           INT AUTO_INCREMENT
    PRIMARY KEY,
  itemId       INT                    NULL,
  userId       INT                    NULL,
  quantity     INT                    NULL,
  offerApplied TINYINT(1) DEFAULT '0' NULL,
  CONSTRAINT Cart_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

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

create table ItemDetails
(
  id int auto_increment
    primary key,
  itemId int not null,
  `key` varchar(255) null,
  value varchar(255) null
)
  engine=InnoDB
;

create table Items
(
  id int auto_increment
    primary key,
  name varchar(255) not null,
  price float not null,
  description varchar(1000) null,
  sellerId int null,
  offerId int null,
  subSubSubCategoryId int null,
  url varchar(255) null,
  brand varchar(255) null,
  height float null,
  width float null,
  quantity int null,
  isEnabled tinyint(1) default '1' not null,
  constraint Items_id_uindex
  unique (id)
)
  engine=InnoDB
;

create table Offers
(
  id int auto_increment
    primary key,
  offerType int null,
  discountPercentage float default '-1' null,
  price int default '-1' null,
  constraint Offers_id_uindex
  unique (id)
)
  engine=InnoDB
;

create table OrderItems
(
  id int auto_increment
    primary key,
  orderId int null,
  itemId int null,
  itemPrice int null,
  quantity int null
)
  engine=InnoDB
;

create table Orders
(
  userId int not null,
  itemsSubTotal int null,
  shippingCharges int null,
  deliveryAddressId int null,
  orderStatus int null,
  id int auto_increment
    primary key,
  constraint Orders_id_uindex
  unique (id)
)
  engine=InnoDB
;

create table Sellers
(
  id int auto_increment
    primary key,
  userName varchar(255) not null,
  emailId varchar(255) not null,
  password varchar(255) not null,
  isEnabled tinyint(1) default '1' null,
  constraint Sellers_id_uindex
  unique (id),
  constraint Sellers_emailId_uindex
  unique (emailId)
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

