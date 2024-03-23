public static void main(String[] args) {

}
    
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
 public class DeliveryDriver {
    private int deliveryID;
    private boolean availability;
 }
 
 
 /*
 * Customer class: create customers in restaurant system
 */
 public class Customer {
    private int customerID;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String paymentInfo; //credit card #
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

 /*
 * Method to call the hardcoded menu that is sectioned off into multiple different types 
 * -Parameters: the menu is sectioend off so the customer will specify what type of food/drink they want (example: beverages, burgers, and etc)
 * -Return: the array of items specified by the type indicated through "menuType[]"
 */
public String[] printMenu(String orderType) {
    String[] menuType; 

    return menuType[];
}

 /*
 * Creates an order object and will be used as the initiator for an entire order related to "fillShoppingCart"
 */
public void createOrder() {

}

 /*
 * Adds an item into an Order object that was created when this function is also executed
 * -Parameters: requires the orderID of the specific order, the item that the customer wants and the quantity
 * -Return: the entire order itself (if multiple items were orderedd)
 */
public String[] fillShoppingCart(int orderID, int itemID, int quantity) {
    return totalOrder[];
}

 /*
 * Checks the status of an order
 * -Parameters: requires the specific orderID
 * -Return: the status of that order
 */
public String checkStatus(int orderID) {
    return deliveryStatus;
}

 /*
 * Updates the status of an order (usually by a staff actor)
 * -Parameters: requires the specific orderID and the written status (can be changed to a number that represents a status)
 */
public void updateStatus(int orderID, String status) {

}

 /*
 * Cancels the order itself
 * -Parameters: requires the orderID of the order to be cancelled (marked cancelled in the status)
 */
public void cancelOrder(int orderID) {

}

 /*
 * Creates a object review from a customer 
 * -Parameters: requires 3 criterias to be rated on a rating of 0/5 and additional comments as a string
 */
public void createReview(int foodQ, int serviceQ, int deliveryQ, String extra) {
    
}

 /*
 * Allows an actor to view a review made by a previous customer
 * -Parameters: requires the specified quality related to foodQ, serviceQ, and/or deliveryQ
 * -Return: returns reviews of a specific quality related to foodQ, serviceQ, and/or deliveryQ
 */
public String viewReview(int foodQ, int serviceQ, int deliveryQ) {
    return specificReview[];
}

 /*
 * Allows staff to see total orders coming into the system
 * -Return: returns all the orderIDs of all coming orders made
 */
public int[] viewOrders() {
    return allOrders[];
}

 /*
 * Allows staff to see orders with the status sthat it is ready to deliver
 * -Return: returns all the orderIDs of orders that are ready for delivery
 */
public int[] readyOrders(){
    return allReadyOrders[];
}




 
 