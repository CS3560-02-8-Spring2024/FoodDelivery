//package Swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;

public class CheckOut extends JFrame {

    private JTextField paymentInfo;
    private JTextField streetField;
    private JTextField cityField;

    private JComboBox<String> stateComboBox; // Added state dropdown
    private JTextField zipCodeField;

    private JTextField phoneNumber;
    private Customer customer;
    private Order order;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                //GETTING RID OF CUSTOMER ID TO BE AUTOINCREMENTED
                Customer temp = new Customer(1, "0", "0", "0", "0");
                Order tempO = new Order(1, 1, 1, "0");
                CheckOut checkOutPage = new CheckOut(temp, tempO);
                checkOutPage.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CheckOut(Customer customer, Order order) throws ClassNotFoundException, SQLException {
        this.customer = customer;
        this.order = order;
        setTitle("Check Out");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 0, 687, 700);

        JPanel mainPanel = new JPanel(); // Create a JPanel to hold the components
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        JPanel redStrip = new JPanel();
        redStrip.setToolTipText("");
        redStrip.setBackground(new Color(206, 41, 48));
        //redStrip.setLayout(new BorderLayout());
        redStrip.setPreferredSize(new Dimension(687, 75)); // Set preferred size here
        mainPanel.add(redStrip, BorderLayout.NORTH);

        JLabel logo = new JLabel("Check Out");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Tahoma", Font.BOLD, 20));
        logo.setBorder(new EmptyBorder(20, 5, 5, 5));
        redStrip.add(logo);


        //shipping info
        JPanel shippingInfoPanel = new JPanel();
        shippingInfoPanel.setLayout(new BoxLayout(shippingInfoPanel, BoxLayout.Y_AXIS));
        shippingInfoPanel.setPreferredSize(new Dimension(425, 1080));
        mainPanel.add(shippingInfoPanel, BorderLayout.WEST);

        //Order details
        JPanel orderDetails = new JPanel();
        orderDetails.setLayout(new BoxLayout(orderDetails, BoxLayout.Y_AXIS));
        orderDetails.setPreferredSize(new Dimension(262, 1080));
        orderDetails.setBorder(new MatteBorder(0, 15, 0, 0, new Color(157, 161, 168)));
        mainPanel.add(orderDetails, BorderLayout.EAST);

        //Create fields
        // shippingInfoPanel.add(Box.createVerticalStrut(15));
        // JLabel creditcardInfo = new JLabel("Enter Credit Card Info");
        // creditcardInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
        // creditcardInfo.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        // shippingInfoPanel.add(creditcardInfo);

        // shippingInfoPanel.add(Box.createVerticalStrut(5));
        // JLabel cardLabel = new JLabel("(Card # / Exp / CVV / ZIP) ");
        // cardLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        // shippingInfoPanel.add(cardLabel);
        // paymentInfo = new JTextField();
        // paymentInfo.setMaximumSize(new Dimension(200, 30));
        // shippingInfoPanel.add(paymentInfo);

        shippingInfoPanel.add(Box.createVerticalStrut(15));
        JLabel titleOfShippingPanel = new JLabel("Enter Delivery Info");
        titleOfShippingPanel.setFont(new Font("Tahoma", Font.BOLD, 20));
        titleOfShippingPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        shippingInfoPanel.add(titleOfShippingPanel);

        shippingInfoPanel.add(Box.createVerticalStrut(15));
        JLabel street = new JLabel("Street");
        street.setFont(new Font("Tahoma", Font.PLAIN, 15));
        street.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        shippingInfoPanel.add(street);
        streetField = new JTextField();
        streetField.setMaximumSize(new Dimension(200, 30));
        shippingInfoPanel.add(streetField);

        shippingInfoPanel.add(Box.createVerticalStrut(15));
        JLabel city = new JLabel("City");
        city.setFont(new Font("Tahoma", Font.PLAIN, 15));
        city.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        shippingInfoPanel.add(city);
        cityField = new JTextField();
        cityField.setMaximumSize(new Dimension(200, 30));
        shippingInfoPanel.add(cityField);

        shippingInfoPanel.add(Box.createVerticalStrut(15));
        JLabel state = new JLabel("State");
        state.setFont(new Font("Tahoma", Font.PLAIN, 15));
        state.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        shippingInfoPanel.add(state);

