package dao_tabela_atributos;

import tabela_atributos.Evento;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DaoEventos extends DaoBase {

    public DaoEventos() {
        this.tabela = "eventos";
        this.id = "id_evento";
        this.idIndex = 2;
    }
    
    public void InserirDados(Evento evento) throws SQLException  {
        String sql = "INSERT INTO eventos (nome, id_endereco) VALUES (?, ?)";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, evento.getNome());
            stmt.setInt(2, evento.getId_Endereco());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no BD_MySQL" + e.getMessage());
            throw e;
        }
    }

    public void alterarDados(Evento evento) throws SQLException {
        String sql = "UPDATE eventos SET nome = ?, id_endereco = ? WHERE id_evento = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql);
            stmt.setString(1, evento.getNome());
            stmt.setInt(2, evento.getId_Endereco());
            stmt.setInt(3, evento.getId_Evento());
            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            throw e;
        }
    }
    
    public Evento getEvento(int id) {
        String sql = "SELECT * FROM eventos WHERE id_evento = ?";
        try {
            PreparedStatement stmt = this.conectar.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Evento evento = new Evento();
            rs.first();
            evento.setId_Evento(id);
            evento.setNome(rs.getString("nome"));
            evento.setId_Endereco(rs.getInt("id_evento"));
            return evento;
        } catch (SQLException e) {
            System.out.println("Id não encontrado" + e.getMessage());
            return null;
        }
    }
    
    public boolean checkDependencias(String[] ids) throws Exception {
        
        String mensagem = "";
        String viagens = "Conflito com viagens de id:";
        
        DaoViagens dvi = new DaoViagens();
        ResultSet dviRS = dvi.getResultSet();
        int[] dviFK = {6};
        
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
        
        mensagem = viagens;
        if (mensagem.equals("Conflito com viagens de id:")) return false;
        
        JOptionPane.showMessageDialog(null, "Os dados que você quer apagar estão vinculados a outros dados no banco de dados,\npor favor remova esse dados em conflito antes de remover o atual:\n" + mensagem, "Conflito", JOptionPane.ERROR_MESSAGE);
        
        return true;
        
    }
}
