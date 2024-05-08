import java.sql.*;

public class Customer {
    private int customerID;
    private String fName;
    private String lName;
    private String phoneNumber;
    private String paymentInfo;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Customer customer1 = new Customer(5, "0000110000", "TEST", "wate", "rese");
        customer1.viewMenu();
    }

    //Constructor
    Customer(int customerID, String phoneNumber, String paymentInfo, String fName, String lName) {
        this.customerID = customerID;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.paymentInfo = paymentInfo;
    }

     // METHODS FOR CUSTOMER (USE CASES)
        // VIEW MENU -- RATE/REVIEW SERVICE -- FILL SHOPPING CART -- CHECK STATUS -- CANCEL ORDER -- VIEW REVIEW

    //Setter method to input the attributes into the table
    public void createCustomer() throws ClassNotFoundException, SQLException {
         //SQL statement
         String sqlQuery = "INSERT INTO cs3560dfss.customer(customer_id, phoneNumber, paymentInfo, firstName, lastName) VALUES (?, ?, ?, ?, ?)";
         //Opens connection to the database
         Connection dbConnect = ConnectToServer.openConnect();
         //Try statement for inserting values into the database
         try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
             // Set parameters for the SQL statement
             sqlSt.setInt(1, customerID);
             sqlSt.setString(2, phoneNumber);
             sqlSt.setString(3, paymentInfo);
             sqlSt.setString(4, fName);
             sqlSt.setString(5, lName);
 
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

    //CAN CHANGE OUTPUT TO BE ResultSet (which will be all the values inside that database NONPARSE)
    public void viewMenu() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        String sqlQuery = "SELECT * FROM cs3560dfss.menu";
        //Printing out all the values inside the database
        String output;
        Statement sqlSt = dbConnect.createStatement();
        ResultSet dbResults = sqlSt.executeQuery(sqlQuery);
        //PRINTS OUT TO CONSOLE
        while (dbResults.next() != false) {
            output = dbResults.getString("item_id") + " " + dbResults.getString("foodName") + " " + dbResults.getDouble("price") + 
                " " + dbResults.getString("_description");
            System.out.println(output);
        }
        ConnectToServer.closeConnect(dbConnect);
    }

    public void rateService(int foodQuality, int serviceQuality, int deliveryQuality, String otherComments) throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();

        String sqlQuery = "INSERT INTO cs3560dfss.review (customer_id, foodQuality, serviceQuality, deliveryQuality, _date, otherComments) VALUES (?, ?, ?, ?, NOW(), ?)";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            sqlSt.setInt(1, customerID);
            sqlSt.setInt(2, foodQuality);
            sqlSt.setInt(3, serviceQuality);
            sqlSt.setInt(4, deliveryQuality);
            sqlSt.setString(5, otherComments);
            sqlSt.executeUpdate();

            ResultSet generatedKeys = sqlSt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int reviewID = generatedKeys.getInt(1);
                System.out.println("Thank you for your review. Your review ID is: " + reviewID);
            }
        } catch (SQLException error) {
            System.err.println("Error inserting review: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }
    }

    public void fillShoppingCart(int orderID, int itemID, int quantity) throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();

        String sqlQuery = "INSERT INTO cs3560dfss.orderItem (order_id, item_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderID);
            sqlSt.setInt(2, itemID);
            sqlSt.setInt(3, quantity);
            sqlSt.executeUpdate();

            System.out.println("Order item added to the shopping cart.");
        } catch (SQLException error) {
            System.err.println("Error adding order item: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }
    }

    public String checkStatus(int orderID) throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        String deliveryStatus = null;

        String sqlQuery = "SELECT deliveryStatus FROM cs3560dfss._order WHERE order_id = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, orderID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                deliveryStatus = resultSet.getString("deliveryStatus");
            } else {
                System.err.println("Error: No order found with order_id " + orderID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving delivery status: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return deliveryStatus;
    }

    public ResultSet viewReviews() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        String sqlQuery = "SELECT * FROM cs3560dfss.review";
        Statement sqlSt = dbConnect.createStatement();
        ResultSet dbResults = sqlSt.executeQuery(sqlQuery);
        ConnectToServer.closeConnect(dbConnect);
        return dbResults;
    }
}