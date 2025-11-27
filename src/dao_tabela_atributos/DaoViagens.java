package dao_tabela_atributos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import tabela_atributos.Viagem;

public class DaoViagens extends DaoBase {

    public DaoViagens() {
        this.tabela = "viagens";
        this.id = "id_viagem";
        this.idIndex = 3;
    }
    
    public void InserirDados(Viagem viagem) throws SQLException{
        String sql = "INSERT INTO viagens " + " (local_de_origem, local_de_destino, id_motorista, id_veiculo, id_evento)" + " VALUES ( ? , ? , ? , ? , ? )"; 
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, viagem.getLocalDeOrigem());
            stmt.setInt(2, viagem.getLocalDeDestino());
            stmt.setInt(3, viagem.getId_Motorista());
            stmt.setString(4, viagem.getId_Veiculo());
            stmt.setInt(5, viagem.getId_Evento());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
            throw e;
        }
    }
    
    public void alterarDados(Viagem viagem) throws SQLException {
        String sql = "UPDATE viagens SET local_de_origem = ?, local_de_destino = ?, id_motorista = ?, id_veiculo = ?, id_evento = ? WHERE id_viagem = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setInt(1, viagem.getLocalDeOrigem());
            stmt.setInt(2, viagem.getLocalDeDestino());
            stmt.setInt(3, viagem.getId_Motorista());
            stmt.setString(4, viagem.getId_Veiculo());
            stmt.setInt(5, viagem.getId_Evento());
            stmt.setInt(6, viagem.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
            throw e;
        }
    }
    
    public Viagem getViagem(int id) {
        String sql = "SELECT * FROM viagens WHERE id_viagem = ?";

        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs_TabelaViagens = stmt.executeQuery();
            Viagem viagem = new Viagem();
            rs_TabelaViagens.first();
            viagem.setId(id);
            viagem.setLocalDeOrigem(rs_TabelaViagens.getInt("local_de_origem"));
            viagem.setLocalDeDestino(rs_TabelaViagens.getInt("local_de_destino"));
            viagem.setId_Motorista(rs_TabelaViagens.getInt("id_motorista"));
            viagem.setId_Veiculo(rs_TabelaViagens.getString("id_veiculo"));
            viagem.setId_Evento(rs_TabelaViagens.getInt("id_evento"));
            return viagem;

        } catch (SQLException e) {
            System.out.println("Id não encontrado" + e.getMessage());
            return null;
        }
    }
    
    public boolean checkDependencias(String[] ids) throws Exception {
        
        String mensagem = "";
        String passageiros = "Conflito com passageiros de id:";
        
        DaoPassageiros dp = new DaoPassageiros();
        ResultSet dpRS = dp.getResultSet();
        int[] dpFK = {8};
        
        try {
            while (dpRS.next()) {
                for (int i = 0; i < dpFK.length; i++) {
                    for (int j = 0; j < ids.length; j++) {
                        if (ids[j].equals(String.valueOf(dpRS.getInt(dpFK[i])))) {
                            if (passageiros.equals("Conflito com passageiros de id:")) {
                                passageiros += " " + String.valueOf(dpRS.getInt(dp.idIndex));
                            } else
                                passageiros += ", " + String.valueOf(dpRS.getInt(dp.idIndex));
                        }
                    }
                } 
            }
        } catch (SQLException ex) {
                System.out.println("Erro: " + ex);
        }
                
        mensagem = passageiros;
        if (mensagem.equals("Conflito com passageiros de id:")) return false;
        
        JOptionPane.showMessageDialog(null, "Os dados que você quer apagar estão vinculados a outros dados no banco de dados,\npor favor remova esse dados em conflito antes de remover o atual:\n" + mensagem, "Conflito", JOptionPane.ERROR_MESSAGE);
        
        return true;
        
    }
}
