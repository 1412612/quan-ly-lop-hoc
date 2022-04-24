package controller;

import data.DataModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JFileChooserExam1 {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    String columns[] = {"ID", "Mã số sinh viên", "Tên sinh viên"};

    DataModel model = new DataModel(columns, 0);

    public JFileChooserExam1() {
        prepareGUI();
    }

    public static void main(String[] args) {
        JFileChooserExam1 demo = new JFileChooserExam1();
        demo.showFileChooserDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Vi du JFileChooser trong Java Swing");
        mainFrame.setSize(1000, 800);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);


        JTable jt = new JTable(model);
        jt.setRowHeight(20);
        jt.getTableHeader().setBackground(new Color(255, 128, 0));
        jt.getTableHeader().setForeground(Color.WHITE);
        jt.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));
        jt.setBounds(50, 500, 500, 200);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(50, 500, 500, 200);
        controlPanel.add(sp);
    }

    private void showFileChooserDemo() {
        headerLabel.setText("Control in action: JFileChooser");
        final JFileChooser fileDialog = new JFileChooser();
        JButton showFileDialogButton = new JButton("Open File");
        showFileDialogButton.addActionListener(new ActionListener() {
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
        });
        controlPanel.add(showFileDialogButton);
        mainFrame.setVisible(true);
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
