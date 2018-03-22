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

CREATE TABLE Items
(
  id            INT AUTO_INCREMENT
    PRIMARY KEY,
  name          VARCHAR(255)           NOT NULL,
  price         FLOAT                  NOT NULL,
  description   VARCHAR(1000)          NULL,
  sellerId      INT                    NULL,
  offerId       INT                    NULL,
  subCategoryId INT                    NULL,
  url           VARCHAR(255)           NULL,
  brand         VARCHAR(255)           NULL,
  height        FLOAT                  NULL,
  width         FLOAT                  NULL,
  quantity      INT                    NULL,
  isEnabled     TINYINT(1) DEFAULT '1' NOT NULL,
  itemBarcode   INT DEFAULT '0'        NULL,
  CONSTRAINT Items_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;



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

-- auto-generated definition
CREATE TABLE Sellers
(
  id            INT AUTO_INCREMENT
    PRIMARY KEY,
  userName      VARCHAR(255)           NOT NULL,
  emailId       VARCHAR(255)           NOT NULL,
  password      VARCHAR(255)           NOT NULL,
  isEnabled     TINYINT(1) DEFAULT '1' NULL,
  storeName     VARCHAR(255)           NULL,
  mobileNumber  VARCHAR(10)            NULL,
  streetAddress VARCHAR(255)           NULL,
  landmark      VARCHAR(255)           NULL,
  city          VARCHAR(255)           NULL,
  state         VARCHAR(255)           NULL,
  pincode       VARCHAR(6)             NULL,
  country       VARCHAR(255)           NULL,
  CONSTRAINT Sellers_id_uindex
  UNIQUE (id),
  CONSTRAINT Sellers_emailId_uindex
  UNIQUE (emailId)
)
  ENGINE = InnoDB;



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

CREATE TABLE SubCategories
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  displayName VARCHAR(255)           NULL,
  categoryId  INT                    NOT NULL,
  isEnabled   TINYINT(1) DEFAULT '0' NULL,
  CONSTRAINT SubCategories_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

CREATE TABLE Transactions
(
  id        INT AUTO_INCREMENT
    PRIMARY KEY,
  orderId   INT      NOT NULL,
  amount    INT      NULL,
  date      DATETIME NULL,
  accountId INT      NOT NULL,
  status    INT      NOT NULL,
  CONSTRAINT Transactions_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

CREATE TABLE Accounts
(
  id     INT AUTO_INCREMENT
    PRIMARY KEY,
  userId INT         NOT NULL,
  name   VARCHAR(50) NULL,
  number INT         NOT NULL,
  amount INT         NULL,
  CONSTRAINT Accounts_id_uindex
  UNIQUE (id),
  CONSTRAINT Accounts_number_uindex
  UNIQUE (number)
)
  ENGINE = InnoDB;





