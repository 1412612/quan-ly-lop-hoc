package controller;

import model.Student;
import service.StudentService;
import utils.PasswordUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordDefault extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField newPassword;
    private JTextField confirmPassword;
    private StudentService studentService;
    private String mssv;
    private Student student;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChangePasswordDefault frame = new ChangePasswordDefault("SV0001");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getStudent(){
        student = studentService.getByMssv(mssv);
    }

    public ChangePasswordDefault(String mssv) {
        this.mssv = mssv;
        this.studentService = new StudentService();
        getStudent();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Đổi mật khẩu");
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbText = new JLabel("Chào "+ student.getName() + " bạn cần đổi mật khẩu mặc định khi đăng nhập lần đầu");
        lbText.setBackground(Color.BLACK);
        lbText.setForeground(Color.BLACK);
        lbText.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbText.setBounds(100, 100, 900, 52);
        contentPane.add(lbText);

        JLabel lbNewPassword = new JLabel("Mật khẩu mới:");
        lbNewPassword.setBackground(Color.BLACK);
        lbNewPassword.setForeground(Color.BLACK);
        lbNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lbNewPassword.setBounds(100, 170, 281, 52);
        contentPane.add(lbNewPassword);

        newPassword = new JTextField();
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

        confirmPassword = new JTextField();
        confirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        confirmPassword.setBounds(481, 250, 281, 68);
        contentPane.add(confirmPassword);
        confirmPassword.setColumns(10);

        JButton button = new JButton("Đổi mật khẩu\r\n");
        button.setBackground(UIManager.getColor("Button.disabledForeground"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPassStr = newPassword.getText();
                String confirmPassStr = confirmPassword.getText();
                if(newPassStr.equals("") || confirmPassStr.equals("")){
                    JOptionPane.showMessageDialog(button, "Vui lòng điền đầy đủ thông tin!");
                }
                else if(PasswordUtils.passwordEncoder.matches(newPassStr, student.getPassword())){
                    JOptionPane.showMessageDialog(button, "Mật khẩu chưa được thay đổi!");
                }else if(!newPassStr.equals(confirmPassStr)){
                    JOptionPane.showMessageDialog(button, "Xác nhận mật khẩu không khớp!");
                }else{
                    student.setPassword(PasswordUtils.passwordEncoder.encode(newPassStr));
                    studentService.update(student);
                    dispose();
                    StudentHome studentHome = new StudentHome(mssv);
                    studentHome.setVisible(true);
                    JOptionPane.showMessageDialog(button, "Đổi mật khẩu thành công!");
                }
            }
        });
        button.setFont(new Font("Tahoma", Font.PLAIN, 26));
        button.setBounds(247, 350, 300, 80);
        contentPane.add(button);
    }
}


