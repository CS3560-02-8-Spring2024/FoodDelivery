//package Swing.CS3560DeliverySubSystem.src;
import Swing.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;

public class ReviewUI extends JFrame {
    private JTextArea reviewTextArea;
    private JPanel switchPanel;
    private String title;

    public ReviewUI(String title) {
        this.title = title;
        setTitle("Review Viewer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //List of components to add
        JPanel mainPanel = new JPanel(new BorderLayout());
        reviewTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(reviewTextArea);
        JButton loadButton = new JButton("Load Reviews");

        //Adding components to the frame
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(loadButton, BorderLayout.SOUTH);
        add(mainPanel);

        JPanel redStrip = new JPanel();
        redStrip.setToolTipText("");
        redStrip.setBackground(new Color(206, 41,48));
        redStrip.setPreferredSize(new Dimension(687, 75));
        mainPanel.add(redStrip, new BorderLayout().NORTH);

        //action listener
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    loadReviews();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ReviewUI.this, "Error loading reviews: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Button to return to main page
        switchPanel = new JPanel();
        switchPanel.setBackground(Color.BLACK);
        JButton switchButton = new JButton("Back to Menu");
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(MainPage);
            }
        });
        mainPanel.add(switchButton,BorderLayout.EAST);
    }

    //This should be used to pass the title to the constructor
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ReviewUI reviewUI = new ReviewUI("Review Viewer");
                reviewUI.setVisible(true);
            }
        });
    }

    private void loadReviews() throws ClassNotFoundException, SQLException {
        //Clear previous content
        reviewTextArea.setText("");

        //Accessing the SQL database for the table of reviews
        Connection dbConnect = ConnectToServer.openConnect();
        String sqlQuery = "SELECT * FROM cs3560dfss.review";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            ResultSet resultSet = sqlSt.executeQuery();
            while (resultSet.next()) {
                int reviewID = resultSet.getInt("review_id");
                int customerID = resultSet.getInt("customer_id");
                int foodQuality = resultSet.getInt("foodQuality");
                int serviceQuality = resultSet.getInt("serviceQuality");
                int deliveryQuality = resultSet.getInt("deliveryQuality");
                LocalDate date = resultSet.getDate("_date").toLocalDate();
                String otherComments = resultSet.getString("otherComments");

                //Append reviews from database into the main components
                reviewTextArea.append("Review ID: " + reviewID + "\n");
                reviewTextArea.append("Customer ID: " + customerID + "\n");
                reviewTextArea.append("Food Quality: " + foodQuality + "\n");
                reviewTextArea.append("Service Quality: " + serviceQuality + "\n");
                reviewTextArea.append("Delivery Quality: " + deliveryQuality + "\n");
                reviewTextArea.append("Date: " + date.toString() + "\n");
                reviewTextArea.append("Other Comments: " + otherComments + "\n\n");
            }
        } finally {
            ConnectToServer.closeConnect(dbConnect);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ReviewUI reviewUI = new ReviewUI();
                reviewUI.setVisible(true);
            }
        });
    }
}