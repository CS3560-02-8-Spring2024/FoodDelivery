//package Demo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Driver;

public class DriverMainPage extends JFrame implements ActionListener {
    private JButton signInButton;
    private JButton readyOrderButton;
    private JButton updateDeliveriesButton;
    /**
     * Run application
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DriverMainPage driverDefaultPage = new DriverMainPage();
                    driverDefaultPage.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create frame
     */
    public DriverMainPage() {
        setTitle("Driver Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 0, 687, 531);

        JPanel redStrip = new JPanel();
        redStrip.setToolTipText("");
        redStrip.setBackground(new Color(206, 41, 48));
        redStrip.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(redStrip);
        redStrip.setLayout(null);

        JLabel logo = new JLabel("Driver");
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

        //Use case == Ready Orders changed for clarification
        readyOrderButton = new JButton("Available Orders");
        readyOrderButton.addActionListener(this);
        readyOrderButton.setSize(100, 30);
        readyOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        mainPanel.add(Box.createVerticalStrut(75)); // Add vertical padding
        mainPanel.add(readyOrderButton);

        //Drivers can update orders here
        updateDeliveriesButton = new JButton("Update Deliveries");
        updateDeliveriesButton.addActionListener(this);
        updateDeliveriesButton.setSize(100, 30);
        updateDeliveriesButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        mainPanel.add(Box.createVerticalStrut(20)); // Add vertical padding
        mainPanel.add(updateDeliveriesButton);

        // Future implementation: Once signed in, remove Sign In button.
        /*signInButton = new JButton("Sign In");
        signInButton.addActionListener(this);
        signInButton.setSize(100, 30);
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        mainPanel.add(Box.createVerticalStrut(50)); // Add vertical padding
        mainPanel.add(signInButton);

         */


    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            // When submit button is clicked, create an instance of Menu and show it
            SignIntoAccount signInFrame = new SignIntoAccount(); //test change later
            signInFrame.setVisible(true);
            // Hide the current frame if needed
            setVisible(false);
        }
        if (e.getSource() == readyOrderButton) {
            // When submit button is clicked, create an instance of Menu and show it
            ReadyOrders readyOrdersPage = new ReadyOrders(); //test change later
            readyOrdersPage.setVisible(true);
            // Hide the current frame if needed
            setVisible(false);
        }
        if (e.getSource() == updateDeliveriesButton) {
            // When submit button is clicked, create an instance of Menu and show it
            DriverUpdateStatus driverUpdateStatusPage = new DriverUpdateStatus(); //test change later
            driverUpdateStatusPage.setVisible(true);
            // Hide the current frame if needed
            setVisible(false);
        }
    }
}

