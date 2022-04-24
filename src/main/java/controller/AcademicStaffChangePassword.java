package controller;

import data.DefaultModel;
import model.AcademicStaff;
import service.AcademicStaffService;
import utils.PasswordUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcademicStaffChangePassword extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private AcademicStaffService academicStaffService = new AcademicStaffService();
    private String username;
    private AcademicStaff academicStaff;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AcademicStaffChangePassword frame = new AcademicStaffChangePassword("admin");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public AcademicStaffChangePassword(String username) {
        this.username = username;
        this.academicStaff = academicStaffService.getByUsername(username);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Đổi mật khẩu");
        setBounds(250, 150, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbOldPassword = new JLabel("Mật khẩu cũ:");
        lbOldPassword.setBackground(Color.BLACK);
        lbOldPassword.setForeground(Color.BLACK);
        lbOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lbOldPassword.setBounds(100, 100, 281, 52);
        contentPane.add(lbOldPassword);

        JTextField oldPassword = new JTextField();
        oldPassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        oldPassword.setBounds(481, 100, 281, 68);
        contentPane.add(oldPassword);
        oldPassword.setColumns(10);

        JLabel lbNewPassword = new JLabel("Mật khẩu mới:");
        lbNewPassword.setBackground(Color.BLACK);
        lbNewPassword.setForeground(Color.BLACK);
        lbNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lbNewPassword.setBounds(100, 170, 281, 52);
        contentPane.add(lbNewPassword);

        JTextField newPassword = new JTextField();
        newPassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        newPassword.setBounds(481, 170, 281, 68);
        contentPane.add(newPassword);
        newPassword.setColumns(10);

        JLabel lblUsername = new JLabel("Xác nhận mật khẩu:");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblUsername.setBounds(100, 250, 281, 52);
        contentPane.add(lblUsername);

        JTextField confirmPassword = new JTextField();
        confirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        confirmPassword.setBounds(481, 250, 281, 68);
        contentPane.add(confirmPassword);
        confirmPassword.setColumns(10);

        Button button = DefaultModel.getDefaultButton("Đổi mật khẩu");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String oldPassStr = oldPassword.getText();
                if (!PasswordUtils.passwordEncoder.matches(oldPassStr, academicStaff.getPassword())) {
                    JOptionPane.showMessageDialog(button, "Mật khẩu cũ không chính xác!");
                    return;
                }
                String newPassStr = newPassword.getText();
                String confirmPassStr = confirmPassword.getText();
                if (newPassStr.equals("") || confirmPassStr.equals("")) {
                    JOptionPane.showMessageDialog(button, "Vui lòng điền đầy đủ thông tin!");
                } else if (PasswordUtils.passwordEncoder.matches(newPassStr, academicStaff.getPassword())) {
                    JOptionPane.showMessageDialog(button, "Mật khẩu chưa được thay đổi!");
                } else if (!newPassStr.equals(confirmPassStr)) {
                    JOptionPane.showMessageDialog(button, "Xác nhận mật khẩu không khớp!");
                } else {
                    academicStaff.setPassword(PasswordUtils.passwordEncoder.encode(newPassStr));
                    academicStaffService.update(academicStaff);
                    dispose();
                    AccountAcademicStaff accountAcademicStaff = new AccountAcademicStaff(username);
                    accountAcademicStaff.setVisible(true);
                    JOptionPane.showMessageDialog(button, "Đổi mật khẩu thành công!");
                }
            }
        });
        button.setBounds(500, 350, 200, 50);
        contentPane.add(button);

        Button backButton = DefaultModel.getDefaultButton("Quay lại");
        backButton.setBounds(100,350,200,50);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AcademicStaffHome academicStaffHome = new AcademicStaffHome(username);
                academicStaffHome.setVisible(true);
                dispose();
            }
        });

        contentPane.add(backButton);
    }
}


