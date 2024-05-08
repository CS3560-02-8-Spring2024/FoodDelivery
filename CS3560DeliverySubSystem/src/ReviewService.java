// package Swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

public class ReviewService extends JFrame implements ActionListener {

    private JTextField titleField;
    private JTextField bodyField;
    private JLabel[] stars;
    private int rating;

    private JButton createReviewButton;

    private JComboBox<Integer> foodQuality;
    private JComboBox<Integer> serviceQuality;
    private JComboBox<Integer> deliveryQuality;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ReviewService reviewService = new ReviewService();
                reviewService.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ReviewService() {
        setTitle("Review Service");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 0, 687, 600);

        JPanel mainPanel = new JPanel(); // Create a JPanel to hold the components
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        JPanel redStrip = new JPanel();
        redStrip.setBackground(new Color(206, 41, 48));
        //redStrip.setLayout(new BorderLayout());
        redStrip.setPreferredSize(new Dimension(687, 75)); // Set preferred size here
        mainPanel.add(redStrip, BorderLayout.NORTH);

        JLabel logo = new JLabel("Rate our Service!");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Tahoma", Font.BOLD, 20));
        logo.setBorder(new EmptyBorder(20, 5, 5, 5));
        redStrip.add(logo);


        JPanel textLabel = new JPanel();
        textLabel.setLayout(new BoxLayout(textLabel, BoxLayout.Y_AXIS));
        textLabel.setPreferredSize(new Dimension(425, 1080));
        mainPanel.add(textLabel, BorderLayout.WEST);

        textLabel.add(Box.createVerticalStrut(15));


        titleField = new JTextField();
        titleField.setMaximumSize(new Dimension(1000, 30));
        textLabel.add(titleField);

        JLabel bodyOfReview = new JLabel("");
        textLabel.add(bodyOfReview);

        textLabel.add(Box.createVerticalStrut(30));
        bodyField = new JTextField();
        bodyField.setMaximumSize(new Dimension(1000, 200));
        textLabel.add(bodyField);

        titleField.setText("Create a title for your post...");
        titleField.setForeground(Color.GRAY);
        bodyField.setText("Enter text...");
        bodyField.setForeground(Color.GRAY);
        titleField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (titleField.getText().equals("Create a title for your post...")) {
                    titleField.setText("");
                    titleField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (titleField.getText().isEmpty()) {
                    titleField.setText("Create a title for your post...");
                    titleField.setForeground(Color.GRAY);
                }
            }
        });

        bodyField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (bodyField.getText().equals("Enter text...")) {
                    bodyField.setText("");
                    bodyField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (bodyField.getText().isEmpty()) {
                    bodyField.setText("Enter text...");
                    bodyField.setForeground(Color.GRAY);
                }
            }
        });

        JPanel orderDetails = new JPanel();
        orderDetails.setLayout(new BoxLayout(orderDetails, BoxLayout.Y_AXIS));
        orderDetails.setPreferredSize(new Dimension(262, 1080));
        mainPanel.add(orderDetails, BorderLayout.EAST);

        orderDetails.add(Box.createVerticalStrut(30));

        JPanel detail1 = new JPanel();
        orderDetails.add(detail1);

        JLabel label1 = new JLabel("Food Quality");
        detail1.add(label1);
        Integer[] values = {1, 2, 3, 4, 5};
        foodQuality = new JComboBox<>(values);
        foodQuality.setSelectedIndex(0); // Select the first item (1) by default
        detail1.add(foodQuality);

        JPanel detail2 = new JPanel();
        orderDetails.add(detail2);

        JLabel label2 = new JLabel("Service Quality");
        detail2.add(label2);
        Integer[] values2 = {1, 2, 3, 4, 5};
        serviceQuality = new JComboBox<>(values2);
        serviceQuality.setSelectedIndex(0); // Select the first item (1) by default
        detail2.add(serviceQuality);

        JPanel detail3 = new JPanel();
        orderDetails.add(detail3);

        JLabel label3 = new JLabel("Delivery Quality");
        detail3.add(label3);
        Integer[] values3 = {1, 2, 3, 4, 5};
        deliveryQuality = new JComboBox<>(values3);
        deliveryQuality.setSelectedIndex(0); // Select the first item (1) by default
        detail3.add(deliveryQuality);

        createReviewButton = new JButton("Create Review");
        createReviewButton.addActionListener(this);
        mainPanel.add(createReviewButton, BorderLayout.SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createReviewButton) {
            // Get selected values from JComboBoxes
            int foodQualityValue = (int) foodQuality.getSelectedItem();
            int serviceQualityValue = (int) serviceQuality.getSelectedItem();
            int deliveryQualityValue = (int) deliveryQuality.getSelectedItem();
        }
    }
}
