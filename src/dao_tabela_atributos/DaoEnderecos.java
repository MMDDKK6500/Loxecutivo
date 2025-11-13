package dao_tabela_atributos;

import tabela_atributos.Endereco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DaoEnderecos extends DaoBase {

    public DaoEnderecos() {
        this.tabela = "enderecos";
        this.id = "id_endereco";
    }
    
    public void InserirDados(Endereco endereco) {
        String sql = "INSERT INTO enderecos (rua, numero, bairro, cidade, uf) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, endereco.getRua());
            stmt.setInt(2, endereco.getNumero());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getUf());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
        }
    }
    
    public Endereco getEndereco(int id) {
        String sql = "SELECT * FROM enderecos WHERE id_endereco = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Endereco endereco = new Endereco();
            rs.first();
            endereco.setId_Endereco(id);
            endereco.setRua(rs.getString("rua"));
            endereco.setNumero(rs.getInt("numero"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setUf(rs.getString("uf"));
            return endereco;
        } catch (SQLException e) {
            System.out.println("Id não encontrado" + e.getMessage());
            return null;
        }
    }
}
