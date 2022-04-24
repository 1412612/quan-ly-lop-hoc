package controller;

import data.DefaultModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountStudent extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AccountStudent frame = new AccountStudent("mssv1", 13);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AccountStudent() {

    }

    public AccountStudent(final String mssv, int id) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Quản lý tài khoản");
        setBounds(250, 150, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel headerLabel = new JLabel("Thông tin tài khoản");

        JLabel usernameLabel = new JLabel("Tài khoản");
        JLabel nameLabel = new JLabel("Họ và tên");

        Button logoutButton = DefaultModel.getDefaultButton("Đăng xuất");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    Login obj = new Login();
                    obj.setVisible(true);
                }
            }
        });
        logoutButton.setBounds(700, 200, 200, 50);
        contentPane.add(logoutButton);

        Button backButton = DefaultModel.getDefaultButton("Quay lại");
        backButton.setBounds(100,200,200,50);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentHome academicStaffHome = new StudentHome(mssv);
                academicStaffHome.setVisible(true);
                dispose();
            }
        });

        contentPane.add(backButton);

//            final JButton updateButton = new JButton("Cập nhật");
//            updateButton.setForeground(new Color(0, 0, 0));
//            updateButton.setBackground(UIManager.getColor("Button.disabledForeground"));
//            updateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            updateButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
////                    int a = JOptionPane.showConfirmDialog(logoutButton, "Are you sure?");
////                    if (a == JOptionPane.YES_OPTION) {
////                        dispose();
////                        Login obj = new Login();
////                        obj.setVisible(true);
////                    }
//                }
//            });
//            updateButton.setBounds(100, 500, 200, 50);
//            contentPane.add(updateButton);

        Button changePasswordButton = DefaultModel.getDefaultButton("Thay đổi mật khẩu");
        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentChangePassword studentChangePassword = new StudentChangePassword(mssv,id);
                studentChangePassword.setVisible(true);
                dispose();
            }
        });
        changePasswordButton.setBounds(400, 200, 200, 50);
        contentPane.add(changePasswordButton);
    }
}

