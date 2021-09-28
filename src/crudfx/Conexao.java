package crudfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*Essa classe é responsável por armazenar as credenciais de conexao com o sqlserver e
  conectar/desconectar do banco de dados.
*/
public class Conexao {
    
    
    /*  
    Essa variável armazena as informações 
    necessárias para realizar a conexão com o sqlserver.
    */      
    private final static String URL = 
            "jdbc:sqlserver://CID;instanceName=SQLEXPRESS;databaseName=CRUD;integratedSecurity=true";
    
    // Criado o objeto de conexão com o sqlserver.
    public static Connection conexao;
    
    //Esse método conecta no banco de dados.
    public static Connection Conectar(){
        
        //Essa estrutura tenta conectar no banco de dados.
        try {
            conexao = DriverManager.getConnection(URL);
        } catch (SQLException e){
        }
        return conexao;
    }
    
    //Essa método fecha a conexao com o banco de dados
    public static void Desconectar(){
        
        //Essa estrutura tenta fechar a conexão.
        try{
            conexao.close();
        } catch (SQLException e) {
        }
        
    }
}
