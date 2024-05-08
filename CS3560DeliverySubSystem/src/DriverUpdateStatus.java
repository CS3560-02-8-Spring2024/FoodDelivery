// package Swing;

// import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DriverUpdateStatus extends JFrame implements ActionListener {
    private JButton updateButton;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DriverUpdateStatus driverUpdateStatusPage = new DriverUpdateStatus();
                driverUpdateStatusPage.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DriverUpdateStatus() {
        setTitle("Driver Update Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 0, 687, 531);

        JPanel redStrip = new JPanel();
        redStrip.setToolTipText("");
        redStrip.setBackground(new Color(206, 41, 48));
        redStrip.setBounds(0, 0, 687, 75);
        setContentPane(redStrip);
        redStrip.setLayout(new BorderLayout());

        JLabel logo = new JLabel("Update Status");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Tahoma", Font.BOLD, 20));
        redStrip.add(logo, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        redStrip.add(mainPanel, BorderLayout.CENTER);

        String data[][] = {
                {"1", "Bob", "123 Elmo St", "NYC", "NY", "12345", "Ready for Delivery"}
        };
        String column[] = {"OrderID", "Customer", "Street", "City", "State", "Zip Code", "Status"};

        DefaultTableModel model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        JTable table = new JTable(model);
        JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Delivered", "Arrived"});
        table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(statusComboBox));
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
            DriverMainPage driverMainPage = new DriverMainPage();
            driverMainPage.setVisible(true);
            setVisible(false);
        }
    }
}
