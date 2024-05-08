package Swing;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateAccount extends JFrame implements ActionListener {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cs3560dfss";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Screen.1!";

    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
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
                    CreateAccount frame = new CreateAccount();
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
    public CreateAccount() {
        setBackground(Color.RED);
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

        JLabel label1 = new JLabel("Create an Account");
        label1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(label1);
        panel1.add(Box.createVerticalStrut(25));

        //first name
        JLabel fname = new JLabel("Enter First Name");
        fname.setFont(new Font("Tahoma", Font.PLAIN, 13));
        fname.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(fname);
        panel1.add(Box.createVerticalStrut(5));

        textField1 = new JTextField(10);
        textField1.setMaximumSize(new Dimension(200, 30));
        textField1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(textField1);
        panel1.add(Box.createVerticalStrut(15));

        //last name
        JLabel lname = new JLabel("Enter Last Name");
        lname.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lname.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(lname);
        panel1.add(Box.createVerticalStrut(5));

        textField2 = new JTextField(10);
        textField2.setMaximumSize(new Dimension(200, 30));
        textField2.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(textField2);
        panel1.add(Box.createVerticalStrut(15));

        JLabel createUSER = new JLabel("Enter E-Mail address");
        createUSER.setFont(new Font("Tahoma", Font.PLAIN, 13));
        createUSER.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(createUSER);
        panel1.add(Box.createVerticalStrut(5));

        textField3 = new JTextField(10);
        textField3.setMaximumSize(new Dimension(200, 30));
        textField3.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(textField3);
        panel1.add(Box.createVerticalStrut(15));

        JLabel createPass = new JLabel("Create a Password");
        createPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
        createPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(createPass);
        panel1.add(Box.createVerticalStrut(5));


        passwordField1 = new JPasswordField();
        passwordField1.setMaximumSize(new Dimension(200, 30));
        passwordField1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(passwordField1);
        panel1.add(Box.createVerticalStrut(20));


        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(submit);
        panel1.add(Box.createVerticalStrut(10));

        JLabel hyperlink = new JLabel("Already have an account? Sign In!");
        hyperlink.setForeground(Color.BLUE.darker());
        hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hyperlink.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the sign-in page when the hyperlink label is clicked
                SignIntoAccount signInFrame = new SignIntoAccount();
                signInFrame.setVisible(true);
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            saveAccount();
            // When submit button is clicked, create an instance of Menu and show it
            MainPage MainPageFrame = new MainPage(); //test change later
            MainPageFrame.setVisible(true);
            // Hide the current frame if needed
            setVisible(false);
        }
    }

    private void saveAccount() {
        // Get data from text fields
        String firstName = textField1.getText();
        String lastName = textField2.getText();
        String email = textField3.getText();
        String password = new String(passwordField1.getPassword());

        // perform JDBC operations
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO customer (firstName, lastName, emailAddress, password) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            if (rowsAffected > 0) { JOptionPane.showMessageDialog(this, "Account Created!"); }
            else { JOptionPane.showMessageDialog(this, "Failed to create account", "Error", JOptionPane.ERROR_MESSAGE); }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}





