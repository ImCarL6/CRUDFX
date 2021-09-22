/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudfx;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author carlo
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @FXML
    private int codigo;
    
    @FXML
    private Cliente c;
    
    @FXML
    private final ClienteDAO dao = new ClienteDAO();
    
    @FXML
    private String nome;
    
    @FXML
    private TableView<Cliente> tabela;
    
    @FXML
    private TableColumn<Cliente, Integer> colCodigo;
    
    @FXML
    private TableColumn<Cliente, String> colNome;
    
    @FXML
    private TableColumn<Cliente, String> colCpf;
    
    @FXML
    private TableColumn<Cliente, String> colNasc;
    
    @FXML
    private TextField Nome;
    
    @FXML
    private TextField Cpf;
    
    @FXML
    private TextField Nasc;
    
    private void atribuirCliente(Cliente c){
        
        codigo = c.getCodigo();
        Nome.setText(c.getNome());
        Cpf.setText(c.getCpf());
        Nasc.setText(c.getNascimento());
        
    }
    
    
    private Cliente obterCliente(){
        
        c = new Cliente();
        c.setCodigo(codigo);
        c.setNome(Nome.getText());
        c.setCpf(Cpf.getText());
        c.setNascimento(Nasc.getText());
        
        return c;
        
    }
    
    
    @FXML
    private void Salvar(ActionEvent event){
        
        try{
            
            Cliente c = obterCliente();
            String erro = dao.salvar(c);

            if ("".equals(erro)){
                atribuirCliente(new Cliente());
                mostrarCliente();
            }
        } catch (Exception e){ 
        }
        
    }
    
    
    @FXML
    public ObservableList<Cliente> todos(){
        
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
                
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
    
    @FXML
    public ObservableList<Cliente> pesquisar(String nome){
        
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        
        
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
    
    
    @FXML
    public void mostrarCliente(){
        
        ObservableList<Cliente> list = todos();
        
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colNasc.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        
        tabela.setItems(list);
        
    }
    
    @FXML
    private void mostrarClientePesquisar(){
        
        ObservableList<Cliente> list = pesquisar(nome);
        
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colNasc.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        
        tabela.setItems(list);
    }
    
    @FXML
    private void Pesquisar(ActionEvent event){
        
        nome = Nome.getText();
        mostrarClientePesquisar();
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event){
        
        Cliente c = tabela.getSelectionModel().getSelectedItem();
        Nome.setText(c.getNome());
        Cpf.setText(c.getCpf());
        Nasc.setText(c.getNascimento());
        atribuirCliente(c);
    }
    
    @FXML
    private void Deletar(ActionEvent event){
        
        
        try{
            
            int a = tabela.getSelectionModel().getSelectedItem().getCodigo();
            
            
                if (a != -1){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Atenção");
                alert.setContentText("Deseja realmente excluir esse registro?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    dao.excluir(a);
                    mostrarCliente();
                }
            }
            
        } catch (Exception e){
            
        }
    }
    
    
    @FXML
    private void Novo(ActionEvent event){
        
        atribuirCliente(new Cliente());
        mostrarCliente();
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        codigo = 0;
        mostrarCliente();
    }    
    
}
