package dao_tabela_atributos;

import tabela_atributos.Passageiro;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DaoPassageiros extends DaoBase {

    public DaoPassageiros() {
        this.tabela = "passageiros";
        this.id = "id_passageiro";
        this.idIndex = 4;
    }
    
    public void InserirDados(Passageiro passageiro) throws SQLException {
        String sql = "INSERT INTO passageiros " + " (nome, sobrenome, numero, empresa, rg, cpf, id_viagem)" + " VALUES ( ? , ? , ? , ? , ? , ? , ? )"; 
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, passageiro.getNome());
            stmt.setString(2, passageiro.getSobrenome());
            stmt.setString(3, passageiro.getNumero());
            stmt.setString(4, passageiro.getEmpresa());
            stmt.setString(5, passageiro.getRG());
            stmt.setString(6, passageiro.getCPF());
            stmt.setInt(7, passageiro.getId_Viagem());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
        }
    }
    
    public Passageiro getPassageiro(int id) {
        String sql = "SELECT * FROM passageiros WHERE id = ?";

        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs_TabelaPassageiros = stmt.executeQuery();
            Passageiro passageiros = new Passageiro();
            rs_TabelaPassageiros.first();
            passageiros.setId(id);
            passageiros.setNome(rs_TabelaPassageiros.getString("nome"));
            passageiros.setSobrenome(rs_TabelaPassageiros.getString("sobrenome"));
            passageiros.setNumero(rs_TabelaPassageiros.getString("numero"));
            passageiros.setEmpresa(rs_TabelaPassageiros.getString("empresa"));
            passageiros.setRG(rs_TabelaPassageiros.getString("rg"));
            passageiros.setCPF(rs_TabelaPassageiros.getString("cpf"));
            passageiros.setId_Viagem(rs_TabelaPassageiros.getInt("id_viagem"));
            return passageiros;

        } catch (SQLException e) {
            System.out.println("Id não encontrado" + e.getMessage());
            return null;
        }
    }

}