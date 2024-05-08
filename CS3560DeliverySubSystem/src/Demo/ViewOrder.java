package Demo;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ViewOrder extends JFrame {
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

    public ViewOrder() throws ClassNotFoundException {
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

        String data[][] = {
                {"1", "Bob", "Steve", "Delivered"},
                {"2", "Chris", "Brandon", "Ready for Delivery"},
                {"3", "Jeff", "Ralph", "Ordered"}
        };
        

        String column[] = {"OrderID", "Customer", "Driver", "Status"};

        DefaultTableModel model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        JTable table = new JTable(model);
        JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Ordered", "Ready for Delivery", "Picked Up", "Delivered"});
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(statusComboBox));
        table.setRowHeight(25);
        JScrollPane sp = new JScrollPane(table);
        mainPanel.add(sp, BorderLayout.CENTER);

        JButton updateButton = new JButton("Update");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(updateButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}
