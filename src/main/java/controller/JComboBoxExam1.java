package controller;


import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JComboBoxExam1 {
    JFrame f;

    JComboBoxExam1() {
        f = new JFrame("Vi du combobox - java swing");

        String city[] = { "Ha Noi", "Vinh Phuc", "Da Nang",
                "TP. Ho Chi Minh", "Nha Trang" };

        JComboBox cb = new JComboBox(city);
        cb.setBounds(100, 50, 150, 20);
        f.add(cb);

        JLabel cityLabel = new JLabel("City: ");
        cityLabel.setBounds(50, 50, 80, 20);
        f.add(cityLabel);

        f.setLayout(null);
        f.setSize(400, 300);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new JComboBoxExam1();
    }
}