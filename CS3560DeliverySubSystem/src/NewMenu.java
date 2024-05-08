//package Demo;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.conf.ConnectionPropertiesTransform;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

public class NewMenu extends JFrame implements ActionListener{
    // private static final String DB_URL = "jdbc:mysql://localhost:3306/cs3560fdss";
    // private static final String DB_USER = "root";
    // private static final String DB_PASSWORD = "2002";
        JButton submit;
        Customer customer;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                //CHANGE FOR CUSTOMERID TO NOT BE EXISTING
                Customer temp = new Customer(8, "0", "0", "0", "0");
                NewMenu menuPage = new NewMenu(temp);
                menuPage.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NewMenu(Customer customer) throws ClassNotFoundException {
        this.customer = customer;
        JFrame f = new JFrame("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 687, 531);
;
        JPanel mainPanel = new JPanel(); // Create a JPanel to hold the components
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        JPanel redStrip = new JPanel();
        redStrip.setToolTipText("");
        redStrip.setBackground(new Color(206, 41, 48));
        //redStrip.setLayout(new BorderLayout());
        redStrip.setPreferredSize(new Dimension(687, 75)); // Set preferred size here
        mainPanel.add(redStrip, new BorderLayout().NORTH);

        JLabel logo = new JLabel("Menu");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Tahoma", Font.BOLD, 20));
        logo.setBorder(new EmptyBorder(20, 5, 5, 5));
        redStrip.add(logo);

        JPanel menuItemsPanel = new JPanel();
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBounds(0, 75, 687, 1000);

        /**
         * add menu items
        **/
        try (Connection connection = ConnectToServer.openConnect()) {
            String query = "SELECT * FROM cs3560dfss.menu";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String foodName = resultSet.getString("foodName");
                    String description = resultSet.getString("_description");
                    Double price = resultSet.getDouble("price");
                    int foodID = resultSet.getInt("item_id");
                    JPanel checkBoxPanel = createCheckBoxPanel(foodName, description, price, foodID);
                    menuItemsPanel.add(checkBoxPanel);
                }
            }
        } catch (SQLException | MalformedURLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
        }
//| MalformedURLException ex
        JScrollPane scrollPane = new JScrollPane(menuItemsPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        submit = new JButton("Fill Shopping Cart");
        submit.addActionListener(this);
        mainPanel.add(submit, BorderLayout.SOUTH);
    }
    private JPanel createCheckBoxPanel(String foodName, String description, double price, int foodID) throws MalformedURLException {
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BorderLayout());

        //create checkbox
        JCheckBox checkBox = new JCheckBox();
        checkBoxPanel.add(checkBox, BorderLayout.EAST);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        JLabel itemLabel = new JLabel(foodName);
        detailsPanel.add(itemLabel);

        detailsPanel.add(Box.createVerticalStrut(10)); // Add space of 10 pixels vertically

        String priceToString = Double.toString(price);
        JLabel priceLabel = new JLabel("$"+priceToString);
        detailsPanel.add(priceLabel);

        //NO IMAGES IN THE ACTUAL DATABASE (MAY CHANGE IN FUTURE ADDITIONS)
        // detailsPanel.add(Box.createVerticalStrut(15)); // Add space of 10 pixels vertically

        // Image originalImage = Toolkit.getDefaultToolkit().getImage(new URL(image));
        // int width = 100;
        // int height = 100;
        // Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // ImageIcon imageIcon = new ImageIcon(resizedImage);
        // JLabel imageLabel = new JLabel(imageIcon);
        // detailsPanel.add(imageLabel);

        detailsPanel.add(Box.createVerticalStrut(10)); // Add space of 10 pixels vertically

        JLabel descriptionLabel = new JLabel(description);
        detailsPanel.add(descriptionLabel);

        detailsPanel.add(Box.createVerticalStrut(50)); // Add space of 10 pixels vertically

        checkBoxPanel.add(detailsPanel, BorderLayout.CENTER);
        checkBoxPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        //Creating singular order
        Order foodOrder = new Order(6, customer.getCustomerID(), 3, "Ordered");
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    try (Connection connection = ConnectToServer.openConnect()) {
                        //WONT NEED ORDERITEM_ID WHEN CHANGING DATABASE
                        String insertQuery = "INSERT INTO cs3560dfss.orderitem (orderitem_id, item_id, order_id) VALUES (?, ?, ?)";
                        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                            //ALSO GETTING RID OF THIS
                            statement.setInt(1, 8);
                            statement.setInt(2, foodID);
                            statement.setInt(3, foodOrder.getOrderID());
                            int rowsAffected = statement.executeUpdate();
                            if (rowsAffected > 0) {
                                System.out.println("Item inserted into shopping cart.");
                            } else {
                                System.err.println("Failed to insert item into shopping cart.");
                            }
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        return checkBoxPanel;
    }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit) {    
                CheckOut accountCreation;
                try {
                    accountCreation = new CheckOut(customer);
                    accountCreation.setVisible(true);
                    // Hide the current frame if needed
                    setVisible(false);
                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                };
            }
        }

}
