package controller;


import swingmodel.DataAttendanceModel;
import model.*;
import model.StudentSubject;
import org.apache.commons.lang3.ObjectUtils;
import repository.StudentRepository;
import repository.StudentSubjectRepository;
import repository.SubjectRepository;
import service.AcademicStaffService;
import service.RoomService;
import service.StudentSubjectService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryAttendance extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private RoomService roomService = new RoomService();
    private AcademicStaff academicStaff;
    private AcademicStaffService academicStaffService = new AcademicStaffService();
    int stt = 0;
    Subject subject;
    StudentSubject studentSubject;
    SubjectRepository subjectRepository = new SubjectRepository();
    StudentSubjectRepository studentSubjectRepository = new StudentSubjectRepository();
    StudentSubjectService studentSubjectService = new StudentSubjectService();
    Student student;
    StudentRepository studentRepository = new StudentRepository();


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HistoryAttendance frame = new HistoryAttendance("mssv1", 1);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public HistoryAttendance() {

    }

    public HistoryAttendance(String mssv, int subjectId) {
        subject = subjectRepository.getBySubjectId(subjectId);
        student = studentRepository.getByMssv(mssv);
        studentSubject = studentSubjectRepository.getBySubjectIdAndMssv(subjectId, mssv);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Điểm danh");
        setBounds(250, 150, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        List<StudentSubject> list = Collections.singletonList(studentSubject);

//        if (ObjectUtils.isNotEmpty(roomList))
//            lastRoomId = roomList.get(roomList.size() - 1).getId();

        JLabel headerLabel = new JLabel("Môn học: " + subject.getCode());
        headerLabel.setBackground(Color.BLACK);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        headerLabel.setBounds(100, 0, 800, 50);
        contentPane.add(headerLabel);
//
//        JLabel contentLabel = new JLabel("Danh sách phòng học");
//        contentLabel.setBackground(Color.BLACK);
//        contentLabel.setForeground(Color.BLACK);
//        contentLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        contentLabel.setBounds(100, 50, 900, 50);
//        contentPane.add(contentLabel);


        DataAttendanceModel model = getDefaultTableModelRoom(list);
        JTable jt = new JTable(model);
        jt.setRowHeight(20);
        jt.getTableHeader().setBackground(new Color(255, 128, 0));
        jt.getTableHeader().setForeground(Color.WHITE);
        jt.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));
        jt.setBounds(200, 0, 650, 400);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(50, 100, 800, 350);
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
//                        roomService.delete(entity);
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
                StudentSubjectSV academicStaffHome = new StudentSubjectSV(mssv,student.getId());
                academicStaffHome.setVisible(true);
                dispose();
            }
        });

        contentPane.add(backButton);

//        Button addButton = new Button("Thêm mới");
//        addButton.setBounds(150 + 175, 500, 100, 30);
//        addButton.setForeground(new Color(255, 255, 255));
//        addButton.setBackground(new Color(0, 128, 255));
//        addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        addButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                JTextField text1 = new JTextField(String.valueOf(++lastRoomId));
//                text1.setFont(new Font("Tahoma", Font.PLAIN, 20));
//                JTextField text2 = new JTextField();
//                text2.setFont(new Font("Tahoma", Font.PLAIN, 20));
//                JTextField text3 = new JTextField();
//                text3.setFont(new Font("Tahoma", Font.PLAIN, 20));
//
//                model.addRow(
//                        new Object[]{
//                                text1.getText(),
//                                text2.getText(),
//                                text3.getText()
//                        }
//                );
//
//                text1.setText("");
//                text2.setText("");
//                text3.setText("");
//            }
//        });
//
//        contentPane.add(addButton);


