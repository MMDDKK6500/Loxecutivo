package dao_tabela_atributos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mysql.Conexao;

public class DaoBase {
    private Conexao conexao;
    private Connection conectar;
    protected String tabela;
    
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
}
