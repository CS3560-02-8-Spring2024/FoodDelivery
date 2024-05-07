import java.sql.*;

public class Staff {
    private int staffID;
    private String name;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        int staffID = 8;
        String name = "Jork";

        Staff staff1 = new Staff(staffID, name);

    }

    //Creation of a staff member also inputs values of the staffID and name into the database
        //Constructor class
    Staff(int staffID, String name) throws ClassNotFoundException, SQLException {
        this.staffID = staffID;
        this.name = name;
        
        //SQL statement
        String sqlQuery = "INSERT INTO cs3560dfss.staff (staff_id, staffName) VALUES (?, ?)";
        //Opens connection to the database
        Connection dbConnect = ConnectToServer.openConnect();
        //Try statement for inserting values into the database
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            // Set parameters for the SQL statement
            sqlSt.setInt(1, staffID);
            sqlSt.setString(2, name);

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

    // METHODS FOR STAFF MEMBER (USE CASES)
            //will most likely consolidate majority of these use cases WITHIN an order itself
        // UPDATE STATUS -- FILL SHOPPING CART -- CHECK STATUS -- CANCEL ORDER -- VIEW ORDER -- VIEW REVIEW
    public void updateStatus(int orderID) throws ClassNotFoundException, SQLException{
        
    }

    public void fillShoppingCart(int[] foodOrder) {
        //Int array that contains the foodIDs of all the foods that want to be added
    }


    public String checkStatus(int orderID) throws ClassNotFoundException, SQLException {

        return null;
    }

    public void cancelOrder(int orderID) {

    }

    public ResultSet viewOrder(int orderID) {
        return null;
    }

    public ResultSet viewReviews() {
        return null;
    }

}
