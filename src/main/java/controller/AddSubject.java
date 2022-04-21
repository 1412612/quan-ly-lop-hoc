package controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSubject extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

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

    public AddSubject() {

    }

    /**
     * Create the frame.
     */
    public AddSubject(final String userSes) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Thêm môn học mới");
        setBounds(450, 190, 1000, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //header
        JLabel headerLabel = new JLabel("Thêm lớp học mới");
        headerLabel.setBackground(Color.BLACK);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        headerLabel.setBounds(30, 30, 500, 50);
        contentPane.add(headerLabel);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBackground(Color.BLACK);
        idLabel.setForeground(Color.BLACK);
        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        idLabel.setBounds(30, 80, 100, 50);
        contentPane.add(idLabel);

        JTextField textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);
    }
}