//        Button updateButton = new Button("Cập nhật");
//        updateButton.setBounds(150 + 175 + 100 + 175, 500, 100, 30);
//        updateButton.setForeground(new Color(255, 255, 255));
//        updateButton.setBackground(new Color(0, 128, 255));
//        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        updateButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
////                List<Room> entities = getListRoomInJTable(jt);
////                roomService.managerUpdate(entities);
//                studentSubjectService.updateBySubjectIdAndMssv(
//                        getListRoomInJTable(jt, id)
//                );
//                JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
//
//            }
//        });
//
//        contentPane.add(updateButton);
//    }
    }


    DataAttendanceModel getDefaultTableModelRoom(List<StudentSubject> list) {
        if (ObjectUtils.isEmpty(list)) {
            String columns[] = {"B1", "B2","B3","B4","B5","B6","B7","B8","B9","B10","B11","B12","B13","B14","B15"};
            DataAttendanceModel model = new DataAttendanceModel(columns, 0);
            return model;
        }

        String columns[] = {"B1", "B2","B3","B4","B5","B6","B7","B8","B9","B10","B11","B12","B13","B14","B15"};

        DataAttendanceModel model = new DataAttendanceModel(columns, 0);

        for (int i = 0; i < list.size(); i++) {
            model.addRow(convertRoomToStrArr(list.get(i)));
        }

        return model;
    }

    Object[] convertRoomToStrArr(StudentSubject studentSubject) {
        Object[] results = new Object[15];
        results[0] = studentSubject.isSessionOne();
        results[1] = studentSubject.isSessionTwo();
        results[2] = studentSubject.isSessionThree();
        results[3] = studentSubject.isSessionFour();
        results[4] = studentSubject.isSessionFive();
        results[5] = studentSubject.isSessionSix();
        results[6] = studentSubject.isSessionSeven();
        results[7] = studentSubject.isSessionEight();
        results[8] = studentSubject.isSessionNine();
        results[9] = studentSubject.isSessionTen();
        results[10] = studentSubject.isSessionEleven();
        results[11] = studentSubject.isSessionTwelve();
        results[12] = studentSubject.isSessionThirteen();
        results[13] = studentSubject.isSessionFourteen();
        results[14] = studentSubject.isSessionFifteen();
        return results;
    }


    private List<StudentSubject> getListRoomInJTable(JTable jt,int id) {
        List<StudentSubject> result = new ArrayList<>();
        for (int i = 0; i < jt.getRowCount(); i++) {
            String mssv = String.valueOf(jt.getValueAt(i, 1));
            Boolean b1 = (Boolean)(jt.getValueAt(i, 2));
            Boolean b2 = (Boolean)(jt.getValueAt(i, 3));
            Boolean b3 = (Boolean)(jt.getValueAt(i, 4));
            Boolean b4 = (Boolean)(jt.getValueAt(i, 5));
            Boolean b5 = (Boolean)(jt.getValueAt(i, 6));
            Boolean b6 = (Boolean)(jt.getValueAt(i, 7));
            Boolean b7 = (Boolean)(jt.getValueAt(i, 8));
            Boolean b8 = (Boolean)(jt.getValueAt(i, 9));
            Boolean b9 = (Boolean)(jt.getValueAt(i, 10));
            Boolean b10 = (Boolean)(jt.getValueAt(i, 11));
            Boolean b11 = (Boolean)(jt.getValueAt(i, 12));
            Boolean b12 = (Boolean)(jt.getValueAt(i, 13));
            Boolean b13 = (Boolean)(jt.getValueAt(i, 14));
            Boolean b14 = (Boolean)(jt.getValueAt(i, 15));
            Boolean b15 = (Boolean)(jt.getValueAt(i, 16));

            StudentSubject entity = new StudentSubject()
                    .setSubjectId(id)
                    .setMssv(mssv)
                    .setSessionOne(b1)
                    .setSessionTwo(b2)
                    .setSessionThree(b3)
                    .setSessionFour(b4)
                    .setSessionFive(b5)
                    .setSessionSix(b6)
                    .setSessionSeven(b7)
                    .setSessionEight(b8)
                    .setSessionNine(b9)
                    .setSessionTen(b10)
                    .setSessionEleven(b11)
                    .setSessionTwelve(b12)
                    .setSessionThirteen(b13)
                    .setSessionFour(b14)
                    .setSessionFifteen(b15);

            result.add(entity);
        }
        return result;
    }
}

