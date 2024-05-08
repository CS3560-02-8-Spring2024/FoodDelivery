import java.sql.*;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private int customerID;
    private int driverID;
    private String deliveryStatus;

    //GET RID OF THE ORDERID CREATION
    Order(int orderID, int customerID, int driverID, String deliveryStatus) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.driverID = driverID;
        this.deliveryStatus = deliveryStatus;
    }

    public void createOrder() throws ClassNotFoundException, SQLException  {
        //SQL statement
        String sqlQuery = "INSERT INTO cs3560dfss._order(orderID, deliveryStatus, totalPrice) VALUES (?, ?, ?)";
        //Opens connection to the database
        Connection dbConnect = ConnectToServer.openConnect();
        //Try statement for inserting values into the database
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            // Set parameters for the SQL statement
            sqlSt.setInt(1, orderID);
            sqlSt.setInt(2, customerID);
            sqlSt.setInt(3, driverID);
            sqlSt.setString(4, deliveryStatus);

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
    
        String sqlQuery = "UPDATE cs3560dfss._order SET deliveryStatus = ? WHERE order_id = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setString(1, newStatus);
            sqlSt.setInt(2, orderID);
            int rowsUpdated = sqlSt.executeUpdate();
    
            if (rowsUpdated > 0) {
                System.out.println("Order with ID " + orderID + " status updated to " + newStatus);
            } else {
                System.err.println("Error: No order found with order_id " + orderID);
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
    
        String sqlQuery = "SELECT deliveryStatus FROM cs3560dfss._order WHERE order_id = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderID);
            ResultSet resultSet = sqlSt.executeQuery();
    
            if (resultSet.next()) {
                deliveryStatus = resultSet.getString("deliveryStatus");
            } else {
                System.err.println("Error: No delivery status found for order_id " + orderID);
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
    
        String sqlQuery = "SELECT * FROM cs3560dfss._order WHERE order_id = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderID);
            ResultSet resultSet = sqlSt.executeQuery();
    
            if (resultSet.next()) {
                System.out.println("Order ID: " + resultSet.getInt("order_id"));
                System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
                System.out.println("Driver ID: " + resultSet.getInt("driver_id"));
                System.out.println("Delivery Status: " + resultSet.getString("deliveryStatus"));
            } else {
                System.err.println("Error: No order found with order_id " + orderID);
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
    
        String sqlQuery = "UPDATE cs3560dfss._order SET deliveryStatus = 'Cancelled' WHERE order_id = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderID);
            int rowsUpdated = sqlSt.executeUpdate();
    
            if (rowsUpdated > 0) {
                System.out.println("Order with ID " + orderID + " has been cancelled.");
            } else {
                System.err.println("Error: No order found with order_id " + orderID);
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

        String sqlQuery = "SELECT order_id FROM cs3560dfss._order WHERE deliveryStatus = 'ready to deliver'";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            ResultSet resultSet = sqlSt.executeQuery();

            while (resultSet.next()) {
                int readyOrderID = resultSet.getInt("order_id");
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

    public int getOrderID() {
        return orderID;
    }


}