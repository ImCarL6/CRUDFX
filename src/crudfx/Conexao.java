
package crudfx;

import java.sql.*;


/*Essa classe é responsável por armazenar as credenciais de conexao com o sqlserver e
  conectar/desconectar do banco de dados.
*/
public class Conexao {
    
    
    /*  
    Essa variável armazena as informações 
    necessárias para realizar a conexão com o sqlserver.
    */      
    private final static String connURL = 
            "jdbc:sqlserver://CID;instanceName=SQLEXPRESS;databaseName=CRUD;integratedSecurity=true";
    
    // Criado o objeto de conexão com o sqlserver.
    public static Connection conexao;
    
    //Esse método conecta no banco de dados.
    public static Connection conectar(){
        
        //Essa estrutura tenta conectar no banco de dados.
        try {
            conexao = DriverManager.getConnection(connURL);
        } catch (SQLException e){
        }
        return conexao;
    }
    
    //Essa método fecha a conexao com o banco de dados
    public static void desconectar(){
        
        //Essa estrutura tenta fechar a conexão.
        try{
            conexao.close();
        } catch (SQLException e) {
        }
        
    }
}
