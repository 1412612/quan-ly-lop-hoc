package controller;

import swingmodel.DefaultModel;
import model.Student;
import service.StudentService;
import utils.PasswordUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentChangePassword extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    StudentService studentService = new StudentService();
    private String mssv;
    private Student student;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentChangePassword frame = new StudentChangePassword("SV7", 13);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public StudentChangePassword(String mssv, int id) {
        this.mssv = mssv;
        this.student = studentService.getByMssv(mssv);

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

        JPasswordField oldPassword = new JPasswordField();
        oldPassword.setEchoChar('*');
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

        JPasswordField newPassword = new JPasswordField();
        newPassword.setEchoChar('*');
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

        JPasswordField confirmPassword = new JPasswordField();
        confirmPassword.setEchoChar('*');
        confirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        confirmPassword.setBounds(481, 250, 281, 68);
        contentPane.add(confirmPassword);
        confirmPassword.setColumns(10);

        JCheckBox showPassword=new JCheckBox("Show Password");
        showPassword.setBounds(481, 320, 300, 50);
        showPassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        contentPane.add(showPassword);
        showPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    newPassword.setEchoChar((char) 0);
                    confirmPassword.setEchoChar((char) 0);
                    oldPassword.setEchoChar((char) 0);
                } else {
                    newPassword.setEchoChar('*');
                    oldPassword.setEchoChar('*');
                    confirmPassword.setEchoChar('*');
                }
            }
        });

        JButton button = DefaultModel.getDefaultButton("Đổi mật khẩu");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String oldPassStr = oldPassword.getText();
                if (!PasswordUtils.passwordEncoder.matches(oldPassStr, student.getPassword())) {
                    JOptionPane.showMessageDialog(button, "Mật khẩu cũ không chính xác!");
                    return;
                }
                String newPassStr = newPassword.getText();
                String confirmPassStr = confirmPassword.getText();
                if (newPassStr.equals("") || confirmPassStr.equals("")) {
                    JOptionPane.showMessageDialog(button, "Vui lòng điền đầy đủ thông tin!");
                } else if (PasswordUtils.passwordEncoder.matches(newPassStr, student.getPassword())) {
                    JOptionPane.showMessageDialog(button, "Mật khẩu chưa được thay đổi!");
                } else if (!newPassStr.equals(confirmPassStr)) {
                    JOptionPane.showMessageDialog(button, "Xác nhận mật khẩu không khớp!");
                } else {
                    student.setPassword(PasswordUtils.passwordEncoder.encode(newPassStr));
                    studentService.update(student);
                    JOptionPane.showMessageDialog(button, "Đổi mật khẩu thành công!");
                    AccountStudent accountStudent = new AccountStudent(mssv, id);
                    accountStudent.setVisible(true);
                    dispose();
                }
            }
        });
        button.setBounds(500, 400, 200, 50);
        contentPane.add(button);

        JButton backButton = DefaultModel.getDefaultButton("Quay lại");
        backButton.setBounds(100,400,200,50);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccountStudent accountStudent = new AccountStudent(mssv, id);
                accountStudent.setVisible(true);
                dispose();
            }
        });

        contentPane.add(backButton);
    }
}


