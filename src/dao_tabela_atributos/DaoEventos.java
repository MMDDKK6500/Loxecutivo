package dao_tabela_atributos;

import tabela_atributos.Evento;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DaoEventos extends DaoBase {

    public DaoEventos() {
        this.tabela = "eventos";
        this.id = "id_evento";
    }
    
    public void InserirDados(Evento evento) {
        String sql = "INSERT INTO eventos (nome, id_endereco) VALUES (?, ?)";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, evento.getNome());
            stmt.setInt(2, evento.getId_Endereco());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
        }
    }
    
    public Evento getEvento(int id) {
        String sql = "SELECT * FROM eventos WHERE id_evento = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Evento evento = new Evento();
            rs.first();
            evento.setId_Evento(id);
            evento.setNome(rs.getString("nome"));
            evento.setId_Endereco(rs.getInt("id_endereco"));
            return evento;
        } catch (SQLException e) {
            System.out.println("Id não encontrado" + e.getMessage());
            return null;
        }
    }
}
