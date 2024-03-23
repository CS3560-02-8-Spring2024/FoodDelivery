/*
 * Staff class: create staff members in restaurant system
 */
public class Staff {
    private int staffID;
    private String name;
}

/*
 * DeliveryDriver Class: create delivery drivers in restaurant system
 */
public class DeliverDriver {
    private int deliveryID;
    private boolean availabilty;
}

/*
 * Customer class: create customers in restaurant system
 */
public class Customer {
    private int customerID;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private int phoneNumber;
    private int paymentInfo; //credit card #
}
/*
 * Order class: create a customer's respective order
 */
public class Order {
    private int orderID;
    private String deliveryStatus; //allow staff and delivery drive to view/manipulate status of delivery.
    private double totalPrice;
}

/*
 * orderItem class: identifies item of order
 */
public class orderItem {
    private int itemID;
    private double price;
    private int quantity;
}
/*
 * Review class: allows customers to rate the service, food, and delivery of the restaurant
 */
public class Review {
    private int reviewID;
    private int foodQuality;
    private int serviceQuality;
    private int deliveryQuality;
    private String date;
    private String otherComments;

}
