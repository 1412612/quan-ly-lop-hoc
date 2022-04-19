package data;

import javax.swing.table.DefaultTableModel;

public class DataModel extends DefaultTableModel {

    public DataModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public DataModel(Object[] column, int count) {
        super(column, count);
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        super.setValueAt(aValue, row, col);
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 1) {
            return getValueAt(0, 1).getClass();
        }
        return super.getColumnClass(col);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col !=0;
    }
}