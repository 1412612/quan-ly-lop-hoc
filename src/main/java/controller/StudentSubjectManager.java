
package controller;


import swingmodel.DataModel;
import lombok.SneakyThrows;
import model.AcademicStaff;
import model.Student;
import model.StudentSubject;
import model.Subject;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import repository.StudentRepository;
import repository.StudentSubjectRepository;
import repository.SubjectRepository;
import service.AcademicStaffService;
import service.StudentService;
import service.StudentSubjectService;
import utils.PasswordUtils;
import utils.WriteExcel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentSubjectManager extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private StudentService studentService = new StudentService();
    private AcademicStaff academicStaff;
    private AcademicStaffService academicStaffService = new AcademicStaffService();
    private SubjectRepository subjectRepository = new SubjectRepository();
    private Subject subject;
    private StudentSubjectService studentSubjectService = new StudentSubjectService();
    private StudentSubjectRepository studentSubjectRepository = new StudentSubjectRepository();
    StudentRepository studentRepository = new StudentRepository();
    int lastStudentId = 0;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentSubjectManager frame = new StudentSubjectManager("admin", 1);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StudentSubjectManager() {

    }

    public StudentSubjectManager(final String username, int id) {
        academicStaff = academicStaffService.getByUsername(username);
        subject = subjectRepository.getBySubjectId(id);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Quản lý sinh viên môn học");
        setBounds(250, 150, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        java.util.List<Student> studentList = studentSubjectService.getListStudentBySSID(id);

        if (ObjectUtils.isNotEmpty(studentList))
            lastStudentId = studentList.get(studentList.size() - 1).getId();

        JLabel headerLabel = new JLabel("Mã môn học: " + subject.getCode());
        headerLabel.setBackground(Color.BLACK);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        headerLabel.setBounds(100, 0, 800, 50);
        contentPane.add(headerLabel);

        JLabel contentLabel = new JLabel("Danh sách sinh viên hiện tại");
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
        deleteButton.setBounds(25+100+50 + 100+50 + 150+50 + 150 + 100+100 , 500, 100, 30);
        deleteButton.setForeground(new Color(255, 255, 255));
        deleteButton.setBackground(new Color(0, 128, 255));
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // check for selected row first
                if (jt.getSelectedRow() != -1) {
                    int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sinh viên này khỏi lớp?");
                    if (a == JOptionPane.YES_OPTION) {
                        studentSubjectRepository.deleteByMssvAndSID(id, String.valueOf(jt.getValueAt(jt.getSelectedRow(), 1)));
                        model.removeRow(jt.getSelectedRow());
                    }
                }
            }
        });

        contentPane.add(deleteButton);


        JButton backButton = new JButton("Quay lại");
        backButton.setBounds(25, 500, 120, 30);
        backButton.setForeground(new Color(255, 255, 255));
        backButton.setBackground(new Color(0, 128, 255));
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DetailSubject parent = new DetailSubject(username, id);
                parent.setVisible(true);
                dispose();
            }
        });

        contentPane.add(backButton);

        JButton addButton = new JButton("Thêm mới");
        addButton.setBounds(25+100+50, 500, 140, 30);
        addButton.setForeground(new Color(255, 255, 255));
        addButton.setBackground(new Color(0, 128, 255));
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddStudentSubject child = new AddStudentSubject(username, id);
                child.setVisible(true);
                dispose();
            }
        });

        contentPane.add(addButton);


        JButton updateButton = new JButton("Duyệt sinh viên");
        updateButton.setBounds(25+100+50 + 100+50, 500, 180, 30);
        updateButton.setForeground(new Color(255, 255, 255));
        updateButton.setBackground(new Color(0, 128, 255));
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddListStudentSubject child = new AddListStudentSubject(username, id);
                child.setVisible(true);
                dispose();
            }
        });

        contentPane.add(updateButton);


        JButton templateButton = new JButton("Tải template");
        templateButton.setBounds(25+100+50 + 100+50 + 150+50, 500, 150, 30);
        templateButton.setForeground(new Color(255, 255, 255));
        templateButton.setBackground(new Color(0, 128, 255));
        templateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        templateButton.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                WriteExcel.renderTemplate();
//                AddListStudentSubject child = new AddListStudentSubject(username, id);
//                child.setVisible(true);
//                dispose();
                JOptionPane.showMessageDialog(null,"Tải template xuống thành công tại D:/template.xlsx!");
            }
        });

        contentPane.add(templateButton);

        JButton importButton = new JButton("Tải lên DS");
        importButton.setBounds(25+100+50 + 100+50 + 150+50 + 150 + 25, 500, 140, 30);
        importButton.setForeground(new Color(255, 255, 255));
        importButton.setBackground(new Color(0, 128, 255));
        importButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        importButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
