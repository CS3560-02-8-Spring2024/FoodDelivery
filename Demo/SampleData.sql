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

CREATE TABLE _order (
	order_id INT(4) NOT NULL AUTO_INCREMENT,
    customer_id int(10),
    staff_id int(10),
    driver_id int(10),
    deliveryStatus ENUM('Ordered','Ready_for_delivery','Picked_up','Delivered') DEFAULT 'Ordered',
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY(staff_id) REFERENCES staff(staff_id),
    FOREIGN KEY(driver_id) REFERENCES customer(customer_id),
    PRIMARY KEY(staff_id)
);

CREATE TABLE menu (
	item_id INT(10) NOT NULL,
    foodName VARCHAR(20),
    price DECIMAL(5,2),
    _description VARCHAR(255),
    PRIMARY KEY(staff_id)
);

CREATE TABLE orderItem(
	orderItem_id INT(10),
    quantity INT(3),
    item_id INT(10),
    order_id INT(4),
    FOREIGN KEY(item_id) REFERENCES menu(item_id),
    FOREIGN KEY( order_id) REFERENCES _order(order_id),
    PRIMARY KEY(orderItem_id)
);

CREATE TABLE review(
	review_id INT(10),
    foodQuality INT(1),
    serviceQuality INT(1),
    deliveryQuality INT(1),
    _date VARCHAR(8),
    otherComments VARCHAR(500),
    customer_id INT(10),
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
    PRIMARY KEY(review_id)
);

CREATE TABLE address(
	address_id INT(10),
    street VARCHAR(50),
    city VARCHAR(20),
    state VARCHAR(5),
    zipCode INT(5),
    customer_id INT(10),
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
    PRIMARY KEY(address_id)
);

INSERT INTO `fdss`.`staff` (`staff_id`, `emailAddres`, `_password`, `firstName`, `lastName`) VALUES ('1', 'staff@cpp.edu', 'staff', 'Vincent', 'La');
INSERT INTO `fdss`.`driver`(`driver_id`, `emailAddres`, `_password`, `availability`) VALUES ('1', 'bob@cpp.edu', 'builder');
