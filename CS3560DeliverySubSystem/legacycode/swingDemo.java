package CS3560DeliverySubSystem.legacycode;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import javax.swing.*;
import java.awt.*;

public class swingDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame("Menu");
        f.setVisible(true);

        f.setSize(300, 400);
        f.setLayout(new FlowLayout());
        //customer create account
        JLabel username = new JLabel("Create username: ");
        f.add(username);
        JTextField u1 = new JTextField(20);
        f.add(u1);

        //password creation
        JLabel password = new JLabel("Create password: ");
        f.add(password);
        JTextField p1 = new JTextField(20);
        f.add(p1);

        JButton submit = new JButton("Submit");
        f.add(submit);

        JButton newPage = new JButton("Next");
        f.add(newPage);
    }
}