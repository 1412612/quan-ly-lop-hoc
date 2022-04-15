package controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentHome extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public StudentHome() {

    }

    /**
     * Create the frame.
     */
    public StudentHome(final String userSes) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trang chủ sinh viên");
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        final JButton btnNewButton = new JButton("Logout");
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 39));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(btnNewButton, "Are you sure?");
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    Login obj = new Login();
                    obj.setTitle("Student-Login");
                    obj.setVisible(true);
                }
                dispose();
                Login obj = new Login();

                obj.setTitle("Student-Login");
                obj.setVisible(true);

            }
        });
        btnNewButton.setBounds(247, 118, 491, 114);
        contentPane.add(btnNewButton);
        JButton button = new JButton("Change-password\r\n");
        button.setBackground(UIManager.getColor("Button.disabledForeground"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangePassword bo = new ChangePassword(userSes);
                bo.setTitle("Change Password");
                bo.setVisible(true);

            }
        });
        button.setFont(new Font("Tahoma", Font.PLAIN, 35));
        button.setBounds(247, 320, 491, 114);
        contentPane.add(button);
    }
}

