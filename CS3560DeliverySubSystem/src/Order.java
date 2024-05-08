import java.sql.*;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private String deliveryStatus; //allow staff and delivery drive to view/manipulate status of delivery.
    private double totalPrice;

    Order(int orderID, String deliveryStatus, double totalPrice) throws ClassNotFoundException, SQLException{
        this.orderID = orderID;
        this.deliveryStatus = deliveryStatus;
        this.totalPrice = totalPrice;

        //SQL statement
        String sqlQuery = "INSERT INTO fdss._order(orderID, deliveryStatus, totalPrice) VALUES (?, ?, ?)";
        //Opens connection to the database
        Connection dbConnect = ConnectToServer.openConnect();
        //Try statement for inserting values into the database
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            // Set parameters for the SQL statement
            sqlSt.setInt(1, orderID);
            sqlSt.setString(2, deliveryStatus);
            sqlSt.setDouble(3, totalPrice);

            // Execute the SQL statement
            int rowsInserted = sqlSt.executeUpdate();
            if (rowsInserted > 0) {
                //Successfully entering the value
                System.out.println("Data inserted into database.");
            } else {
                //Did not enter any values
                System.err.println("Error: Failed to insert data into database.");
            }
            //Try-Catch throw for errors
        } catch (SQLException error) {
            System.err.println("Error inserting data into database: " + error.getMessage());
            error.printStackTrace();
        }
        //Closing the connection to the database (no leaks)
        ConnectToServer.closeConnect(dbConnect);
    }

    public void updateDeliveryStatus(String newStatus) throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
    
        String sqlQuery = "UPDATE orders SET deliveryStatus = ? WHERE orderID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setString(1, newStatus);
            sqlSt.setInt(2, orderID);
            int rowsUpdated = sqlSt.executeUpdate();
    
            if (rowsUpdated > 0) {
                System.out.println("Order with ID " + orderID + " status updated to " + newStatus);
            } else {
                System.err.println("Error: No order found with orderID " + orderID);
            }
        } catch (SQLException error) {
            System.err.println("Error updating order status: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }
    }

    public String getDeliveryStatus() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        String deliveryStatus = null;
    
        String sqlQuery = "SELECT deliveryStatus FROM orders WHERE orderID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderID);
            ResultSet resultSet = sqlSt.executeQuery();
    
            if (resultSet.next()) {
                deliveryStatus = resultSet.getString("deliveryStatus");
            } else {
                System.err.println("Error: No delivery status found for orderID " + orderID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving delivery status: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }
    
        return deliveryStatus;
    }

    public void viewOrder() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
    
        String sqlQuery = "SELECT * FROM orders WHERE orderID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderID);
            ResultSet resultSet = sqlSt.executeQuery();
    
            if (resultSet.next()) {
                System.out.println("Order ID: " + resultSet.getInt("orderID"));
                System.out.println("Delivery Status: " + resultSet.getString("deliveryStatus"));
                System.out.println("Total Price: " + resultSet.getDouble("totalPrice"));
            } else {
                System.err.println("Error: No order found with orderID " + orderID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving order details: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }
    }

    public void cancelOrder(int orderID) throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
    
        String sqlQuery = "UPDATE orders SET deliveryStatus = 'Cancelled' WHERE orderID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderID);
            int rowsUpdated = sqlSt.executeUpdate();
    
            if (rowsUpdated > 0) {
                System.out.println("Order with ID " + orderID + " has been cancelled.");
            } else {
                System.err.println("Error: No order found with orderID " + orderID);
            }
        } catch (SQLException error) {
            System.err.println("Error cancelling order: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }
    }

    public ArrayList<Integer> readyOrders() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        ArrayList<Integer> readyOrderIDs = new ArrayList<>();

        String sqlQuery = "SELECT orderID FROM orders WHERE deliveryStatus = 'ready to deliver'";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            ResultSet resultSet = sqlSt.executeQuery();

            while (resultSet.next()) {
                int readyOrderID = resultSet.getInt("orderID");
                readyOrderIDs.add(readyOrderID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving ready orders: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return readyOrderIDs;
    }
}
