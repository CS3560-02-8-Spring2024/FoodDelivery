package Swing;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class UserCheckStatus extends JFrame implements ActionListener {
    private JButton exit;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UserCheckStatus userCheckStatus = new UserCheckStatus();
                    userCheckStatus.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public UserCheckStatus() {
        setTitle("Check Out");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 0, 687, 700);

        JPanel mainPanel = new JPanel(); // Create a JPanel to hold the components
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setContentPane(mainPanel);

        mainPanel.add(Box.createVerticalStrut(125)); // Add vertical padding
        JLabel titleLabel = new JLabel("Your Order Details");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25)); // Set font and size
        mainPanel.add(titleLabel);

        int orderID = 1;
        //String itemName[] = {"Burger"};
        String deliveryStatus = "Pick Up";
        /*
        Order ID
         */
        mainPanel.add(Box.createVerticalStrut(15)); // Add vertical padding
        JLabel orderIDLabel = new JLabel("OrderID: " + orderID);
        orderIDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 20)); // Set font and size
        mainPanel.add(orderIDLabel);


        /*
        orderItems (if possible)
        mainPanel.add(Box.createVerticalStrut(15)); // Add vertical padding
        JLabel itemNameLabel = new JLabel("Item(s): " + itemName);
        itemNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20)); // Set font and size
        mainPanel.add(itemNameLabel);
        */

        /*
        delivery status
         */
        mainPanel.add(Box.createVerticalStrut(15)); // Add vertical padding
        JLabel deliveryStatusLabel = new JLabel("Status: " + deliveryStatus);
        deliveryStatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        deliveryStatusLabel.setFont(new Font("Tahoma", Font.PLAIN, 20)); // Set font and size
        mainPanel.add(deliveryStatusLabel);

        mainPanel.add(Box.createVerticalStrut(150)); // Add vertical padding
        exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(exit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
            setVisible(false);
        }
    }
}
