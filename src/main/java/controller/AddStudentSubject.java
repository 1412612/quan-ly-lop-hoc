package controller;

import model.Student;
import model.StudentSubject;
import repository.StudentRepository;
import repository.StudentSubjectRepository;
import repository.SubjectRepository;
import service.RoomService;
import utils.PasswordUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentSubject extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private RoomService roomService = new RoomService();
    private SubjectRepository subjectRepository = new SubjectRepository();
    private StudentRepository studentRepository = new StudentRepository();
    private StudentSubjectRepository studentSubjectRepository = new StudentSubjectRepository();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddStudentSubject frame = new AddStudentSubject("admin", 1);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AddStudentSubject(String username, int subjectId) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Quản lý môn học");
        setBounds(250, 150, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //header
        JLabel headerLabel = new JLabel("Thêm sinh viên mới vào lớp học");
        headerLabel.setBackground(Color.BLACK);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        headerLabel.setBounds(30, 30, 500, 50);
        contentPane.add(headerLabel);

        JLabel mssvLabel = new JLabel("MSSV");
        mssvLabel.setBackground(Color.BLACK);
        mssvLabel.setForeground(Color.BLACK);
        mssvLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mssvLabel.setBounds(30, 130, 200, 30);
        contentPane.add(mssvLabel);

        JTextField mssvTextField = new JTextField();
        mssvTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mssvTextField.setBounds(250, 130, 200, 30);
        contentPane.add(mssvTextField);
        mssvTextField.setColumns(10);

        JLabel nameLabel = new JLabel("Tên sinh viên");
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameLabel.setBounds(500, 130, 200, 30);
        contentPane.add(nameLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameTextField.setBounds(700, 130, 200, 30);
        contentPane.add(nameTextField);
        nameTextField.setColumns(10);

        JButton backButton = new JButton("Quay lại");
        backButton.setBounds(50, 500, 120, 30);
        backButton.setForeground(new Color(255, 255, 255));
        backButton.setBackground(new Color(0, 128, 255));
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentSubjectManager subjectManager = new StudentSubjectManager(username, subjectId);
                subjectManager.setVisible(true);
                dispose();
            }
        });

        contentPane.add(backButton);

        JButton addButton = new JButton("Thêm mới");
        addButton.setBounds(150 + 175, 500, 120, 30);
        addButton.setForeground(new Color(255, 255, 255));
        addButton.setBackground(new Color(0, 128, 255));
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mssv = mssvTextField.getText();
                String name = nameTextField.getText();
                Student student = new Student()
                        .setMssv(mssv)
                        .setName(name)
                        .setPassword(PasswordUtils.passwordEncoder.encode(mssv));

                studentRepository.save(student);

                StudentSubject studentSubject = new StudentSubject()
                        .setSubjectId(subjectId)
                        .setMssv(mssv);
                studentSubjectRepository.save(studentSubject);
                JOptionPane.showMessageDialog(null, "Thêm sinh viên vào lớp thành công!");
                StudentSubjectManager subjectManager = new StudentSubjectManager(username, subjectId);
                subjectManager.setVisible(true);
                dispose();
            }
        });

        contentPane.add(addButton);
    }
}

