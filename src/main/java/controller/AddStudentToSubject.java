//package controller;
//
//import data.DataModel;
//import model.*;
//import org.apache.commons.lang3.ObjectUtils;
//import repository.StudentSubjectRepository;
//import repository.SubjectRepository;
//import service.AcademicStaffService;
//import service.StudentSubjectService;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//    public class AddStudentToSubject extends JFrame {
//
//        private static final long serialVersionUID = 1L;
//        private JPanel contentPane;
//        //private RoomService roomService = new RoomService();
//        private StudentSubjectService studentSubjectService = new StudentSubjectService();
//        private AcademicStaff academicStaff;
//        private AcademicStaffService academicStaffService = new AcademicStaffService();
//        private Subject subject;
//        private SubjectRepository subjectRepository = new SubjectRepository();
//        private StudentSubjectRepository studentSubjectRepository = new StudentSubjectRepository();
//
//        public static void main(String[] args) {
//            EventQueue.invokeLater(new Runnable() {
//                public void run() {
//                    try {
//                        AddStudentToSubject frame = new AddStudentToSubject("admin", 1);
//                        frame.setVisible(true);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//
//        public AddStudentToSubject() {
//
//        }
//
//        public AddStudentToSubject(final String username, int subjectId) {
//            academicStaff = academicStaffService.getByUsername(username);
//            subject = subjectRepository.getBySubjectId(subjectId);
//
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setTitle("Quản lý lớp học");
//            setBounds(450, 190, 1000, 600);
//            setResizable(false);
//            contentPane = new JPanel();
//            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//            setContentPane(contentPane);
//            contentPane.setLayout(null);
//
//            JLabel headerLabel = new JLabel("Mã lớp học: "+ subject.getCode());
//            headerLabel.setBackground(Color.BLACK);
//            headerLabel.setForeground(Color.BLACK);
//            headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
//            headerLabel.setBounds(100, 0, 800, 50);
//            contentPane.add(headerLabel);
//
//            JLabel contentLabel = new JLabel("Danh sách sinh viên hiện tại");
//            contentLabel.setBackground(Color.BLACK);
//            contentLabel.setForeground(Color.BLACK);
//            contentLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            contentLabel.setBounds(100, 50, 900, 50);
//            contentPane.add(contentLabel);
//
//
//            List<Student> students = studentSubjectService.getListStudentBySSID(subjectId);
//            DataModel model = getDefaultTableModelObj(students);
//            JTable jt = new JTable(model);
//            jt.setRowHeight(20);
//            jt.getTableHeader().setBackground(new Color(255, 128, 0));
//            jt.getTableHeader().setForeground(Color.WHITE);
//            jt.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));
//            jt.setBounds(200, 0, 650, 400);
//            JScrollPane sp = new JScrollPane(jt);
//            sp.setBounds(50, 100, 800, 350);
//            contentPane.add(sp);
//
//            jt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
//            JButton deleteButton = new JButton("Xóa");
//            deleteButton.setBounds(150 + 175 + 100 + 175 + 100 + 175, 500, 100, 30);
//            deleteButton.setForeground(new Color(255, 255, 255));
//            deleteButton.setBackground(new Color(0, 128, 255));
//            deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            deleteButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent ae) {
//                    // check for selected row first
//                    if (jt.getSelectedRow() != -1) {
//                        int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sinh viên này?");
//                        if (a == JOptionPane.YES_OPTION) {
//                            studentSubjectRepository.deleteBySubjectIdAndMssv(subjectId,String.valueOf(jt.getValueAt(jt.getSelectedRow(), 0)));
//                            model.removeRow(jt.getSelectedRow());
//                        }
//                    }
//                }
//            });
//
//            contentPane.add(deleteButton);
//
//
//            Button backButton = new Button("Quay lại");
//            backButton.setBounds(50, 500, 100, 30);
//            backButton.setForeground(new Color(255, 255, 255));
//            backButton.setBackground(new Color(0, 128, 255));
//            backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            backButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
////                    AcademicStaffHome academicStaffHome = new AcademicStaffHome(username);
////                    academicStaffHome.setVisible(true);
////                    dispose();
//                }
//            });
//
//            contentPane.add(backButton);
//
//            Button addButton = new Button("Thêm mới");
//            addButton.setBounds(150 + 175, 500, 100, 30);
//            addButton.setForeground(new Color(255, 255, 255));
//            addButton.setBackground(new Color(0, 128, 255));
//            addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            addButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
////                    JTextField text1 = new JTextField(String.valueOf(++lastRoomId));
////                    text1.setFont(new Font("Tahoma", Font.PLAIN, 20));
////                    JTextField text2 = new JTextField();
////                    text2.setFont(new Font("Tahoma", Font.PLAIN, 20));
////                    JTextField text3 = new JTextField();
////                    text3.setFont(new Font("Tahoma", Font.PLAIN, 20));
////
////                    model.addRow(
////                            new Object[]{
////                                    text1.getText(),
////                                    text2.getText(),
////                                    text3.getText()
////                            }
////                    );
////
////                    text1.setText("");
////                    text2.setText("");
////                    text3.setText("");
//                }
//            });
//
//            contentPane.add(addButton);
//
//
//            Button updateButton = new Button("Cập nhật");
//            updateButton.setBounds(150 + 175 + 100 + 175, 500, 100, 30);
//            updateButton.setForeground(new Color(255, 255, 255));
//            updateButton.setBackground(new Color(0, 128, 255));
//            updateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            updateButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
//
//            contentPane.add(updateButton);
//        }
//
//
//        DataModel getDefaultTableModelObj(List<Student> list) {
//            if (ObjectUtils.isEmpty(list)) {
//                String columns[] = {"MSSV", "Tên sinh viên"};
//                DataModel model = new DataModel(columns, 0);
//                return model;
//            }
//            String data[][] = new String[list.size()][];
//            String columns[] = {"MSSV", "Tên sinh viên"};
//            DataModel model = new DataModel(columns, 0);
//
//            for (int i = 0; i < list.size(); i++) {
//                model.addRow(convertObjToStrArr(list.get(i)));
//            }
//
//            return model;
//        }
//
//        String[] convertObjToStrArr(Student obj) {
//            String[] results = new String[2];
//            results[0] = String.valueOf(obj.getMssv());
//            results[1] = String.valueOf(obj.getName());
//            return results;
//        }
//
//
//        private java.util.List<Room> getListRoomInJTable(JTable jt) {
//            List<Room> result = new ArrayList<>();
//            for (int i = 0; i < jt.getRowCount(); i++) {
//                int id = Integer.valueOf(String.valueOf(jt.getValueAt(i, 0)));
//                String code = String.valueOf(jt.getValueAt(i, 1));
//                String name = String.valueOf(jt.getValueAt(i, 2));
//                Room entity = new Room()
//                        .setId(id)
//                        .setCode(code)
//                        .setName(name);
//                result.add(entity);
//            }
//            return result;
//        }
//    }
//
//
