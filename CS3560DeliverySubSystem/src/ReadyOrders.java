// package Swing;

// import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ReadyOrders extends JFrame implements ActionListener{
    private JButton updateButton;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ReadyOrders readyOrdersPage = new ReadyOrders();
                readyOrdersPage.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ReadyOrders() throws SQLException, ClassNotFoundException {
        setTitle("View Ready Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 0, 687, 531);

        JPanel redStrip = new JPanel();
        redStrip.setToolTipText("");
        redStrip.setBackground(new Color(206, 41, 48));
        redStrip.setBounds(0, 0, 687, 75);
        setContentPane(redStrip);
        redStrip.setLayout(new BorderLayout());

        JLabel logo = new JLabel("Orders Ready for Pick-up");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Tahoma", Font.BOLD, 20));
        redStrip.add(logo, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        redStrip.add(mainPanel, BorderLayout.CENTER);

        String column[] = {"OrderID", "Customer", "Street", "City", "State", "Zip Code", "Status"};

        String[][] rawDataVals = new String[25][7];
        int counter = 0;
        Connection dbConnect = ConnectToServer.openConnect();
        int customerID;
        String sqlQuery = "SELECT * FROM cs3560dfss._order WHERE deliveryStatus = ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setString(1, "RtD");
            try (ResultSet dbResults = sqlSt.executeQuery()) {
                while (dbResults.next()) {
                    // Getting the orderID and the status of the delivery
                    rawDataVals[counter][0] = String.valueOf(dbResults.getInt("order_id"));
                    rawDataVals[counter][6] = dbResults.getString("deliveryStatus");
                    customerID = dbResults.getInt("customer_id");
                    // Getting the names of the customers of the orders
                    String sqlQuery2 = "SELECT * FROM cs3560dfss.customer WHERE customer_id = ?";
                    try (PreparedStatement sqlSt2 = dbConnect.prepareStatement(sqlQuery2)) {
                        sqlSt2.setInt(1, customerID);
                        try (ResultSet dbResults2 = sqlSt2.executeQuery()) {
                            if (dbResults2.next()) {
                                //Getting the first name of the customer
                                rawDataVals[counter][1] = dbResults2.getString("firstName");
                            }
                        }
                    }
                    String sqlQuery3 = "SELECT * FROM cs3560dfss.address WHERE customer_id = ?";
                    try (PreparedStatement sqlSt2 = dbConnect.prepareStatement(sqlQuery3)) {
                        sqlSt2.setInt(1, customerID);
                        try (ResultSet dbResults2 = sqlSt2.executeQuery()) {
                            if (dbResults2.next()) {
                                //These are the names
                                rawDataVals[counter][2] = dbResults2.getString("street");
                                rawDataVals[counter][3] = dbResults2.getString("city");
                                rawDataVals[counter][4] = dbResults2.getString("state");
                                rawDataVals[counter][5] = dbResults2.getString("zipCode");
                            }
                        }
                    }
                    counter++;
                }
            }
        }
        String[][] data = new String[counter][7];
        for (int i = 0; i < counter; i++) {
            for (int j = 0; j <= 6; j++) {
                data[i][j] = rawDataVals[i][j];
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(25);
        JScrollPane sp = new JScrollPane(table);
        mainPanel.add(sp, BorderLayout.CENTER);

        updateButton = new JButton("Choose Order");
        updateButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(updateButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton){
            DriverMainPage driverMainPage = new DriverMainPage();
            driverMainPage.setVisible(true);
            setVisible(false);
        }
    }
}
