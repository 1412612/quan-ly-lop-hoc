package controller;

import model.Room;
import model.Student;
import repository.StudentRepository;
import service.AcademicStaffService;
import service.RoomService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentHome extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private RoomService roomService = new RoomService();
    private Student student;
    private AcademicStaffService academicStaffService = new AcademicStaffService();
    private StudentRepository studentRepository = new StudentRepository();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentHome frame = new StudentHome("mssv1");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StudentHome() {

    }

    public StudentHome(final String mssv) {
        student = studentRepository.getByMssv(mssv);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trang chủ sinh viên");
        setBounds(250, 150, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel infoLabel = new JLabel("Thông tin sinh viên:");
        infoLabel.setBackground(Color.BLACK);
        infoLabel.setForeground(Color.BLACK);
        infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
        infoLabel.setBounds(300, 50, 500, 50);
        contentPane.add(infoLabel);

        JLabel usernameLabel = new JLabel("Tài khoản: "+ student.getMssv());
        usernameLabel.setBackground(Color.BLACK);
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        usernameLabel.setBounds(300, 100, 200, 100);
        contentPane.add(usernameLabel);

        JLabel nameLabel = new JLabel("Tên hiển thị: "+ student.getName());
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameLabel.setBounds(300, 150, 500, 100);
        contentPane.add(nameLabel);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.GRAY);
        menuPanel.setLayout(null);
        menuPanel.setBounds(0, 0, 200, 600);
        contentPane.add(menuPanel);

        Button subjectManagementButton = new Button("Quản lý lớp học");
        subjectManagementButton.setBounds(10, 10, 170, 50);
        subjectManagementButton.setForeground(new Color(0, 0, 0));
        subjectManagementButton.setBackground(UIManager.getColor("Button.disabledForeground"));
        subjectManagementButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        subjectManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentSubjectSV manager = new StudentSubjectSV(mssv, student.getId());
                manager.setVisible(true);
                dispose();
            }
        });


        Button accountManagementButton = new Button("Quản lý tài khoản");
        accountManagementButton.setBounds(10, 200, 170, 50);
        accountManagementButton.setForeground(new Color(0, 0, 0));
        accountManagementButton.setBackground(UIManager.getColor("Button.disabledForeground"));
        accountManagementButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        accountManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccountStudent accountStudent = new AccountStudent(mssv, student.getId());
                accountStudent.setVisible(true);
                dispose();
            }
        });

        menuPanel.add(subjectManagementButton);
        menuPanel.add(accountManagementButton);
    }

    JScrollPane getScrollPaneRoom(List<Room> list) {
        String data[][] = new String[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            data[i] = convertRoomToStrArr(list.get(i));
        }

        String column[] = {"ID", "NAME"};

        JTable jt = new JTable(data, column);
        jt.setBounds(200, 0, 600, 600);

        JScrollPane sp = new JScrollPane(jt);
        return sp;
    }

    String[] convertRoomToStrArr(Room room) {
        String[] results = new String[2];
        results[0] = String.valueOf(room.getId());
        results[1] = room.getName();
        return results;
    }

    JPanel getRoomManagerPanel(){
        JPanel mainPanel = new JPanel();
        List<Room> roomList = roomService.getAllRoom();

        JLabel contentLabel = new JLabel("Danh sách phòng học");
        contentLabel.setBackground(Color.BLACK);
        contentLabel.setForeground(Color.BLACK);
        contentLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
        contentLabel.setBounds(300, 100, 200, 50);
        mainPanel.add(contentLabel);

        JLabel headerLabel = new JLabel("Quản lý phòng học:");
        headerLabel.setBackground(Color.BLACK);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
        headerLabel.setBounds(300, 50, 200, 50);
        mainPanel.add(headerLabel);

        JScrollPane jScrollPane = getScrollPaneRoom(roomList);
        jScrollPane.setBounds(0, 150, 800, 400);

        mainPanel.add(jScrollPane);

        mainPanel.setBounds(200, 0, 800, 600);

        return mainPanel;
    }
}
