
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

// // Register the JDBC driver
// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

// // Specify the connection URL, username, and password
// String url = "jdbc:sqlserver://localhost\\instanceName:portNumber;databaseName=yourDatabase";
// String username = "yourUsername";
// String password = "yourPassword";

// // Create the connection
// Connection connection = DriverManager.getConnection(url, username, password);


public class ConnectToServer {

    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:sqlserver://127.0.0.1:3306;databaseName=CS3560DFSS";
        String username = "root";
        String password = "2002";

        // Try-with-resources to automatically close the connection
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Connected successfully
            System.out.println("Connected to the database.");

            // Perform database operations here...
        } catch (SQLException e) {
            // Connection failed
            System.err.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // public static void main(String[] args) throws ClassNotFoundException {

    //     Connection conn = null;

    //     try {

    //         // Class.forName("com.mysql.cj.jdbc.Driver");
    //         DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

    //         String dbURL = "jdbc:sqlserver://localhost:3306\\CS3560DFSS";
    //         String user = "root";
    //         String pass = "2002";
    //         conn = DriverManager.getConnection(dbURL, user, pass);
            
    //         if (conn != null) {
    //             System.out.println("The connection has been successfully established.");
                
    //             // DatabaseMetaData dm = conn.getMetaData();
    //             // System.out.println("Driver name: " + dm.getDriverName());
    //             // System.out.println("Driver version: " + dm.getDriverVersion());
    //             // System.out.println("Product name: " + dm.getDatabaseProductName());
    //             // System.out.println("Product version: " + dm.getDatabaseProductVersion());
    //         }

    //     } catch (SQLException ex) {
    //         System.out.println("An error occurred while establishing the connection:");
    //         ex.printStackTrace();
    //     } finally {
    //         try {
    //             if (conn != null && !conn.isClosed()) {
    //                 conn.close();
    //             }
    //         } catch (SQLException ex) {
    //             ex.printStackTrace();
    //         }
    //     }
    // }
}

