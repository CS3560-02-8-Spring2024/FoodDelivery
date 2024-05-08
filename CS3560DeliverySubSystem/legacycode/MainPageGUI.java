import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageGUI extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    //Main Panel Menu
    public MainPageGUI() {
        setTitle("Fast Food Ordering System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        // Create panels for different screens
        createMainPanel();
        createSignInPanel();
        createMenuPanel();
        createDrinkPanel();

        setVisible(true);
    }

    //Creates the actual main panel
    private void createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Ordering Food and Delivery Subsystem", SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(); 
        JButton signInButton = new JButton("Sign In");
        signInButton.setSize(100, 30);
        signInButton.addActionListener(this);
        menuPanel.add(signInButton);
        JButton friesButton = new JButton("Fries");
        friesButton.setSize(80, 30);
        friesButton.addActionListener(this);
        menuPanel.add(friesButton);
        JButton drinkButton = new JButton("Drink");
        drinkButton.setSize(80, 30);
        drinkButton.addActionListener(this);
        menuPanel.add(drinkButton);
        panel.add(menuPanel, BorderLayout.CENTER);

        mainPanel.add(panel, "MainPanel");
    }

    //Goes to the sign in panel
    private void createSignInPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel userName = new JPanel();
        JLabel label = new JLabel("Username");
        label.setPreferredSize(new Dimension(75,30));
        userName.add(label);
        JTextField userText = new JTextField();
        userText.setPreferredSize(new Dimension(100, 30));
        userName.add(userText);
        panel.add(userName, BorderLayout.PAGE_START);

        JPanel passWord = new JPanel();
        JLabel passLabel = new JLabel("Password");
        passLabel.setPreferredSize(new Dimension(75,30));
        passWord.add(passLabel);
        JPasswordField passText = new JPasswordField();
        passText.setPreferredSize(new Dimension(100, 30));
        passWord.add(passText);
        panel.add(passWord);

        JPanel bottomButtons = new JPanel();
        JButton backButton = new JButton("Return");
        backButton.addActionListener(this);
        backButton.setPreferredSize(new Dimension(100, 30));
        bottomButtons.add(backButton);
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(this);
        enterButton.setPreferredSize(new Dimension(100, 30));
        bottomButtons.add(enterButton);
        panel.add(bottomButtons, BorderLayout.SOUTH);

        mainPanel.add(panel, "SignInPanel");
    }

    private void createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Fries Screen", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(100,30));
        panel.add(label, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(this);
        panel.add(backButton, BorderLayout.SOUTH);

        mainPanel.add(panel, "FriesPanel");
    }

    private void createDrinkPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Drink Screen", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(100,30));
        JLabel label2 = new JLabel("Drinking", SwingConstants.CENTER);
        label2.setPreferredSize(new Dimension(10,30));
        panel.add(label, BorderLayout.CENTER);
        panel.add(label2);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(this);
        panel.add(backButton, BorderLayout.SOUTH);

        mainPanel.add(panel, "DrinkPanel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Fries") || command.equals("Drink")) {
            cardLayout.show(mainPanel, command + "Panel");
        } 
        else if (command.equals("Back to Main Menu")) {
            cardLayout.show(mainPanel, "MainPanel");
        }
        else if (command.equals("Sign In")) {
            cardLayout.show(mainPanel, "SignInPanel");
        }
        else if (command.equals("Return")) {
            cardLayout.show(mainPanel, "MainPanel");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainPageGUI::new);
    }

}