////                AddListStudentSubject child = new AddListStudentSubject(username, id);
////                child.setVisible(true);
////                dispose();
//            }
//        });

        contentPane.add(importButton);


        final JFileChooser fileDialog = new JFileChooser();
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //               int returnVal = fileDialog.showOpenDialog(mainFrame);
//                if (returnVal == JFileChooser.APPROVE_OPTION) {
//                    java.io.File file = fileDialog.getSelectedFile();
//                    statusLabel.setText("File Selected :" + file.getName());
//                } else {
//                    statusLabel.setText("Open command cancelled by user.");
//                }

                File excelFile;
                FileInputStream excelFIS = null;
                BufferedInputStream excelBIS = null;
                XSSFWorkbook excelImportToJTable = null;
                String defaultCurrentDirectoryPath = "C:\\Users\\Authentic\\Desktop";
                JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
                excelFileChooser.setDialogTitle("Select Excel File");

                excelFileChooser.setDialogTitle("Select Excel File");
                FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
                excelFileChooser.setFileFilter(fnef);
                int excelChooser = excelFileChooser.showOpenDialog(null);

                if (excelChooser == JFileChooser.APPROVE_OPTION) {
                    try {
                        String columns[] = {"ID", "Mã số sinh viên", "Tên sinh viên"};
                        DataModel model = new DataModel(columns, 0);
                        excelFile = excelFileChooser.getSelectedFile();
                        excelFIS = new FileInputStream(excelFile);
                        excelBIS = new BufferedInputStream(excelFIS);
                        excelImportToJTable = new XSSFWorkbook(excelBIS);
                        XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);

                        for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                            XSSFRow excelRow = excelSheet.getRow(row);

                            String excelId = String.valueOf(excelRow.getCell(0)).split("\\.")[0];
                            XSSFCell excelMSSV = excelRow.getCell(1);
                            XSSFCell excelName = excelRow.getCell(2);

                            model.addRow(new Object[]{excelId, excelMSSV, excelName});
                            Student student = new Student()
                                    .setMssv(excelMSSV.getStringCellValue())
                                    .setName(excelName.getStringCellValue())
                                    .setPassword(PasswordUtils.passwordEncoder.encode(excelMSSV.getStringCellValue()));

                            studentRepository.save(student);

                            StudentSubject studentSubject = new StudentSubject()
                                    .setSubjectId(id)
                                    .setMssv(excelMSSV.getStringCellValue());
                            studentSubjectRepository.save(studentSubject);
                            JOptionPane.showMessageDialog(null, "Import file sinh viên vào lớp thành công!");
                            StudentSubjectManager subjectManager = new StudentSubjectManager(username, id);
                            subjectManager.setVisible(true);
                            dispose();
                        }
                        //JOptionPane.showMessageDialog(null, "Imported Successfully !!.....");
                    } catch (IOException iOException) {
                        JOptionPane.showMessageDialog(null, iOException.getMessage());
                    } finally {
                        try {
                            if (excelFIS != null) {
                                excelFIS.close();
                            }
                            if (excelBIS != null) {
                                excelBIS.close();
                            }
                            if (excelImportToJTable != null) {
                                excelImportToJTable.close();
                            }
                        } catch (IOException iOException) {
                            JOptionPane.showMessageDialog(null, iOException.getMessage());
                        }
                    }
                }
            }
        });
        contentPane.add(importButton);
    }




    DataModel getDefaultTableModelObj(java.util.List<Student> list) {
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


    private java.util.List<Student> getListObjectInJTable(JTable jt) {
        List<Student> result = new ArrayList<>();
        for (int i = 0; i < jt.getRowCount(); i++) {
            int id = Integer.valueOf(String.valueOf(jt.getValueAt(i, 0)));
            String mssv = String.valueOf(jt.getValueAt(i, 1));
            String name = String.valueOf(jt.getValueAt(i, 2));
            Student entity = new Student()
                    .setMssv(mssv)
                    .setName(name);
            result.add(entity);
        }
        return result;
    }

    public void importExcelToJtableJava() {

        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportToJTable = null;
        String defaultCurrentDirectoryPath = "C:\\Users\\Authentic\\Desktop";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        excelFileChooser.setDialogTitle("Select Excel File");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelImportToJTable = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);

                for (int row = 0; row < excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);

                    XSSFCell excelName = excelRow.getCell(0);
                    XSSFCell excelGender = excelRow.getCell(1);
                    XSSFCell excelProgrammingLanguage = excelRow.getCell(2);
                    XSSFCell excelSubject = excelRow.getCell(3);
                    XSSFCell excelImage = excelRow.getCell(4);

                    JLabel excelJL = new JLabel(new ImageIcon(new ImageIcon(excelImage.getStringCellValue()).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
                    //model.addRow(new Object[]{excelName, excelGender, excelProgrammingLanguage, excelSubject, excelJL});
                }
                JOptionPane.showMessageDialog(null, "Imported Successfully !!.....");
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelImportToJTable != null) {
                        excelImportToJTable.close();
                    }
                } catch (IOException iOException) {
                    JOptionPane.showMessageDialog(null, iOException.getMessage());
                }
            }
        }
    }
}


