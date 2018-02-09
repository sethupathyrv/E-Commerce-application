CREATE TABLE `Category` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `displayName` varchar(255) DEFAULT NULL,
    `isEnabled` tinyint(1) DEFAULT '1',
    PRIMARY KEY (`id`),
    UNIQUE KEY `Category_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1

-- auto-generated definition
CREATE TABLE users
(
    id        INT AUTO_INCREMENT,
    userName  VARCHAR(255) NOT NULL,
    emailId   VARCHAR(255) NOT NULL
        PRIMARY KEY,
    password  VARCHAR(255) NOT NULL,
    isEnabled TINYINT(1)   NOT NULL,
    CONSTRAINT Users_id_uindex
    UNIQUE (id)
)
    ENGINE = InnoDB;

