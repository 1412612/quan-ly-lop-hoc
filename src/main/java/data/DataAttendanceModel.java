package data;

import javax.swing.table.DefaultTableModel;

public class DataAttendanceModel extends DefaultTableModel {

    public DataAttendanceModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public DataAttendanceModel(Object[] column, int count) {
        super(column, count);
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        super.setValueAt(aValue, row, col);
    }

    @Override
    public Class<?> getColumnClass(int col) {
      //  if (col >=2) {
            return getValueAt(0, col).getClass();
       // }
        //return super.getColumnClass(col);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col !=0;
    }
}
