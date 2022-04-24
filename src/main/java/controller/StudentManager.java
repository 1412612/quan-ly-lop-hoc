package controller;


import data.DataModel;
import model.AcademicStaff;
import model.Room;
import model.Student;
import org.apache.commons.lang3.ObjectUtils;
import service.AcademicStaffService;
import service.StudentService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentManager extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private StudentService studentService = new StudentService();
    private AcademicStaff academicStaff;
    private AcademicStaffService academicStaffService = new AcademicStaffService();
    int lastStudentId = 0;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentManager frame = new StudentManager("admin");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StudentManager() {

    }

    public StudentManager(final String username) {
        academicStaff = academicStaffService.getByUsername(username);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Quản lý sinh viên");
        setBounds(250, 150, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        List<Student> studentList = studentService.getAllStudent();

        if (ObjectUtils.isNotEmpty(studentList))
            lastStudentId = studentList.get(studentList.size() - 1).getId();

        JLabel headerLabel = new JLabel("Quản lý Sinh viên:");
        headerLabel.setBackground(Color.BLACK);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        headerLabel.setBounds(100, 0, 800, 50);
        contentPane.add(headerLabel);

        JLabel contentLabel = new JLabel("Danh sách sinh viên");
        contentLabel.setBackground(Color.BLACK);
        contentLabel.setForeground(Color.BLACK);
        contentLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentLabel.setBounds(100, 50, 900, 50);
        contentPane.add(contentLabel);


        DataModel model = getDefaultTableModelObj(studentList);
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
        JButton deleteButton = new JButton("Xóa");
        deleteButton.setBounds(150 + 175 + 100 + 175 + 100 + 175, 500, 100, 30);
        deleteButton.setForeground(new Color(255, 255, 255));
        deleteButton.setBackground(new Color(0, 128, 255));
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // check for selected row first
                if (jt.getSelectedRow() != -1) {
                    int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phòng học này?");
                    if (a == JOptionPane.YES_OPTION) {
                        Student entity = new Student()
                                .setId(Integer.valueOf(String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0))))
                                .setMssv(String.valueOf(jt.getValueAt(jt.getSelectedRow(), 1)))
                                .setName(String.valueOf(jt.getValueAt(jt.getSelectedRow(), 2)));

                        boolean b = studentService.delete(entity);
                        if(!b) {
                            JOptionPane.showMessageDialog(null, "Không thể xóa sinh viên do sinh viên thuộc một số lớp học!");
                            return;
                        }
                        model.removeRow(jt.getSelectedRow());
                        StudentManager studentManager = new StudentManager(username);
                        studentManager.setVisible(true);
                        dispose();
                    }
                }
            }
        });

        contentPane.add(deleteButton);


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
                JTextField text1 = new JTextField(String.valueOf(++lastStudentId));
                text1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                JTextField text2 = new JTextField();
                text2.setFont(new Font("Tahoma", Font.PLAIN, 20));
                JTextField text3 = new JTextField();
                text3.setFont(new Font("Tahoma", Font.PLAIN, 20));

                model.addRow(
                        new Object[]{
                                text1.getText(),
                                text2.getText(),
                                text3.getText()
                        }
                );

                text1.setText("");
                text2.setText("");
                text3.setText("");
            }
        });

        contentPane.add(addButton);


        Button updateButton = new Button("Cập nhật");
        updateButton.setBounds(150 + 175 + 100 + 175, 500, 100, 30);
        updateButton.setForeground(new Color(255, 255, 255));
        updateButton.setBackground(new Color(0, 128, 255));
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Student> entities = getListObjectInJTable(jt);
                boolean b = studentService.managerUpdate(entities);
                StudentManager studentManager = new StudentManager(username);
                studentManager.setVisible(true);
                dispose();
            }
        });

        contentPane.add(updateButton);
    }


    DataModel getDefaultTableModelObj(List<Student> list) {
        if (ObjectUtils.isEmpty(list)) {
            String columns[] = {"ID", "Mã số sinh viên", "Tên sinh viên"};
            DataModel model = new DataModel(columns, 0);
            return model;
        }
        String data[][] = new String[list.size()][];

        String columns[] = {"ID", "Mã số sinh viên", "Tên sinh viên"};

        DataModel model = new DataModel(columns, 0);

        for (int i = 0; i < list.size(); i++) {
            model.addRow(convertObjToStrArr(list.get(i)));
        }

        return model;
    }

    String[] convertObjToStrArr(Student student) {
        String[] results = new String[3];
        results[0] = String.valueOf(student.getId());
        results[1] = String.valueOf(student.getMssv());
        results[2] = student.getName();
        return results;
    }


    private List<Student> getListObjectInJTable(JTable jt) {
        List<Student> result = new ArrayList<>();
        for (int i = 0; i < jt.getRowCount(); i++) {
            int id = Integer.valueOf(String.valueOf(jt.getValueAt(i, 0)));
            String mssv = String.valueOf(jt.getValueAt(i, 1));
            String name = String.valueOf(jt.getValueAt(i, 2));
            Student entity = new Student()
                    .setId(id)
                    .setMssv(mssv)
                    .setName(name);
            result.add(entity);
        }
        return result;
    }
}

