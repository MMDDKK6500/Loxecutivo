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
    
    public void InserirDados(Endereco endereco) throws SQLException{
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
    
    
    public void checkDependencias(String[] ids) throws Exception {
        
        String[] options = {"Confirmar", "Trocar id de todos", "Cancelar"};
        
        DaoViagens dvi = new DaoViagens();
        ResultSet dviRS = dvi.getResultSet();
        int[] dviFK = {1, 2};
        
        try {
            while (dviRS.next()) {
                for (int i = 0; i < dviFK.length; i++) {
                    for (int j = 0; j < ids.length; j++) {
                        if (ids[j].equals(String.valueOf(dviRS.getInt(dviFK[i])))) {
                            System.out.println("Conflito com viagens  de id: " + dviRS.getInt(dvi.idIndex));
                        }
                    }
                } 
            }
        } catch (SQLException ex) {
                System.out.println("Erro: " + ex);
        }
        
        int confirmacao = JOptionPane.showOptionDialog(null, "Os dados que você quer apagar estão vinculados a outros dados no banco de dados,\na deleção dos dados irão resultar na deleção destes outros dados também, tem certeza que quer fazer isso?\n" + Arrays.toString(ids), "Conflito", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        switch (confirmacao) {
            case 0:
                break;
            case 1:
                String novoId = JOptionPane.showInputDialog(null, "Trocar por qual id?");
                System.out.println(novoId);
                break;
            case 2:
                throw new Exception();
        }
        
        DaoEventos dve = new DaoEventos();
        ResultSet dveRS = dve.getResultSet();
        int[] dveFK = {3};
        
        try {
            while (dveRS.next()) {
                for (int i = 0; i < dveFK.length; i++) {
                    for (int j = 0; j < ids.length; j++) {
                        if (ids[j].equals(String.valueOf(dveRS.getInt(dveFK[i])))) {
                            System.out.println("Conflito com eventos de id: " + dveRS.getInt(dve.idIndex));
                        }
                    }
                } 
            }
        } catch (SQLException ex) {
                System.out.println("Erro: " + ex);
        }
        
        int confirmacao2 = JOptionPane.showOptionDialog(null, "Os dados que você quer apagar estão vinculados a outros dados no banco de dados,\na deleção dos dados irão resultar na deleção destes outros dados também, tem certeza que quer fazer isso?\n" + Arrays.toString(ids), "Conflito", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        switch (confirmacao2) {
            case 0:
                break;
            case 1:
                String novoId = JOptionPane.showInputDialog(null, "Trocar por qual id?");
                System.out.println(novoId);
                break;
            case 2:
                throw new Exception();
        }
        
        //TODO: Fazer resto do sistema
        
        dvi.getResultSet();
        
    }
}
