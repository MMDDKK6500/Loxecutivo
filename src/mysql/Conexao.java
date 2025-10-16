package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
/** 
 * 
 * @author Ozias G. Santos 
 */
public class Conexao {

  public Connection getConexao() {
      
    try {
      String endereco = "jdbc:mysql://127.0.0.1:3306/Loxecutivo";
      String usuario = "root";
      String senha = "";
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conectar = DriverManager.getConnection(endereco, usuario, senha);
      
      return conectar;
      
    } catch (Exception e) {
      System.out.println("Erro ao tentar conectar-se: " + e);
    }
    
    return null;

  }

}