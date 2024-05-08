// package Demo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class SignIntoAccount extends JFrame implements ActionListener {

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

        JLabel welcome = new JLabel("Welcome to the Landfill!");
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
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == submit) {
            String firstName = textField1.getText().trim();
            String phoneNumber = textField2.getText().trim();
            Connection connection;
            try {
                // Create a prepared statement to execute the SQL query
                String query = "SELECT * FROM cs3560dfss.customer WHERE phonenumber = ? ";
                connection = ConnectToServer.openConnect();
                try(PreparedStatement statement = connection.prepareStatement(query)) {
                    // Set the parameters for the prepared statement
                    statement.setString(1, phoneNumber);
                    // Execute the SQL query to insert data into the database
                    ResultSet customerInfo = statement.executeQuery();
                    if (customerInfo.next()) {
                        String custName = customerInfo.getString("firstName");
                        if (custName.equals(firstName)) {
                            // Data inserted successfully
                            System.out.println("You have an account!");
                            Customer customer = new Customer(customerInfo.getInt("customer_id"), 
                                                            customerInfo.getString("phoneNumber"),
                                                            customerInfo.getString("paymentInfo"), 
                                                            customerInfo.getString("firstName"), 
                                                            customerInfo.getString("lastName"));
                            MainPage mainPage = new MainPage(customer);
                            mainPage.setVisible(true);
                            setVisible(false);
                        } else {
                            // No rows affected, data not inserted
                            System.out.println("Failed to have an account!");
                            JOptionPane.showMessageDialog(this, "You do not have an existing account!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    ConnectToServer.closeConnect(connection);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                };
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                    
            }
        }
}

