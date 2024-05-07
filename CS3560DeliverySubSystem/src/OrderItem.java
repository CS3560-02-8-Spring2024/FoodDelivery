import java.sql.*;

public class OrderItem {
    private int orderItemID;
    private int quantity;

    OrderItem(int orderItemID, String deliveryStatus, double totalPrice)  throws ClassNotFoundException, SQLException{
        this.orderItemID = orderItemID;
        this.quantity = quantity;

        // SQL statement
        String sqlQuery = "INSERT INTO fdss.orderItem(itemID, price, quantity) VALUES (?, ?, ?)";
        //Opens connection to the database
        Connection dbConnect = ConnectToServer.openConnect();
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            // Set parameters for the SQL statement
            sqlSt.setInt(1, orderItemID);
            sqlSt.setInt(3, quantity);

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

    public int getItemID() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        int retrievedItemID = 0;

        String sqlQuery = "SELECT itemID FROM DFSS.orderitem WHERE itemID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderItemID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedItemID = resultSet.getInt("itemID");
            } else {
                System.err.println("Error: No order item found with itemID " + orderItemID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving order item ID: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedItemID;
    }

    public double getPrice() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        double retrievedPrice = 0.0;

        String sqlQuery = "SELECT price FROM DFSS.orderitem WHERE itemID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderItemID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedPrice = resultSet.getDouble("price");
            } else {
                System.err.println("Error: No order item found with itemID " + orderItemID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving order item price: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedPrice;
    }

    public int getQuantity() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        int retrievedQuantity = 0;

        String sqlQuery = "SELECT quantity FROM DFSS.orderitem WHERE itemID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderItemID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedQuantity = resultSet.getInt("quantity");
            } else {
                System.err.println("Error: No order item found with itemID " + orderItemID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving order item quantity: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedQuantity;
    }

    public void setQuantity(int quantity) throws ClassNotFoundException, SQLException {
        this.quantity = quantity;

        // SQL statement
        String sqlQuery = "UPDATE DFSS.orderitem SET quantity = ? WHERE itemID = ?";
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
