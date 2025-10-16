package dao_tabela_atributos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mysql.Conexao;
import tabela_atributos.Passageiro;
import tabela_atributos.Viagem;


//TODO: get
public class DaoViagens {
    private Conexao conexao;
    private Connection conectar;
    
    //CRIAÇÃO DO CONSTRUTOR 
    public DaoViagens() {
        this.conexao = new Conexao();
        this.conectar = this.conexao.getConexao();
    }
    
    public void InserirDados(Viagem viagem) {
        String sql = "INSERT INTO viagens " + " (local_de_origem, local_de_destino, id_motorista, id_veiculo, id_evento)" + " VALUES ( ? , ? , ? , ? , ? )"; 
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, viagem.getLocalDeOrigem());
            stmt.setInt(2, viagem.getLocalDeDestino());
            //TODO: Resto dos sets
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
            ResultSet rs_TabelaPassageiros = stmt.executeQuery(); //Retorna a tabela SQL e armazena em rs_TabelaCursos
            Passageiro viagem = new Passageiro();
            rs_TabelaPassageiros.first();
            viagem.setId(id);
            viagem.setNome(rs_TabelaPassageiros.getString("nome"));
            viagem.setSobrenome(rs_TabelaPassageiros.getString("sobrenome"));
            viagem.setNumero(rs_TabelaPassageiros.getString("numero"));
            viagem.setEmpresa(rs_TabelaPassageiros.getString("empresa"));
            viagem.setRG(rs_TabelaPassageiros.getString("rg"));
            viagem.setCPF(rs_TabelaPassageiros.getString("cpf"));
            viagem.setId_Viagem(rs_TabelaPassageiros.getInt("id_viagem"));
            return viagem;

        } catch (SQLException e) {
            System.out.println("Id não encontrado" + e.getMessage());
            return null;
        }
    }
}
