package mysql;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {

  public Connection getConexao() {
      
    try {
      String endereco = "jdbc:mysql://144.22.229.211:3306/loxecutivo";
      String usuario = "aula";
      String senha = "3654";
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conectar = DriverManager.getConnection(endereco, usuario, senha);
      
      return conectar;
      
    } catch (Exception e) {
      System.out.println("Erro ao tentar conectar-se: " + e);
    }
    
    return null;

  }

}