import java.sql.*;

public class Staff {
    private int staffID;
    private String name;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //TESTING AREA//
    }

    //Creation of a staff member 
        //Constructor class
    Staff(int staffID, String name) {
        this.staffID = staffID;
        this.name = name;
    }

    //Setter method to put the values into the database
    public void createStaff() throws ClassNotFoundException, SQLException{
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
}
