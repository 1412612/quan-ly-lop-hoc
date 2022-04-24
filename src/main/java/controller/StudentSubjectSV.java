package controller;


import data.DataModel;
import data.Table;
import model.Room;
import model.Student;
import model.StudentSubject;
import model.Subject;
import org.apache.commons.lang3.ObjectUtils;
import repository.StudentSubjectRepository;
import repository.SubjectRepository;
import service.StudentService;
import service.StudentSubjectService;
import utils.DateToString;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StudentSubjectSV extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private SubjectRepository subjectRepository = new SubjectRepository();
    Student student;
    StudentService studentService = new StudentService();
    StudentSubjectService studentSubjectService = new StudentSubjectService();
    StudentSubjectRepository studentSubjectRepository = new StudentSubjectRepository();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentSubjectSV frame = new StudentSubjectSV("mssv1", 13);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StudentSubjectSV() {

    }

    public StudentSubjectSV(final String mssv, int id) {
        student = studentService.getByMssv(mssv);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Quản lý lớp học");
        setBounds(250, 150, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        List<Subject> subjects = studentSubjectService.getListSubjectByMssv(mssv);

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
                StudentHome academicStaffHome = new StudentHome(mssv);
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
//                AddSubject addSubject = new AddSubject(username);
//                addSubject.setVisible(true);
//                dispose();
//            }
//        });
//
//        contentPane.add(addButton);

        Button detailButton = new Button("Lịch sử điểm danh");
        detailButton.setBounds(150 + 175+175 +100, 500, 200, 30);
        detailButton.setForeground(new Color(255, 255, 255));
        detailButton.setBackground(new Color(0, 128, 255));
        detailButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        detailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jt.getSelectedRow() != -1) {
                    Integer id = Integer.valueOf(String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0)));
                    HistoryAttendance historyAttendance = new HistoryAttendance(mssv, id);
                    historyAttendance.setVisible(true);
                    dispose();
                }
            }
        });

        contentPane.add(detailButton);

        Button attendanceButton = new Button("Điểm danh");
        attendanceButton.setBounds(150 + 175, 500, 100, 30);
        attendanceButton.setForeground(new Color(255, 255, 255));
        attendanceButton.setBackground(new Color(0, 128, 255));
        attendanceButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        attendanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jt.getSelectedRow() != -1) {
                    int index = jt.getSelectedRow();
                    diemdanh(subjects.get(index), studentSubjectRepository.getBySubjectIdAndMssv(subjects.get(index).getId(), mssv));
                }
            }
        });

        contentPane.add(attendanceButton);


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

    public void diemdanh(Subject subject, StudentSubject studentSubject){
        int startHour; int endHour; int startM; int endM;
        String[] split = subject.getTimeStart().split(" ");
        String[] strings = split[0].split("\\:");
        if(split[1].equals("AM")){
            startHour = Integer.valueOf(strings[0]);
        }else{
            startHour = Integer.valueOf(strings[0])+12;
        }
        startM = Integer.valueOf(strings[1]);

        split = subject.getTimeEnd().split(" ");
        strings = split[0].split("\\:");
        if(split[1].equals("AM")){
            endHour = Integer.valueOf(strings[0]);
        }else{
            endHour = Integer.valueOf(strings[0])+12;
        }
        endM = Integer.valueOf(strings[1]);

        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.get(Calendar.HOUR);
        calendar.get(Calendar.MINUTE);
        if(!(startHour<=calendar.get(Calendar.HOUR) && endHour>=calendar.get(Calendar.HOUR))){
            JOptionPane.showMessageDialog(null, "Không phải thời gian điểm danh môn học này!");
            return;
        }if(startHour==calendar.get(Calendar.HOUR) && startM<=calendar.get(Calendar.MINUTE)){
            JOptionPane.showMessageDialog(null, "Không phải thời gian điểm danh môn học này!");
            return;
        }if(endHour==calendar.get(Calendar.HOUR) && endM>=calendar.get(Calendar.MINUTE)){
            JOptionPane.showMessageDialog(null, "Không phải thời gian điểm danh môn học này!");
            return;
        }

        nowDate = new DateToString().reConvert(new DateToString<>().convert(nowDate));

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(subject.getDateStart());
        Date b1 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b2 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b3 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b4 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b5 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b6 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b7 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b8 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b9 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b10 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b11 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b12 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b13 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b14 = calendar1.getTime();
        calendar1.add(Calendar.DAY_OF_YEAR, 7);
        Date b15 = calendar1.getTime();

        if(nowDate.compareTo(b1)==0){
            studentSubject.setSessionOne(true);
        }else if(nowDate.compareTo(b2)==0){
            studentSubject.setSessionTwo(true);
        }else if(nowDate.compareTo(b3)==0){
            studentSubject.setSessionThree(true);
        }else if(nowDate.compareTo(b4)==0){
            studentSubject.setSessionFour(true);
        }else if(nowDate.compareTo(b5)==0){
            studentSubject.setSessionFive(true);
        }else if(nowDate.compareTo(b6)==0){
            studentSubject.setSessionSix(true);
        }else if(nowDate.compareTo(b7)==0){
            studentSubject.setSessionSeven(true);
        }else if(nowDate.compareTo(b8)==0){
            studentSubject.setSessionEight(true);
        }else if(nowDate.compareTo(b9)==0){
            studentSubject.setSessionNine(true);
        }else if(nowDate.compareTo(b10)==0){
            studentSubject.setSessionTen(true);
        }else if(nowDate.compareTo(b11)==0){
            studentSubject.setSessionEleven(true);
        }else if(nowDate.compareTo(b12)==0){
            studentSubject.setSessionTwelve(true);
        }else if(nowDate.compareTo(b13)==0){
            studentSubject.setSessionThirteen(true);
        }else if(nowDate.compareTo(b14)==0){
            studentSubject.setSessionFourteen(true);
        }else if(nowDate.compareTo(b15)==0){
            studentSubject.setSessionFifteen(true);
        }else{
            JOptionPane.showMessageDialog(null, "Không phải thời gian điểm danh môn học này!");
            return;
        }
        studentSubjectRepository.updateBySubjectIdAndMssv(studentSubject);
        JOptionPane.showMessageDialog(null, "Điểm danh thành công!");


    }
}

