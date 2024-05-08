package Swing;

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

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ReviewService reviewServicePage = new ReviewService();
                reviewServicePage.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public ReviewService() {
        setTitle("Review Service");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 0, 687, 700);

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

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        mainPanel.add(panel);

        panel.add(Box.createVerticalStrut(15));

        JLabel titleLabel = new JLabel("Talk about your experience");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(15));

        titleField = new JTextField();
        titleField.setMaximumSize(new Dimension(1000, 10000));
        panel.add(titleField);

        JLabel bodyOfReview = new JLabel("");
        panel.add(bodyOfReview);

        panel.add(Box.createVerticalStrut(30));
        bodyField = new JTextField();
        bodyField.setMaximumSize(new Dimension(1000, 100000));
        panel.add(bodyField);

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

        //create south panel
        JPanel starRating = new JPanel();
        //starRating.setLayout(new BorderLayout());
        starRating.setPreferredSize(new Dimension(687, 2));
        panel.add(starRating, BorderLayout.SOUTH);

        stars = new JLabel[5];
        rating = 0;

        // Create 5 JLabels representing stars
        for (int i = 0; i < 5; i++) {
            stars[i] = new JLabel("\u2606"); // Unicode for empty star
            stars[i].setFont(new Font("Arial Unicode MS", Font.PLAIN, 24)); // Try "Arial Unicode MS" font
            stars[i].setForeground(Color.GRAY);
            stars[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            stars[i].setName(Integer.toString(i)); // Set name as index
            stars[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel star = (JLabel) e.getSource();
                    int index = Integer.parseInt(star.getName());
                    setRating(index + 1);
                }
            });
            starRating.add(stars[i]);
        }

        setVisible(true);

        //JPanel test = new JPanel();
        //test.setLayout(new BorderLayout());
        createReviewButton = new JButton("Create Review");
        createReviewButton.addActionListener(this);
        mainPanel.add(createReviewButton, BorderLayout.SOUTH);

    }
    private void setRating(int newRating) {
        rating = newRating;
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                stars[i].setText("\u2605"); // Unicode for filled star
                stars[i].setForeground(Color.ORANGE);
            } else {
                stars[i].setText("\u2606"); // Unicode for empty star
                stars[i].setForeground(Color.GRAY);
            }
        }
    }

    // Get the current rating
    public int getRating() {
        return rating;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createReviewButton) {
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
            setVisible(false);
        }
    }
}

