package controller;

import model.AcademicStaff;
import model.Student;
import service.LoginService;
import service.StudentService;
import utils.PasswordUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;
    private JRadioButton radioStudent;
    private JRadioButton radioStaff;
    private LoginService loginService = new LoginService();
    private StudentService studentService = new StudentService();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 150, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);
        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);
        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(545, 480, 130, 50);

        radioStudent = new JRadioButton("Student", true);
        radioStaff = new JRadioButton("Academic Staff");
        radioStaff.setFont(new Font("Tahoma", Font.PLAIN, 26));
        radioStudent.setFont(new Font("Tahoma", Font.PLAIN, 26));
        radioStudent.setBounds(400,360,200,50);
        radioStaff.setBounds(600,360,200,50);
        ButtonGroup bg=new ButtonGroup();
        bg.add(radioStaff);bg.add(radioStudent);
        contentPane.add(radioStudent);
        contentPane.add(radioStaff);

        JCheckBox showPassword=new JCheckBox("Show Password");
        showPassword.setBounds(500, 420, 300, 50);
        showPassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        contentPane.add(showPassword);
        showPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });


        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                String password = passwordField.getText();

                boolean isLogin = false;

                if(radioStudent.isSelected()){
                    Student student = new Student()
                    .setMssv(userName)
                    .setPassword(password);
                    isLogin = loginService.studentLogin(student);

                }else{
                    AcademicStaff academicStaff = new AcademicStaff();
                    academicStaff.setUsername(userName);
                    academicStaff.setPassword(password);
                    isLogin = loginService.academicStaffLogin(academicStaff);
                }

                if (isLogin) {
                    dispose();
                    if(radioStudent.isSelected()){
                        Student student = studentService.getByMssv(userName);
                        if(PasswordUtils.passwordEncoder.matches(userName, student.getPassword())){
                            ChangePasswordDefault changePasswordDefault = new ChangePasswordDefault(userName);
                            changePasswordDefault.setVisible(true);
                        }else {
                            StudentHome studentHome = new StudentHome(userName);
                            studentHome.setVisible(true);
                        }
                    }else {
                        AcademicStaffHome academicStaffHome = new AcademicStaffHome(userName);
                        academicStaffHome.setVisible(true);
                    }
                    JOptionPane.showMessageDialog(btnNewButton, "Ban da dang nhap thanh cong");
                }else if(isLogin && radioStudent.isSelected() && userName.equals(password)){
                    dispose();
                    AcademicStaffHome ah = new AcademicStaffHome(userName);
                    ah.setTitle("Chao mung");
                    ah.setVisible(true);
                    JOptionPane.showMessageDialog(btnNewButton, "Ban da dang nhap thanh cong");
                }
                else {
                    JOptionPane.showMessageDialog(btnNewButton, "User hoac password sai!");
                }
            }
        });
        contentPane.add(btnNewButton);
        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }
}