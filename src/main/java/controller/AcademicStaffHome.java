package controller;

import model.Room;
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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trang chủ giáo vụ");
        setBounds(450, 190, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        List<Room> list = new ArrayList<>();
        list.add(new Room().setId(1).setName("TC201"));
        list.add(new Room().setId(2).setName("TC202"));

        String data[][] = new String[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            data[i] = convertObjectToStr(list.get(i));
        }

        String column[] = {"ID", "NAME"};

        JTable jt = new JTable(data, column);
        jt.setBounds(200, 50, 800, 600);

        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(200, 0, 800, 600);
        contentPane.add(sp);

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

    JScrollPane getScrollPaneRoom(List<Room> roomList) {
        List<Room> list = new ArrayList<>();
        list.add(new Room().setId(1).setName("TC201"));
        list.add(new Room().setId(2).setName("TC202"));

        String data[][] = new String[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            data[i] = convertObjectToStr(list.get(i));
        }

        String column[] = {"ID", "NAME"};

        JTable jt = new JTable(data, column);
        jt.setBounds(200, 0, 600, 600);

        JScrollPane sp = new JScrollPane(jt);
        return sp;
    }

    String[] convertObjectToStr(Room room) {
        String[] results = new String[2];
        results[0] = String.valueOf(room.getId());
        results[1] = room.getName();
        return results;
    }

    JPanel getRoomManagerPanel(){
        JPanel mainPanel = new JPanel();

        JLabel headerLabel = new JLabel("Quản lý phòng học");
        JLabel contentLabel = new JLabel("Danh sách phòng học");

        List<Room> roomList = roomService.getAllRoom();
    }
}
