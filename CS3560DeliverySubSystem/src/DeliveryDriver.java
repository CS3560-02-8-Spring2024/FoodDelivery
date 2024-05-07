import java.sql.*;

public class DeliveryDriver {
    private int driverID;
    private String availability;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DeliveryDriver driver2 = new DeliveryDriver(2, "yes");
        String update = "no";
        driver2.updateAvail(2, update);
    }


    DeliveryDriver(int driverID, String avaliable) throws ClassNotFoundException, SQLException{
        this.driverID = driverID;
        availability = avaliable;

         //SQL statement
         String sqlQuery = "INSERT INTO cs3560dfss.deliverdriver(driver_id, availability) VALUES (?, ?)";
         //Opens connection to the database
         Connection dbConnect = ConnectToServer.openConnect();
         //Try statement for inserting values into the database
         try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
             // Set parameters for the SQL statement
             sqlSt.setInt(1, driverID);
             sqlSt.setString(2, avaliable);

 
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

    public void updateAvail(int driverID, String status) throws ClassNotFoundException, SQLException {
        availability = status;

        Connection dbConnect = ConnectToServer.openConnect();
        String sqlQuery = "UPDATE cs3560dfss.deliverdriver(driver_id, availability) SET availability = ? WHERE driver_id = ?";

        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setString(1, status);
            sqlSt.setInt(2, driverID);
    
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
