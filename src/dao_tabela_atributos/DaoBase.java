package dao_tabela_atributos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import mysql.Conexao;

public class DaoBase {
    protected Conexao conexao;
    protected Connection conectar;
    protected String tabela;
    protected String id;
    protected int idIndex;
    
    public DaoBase() {
        this.conexao = new Conexao();
        this.conectar = this.conexao.getConexao();
    }
    
    public ResultSet getResultSet() {
        String sql = "SELECT * FROM " + this.tabela;
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Tabela não encontrado" + e.getMessage());
            return null;
        }
    }
    
    public boolean removeByID(int id) {
        String sql = "DELETE FROM " + this.tabela + " WHERE " + this.id + " = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar da tabela: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Integer> checkDependency(String[] ids) {
        
        ArrayList<String> _out = new ArrayList<>();
        ArrayList<Integer> out = new ArrayList<>();

        
        try {
            ResultSet rs = this.getResultSet();
            ResultSetMetaData meta = rs.getMetaData();
           
            int columnCount = meta.getColumnCount();
            
            while (rs.next()) {
                for (int i = 1; i < columnCount; i++) {
                    for (int j = 0; j < ids.length; j++) {
                        System.out.println("coluna: " + this.idIndex + " id: " + j);
                        if (ids[j] == rs.getObject(this.idIndex)) {
                            System.out.println("AA");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
        
        for (String s : _out) {
            out.add(Integer.valueOf(s));
        }
        
        return out;
    }
}
