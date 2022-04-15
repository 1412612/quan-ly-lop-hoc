package controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountAcademicStaff extends JFrame {

        private static final long serialVersionUID = 1L;
        private JPanel contentPane;

        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        AccountAcademicStaff frame = new AccountAcademicStaff("admin");
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public AccountAcademicStaff() {

        }

        public AccountAcademicStaff(final String username) {

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Trang chủ giáo vụ");
            setBounds(450, 190, 1014, 597);
            setResizable(false);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel headerLabel = new JLabel("Thông tin tài khoản");

            JLabel usernameLabel = new JLabel("Tài khoản");
            JLabel nameLabel = new JLabel("Họ và tên");

            final JButton logoutButton = new JButton("Đăng xuất");
            logoutButton.setForeground(new Color(0, 0, 0));
            logoutButton.setBackground(UIManager.getColor("Button.disabledForeground"));
            logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
            logoutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int a = JOptionPane.showConfirmDialog(logoutButton, "Are you sure?");
                    if (a == JOptionPane.YES_OPTION) {
                        dispose();
                        Login obj = new Login();
                        obj.setVisible(true);
                    }
                }
            });
            logoutButton.setBounds(700, 500, 200, 50);
            contentPane.add(logoutButton);

            final JButton updateButton = new JButton("Cập nhật");
            updateButton.setForeground(new Color(0, 0, 0));
            updateButton.setBackground(UIManager.getColor("Button.disabledForeground"));
            updateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                    int a = JOptionPane.showConfirmDialog(logoutButton, "Are you sure?");
//                    if (a == JOptionPane.YES_OPTION) {
//                        dispose();
//                        Login obj = new Login();
//                        obj.setVisible(true);
//                    }
                }
            });
            updateButton.setBounds(100, 500, 200, 50);
            contentPane.add(updateButton);

            final JButton changePasswordButton = new JButton("Thay đổi mật khẩu");
            changePasswordButton.setForeground(new Color(0, 0, 0));
            changePasswordButton.setBackground(UIManager.getColor("Button.disabledForeground"));
            changePasswordButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
            changePasswordButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AcademicStaffChangePassword academicStaffChangePassword = new AcademicStaffChangePassword(username);
                    academicStaffChangePassword.setVisible(true);
                    dispose();
                }
            });
            changePasswordButton.setBounds(400, 500, 200, 50);
            contentPane.add(changePasswordButton);
        }
    }
