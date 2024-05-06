CREATE DATABASE FDSS;
USE FDSS;

CREATE TABLE customer(
	customer_id int(10) AUTO_INCREMENT,
    phoneNumber varchar(10),
    paymentInfo varchar(30),
    firstName varchar(255) NOT NULL,
    lastName varchar(255) NOT NULL,
    emailAddres varchar(255) NOT NULL,
    _password varchar(20) NOT NULL,
    PRIMARY KEY(customer_id)
);

CREATE TABLE driver(
	driver_id int(10) NOT NULL,
    emailAddres varchar(255) NOT NULL,
	_password varchar(20) NOT NULL,
    availability varchar(20),
    PRIMARY KEY(driver_id)
);

CREATE TABLE staff(
	staff_id int(10) NOT NULL,
    emailAddres varchar(50) NOT NULL,
    _password varchar(50) NOT NULL,
    firstName varchar(30) NOT NULL,
	lastName varchar(30) NOT NULL,
    PRIMARY KEY(staff_id)
);

INSERT INTO `fdss`.`staff` (`staff_id`, `emailAddres`, `_password`, `firstName`, `lastName`) VALUES ('1', 'staff@cpp.edu', 'staff', 'Vincent', 'La');
INSERT INTO `fdss`.`driver`(`driver_id`, `emailAddres`, `_password`, `availability`) VALUES ('1', 'bob@cpp.edu', 'builder');