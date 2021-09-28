package crudfx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ClienteDAO {
    
    public String Salvar(Cliente c){
        
        String erro = "";
        
        try {
            
            Connection conexao = Conexao.Conectar();
            
            if (c.getCodigo() == 0){
            
                String sql = "INSERT INTO cliente (nome, cpf, nascimento) VALUES (?, ?, ?) ";

                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, c.getNome());
                stmt.setString(2, c.getCpf());
                stmt.setString(3, c.getNascimento());
                stmt.execute();

            } else {
                
                String sql = "UPDATE cliente SET nome = ?, cpf = ?, nascimento = ? WHERE codigo = ?";
                
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, c.getNome());
                stmt.setString(2, c.getCpf());
                stmt.setString(3, c.getNascimento());
                stmt.setInt(4, c.getCodigo());
                stmt.execute();
                
            }
                Conexao.Desconectar();
            
        } catch (SQLException e) {
            erro = "Ocorreu um erro ao salvar" + e.getMessage();
        }
        
        return erro;
    }
    
    
    public ObservableList<Cliente> Todos(){
        
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
                
        try {
            
            Connection conexao = Conexao.Conectar();
            
            String sql = "SELECT * FROM cliente";
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNascimento(rs.getString("nascimento"));
                clientes.add(cliente);
            }
            
            Conexao.Desconectar();
            
        } catch (SQLException e){
        }
        
        return clientes;
    }
    
    
    public ObservableList<Cliente> Pesquisar(String nome){
        
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        
        
        try {
            
            Connection conexao = Conexao.Conectar();
            
            String sql = "SELECT * FROM cliente WHERE nome LIKE ?";
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNascimento(rs.getString("nascimento"));
                clientes.add(cliente);
            }
            
            Conexao.Desconectar();
            
        } catch (SQLException e){
        }
        
        
        return clientes;
    }
    
    
    public String Excluir(int codigo){
        
        String erro = "";
        
        try {
            
            Connection conexao = Conexao.Conectar();
            
            String sql = "DELETE FROM cliente WHERE codigo = ?";
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
            Conexao.Desconectar();
            
            
        } catch (SQLException e){
            erro = "Ocorreu um erro ao excluir" + e.getMessage();
        }
        
        return erro;
    }
    
}
