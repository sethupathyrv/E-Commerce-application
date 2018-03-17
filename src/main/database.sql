create table Cart
(
  id int auto_increment
    primary key,
  itemId int null,
  userId int null,
  quantity int null,
  constraint Cart_id_uindex
  unique (id)
)
  engine=InnoDB
;

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
  x int default '-1' null,
  y int default '-1' null,
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
  itemPrice float null,
  quantity int null
)
  engine=InnoDB
;

create table Orders
(
  id int auto_increment
    primary key,
  userId int not null,
  itemsSubTotal int default '0' null,
  shippingCharges int default '0' null,
  deliveryAddressId int null,
  orderStatus int null,
  promotionApplied int default '0' null,
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

create table UserAddresses
(
  id int auto_increment
    primary key,
  fullName varchar(255) not null,
  mobileNumber varchar(10) not null,
  pincode varchar(6) not null,
  streetAddress varchar(255) not null,
  landmark varchar(255) not null,
  city varchar(255) not null,
  state varchar(255) not null,
  userId int null
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
  defaultAddressId int default '-1' null,
  constraint Users_id_uindex
  unique (id),
  constraint users_emailId_uindex
  unique (emailId)
)
  engine=InnoDB
;

