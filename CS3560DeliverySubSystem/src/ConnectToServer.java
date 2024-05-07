import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Utility class to open/close the connection to the database
public class ConnectToServer {
    //Connecting to database (locally - may turn this into an online cloud)

        // Database connection parameters        
    private static final String url = "jdbc:mysql://localhost:3306/CS3560DFSS";
    private static final String username = "root";
    private static final String password = "2002"; 

        private static final Connection connection = null;

    //Opening the connection
    public static Connection openConnect() throws ClassNotFoundException {
        //Try statement to ensure that the connection has been made
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                //Successfully connected
                System.out.println("Connected to the database!");
                return connection;

            } catch (SQLException error) {
                // Connection failed
                System.err.println("Error connecting to the database: " + error.getMessage());
                error.printStackTrace();
            }
        return null;
    }

    //Obtaining connection return
    public static Connection getConnection() {
        return connection;
    }
    
    //Closing the connection
    public static void closeConnect(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Closed connection to the database!");
            }
        }    
            catch (SQLException error) {
                System.err.println("Error in closing the connection to the database" + error.getMessage());
                error.printStackTrace();
            }   
    }
}

