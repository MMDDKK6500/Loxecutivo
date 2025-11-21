package dao_tabela_atributos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import mysql.Conexao;

public class DaoBase {
    protected Conexao conexao;
    protected Connection conectar;
    protected String tabela;
    protected String id;
    protected int idIndex;
    protected int[] foreignKeysIndex;
    
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
    
    public ResultSet getResultSet(String where) {
        String sql = "SELECT * FROM " + this.tabela + "WHERE " + this.id + " = " + where;
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
    
    public ArrayList<Integer> checkDependency(String[] ids) throws Exception {
        
        ArrayList<String> _out = new ArrayList<>();
        ArrayList<Integer> out = new ArrayList<>();
        
        String[] options = {"Confirmar", "Trocar id de todos", "Cancelar"};

        
        try {
            ResultSet rs = this.getResultSet();
            ResultSetMetaData meta = rs.getMetaData();
            
            while (rs.next()) {
                for (int i = 0; i < ids.length; i++) {
                    System.out.println("Id atual: " + ids[i] + " Id Procurado: " + rs.getObject(this.idIndex));
                    if (ids[i].equals(String.valueOf(rs.getObject(this.idIndex)))) {
                        System.out.println("Conflito!");
                        _out.add(String.valueOf(rs.getObject(this.idIndex)));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
        
        for (String s : _out) {
            out.add(Integer.valueOf(s));
        }
        
        if (out.isEmpty()) {
            return out;
        }
        int confirmacao = JOptionPane.showOptionDialog(null, "Os dados que você quer apagar estão vinculados a outros dados no banco de dados,\na deleção dos dados irão resultar na deleção destes outros dados também, tem certeza que quer fazer isso?\n" + Arrays.toString(ids), "Conflito", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        switch (confirmacao) {
            case 0:
                break;
            case 1:
                String novoId = JOptionPane.showInputDialog(null, "Trocar por qual id?");
                System.out.println(novoId);
                break;
            case 2:
                throw new Exception();
        }
        
        return out;
    }
}
