

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import CS3560DeliverySubSystem.legacycode.ReviewUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StaffMainPage extends JFrame implements ActionListener {
        // private JButton signInButton;
        private JButton viewOrderButton;
        private JButton viewReviewButton;
        private JButton menuButton;
        /**
         * Run application
         */
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        StaffMainPage frame = new StaffMainPage();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        /**
         * Create frame
         */
        public StaffMainPage() {
            setTitle("Staff Page");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 687, 531);

            JPanel redStrip = new JPanel();
            redStrip.setToolTipText("");
            redStrip.setBackground(new Color(206, 41, 48));
            redStrip.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(redStrip);
            redStrip.setLayout(null);

            JLabel logo = new JLabel("LOGO");
            logo.setForeground(Color.WHITE); // Set text color to white
            logo.setFont(new Font("Tahoma", Font.BOLD, 20)); // Set font and size
            logo.setBounds(20, 20, 200, 30); // Set bounds
            redStrip.add(logo);

            //create red strip at top, but use this panel as main panel for buttons, text, etc
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBounds(0, 75, 687, 525);
            mainPanel.setBackground(new Color(255, 255, 255));
            redStrip.add(mainPanel);

            menuButton = new JButton("Online Order Creation");
            menuButton.addActionListener(this);
            menuButton.setSize(100, 30);
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
            mainPanel.add(Box.createVerticalStrut(75)); // Add vertical padding
            mainPanel.add(menuButton);

            viewOrderButton = new JButton("View Order");
            viewOrderButton.addActionListener(this);
            viewOrderButton.setSize(100, 30);
            viewOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
            mainPanel.add(Box.createVerticalStrut(20)); // Add vertical padding
            mainPanel.add(viewOrderButton);

            viewReviewButton = new JButton("View Past Reviews");
            viewOrderButton.addActionListener(this);
            viewReviewButton.setSize(100, 30);
            viewReviewButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
            mainPanel.add(Box.createVerticalStrut(20)); // Add vertical padding
            mainPanel.add(viewReviewButton);

            // Future implementation: Once signed in, remove Sign In button.
            /*signInButton = new JButton("Sign In");
            signInButton.addActionListener(this);
            signInButton.setSize(100, 30);
            signInButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
            mainPanel.add(Box.createVerticalStrut(50)); // Add vertical padding
            mainPanel.add(signInButton);*/


        }
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == menuButton) {
                // When Search Menu button is clicked, create an instance of Menu and show it
                SignIntoAccount accCreateFrame = null;
                //Handles SQL so needs error checking to make sure it can be done
                accCreateFrame = new SignIntoAccount();
				accCreateFrame.setVisible(true);
                // Hide the current frame if needed
                setVisible(false);
            }
        	
            if (e.getSource() == viewOrderButton) {
                // When View Order button is clicked, create an instance of Order and show it
                ViewOrder viewOrderFrame = null;
				try {
					viewOrderFrame = new ViewOrder();
                    viewOrderFrame.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //test change later
                // Hide the current frame if needed
                setVisible(false);
            }
            if (e.getSource() == viewReviewButton) {
                // When View Review button is clicked, create an instance of Review and show it
                ReviewUI viewReviewFrame = null;
				viewReviewFrame = new ReviewUI();
                viewReviewFrame.setVisible(true);
                // Hide the current frame if needed
                setVisible(false);
            }  
        }
    }
