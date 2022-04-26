package controller;

import swingmodel.DateLabelFormatter;
import swingmodel.JHourMinuteChooser;
import model.Room;
import model.Subject;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import repository.SubjectRepository;
import service.RoomService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class AddSubject extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private RoomService roomService = new RoomService();
    private SubjectRepository subjectRepository = new SubjectRepository();
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddSubject frame = new AddSubject("admin");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AddSubject(String username) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Thêm môn học mới");
        setBounds(250, 150, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //header
        JLabel headerLabel = new JLabel("Thêm môn học mới");
        headerLabel.setBackground(Color.BLACK);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        headerLabel.setBounds(30, 30, 500, 50);
        contentPane.add(headerLabel);

//        JLabel idLabel = new JLabel("ID");
//        idLabel.setBackground(Color.BLACK);
//        idLabel.setForeground(Color.BLACK);
//        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        idLabel.setBounds(30, 80, 200, 30);
//        contentPane.add(idLabel);
//
//        JTextField idTextField = new JTextField();
//        idTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        idTextField.setBounds(250, 80, 200, 30);
//        contentPane.add(idTextField);



        JLabel codeLabel = new JLabel("MÃ môn học");
        codeLabel.setBackground(Color.BLACK);
        codeLabel.setForeground(Color.BLACK);
        codeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        codeLabel.setBounds(30, 130, 200, 30);
        contentPane.add(codeLabel);

        JTextField codeTextField = new JTextField();
        codeTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        codeTextField.setBounds(250, 130, 200, 30);
        contentPane.add(codeTextField);
        codeTextField.setColumns(10);

        JLabel nameLabel = new JLabel("TÊN môn học");
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameLabel.setBounds(30, 180, 200, 30);
        contentPane.add(nameLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameTextField.setBounds(250, 180, 200, 30);
        contentPane.add(nameTextField);
        nameTextField.setColumns(10);


        JLabel dayOfWeekLabel = new JLabel("NGÀY TRONG TUẦN");
        dayOfWeekLabel.setBackground(Color.BLACK);
        dayOfWeekLabel.setForeground(Color.BLACK);
        dayOfWeekLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        dayOfWeekLabel.setBounds(30, 230, 200, 30);
        contentPane.add(dayOfWeekLabel);

        String dayOfWeek[] = { "Chọn ngày", "Thứ hai", "Thứ ba",
                "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy" };

        JComboBox dayOfWeekJCB = new JComboBox(dayOfWeek);
        dayOfWeekJCB.setBounds(250, 230, 200, 20);
        contentPane.add(dayOfWeekJCB);

        JLabel roomLabel = new JLabel("MÃ PHÒNG HỌC");
        roomLabel.setBackground(Color.BLACK);
        roomLabel.setForeground(Color.BLACK);
        roomLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        roomLabel.setBounds(30, 280, 200, 30);
        contentPane.add(roomLabel);

        String codeRoomStr[] = getCodeRooms();

        JComboBox codeRoomJCB = new JComboBox(codeRoomStr);
        codeRoomJCB.setBounds(250, 280, 200, 20);
        contentPane.add(codeRoomJCB);





        JLabel dateStartLabel = new JLabel("NGÀY BẮT ĐẦU");
        dateStartLabel.setBackground(Color.BLACK);
        dateStartLabel.setForeground(Color.BLACK);
        dateStartLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        dateStartLabel.setBounds(500, 130, 200, 30);
        contentPane.add(dateStartLabel);

        JDatePickerImpl startDatePicker;
        UtilDateModel startModel = new UtilDateModel();
        Calendar startCalendar = Calendar.getInstance();
        startModel.setDate(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH));
        startModel.setSelected(true);
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel);
        startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
        startDatePicker.setBounds(700, 130,200,30);
        contentPane.add(startDatePicker);

