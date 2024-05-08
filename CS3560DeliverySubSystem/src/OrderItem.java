import java.sql.*;

public class OrderItem {
    private int orderItemID;
    private int quantity;
    private int itemID;
    private int orderID;

    OrderItem(int orderItemID, int quantity, int itemID, int orderID) throws ClassNotFoundException, SQLException {
        this.orderItemID = orderItemID;
        this.quantity = quantity;
        this.itemID = itemID;
        this.orderID = orderID;

        // SQL statement
        String sqlQuery = "INSERT INTO cs3560dfss.orderitem(orderitem_id, quantity, item_id, order_id) VALUES (?, ?, ?, ?)";
        //Opens connection to the database
        Connection dbConnect = ConnectToServer.openConnect();
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            // Set parameters for the SQL statement
            sqlSt.setInt(1, orderItemID);
            sqlSt.setInt(2, quantity);
            sqlSt.setInt(3, itemID);
            sqlSt.setInt(4, orderID);

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
        } finally {
            //Closing the connection to the database (no leaks)
            ConnectToServer.closeConnect(dbConnect);
        }
    }

    public int getOrderItemID() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        int retrievedOrderItemID = 0;

        String sqlQuery = "SELECT orderitem_id FROM cs3560dfss.orderitem WHERE orderitem_id = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderItemID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedOrderItemID = resultSet.getInt("orderitem_id");
            } else {
                System.err.println("Error: No order item found with orderitem_id " + orderItemID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving order item ID: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedOrderItemID;
    }

    public int getQuantity() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        int retrievedQuantity = 0;

        String sqlQuery = "SELECT quantity FROM cs3560dfss.orderitem WHERE orderitem_id = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderItemID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedQuantity = resultSet.getInt("quantity");
            } else {
                System.err.println("Error: No order item found with orderitem_id " + orderItemID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving order item quantity: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedQuantity;
    }

    public int getItemID() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        int retrievedItemID = 0;

        String sqlQuery = "SELECT item_id FROM cs3560dfss.orderitem WHERE orderitem_id = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderItemID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedItemID = resultSet.getInt("item_id");
            } else {
                System.err.println("Error: No order item found with orderitem_id " + orderItemID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving item ID: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedItemID;
    }

    public int getOrderID() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        int retrievedOrderID = 0;

        String sqlQuery = "SELECT order_id FROM cs3560dfss.orderitem WHERE orderitem_id = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderItemID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedOrderID = resultSet.getInt("order_id");
            } else {
                System.err.println("Error: No order item found with orderitem_id " + orderItemID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving order ID: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedOrderID;
    }

    public void setQuantity(int quantity) throws ClassNotFoundException, SQLException {
        this.quantity = quantity;

        // SQL statement
        String sqlQuery = "UPDATE cs3560dfss.orderitem SET quantity = ? WHERE orderitem_id = ?";
        Connection dbConnect = ConnectToServer.openConnect();

        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            // Set parameters for the SQL statement
            sqlSt.setInt(1, quantity);
            sqlSt.setInt(2, orderItemID);

            // Execute the SQL statement
            int rowsUpdated = sqlSt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Order item quantity updated in the database.");
            } else {
                System.err.println("Error: Failed to update order item quantity in the database.");
            }
        } catch (SQLException error) {
            System.err.println("Error updating order item quantity in the database: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }
    }
}