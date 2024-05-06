package Swing;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class SignIntoAccount extends JFrame implements ActionListener {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cs3560dfss";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Screen.1!";

    private JPanel mainPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;

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
                    SignIntoAccount frame = new SignIntoAccount();
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


        JLabel createUSER = new JLabel("Enter E-Mail address");
        createUSER.setFont(new Font("Tahoma", Font.PLAIN, 13));
        createUSER.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(createUSER);
        panel1.add(Box.createVerticalStrut(5));

        textField1 = new JTextField(10);
        textField1.setMaximumSize(new Dimension(200, 30));
        textField1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(textField1);
        panel1.add(Box.createVerticalStrut(15));

        JLabel createPass = new JLabel("Enter Password");
        createPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
        createPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(createPass);
        panel1.add(Box.createVerticalStrut(5));


        passwordField1 = new JPasswordField();
        passwordField1.setMaximumSize(new Dimension(200, 30));
        passwordField1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(passwordField1);
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
            //connecting to database

            //retrieve email and password from fields
            String email = textField1.getText().trim();
            String password = new String(passwordField1.getPassword());

            //authenticate user and get role from databse
            String role = authenticateUser(email, password);

            //Direct user to respective view based on role
            if (role != null) {
                switch (role) {
                    case "customer":
                        MainPage MainPageFrame = new MainPage(); //test change later
                        MainPageFrame.setVisible(true);
                        break;
                    case "driver":
                        DriverMainPage driverPage = new DriverMainPage();
                        driverPage.setVisible(true);
                        break;
                    case "staff":
                        StaffMainPage staffPage = new StaffMainPage();
                        staffPage.setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Role not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
            }

            /**
            // When submit button is clicked, create an instance of Menu and show it
            MainPage MainPageFrame = new MainPage(); //test change later
            MainPageFrame.setVisible(true);
            // Hide the current frame if needed
            setVisible(false);
             **/
        }
    }

    private String authenticateUser(String email, String password) {
          try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

              String query = "SELECT 'customer' AS role FROM customers WHERE username = ? AND password = ? " + "UNION ALL " +
                 "SELECT 'driver' AS role FROM drivers WHERE username = ? AND password = ? " + "UNION ALL " +
                 "SELECT 'staff' AS role FROM staff WHERE username = ? AND password = ?";

              try (PreparedStatement statement = connection.prepareStatement(query)) {
                  statement.setString(1, email);
                  statement.setString(2, password);
                  try (ResultSet resultSet = statement.executeQuery()) {
                      if (resultSet.next()) {
                          return resultSet.getString("role");
                      }
                  }
              }
          } catch (SQLException ex) {
              ex.printStackTrace();
              JOptionPane.showMessageDialog(this, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
          }
          return null; // Return null if authentication fails
      }
}
