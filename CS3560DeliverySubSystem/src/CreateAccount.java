
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

    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    // private JPasswordField passwordField1;
    // private JPasswordField passwordField2;
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
                    CreateAccount createAccountPage = new CreateAccount();
                    createAccountPage.setVisible(true);
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

        JLabel createPhoneNum = new JLabel("Enter Phone Number");
        createPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 13));
        createPhoneNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(createPhoneNum);
        panel1.add(Box.createVerticalStrut(5));

        textField3 = new JTextField(10);
        textField3.setMaximumSize(new Dimension(200, 30));
        textField3.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(textField3);
        panel1.add(Box.createVerticalStrut(15));

        JLabel createPayment = new JLabel("Enter Payment Type");
        createPayment.setFont(new Font("Tahoma", Font.PLAIN, 13));
        createPayment.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(createPayment);
        panel1.add(Box.createVerticalStrut(5));


        textField4 = new JTextField();
        textField4.setMaximumSize(new Dimension(200, 30));
        textField4.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        panel1.add(textField4);
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
            try {
                saveAccount();
                //Catching errors (pre-generated)
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // When submit button is clicked, create an instance of Menu and show it
            MainPage MainPageFrame = new MainPage(); //test change later
            MainPageFrame.setVisible(true);
            // Hide the current frame if needed
            setVisible(false);
        }
    }

    private void saveAccount() throws ClassNotFoundException, SQLException {
        // Get data from text fields
        String firstName = textField1.getText();
        String lastName = textField2.getText();
        String phoneNum = textField3.getText();
        String paymentType = textField4.getText();
        //UPDATE THIS TO BE UNIQUE EVERYTIME
        int uniqueID = 10;

        Customer newCustomer = new Customer(uniqueID, phoneNum, paymentType, firstName, lastName);
        newCustomer.createCustomer();
    }


}





