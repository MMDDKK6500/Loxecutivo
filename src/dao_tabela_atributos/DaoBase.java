package dao_tabela_atributos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mysql.Conexao;

public class DaoBase {
    protected Conexao conexao;
    protected Connection conectar;
    protected String tabela;
    protected String id;
    
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
}
