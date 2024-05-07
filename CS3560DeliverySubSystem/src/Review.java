import java.sql.*;
import java.time.LocalDate;

public class Review {
    private int reviewID;
    private int foodQuality;
    private int serviceQuality;
    private int deliveryQuality;
    private LocalDate date;
    private String otherComments;

    Review(int foodQuality, int serviceQuality, int deliveryQuality, String otherComments) throws ClassNotFoundException, SQLException {
        this.foodQuality = foodQuality;
        this.serviceQuality = serviceQuality;
        this.deliveryQuality = deliveryQuality;
        this.date = LocalDate.now(); // Set the current date
        this.otherComments = otherComments;

        // SQL statement
        String sqlQuery = "INSERT INTO FDSS.review (foodQuality, serviceQuality, deliveryQuality, date, otherComments) VALUES (?, ?, ?, ?, ?)";
        //Opens connection to the database
        Connection dbConnect = ConnectToServer.openConnect();
        //Try statement for inserting values into the database
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            // Set parameters for the SQL statement
            sqlSt.setInt(1, foodQuality);
            sqlSt.setInt(2, serviceQuality);
            sqlSt.setInt(3, deliveryQuality);
            sqlSt.setDate(4, java.sql.Date.valueOf(date));
            sqlSt.setString(5, otherComments);

            // Execute the SQL statement
            int rowsInserted = sqlSt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Review data inserted into the database.");
            } else {
                System.err.println("Error: Failed to insert review data into the database.");
            }
        } catch (SQLException error) {
            System.err.println("Error inserting review data into the database: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }
    }

    public int getReviewID() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        int retrievedReviewID = 0;

        String sqlQuery = "SELECT reviewID FROM FDSS.review ORDER BY reviewID DESC LIMIT 1";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedReviewID = resultSet.getInt("reviewID");
            } else {
                System.err.println("Error: No review found.");
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving review ID: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedReviewID;
    }

    public int getFoodQuality() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        int retrievedFoodQuality = 0;

        String sqlQuery = "SELECT foodQuality FROM DFSS.review WHERE reviewID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, reviewID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedFoodQuality = resultSet.getInt("foodQuality");
            } else {
                System.err.println("Error: No review found with reviewID " + reviewID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving food quality rating: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedFoodQuality;
    }

    public int getServiceQuality() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        int retrievedServiceQuality = 0;

        String sqlQuery = "SELECT serviceQuality FROM DFSS.review WHERE reviewID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, reviewID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedServiceQuality = resultSet.getInt("serviceQuality");
            } else {
                System.err.println("Error: No review found with reviewID " + reviewID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving service quality rating: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedServiceQuality;
    }

    public int getDeliveryQuality() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        int retrievedDeliveryQuality = 0;

        String sqlQuery = "SELECT deliveryQuality FROM DFSS.review WHERE reviewID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, reviewID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedDeliveryQuality = resultSet.getInt("deliveryQuality");
            } else {
                System.err.println("Error: No review found with reviewID " + reviewID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving delivery quality rating: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedDeliveryQuality;
    }

    public LocalDate getDate() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        LocalDate retrievedDate = null;

        String sqlQuery = "SELECT date FROM DFSS.review WHERE reviewID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, reviewID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedDate = resultSet.getDate("date").toLocalDate();
            } else {
                System.err.println("Error: No review found with reviewID " + reviewID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving review date: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedDate;
    }

    public String getOtherComments() throws ClassNotFoundException, SQLException {
        Connection dbConnect = ConnectToServer.openConnect();
        String retrievedComments = null;

        String sqlQuery = "SELECT otherComments FROM DFSS.review WHERE reviewID = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, reviewID);
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedComments = resultSet.getString("otherComments");
            } else {
                System.err.println("Error: No review found with reviewID " + reviewID);
            }
        } catch (SQLException error) {
            System.err.println("Error retrieving other comments: " + error.getMessage());
            error.printStackTrace();
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }

        return retrievedComments;
    }
}