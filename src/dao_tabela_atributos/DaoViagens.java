package dao_tabela_atributos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tabela_atributos.Viagem;

public class DaoViagens extends DaoBase {

    public DaoViagens() {
        this.tabela = "viagens";
        this.id = "id_viagem";
        this.idIndex = 3;
    }
    
    public void InserirDados(Viagem viagem) {
        String sql = "INSERT INTO viagens " + " (local_de_origem, local_de_destino, id_motorista, id_veiculo, id_evento)" + " VALUES ( ? , ? , ? , ? , ? )"; 
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, viagem.getLocalDeOrigem());
            stmt.setInt(2, viagem.getLocalDeDestino());
            stmt.setInt(3, viagem.getId_Motorista());
            stmt.setInt(4, viagem.getId_Veiculo());
            stmt.setInt(5, viagem.getId_Evento());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
        }
    }
    
    public Viagem getViagem(int id) {
        String sql = "SELECT * FROM viagens WHERE id = ?";

        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs_TabelaViagens = stmt.executeQuery();
            Viagem viagem = new Viagem();
            rs_TabelaViagens.first();
            viagem.setId(id);
            viagem.setLocalDeOrigem(rs_TabelaViagens.getInt("local_de_origem"));
            viagem.setLocalDeDestino(rs_TabelaViagens.getInt("local_de_destino"));
            viagem.setId_Motorista(rs_TabelaViagens.getInt("id_motorista"));
            viagem.setId_Veiculo(rs_TabelaViagens.getInt("id_veiculo"));
            viagem.setId_Evento(rs_TabelaViagens.getInt("id_evento"));
            return viagem;

        } catch (SQLException e) {
            System.out.println("Id não encontrado" + e.getMessage());
            return null;
        }
    }
}
