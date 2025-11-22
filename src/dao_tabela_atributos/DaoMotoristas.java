package dao_tabela_atributos;

import tabela_atributos.Motorista;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DaoMotoristas extends DaoBase  {

    public DaoMotoristas() {
        this.tabela = "motoristas";
        this.id = "id_motorista";
        this.idIndex = 5;
    }
    
    public void InserirDados(Motorista motorista)throws SQLException {
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
