
package crudfx;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    public String salvar(Cliente c){
        
        String erro = "";
        
        try {
            
            Connection conexao = Conexao.conectar();
            
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
                Conexao.desconectar();
            
        } catch (SQLException e) {
            erro = "Ocorreu um erro ao salvar" + e.getMessage();
        }
        
        return erro;
    }
    
    public List<Cliente> todos(){
        
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            
            Connection conexao = Conexao.conectar();
            
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
            
            Conexao.desconectar();
            
        } catch (SQLException e){
        }
        
        return clientes;
    }
    
    public List<Cliente> pesquisar(String nome){
        
        List<Cliente> clientes = new ArrayList<>();
        
        
        try {
            
            Connection conexao = Conexao.conectar();
            
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
            
            Conexao.desconectar();
            
        } catch (SQLException e){
        }
        
        
        return clientes;
    }
    
    public Cliente obter(int codigo){
        
        try {
            
            Connection conexao = Conexao.conectar();
            
            String sql = "SELECT * FROM cliente WHERE codigo = ?";
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNascimento(rs.getString("nascimento"));
                return cliente;
            }
            
            Conexao.desconectar();
            
        } catch (SQLException e) {
        }
        
        return null;
    }
    
    public String excluir(int codigo){
        
        String erro = "";
        
        try {
            
            Connection conexao = Conexao.conectar();
            
            String sql = "DELETE FROM cliente WHERE codigo = ?";
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
            Conexao.desconectar();
            
            
        } catch (SQLException e){
            erro = "Ocorreu um erro ao excluir" + e.getMessage();
        }
        
        return erro;
    }
    
}
