package util;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class TableHelper {
    public static DefaultTableModel modelFromRS(ResultSet rs) {
        DefaultTableModel model = new DefaultTableModel();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();  
            for (int i = 1; i <= columnCount; i++) {  
                model.addColumn(metaData.getColumnName(i));
            }
            while (rs.next()) {  
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {  
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }
            
        } catch(SQLException e) {
            
        }
        return model;
    }
}
