import java.sql.*;
import java.time.LocalDate;

public class Review {
    private int reviewID;
    private int customerID;
    private int foodQuality;
    private int serviceQuality;
    private int deliveryQuality;
    private LocalDate date;
    private String otherComments;

    Review(int customerID, int foodQuality, int serviceQuality, int deliveryQuality, String otherComments) throws ClassNotFoundException, SQLException {
        this.customerID = customerID;
        this.foodQuality = foodQuality;
        this.serviceQuality = serviceQuality;
        this.deliveryQuality = deliveryQuality;
        this.date = LocalDate.now(); // Set the current date
        this.otherComments = otherComments;

        // SQL statement
        String sqlQuery = "INSERT INTO cs3560dfss.review(review_id, customer_id, foodQuality, serviceQuality, deliveryQuality, _date, otherComments) VALUES (?, ?, ?, ?, ?, ?, ?)";
        //Opens connection to the database
        Connection dbConnect = ConnectToServer.openConnect();
        //Try statement for inserting values into the database
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            // Set parameters for the SQL statement
            sqlSt.setInt(1, this.reviewID);
            sqlSt.setInt(2, this.customerID);
            sqlSt.setInt(3, this.foodQuality);
            sqlSt.setInt(4, this.serviceQuality);
            sqlSt.setInt(5, this.deliveryQuality);
            sqlSt.setDate(6, java.sql.Date.valueOf(this.date));
            sqlSt.setString(7, this.otherComments);

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

        String sqlQuery = "SELECT review_id FROM cs3560dfss.review(review_id, customer_id, foodQuality, serviceQuality, deliveryQuality, _date, otherComments) ORDER BY review_ID DESC LIMIT 1";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            ResultSet resultSet = sqlSt.executeQuery();

            if (resultSet.next()) {
                retrievedReviewID = resultSet.getInt("review_id");
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

        String sqlQuery = "SELECT foodQuality FROM cs3560dfss.review(review_id, customer_id, foodQuality, serviceQuality, deliveryQuality, _date, otherComments) WHERE review_ID = ?";
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

        String sqlQuery = "SELECT serviceQuality FROM cs3560dfss.review(review_id, customer_id, foodQuality, serviceQuality, deliveryQuality, _date, otherComments) WHERE review_ID = ?";
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

        String sqlQuery = "SELECT deliveryQuality FROM cs3560dfss.review(review_id, customer_id, foodQuality, serviceQuality, deliveryQuality, _date, otherComments) WHERE review_ID = ?";
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

        String sqlQuery = "SELECT _date FROM cs3560dfss.review(review_id, customer_id, foodQuality, serviceQuality, deliveryQuality, _date, otherComments) WHERE review_ID = ?";
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

        String sqlQuery = "SELECT otherComments FROM cs3560dfss.review(review_id, customer_id, foodQuality, serviceQuality, deliveryQuality, _date, otherComments) WHERE review_ID = ?";
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