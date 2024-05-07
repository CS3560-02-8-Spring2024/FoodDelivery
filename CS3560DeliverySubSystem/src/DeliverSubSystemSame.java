import java.sql.*;


public class DeliverSubSystemSame {

    public class DeliverySubSystem {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

        public static void main(String[] args) {
            System.out.println("hello world");
        }
    
            
        /*
        * Staff class: create staff members in restaurant system
        */
        public class Staff {
            private int staffID;
            private String name;

            public Staff(int staffID, String name) {
                this.staffID = staffID;
                this.name = name;
            }
        }
        
        
        /*
        * DeliveryDriver Class: create delivery drivers in restaurant system
        */
        public class DeliveryDriver {
            private int driverID;
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

            //constructor
            public Order(int orderID, String deliveryStatus, double totalPrice) {
                this.orderID = orderID;
                this.deliveryStatus = deliveryStatus;
                this.totalPrice = totalPrice;
            }

            public void createOrder() {
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    String sql = "INSERT INTO orders (orderID, deliveryStatus, totalPrice) VALUES (?, ?, ?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setInt(1, orderID);
                    statement.setString(2, deliveryStatus);
                    statement.setDouble(3, totalPrice);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Method to check the delivery status of an order
            public String getDeliveryStatus() {
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    String sql = "SELECT deliveryStatus FROM orders WHERE orderID = ?";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setInt(1, orderID);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        return resultSet.getString("deliveryStatus");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }

            // Method to cancel an order
            public void cancelOrder() {
                updateStatus(orderID, "Cancelled");
            }
        
            public void viewOrder() {
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    String sql = "SELECT * FROM orders WHERE orderID = ?";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setInt(1, orderID);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        System.out.println("Order ID: " + resultSet.getInt("orderID"));
                        System.out.println("Delivery Status: " + resultSet.getString("deliveryStatus"));
                        System.out.println("Total Price: " + resultSet.getDouble("totalPrice"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        public static void updateStatus(int orderID, String status) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "UPDATE orders SET deliveryStatus = ? WHERE orderID = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, status);
                statement.setInt(2, orderID);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    
    
    }
    
     
     
}
