package util;

import dao_tabela_atributos.DaoViagens;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLHelper {

    static public void checkDependencias(int[] check) {
        
        try {
            DaoViagens viagens = new DaoViagens();
            ResultSetMetaData meta = viagens.getResultSet().getMetaData();
           
            int count = meta.getColumnCount();
            
            for (int i = 1; i < count; i++) {
                System.out.println(i);
                System.out.println(meta.getColumnName(i) + " " + check[i-1]);
            }
        } catch (SQLException ex) {
            
        }
        
    }
    
}
