package controller;


import constant.DayOfWeekConstant;
import data.DataModel;
import data.Table;
import model.AcademicStaff;
import model.Room;
import model.Subject;
import org.apache.commons.lang3.ObjectUtils;
import repository.SubjectRepository;
import service.AcademicStaffService;
import utils.DateToString;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SubjectManager extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private SubjectRepository subjectRepository = new SubjectRepository();
    private AcademicStaff academicStaff;
    private AcademicStaffService academicStaffService = new AcademicStaffService();
    int lastRoomId = 0;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SubjectManager frame = new SubjectManager("admin");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SubjectManager() {

    }

    public SubjectManager(final String username) {
        academicStaff = academicStaffService.getByUsername(username);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Quản lý lớp học");
        setBounds(450, 190, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        List<Subject> subjects = subjectRepository.getAll();

//        if (ObjectUtils.isNotEmpty(roomList))
//            lastRoomId = roomList.get(roomList.size() - 1).getId();

        JLabel headerLabel = new JLabel("Quản lý lớp học:");
        headerLabel.setBackground(Color.BLACK);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        headerLabel.setBounds(100, 0, 800, 50);
        contentPane.add(headerLabel);

        JLabel contentLabel = new JLabel("Danh sách lớp học");
        contentLabel.setBackground(Color.BLACK);
        contentLabel.setForeground(Color.BLACK);
        contentLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentLabel.setBounds(100, 50, 900, 50);
        contentPane.add(contentLabel);


        DataModel model = getDefaultTableModel(subjects);
        Table jt = new Table(model);
        jt.setColumnWidths(30, 60,80,60,60, 150, 150,130,130);
        jt.setRowHeight(20);
        jt.getTableHeader().setBackground(new Color(255, 128, 0));
        jt.getTableHeader().setForeground(Color.WHITE);
        jt.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
        jt.setBounds(200, 0, 650, 400);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(30, 100, 850, 350);
        contentPane.add(sp);

        jt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
//        JButton deleteButton = new JButton("Xóa");
//        deleteButton.setBounds(150 + 175 + 100 + 175 + 100 + 175, 500, 100, 30);
//        deleteButton.setForeground(new Color(255, 255, 255));
//        deleteButton.setBackground(new Color(0, 128, 255));
//        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                // check for selected row first
//                if (jt.getSelectedRow() != -1) {
//                    int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phòng học này?");
//                    if (a == JOptionPane.YES_OPTION) {
//                        Room entity = new Room()
//                                .setId(Integer.valueOf(String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0))))
//                                .setCode(String.valueOf(jt.getValueAt(jt.getSelectedRow(), 1)))
//                                .setName(String.valueOf(jt.getValueAt(jt.getSelectedRow(), 2)));
//
//                        //roomService.delete(entity);
//                        model.removeRow(jt.getSelectedRow());
//                    }
//                }
//            }
//        });
//
//        contentPane.add(deleteButton);


        Button backButton = new Button("Quay lại");
        backButton.setBounds(50, 500, 100, 30);
        backButton.setForeground(new Color(255, 255, 255));
        backButton.setBackground(new Color(0, 128, 255));
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AcademicStaffHome academicStaffHome = new AcademicStaffHome(username);
                academicStaffHome.setVisible(true);
                dispose();
            }
        });

        contentPane.add(backButton);

        Button addButton = new Button("Thêm mới");
        addButton.setBounds(150 + 175, 500, 100, 30);
        addButton.setForeground(new Color(255, 255, 255));
        addButton.setBackground(new Color(0, 128, 255));
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddSubject addSubject = new AddSubject(username);
                addSubject.setVisible(true);
                dispose();
            }
        });

        contentPane.add(addButton);

        Button detailButton = new Button("Chi tiết");
        detailButton.setBounds(150 + 175+175 +100, 500, 100, 30);
        detailButton.setForeground(new Color(255, 255, 255));
        detailButton.setBackground(new Color(0, 128, 255));
        detailButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        detailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jt.getSelectedRow() != -1) {
                    Integer id = Integer.valueOf(String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0)));
                    DetailSubject addSubject = new DetailSubject(username, id);
                    addSubject.setVisible(true);
                    dispose();
                }
            }
        });

        contentPane.add(detailButton);


//        Button updateButton = new Button("Cập nhật");
//        updateButton.setBounds(150 + 175 + 100 + 175, 500, 100, 30);
//        updateButton.setForeground(new Color(255, 255, 255));
//        updateButton.setBackground(new Color(0, 128, 255));
//        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        updateButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                List<Subject> entities = getListObjInJTable(jt);
//                //roomService.managerUpdate(entities);
//            }
//        });
//
//        contentPane.add(updateButton);
    }


    DataModel getDefaultTableModel(List<Subject> list) {
        if (ObjectUtils.isEmpty(list)) {
            String columns[] = {"ID", "Mã lớp", "Tên lớp", "Phòng", "Thứ", "Thời gian bắt đầu", "Thời gian kết thúc" ,"Ngày bắt đầu", "Ngày kết thúc"};

            DataModel model = new DataModel(columns, 0);
            return model;
        }
        String data[][] = new String[list.size()][];

        String columns[] = {"ID", "Mã lớp", "Tên lớp", "Phòng", "Thứ", "Thời gian bắt đầu", "Thời gian kết thúc" ,"Ngày bắt đầu", "Ngày kết thúc"};

        DataModel model = new DataModel(columns, 0);

        for (int i = 0; i < list.size(); i++) {
            model.addRow(convertObjToStrArr(list.get(i)));
        }

        return model;
    }

    String[] convertObjToStrArr(Subject subject) {
        String[] results = new String[9];
        results[0] = String.valueOf(subject.getId());
        results[1] = String.valueOf(subject.getCode());
        results[2] = String.valueOf(subject.getName());
        results[3] = String.valueOf(subject.getRoom().getCode());
        results[4] = String.valueOf(subject.getDayOfWeek());
        results[5] = String.valueOf(new DateToString().convert(subject.getDateStart()));
        results[6] = String.valueOf(new DateToString().convert(subject.getDateEnd()));
        results[7] = String.valueOf(subject.getTimeStart());
        results[8] = String.valueOf(subject.getTimeEnd());
        return results;
    }


    private List<Subject> getListObjInJTable(JTable jt) {
        List<Subject> result = new ArrayList<>();
        for (int i = 0; i < jt.getRowCount(); i++) {
            int id = Integer.valueOf(String.valueOf(jt.getValueAt(i, 0)));
            String code = String.valueOf(jt.getValueAt(i, 1));
            String name = String.valueOf(jt.getValueAt(i, 2));
            Room entity = new Room()
                    .setId(id)
                    .setCode(code)
                    .setName(name);
           // result.add(entity);
        }
        return result;
    }
}

