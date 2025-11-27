package dao_tabela_atributos;

import tabela_atributos.Endereco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class DaoEnderecos extends DaoBase {

    public DaoEnderecos() {
        this.tabela = "enderecos";
        this.id = "id_endereco";
        this.idIndex = 6;
    }
    
    public void InserirDados(Endereco endereco) throws SQLException {
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
            throw e;
        }
    }
    
    public void alterarDados(Endereco endereco) throws SQLException {
        String sql = "UPDATE enderecos SET rua = ?, numero = ?, bairro = ?, cidade = ?, uf = ? WHERE id_endereco = ?";
        PreparedStatement stmt = this.conectar.prepareStatement(sql);
        stmt.setString(1, endereco.getRua());
        stmt.setInt(2, endereco.getNumero());
        stmt.setString(3, endereco.getBairro());
        stmt.setString(4, endereco.getCidade());
        stmt.setString(5, endereco.getUf());
        stmt.setInt(6, endereco.getId_Endereco());
        stmt.execute();
        stmt.close();
    }
    
    public Endereco getEndereco(int id) {
        String sql = "SELECT * FROM enderecos WHERE id_endereco = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
    
    
    public boolean checkDependencias(String[] ids) throws Exception {
        
        String mensagem = "";
        String viagens = "Conflito com viagens de id:";
        String eventos = "Conflito com eventos de id:";
        
        DaoViagens dvi = new DaoViagens();
        ResultSet dviRS = dvi.getResultSet();
        int[] dviFK = {1, 2};
        
        try {
            while (dviRS.next()) {
                for (int i = 0; i < dviFK.length; i++) {
                    for (int j = 0; j < ids.length; j++) {
                        if (ids[j].equals(String.valueOf(dviRS.getInt(dviFK[i])))) {
                            if (viagens.equals("Conflito com viagens de id:")) {
                                viagens += " " + String.valueOf(dviRS.getInt(dvi.idIndex));
                            } else
                                viagens += ", " + String.valueOf(dviRS.getInt(dvi.idIndex));
                        }
                    }
                } 
            }
        } catch (SQLException ex) {
                System.out.println("Erro: " + ex);
        }
                
        DaoEventos dve = new DaoEventos();
        ResultSet dveRS = dve.getResultSet();
        int[] dveFK = {3};
        
        try {
            while (dveRS.next()) {
                for (int i = 0; i < dveFK.length; i++) {
                    for (int j = 0; j < ids.length; j++) {
                        if (ids[j].equals(String.valueOf(dveRS.getInt(dveFK[i])))) {
                            if (eventos.equals("Conflito com eventos de id:")) {
                                eventos += " " + String.valueOf(dveRS.getInt(dve.idIndex));
                            } else
                                eventos += ", " + String.valueOf(dveRS.getInt(dve.idIndex));
                        }
                    }
                } 
            }
        } catch (SQLException ex) {
                System.out.println("Erro: " + ex);
        }
        
        if (viagens.equals("Conflito com viagens de id:") && eventos.equals("Conflito com eventos de id:")) {
           return false;
        }  else if (viagens.equals("Conflito com viagens de id:")) {
            mensagem = eventos;
        } else if (eventos.equals("Conflito com eventos de id:")) {
            mensagem = viagens;
        } else {
            mensagem = viagens + "\n" + eventos;
        }
        
        JOptionPane.showMessageDialog(null, "Os dados que você quer apagar estão vinculados a outros dados no banco de dados,\npor favor remova esse dados em conflito antes de remover o atual:\n" + mensagem, "Conflito", JOptionPane.ERROR_MESSAGE);
        return true;
    }
}
