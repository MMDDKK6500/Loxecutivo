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
    
    public void InserirDados(Motorista evento)throws SQLException {
        String sql = "INSERT INTO eventos (nome, sobrenome, rg, cpf) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, evento.getNome());
            stmt.setString(2, evento.getSobrenome());
            stmt.setString(3, evento.getRG());
            stmt.setString(4, evento.getCPF());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
        }
    }

    public void alterarDados(Motorista motorista) throws SQLException {
        String sql = "UPDATE motoristas SET nome = ?, sobrenome = ?, rg = ?, cpf = ? WHERE id_motorista = ?";
        PreparedStatement stmt = this.conectar.prepareStatement(sql);
        stmt.setString(1, motorista.getNome());
        stmt.setString(2, motorista.getSobrenome());
        stmt.setString(3, motorista.getRG());
        stmt.setString(4, motorista.getCPF());
        stmt.setInt(5, motorista.getId_Motorista());
        stmt.execute();
        stmt.close();
    }
    
    public Motorista getMotorista(int id) {
        String sql = "SELECT * FROM motoristas WHERE id_motorista = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Motorista evento = new Motorista();
            rs.first();
            evento.setId_Motorista(id);
            evento.setNome(rs.getString("nome"));
            evento.setSobrenome(rs.getString("sobrenome"));
            evento.setRG(rs.getString("rg"));
            evento.setCPF(rs.getString("cpf"));
            return evento;
        } catch (SQLException e) {
            System.out.println("Id não encontrado" + e.getMessage());
            return null;
        }
    }
}
