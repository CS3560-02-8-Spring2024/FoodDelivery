package Demo;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

public class NewMenu extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cs3560fdss";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2002";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NewMenu menuPage = new NewMenu();
                menuPage.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NewMenu() {
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
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT foodName, _description, price, image FROM menu";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String foodName = resultSet.getString("foodName");
                    String description = resultSet.getString("_description");
                    Double price = resultSet.getDouble("price");
                    String image = resultSet.getString("image");
                    JPanel checkBoxPanel = createCheckBoxPanel(foodName, description, price, image);
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

        JButton submit = new JButton("Fill Shopping Cart");
        mainPanel.add(submit, BorderLayout.SOUTH);
    }
    private JPanel createCheckBoxPanel(String foodName, String description, double price, String image) throws MalformedURLException {
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

        detailsPanel.add(Box.createVerticalStrut(15)); // Add space of 10 pixels vertically

        Image originalImage = Toolkit.getDefaultToolkit().getImage(new URL(image));
        int width = 100;
        int height = 100;
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(imageIcon);
        detailsPanel.add(imageLabel);

        detailsPanel.add(Box.createVerticalStrut(10)); // Add space of 10 pixels vertically

        JLabel descriptionLabel = new JLabel(description);
        detailsPanel.add(descriptionLabel);

        detailsPanel.add(Box.createVerticalStrut(50)); // Add space of 10 pixels vertically

        checkBoxPanel.add(detailsPanel, BorderLayout.CENTER);
        checkBoxPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        return checkBoxPanel;

    }
}
