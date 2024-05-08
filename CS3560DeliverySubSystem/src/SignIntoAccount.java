package Demo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class SignIntoAccount extends JFrame implements ActionListener {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fdss";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;

    private JButton submit;
    private BorderLayout layout;

    /**
     * Run application
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SignIntoAccount signInPage = new SignIntoAccount();
                    signInPage.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create frame
     */
    public SignIntoAccount() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 687, 531);
        JPanel panel = new JPanel();
        panel.setToolTipText("");
        panel.setBackground(new Color(206, 41, 48));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS alignment
        panel1.setForeground(Color.WHITE);
        panel1.setBackground(Color.WHITE);
        panel1.setBounds(125, 0, 400, 531);
        panel.add(panel1);

        JLabel welcome = new JLabel("Welcome to the PlaceHolder!");
        welcome.setFont(new Font("Tahoma", Font.PLAIN, 25));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(welcome);
        panel1.add(Box.createVerticalStrut(10));

        JLabel label1 = new JLabel("Sign into your Account");
        label1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(label1);
        panel1.add(Box.createVerticalStrut(25));


        JLabel createUSER = new JLabel("Enter First Name");
        createUSER.setFont(new Font("Tahoma", Font.PLAIN, 13));
        createUSER.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(createUSER);
        panel1.add(Box.createVerticalStrut(5));

        textField1 = new JTextField(10);
        textField1.setMaximumSize(new Dimension(200, 30));
        textField1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(textField1);
        panel1.add(Box.createVerticalStrut(15));

        JLabel createPass = new JLabel("Enter Phone Number");
        createPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
        createPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(createPass);
        panel1.add(Box.createVerticalStrut(5));


        textField2 = new JTextField();
        textField2.setMaximumSize(new Dimension(200, 30));
        textField2.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(textField2);
        panel1.add(Box.createVerticalStrut(20));


        submit = new JButton("Sign In");
        submit.addActionListener(this);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(submit);
        panel1.add(Box.createVerticalStrut(10));

        JLabel hyperlink = new JLabel("Don't have an account? Create one!");
        hyperlink.setForeground(Color.BLUE.darker());
        hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hyperlink.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the sign-in page when the hyperlink label is clicked
                CreateAccount createAccFrame = new CreateAccount();
                createAccFrame.setVisible(true);
                // Hide the current frame if needed
                setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // the mouse has entered the label
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // the mouse has exited the label
            }
        });
        hyperlink.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(hyperlink);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
            setVisible(false);

            String firstName = textField1.getText().trim();
            String phoneNumber = textField2.getText().trim();

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

                String query = "INSERT INTO customer (firstName, phoneNumber) VALUES (?, ?)";
                // Create a prepared statement to execute the SQL query
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    // Set the parameters for the prepared statement
                    statement.setString(1, firstName);
                    statement.setString(2, phoneNumber);

                    // Execute the SQL query to insert data into the database
                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Data inserted successfully
                        System.out.println("Data inserted successfully!");
                    } else {
                        // No rows affected, data not inserted
                        System.out.println("Failed to insert data!");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