        String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE",
                "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
                "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT",
                "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH",
                "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT",
                "VT", "VA", "WA", "WV", "WI", "WY"};

        // Create the combo box and add the states to it
        stateComboBox = new JComboBox<>(states);
        stateComboBox.setMaximumSize(new Dimension(200, 30));
        shippingInfoPanel.add(stateComboBox);
        String stateAddr = (String) stateComboBox.getSelectedItem();

        shippingInfoPanel.add(Box.createVerticalStrut(15));
        JLabel zipcodes = new JLabel("Zip Code");
        zipcodes.setFont(new Font("Tahoma", Font.PLAIN, 15));
        zipcodes.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        shippingInfoPanel.add(zipcodes);
        zipCodeField = new JTextField();
        zipCodeField.setMaximumSize(new Dimension(200, 30));
        shippingInfoPanel.add(zipCodeField);

        // shippingInfoPanel.add(Box.createVerticalStrut(15));
        // JLabel pn = new JLabel("Phone Number");
        // pn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        // pn.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        // shippingInfoPanel.add(pn);
        // phoneNumber = new JTextField();
        // phoneNumber.setMaximumSize(new Dimension(200, 30));
        // shippingInfoPanel.add(phoneNumber);

        shippingInfoPanel.add(Box.createVerticalStrut(35));
        JButton confirm = new JButton("Confirm");
        confirm.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        shippingInfoPanel.add(confirm);


        //Order detail fields
        orderDetails.add(Box.createVerticalStrut(15));
        JLabel orderDetailsTitle = new JLabel("Your Order Details");
        orderDetailsTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        orderDetailsTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        orderDetails.add(orderDetailsTitle);

        // String foodName[] = {"Burger", "Fries"};
        // Double prices[] = {3.99, 4.99};

        String allFoodNames[] = new String[10];
        Double allPrices[] = new Double[10];
        int foodID;
        int counter = 0;
        Connection dbConnect = ConnectToServer.openConnect();
        //Getting the food IDs 
        String sqlQuery = "SELECT * FROM cs3560dfss.orderitem WHERE order_id = ";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setInt(1, order.getOrderID());
            ResultSet dbResults = sqlSt.executeQuery(sqlQuery);
            while(dbResults.next()) {
                //Getting the food names
                foodID = dbResults.getInt("item_id");
                String sqlQuery2 = "SELECT * FROM cs3560dfss.menu WHERE item_id = ? ";
                try (PreparedStatement sqlSt2 = dbConnect.prepareStatement(sqlQuery2)) {
                    sqlSt2.setInt(1, foodID);
                    ResultSet dbResults2 = sqlSt.executeQuery(sqlQuery);
                    allFoodNames[counter] = dbResults2.getString("foodName");
                    allPrices[counter] = dbResults2.getDouble("price");
                }     
                counter++;
            }
        }
        ConnectToServer.closeConnect(dbConnect);

        //Only obtain filled entries
        String foodNames[] = new String[counter];
        Double prices[] = new Double[counter];
        for (int i = 0; i < counter; i++) {
            foodNames[i] = allFoodNames[i];
            prices[i] =  allPrices[i];
        }    

        for (int i = 0; i < foodNames.length; i++) {
            JLabel foodLabel = new JLabel(foodNames[i] + ": $" + prices[i]);
            foodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderDetails.add(foodLabel);

        }
        /**
         * Order information from database goes here
         */
        Double totalAmt = 0.0;
        for (int i = 0; i < allPrices.length; i++) {
            totalAmt += allPrices[i];
        }

        String streetAddr = streetField.getText();
        String cityAddr = cityField.getText();
        String zipCodeAddr = zipCodeField.getText();
        Address customerAddr = new Address(1, customer.getCustomerID(), streetAddr, cityAddr, stateAddr, zipCodeAddr);
        customerAddr.createAddress();
        
        orderDetails.add(Box.createVerticalStrut(300));
        JLabel total = new JLabel("Total: " + totalAmt); // get totalPrice
        orderDetails.add(total, Component.LEFT_ALIGNMENT);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the current width of the frame
                int frameWidth = getWidth();

                // Calculate the preferred width for the shippingInfoPanel based on a percentage of the frame width
                int shippingInfoWidth = (int) (frameWidth * 0.62); // Approximately 425/687

                // Calculate the preferred width for the orderDetails panel
                int orderDetailsWidth = frameWidth - shippingInfoWidth;

                // Set the preferred sizes of the panels
                shippingInfoPanel.setPreferredSize(new Dimension(shippingInfoWidth, getHeight()));
                orderDetails.setPreferredSize(new Dimension(orderDetailsWidth, getHeight()));

                // Update the layout
                revalidate();
            }
        });
    }
}
