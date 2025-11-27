package dao_tabela_atributos;

import tabela_atributos.Veiculo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DaoVeiculos extends DaoBase {

    public DaoVeiculos() {
        this.tabela = "veiculos";
        this.id = "id_placa";
        this.idIndex = 1;
    }
    
    public void InserirDados(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculos (id_placa, modelo, ano, marca, cor) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, veiculo.getId_Placa());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setString(4, veiculo.getMarca());
            stmt.setString(5, veiculo.getCor());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
            throw e;
        }
    }

    public void alterarDados(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE veiculos SET id_placa = ?, modelo = ?, ano = ?, marca = ?, cor = ? WHERE id_placa = ?)";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, veiculo.getId_Placa());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setString(4, veiculo.getMarca());
            stmt.setString(5, veiculo.getCor());
            stmt.setString(6, veiculo.getId_Placa());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
            throw e;
        }
    }
    
    public Veiculo getVeiculo(String id) {
        String sql = "SELECT * FROM veiculos WHERE id_placa = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, id);
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
    
    public boolean removeByID(String id) {
        String sql = "DELETE FROM " + this.tabela + " WHERE " + this.id + " = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar da tabela: " + e.getMessage());
            return false;
        }
    }
    
    public boolean checkDependencias(String[] ids) throws Exception {
        
        String mensagem = "";
        String viagens = "Conflito com viagens de id:";
        
        DaoViagens dvi = new DaoViagens();
        ResultSet dviRS = dvi.getResultSet();
        int[] dviFK = {5};
        
        try {
            while (dviRS.next()) {
                for (int i = 0; i < dviFK.length; i++) {
                    for (int j = 0; j < ids.length; j++) {
                        if (ids[j].equals(String.valueOf(dviRS.getString(dviFK[i])))) {
                            if (viagens.equals("Conflito com viagens de id:")) {
                                viagens += " " + String.valueOf(dviRS.getInt(dvi.idIndex));
                            } else 
                                viagens += ", " + String.valueOf(dviRS.getString(dvi.idIndex));
                        }
                    }
                } 
            }
        } catch (SQLException ex) {
                System.out.println("Erro: " + ex);
        }
                
        mensagem = viagens;
        if (mensagem.equals("Conflito com viagens de id:")) return false;
        
        JOptionPane.showMessageDialog(null, "Os dados que você quer apagar estão vinculados a outros dados no banco de dados,\npor favor remova esse dados em conflito antes de remover o atual:\n" + mensagem, "Conflito", JOptionPane.ERROR_MESSAGE);
        
        return true;
        
    }
    
}
