import java.sql.*;


public class Address {

    private int addressID;
    private int customerID;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    }

    //Constructor
    Address(int addressID, int customerID, String street, String city, String state, String zipCode) {
        this.addressID = addressID;
        this.customerID = customerID;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

     // METHODS FOR CUSTOMER (USE CASES)
        // VIEW MENU -- RATE/REVIEW SERVICE -- FILL SHOPPING CART -- CHECK STATUS -- CANCEL ORDER -- VIEW REVIEW

    //Setter method to input the attributes into the table
    public void createAddress() throws ClassNotFoundException, SQLException {
         //SQL statement
         String sqlQuery = "INSERT INTO cs3560dfss.address(address_id, customer_id, phoneNumber, street, city, state) VALUES (?, ?, ?, ?, ?, ?)";
         //Opens connection to the database
         Connection dbConnect = ConnectToServer.openConnect();
         //Try statement for inserting values into the database
         try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
             // Set parameters for the SQL statement
             sqlSt.setInt(1, addressID);
             sqlSt.setInt(2, customerID);
             sqlSt.setString(3, street);
             sqlSt.setString(4, city);
             sqlSt.setString(5, state);
             sqlSt.setString(6, zipCode);
 
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
}