//        JLabel dateEndLabel = new JLabel("NGÀY KẾT THÚC");
//        dateEndLabel.setBackground(Color.BLACK);
//        dateEndLabel.setForeground(Color.BLACK);
//        dateEndLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        dateEndLabel.setBounds(500, 180, 200, 30);
//        contentPane.add(dateEndLabel);
//
//        JDatePickerImpl endDatePicker;
//        UtilDateModel endModel = new UtilDateModel();
//        Calendar endCalendar = Calendar.getInstance();
//        endModel.setDate(endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH));
//        endModel.setSelected(true);
//        JDatePanelImpl endDatePanel = new JDatePanelImpl(endModel);
//        endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
//        endDatePicker.setBounds(700, 180,200,30);
//        contentPane.add(endDatePicker);

        JLabel timeStartLabel = new JLabel("THỜI GIAN BẮT ĐẦU");
        timeStartLabel.setBackground(Color.BLACK);
        timeStartLabel.setForeground(Color.BLACK);
        timeStartLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        timeStartLabel.setBounds(500, 180, 200, 30);
        contentPane.add(timeStartLabel);

        JHourMinuteChooser timeStartChooser = new JHourMinuteChooser(0, 0);
        timeStartChooser.setFont(new Font("Tahoma", Font.PLAIN, 20));
        timeStartChooser.setBounds(700, 180, 200, 30);
        contentPane.add(timeStartChooser);

        JLabel timeEndLabel = new JLabel("THỜI GIAN KẾT THÚC");
        timeEndLabel.setBackground(Color.BLACK);
        timeEndLabel.setForeground(Color.BLACK);
        timeEndLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        timeEndLabel.setBounds(500, 230, 200, 30);
        contentPane.add(timeEndLabel);

        JHourMinuteChooser timeEndChooser = new JHourMinuteChooser(0, 0);
        timeEndChooser.setFont(new Font("Tahoma", Font.PLAIN, 20));
        timeEndChooser.setBounds(700, 230, 200, 30);
        contentPane.add(timeEndChooser);


        JButton backButton = new JButton("Quay lại");
        backButton.setBounds(50, 500, 120, 30);
        backButton.setForeground(new Color(255, 255, 255));
        backButton.setBackground(new Color(0, 128, 255));
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 SubjectManager subjectManager = new SubjectManager(username);
                subjectManager.setVisible(true);
                dispose();
            }
        });

        contentPane.add(backButton);

        JButton addButton = new JButton("Tạo mới");
        addButton.setBounds(150 + 175, 500, 120, 30);
        addButton.setForeground(new Color(255, 255, 255));
        addButton.setBackground(new Color(0, 128, 255));
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codeSubject = codeTextField.getText();
                if(codeSubject.equals("")){
                    JOptionPane.showMessageDialog(null, "Mã môn học không được trống!");
                    return;
                }
                String nameSubject = nameTextField.getText();
                if(nameSubject.equals("")){
                    JOptionPane.showMessageDialog(null, "Tên môn học không được trống!");
                    return;
                }
                String dayOfWeekSubject = (String)dayOfWeekJCB.getSelectedItem();
                if(dayOfWeekSubject.equals("Chọn ngày")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn ngày trong tuần!");
                    return;
                }
                String roomCodeSubject = (String)codeRoomJCB.getSelectedItem();
                System.out.println(roomCodeSubject);
                if(roomCodeSubject.equals("Chọn mã phòng")){
                    JOptionPane.showMessageDialog(null, "Hãy chọn phòng học!");
                    return;
                }
                Date dateStart = (Date) startDatePicker.getModel().getValue();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateStart);
                calendar.add(Calendar.WEEK_OF_YEAR, 15);
                Date dateEnd = calendar.getTime();//(Date) endDatePicker.getModel().getValue();
                String timeStart = timeStartChooser.getTime();
                String timeEnd = timeEndChooser.getTime();
                Subject subject = new Subject()
                        .setCode(codeSubject)
                        .setName(nameSubject)
                        .setDayOfWeek(dayOfWeekSubject)
                        .setRoom(roomService.getByCode(roomCodeSubject))
                        .setDateStart(dateStart)
                        .setDateEnd(dateEnd)
                        .setTimeStart(timeStart)
                        .setTimeEnd(timeEnd);
                subjectRepository.save(subject);
                JOptionPane.showMessageDialog(null, "Thêm môn học thành công!");
                SubjectManager subjectManager = new SubjectManager(username);
                subjectManager.setVisible(true);
                dispose();
            }
        });

        contentPane.add(addButton);
    }

    String[] getCodeRooms(){
        List<String> collect = Optional.ofNullable(roomService.getAllRoom()).orElse(new ArrayList<>()).stream().map(Room::getCode).collect(Collectors.toList());
        collect.add(0, "Chọn mã phòng");
        return collect.toArray(new String[0]);
    }
}

