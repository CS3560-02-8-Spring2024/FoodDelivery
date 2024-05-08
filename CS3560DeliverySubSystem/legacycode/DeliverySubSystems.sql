/**INITIALIZING A DATABASE AND USING IT TO CREATE THE NECESSARY TABLES**/
CREATE DATABASE CS3560DFSS;

USE CS3560DFSS;

CREATE TABLE menu(
	item_id int(10) NOT NULL,
    foodName varchar(20) NOT NULL,
    price decimal(5,2) NOT NULL,
    PRIMARY KEY(item_id)
);
CREATE TABLE staff(
	staff_id int(10) NOT NULL,
    staffName varchar(30) NOT NULL,
    PRIMARY KEY(staff_id)
);
CREATE TABLE deliverDriver(
	driver_id int(10) NOT NULL,
    availability varchar(3) NOT NULL,
    PRIMARY KEY(driver_id)
);
CREATE TABLE customer(
	customer_id int(10) NOT NULL,
    address varchar(30) NOT NULL,
    phoneNumber varchar(10) NOT NULL,
    paymentInfo varchar(30) NOT NULL,
    PRIMARY KEY(customer_id)
);
CREATE TABLE _order(
	order_id int(10) NOT NULL,
    customer_id int(10) NOT NULL,
    driver_id int(10) NOT NULL,
    staff_id int(10) NOT NULL,
    deliveryStatus char(12) NOT NULL,
    PRIMARY KEY(order_id),
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY(driver_id) REFERENCES deliverDriver(driver_id),
    FOREIGN KEY(staff_id) REFERENCES staff(staff_id)
);
CREATE TABLE orderItem(
	orderitem_id int(10) NOT NULL,
    quantity int(10) NOT NULL,
    item_id int(10) NOT NULL,
    order_id int(10) NOT NULL,
    PRIMARY KEY(orderitem_id),
    FOREIGN KEY(item_id) REFERENCES menu(item_id),
    FOREIGN KEY(order_id) REFERENCES _order(order_id)
);
CREATE TABLE review(
	review_id int(10) NOT NULL,
    customer_id int(10) NOT NULL,
    foodQuality int(5) NOT NULL,
    serviceQuality int(5) NOT NULL,
    deliveryQuality int(5) NOT NULL,
    _date varchar(8) NOT NULL,
    otherComments varchar(500) NOT NULL,
    PRIMARY KEY(review_id),
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);
