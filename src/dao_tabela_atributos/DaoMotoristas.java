package dao_tabela_atributos;
import java.sql.Connection;
import mysql.Conexao;
import tabela_atributos.Motorista;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DaoMotoristas {
    private Conexao conexao;
    private Connection conectar;
    
    public DaoMotoristas() {
        this.conexao = new Conexao();
        this.conectar = this.conexao.getConexao();
    }
    
    public void InserirDados(Motorista motorista) {
        String sql = "INSERT INTO motoristas (nome, sobrenome, rg, cpf) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getSobrenome());
            stmt.setString(3, motorista.getRG());
            stmt.setString(4, motorista.getCPF());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
        }
    }
    
    public Motorista getMotorista(int id) {
        String sql = "SELECT * FROM motoristas WHERE id_motorista = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Motorista motorista = new Motorista();
            rs.first();
            motorista.setId_Motorista(id);
            motorista.setNome(rs.getString("nome"));
            motorista.setSobrenome(rs.getString("sobrenome"));
            motorista.setRG(rs.getString("rg"));
            motorista.setCPF(rs.getString("cpf"));
            return motorista;
        } catch (SQLException e) {
            System.out.println("Id não encontrado" + e.getMessage());
            return null;
        }
    }
}
