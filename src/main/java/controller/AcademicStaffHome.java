package controller;

import model.AcademicStaff;
import model.Room;
import service.AcademicStaffService;
import service.RoomService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AcademicStaffHome extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private RoomService roomService = new RoomService();
    private AcademicStaff academicStaff;
    private AcademicStaffService academicStaffService = new AcademicStaffService();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AcademicStaffHome frame = new AcademicStaffHome("admin");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AcademicStaffHome() {

    }

    public AcademicStaffHome(final String username) {
        academicStaff = academicStaffService.getByUsername(username);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trang chủ giáo vụ");
        setBounds(450, 190, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel infoLabel = new JLabel("Thông tin:");
        infoLabel.setBackground(Color.BLACK);
        infoLabel.setForeground(Color.BLACK);
        infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
        infoLabel.setBounds(300, 50, 200, 50);
        contentPane.add(infoLabel);

        JLabel usernameLabel = new JLabel("Tài khoản: "+ academicStaff.getUsername());
        usernameLabel.setBackground(Color.BLACK);
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        usernameLabel.setBounds(300, 100, 200, 100);
        contentPane.add(usernameLabel);

        JLabel nameLabel = new JLabel("Tên hiển thị: "+ academicStaff.getName());
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

            }
        });

        Button studentManagementButton = new Button("Quản lý sinh viên");
        studentManagementButton.setBounds(10, 110, 170, 50);
        studentManagementButton.setForeground(new Color(0, 0, 0));
        studentManagementButton.setBackground(UIManager.getColor("Button.disabledForeground"));
        studentManagementButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        studentManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        Button roomManagementButton = new Button("Quản lý phòng học");
        roomManagementButton.setBounds(10, 210, 170, 50);
        roomManagementButton.setForeground(new Color(0, 0, 0));
        roomManagementButton.setBackground(UIManager.getColor("Button.disabledForeground"));
        roomManagementButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        roomManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RoomManager roomManager = new RoomManager(username);
                roomManager.setVisible(true);
                dispose();
            }
        });

        Button accountManagementButton = new Button("Quản lý tài khoản");
        accountManagementButton.setBounds(10, 310, 170, 50);
        accountManagementButton.setForeground(new Color(0, 0, 0));
        accountManagementButton.setBackground(UIManager.getColor("Button.disabledForeground"));
        accountManagementButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        accountManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccountAcademicStaff accountAcademicStaff = new AccountAcademicStaff(username);
                accountAcademicStaff.setVisible(true);
                dispose();
            }
        });

        menuPanel.add(subjectManagementButton);
        menuPanel.add(studentManagementButton);
        menuPanel.add(roomManagementButton);
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
