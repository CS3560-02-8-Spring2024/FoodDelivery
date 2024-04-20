Create Table 'menu'(
	'item_id' int(10) Not Null,
    'foodName' varchar(20) Not Null,
    'price' decimal(5,2) Not Null,
    Primary Key('item_id')
)
Create Table 'orderItem'(
	'orderitem_id' int(10) Not Null,
    'quantity' int(10) Not Null,
    'item_id' int(10) Not Null,
    'order_id' int(10) Not Null,
    Primary Key('orderitem_id')
    Foreign Key('item_id')
    Foreign Key('order_id')
)
Create Table 'order'(
	'order_id' int(10) Not Null,
    'customer_id' int(10) Not Null,
    'driver_id' int(10) Not Null,
    'staff_id' int(10) Not Null,
    'deliveryStatus' char(12) Not Null,
    Primary Key('order_id')
    Foreign Key('customer_id')
    Foreign Key('driver_id')
    Foreign Key('staff_id')
)
Create Table 'staff'(
	'staff_id' int(10) Not Null,
    'staffName' varchar(30) Not Null,
    Primary Key('staff_id')
)
Create Table 'deliverDriver'(
	'driver_id' int(10) Not Null,
    'availability' varchar(3) Not Null,
    Primary Key('driver_id')
)
Create Table 'customer'(
	'customer_id' int(10) Not Null,
    'address' varchar(30) Not Null,
    'phoneNumber' varchar(10) Not Null,
    'paymentInfo' varchar(30) Not Null,
    Primary Key ('customer_id')
)
Create Table 'review'(
	'review_id' int(10) Not Null,
    'customer_id' int(10) Not Null,
    'foodQuality' int(5) Not Null,
    'serviceQuality' int(5) Not Null,
    'deliveryQuality' int(5) Not Null,
    'date' varchar(8) Not Null,
    'otherComments' varchar(500) Not Null,
    Primary Key('review_id')
    Foreign Key('customer_id')
)
