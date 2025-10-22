package dao_tabela_atributos;
import java.sql.Connection;
import mysql.Conexao;
import tabela_atributos.Veiculo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DaoVeiculos {
    private Conexao conexao;
    private Connection conectar;
    
    public DaoVeiculos() {
        this.conexao = new Conexao();
        this.conectar = this.conexao.getConexao();
    }
    
    public void InserirDados(Veiculo veiculo) {
        String sql = "INSERT INTO veiculos (modelo, ano, marca, cor) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, veiculo.getModelo());
            stmt.setInt(2, veiculo.getAno());
            stmt.setString(3, veiculo.getMarca());
            stmt.setString(4, veiculo.getCor());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
        }
    }
    
    public Veiculo getVeiculo(int id) {
        String sql = "SELECT * FROM veiculos WHERE id_placa = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Veiculo veiculo = new Veiculo();
            rs.first();
            veiculo.setId_Placa(id);
            veiculo.setModelo(rs.getString("modelo"));
            veiculo.setAno(rs.getInt("ano"));
            veiculo.setMarca(rs.getString("marca"));
            veiculo.setCor(rs.getString("cor"));
            return veiculo;
        } catch (SQLException e) {
            System.out.println("Id não encontrado" + e.getMessage());
            return null;
        }
    }
}
