
import com.mysql.cj.x.protobuf.MysqlxCrud;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ViewOrder extends JFrame implements ActionListener{
    private JButton updateButton;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewOrder viewOrderPage = new ViewOrder();
                viewOrderPage.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ViewOrder() throws ClassNotFoundException, SQLException {
        setTitle("View Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 687, 531);

        JPanel redStrip = new JPanel();
        redStrip.setToolTipText("");
        redStrip.setBackground(new Color(206, 41, 48));
        redStrip.setBounds(0, 0, 687, 75);
        setContentPane(redStrip);
        redStrip.setLayout(new BorderLayout());

        JLabel logo = new JLabel("All Orders");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Tahoma", Font.BOLD, 20));
        redStrip.add(logo, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        redStrip.add(mainPanel, BorderLayout.CENTER);

        String[][] rawDataVals = new String[25][4];
        int counter = 0;
        Connection dbConnect = ConnectToServer.openConnect();
        int customerID;
        String sqlQuery = "SELECT * FROM cs3560dfss._order WHERE deliveryStatus != ? AND deliveryStatus != ?";
        try (PreparedStatement sqlSt = dbConnect.prepareStatement(sqlQuery)) {
            sqlSt.setString(1, "Cancelled");
            sqlSt.setString(2, "Delivered");
            try (ResultSet dbResults = sqlSt.executeQuery()) {
                while (dbResults.next()) {
                    // Getting the orderID and the status of the delivery
                    rawDataVals[counter][0] = String.valueOf(dbResults.getInt("order_id"));
                    rawDataVals[counter][3] = dbResults.getString("deliveryStatus");
                    customerID = dbResults.getInt("customer_id");
                    // Getting the names of the customers of the orders
                    String sqlQuery2 = "SELECT * FROM cs3560dfss.customer WHERE customer_id = ?";
                    try (PreparedStatement sqlSt2 = dbConnect.prepareStatement(sqlQuery2)) {
                        sqlSt2.setInt(1, customerID);
                        try (ResultSet dbResults2 = sqlSt2.executeQuery()) {
                            if (dbResults2.next()) {
                                //These are the names
                                rawDataVals[counter][1] = dbResults2.getString("firstName");
                                rawDataVals[counter][2] = dbResults2.getString("lastName");
                            }
                        }
                    }
                    counter++;
                }
            }
        }
        //Closing the connection
        ConnectToServer.closeConnect(dbConnect);

        //Deep copying the values so we dont take up space 
        String[][] data = new String[counter][4];
        for (int i = 0; i < counter; i++) {
            for (int j = 0; j <= 3; j++) {
                data[i][j] = rawDataVals[i][j];
            }
        }
        
        String column[] = {"OrderID", "Customer", "Driver", "Status"};

        DefaultTableModel model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        JTable table = new JTable(model);
        JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Ordered", "Ready to Deliver", "Picked Up", "Delivered", "Cancelled"});
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                //Checking for updates within the table itself
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 3) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    DefaultTableModel model = (DefaultTableModel) e.getSource();
                    String columnName = model.getColumnName(column);
                    Object data = model.getValueAt(row, column);
                    Object orderID = model.getValueAt(row, 0);
                    int orderIDVal = Integer.parseInt((String) orderID);
                    Order updateOrd = new Order(orderIDVal, 1, 1, "test");
                    try {
                        updateOrd.updateDeliveryStatus(data.toString());
                    //Catching potential errors (pre-generated)
                    } catch (ClassNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } 
            }
        });
        
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(statusComboBox));
        table.setRowHeight(25);
        JScrollPane sp = new JScrollPane(table);
        mainPanel.add(sp, BorderLayout.CENTER);

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(updateButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {

            StaffMainPage staffPage = new StaffMainPage();
            staffPage.setVisible(true);
            // Hide the current frame if needed
            setVisible(false);
        }
    }
}