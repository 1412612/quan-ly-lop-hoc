package swingmodel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {
    public void setColumnWidths(int... widths) {
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
            }
            else break;
        }
    }

    public Table(){
        super();
    }

    public Table(DefaultTableModel defaultTableModel){
        super(defaultTableModel);
    }
}
