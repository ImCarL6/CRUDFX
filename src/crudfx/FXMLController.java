package crudfx;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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

public class FXMLController implements Initializable {
    
/********************************************************/
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
    
/********************************************************/
    
    
    @FXML
    private void Salvar(ActionEvent event){
        
        try{
            
            Cliente c = ObterCliente();
            String erro = dao.Salvar(c);

            if ("".equals(erro)){
                AtribuirCliente(new Cliente());
                MostrarCliente();
                Alerta("Salvo com sucesso");
            }
        } catch (Exception e){
        }
    }
    
    
    @FXML
    private void Novo(ActionEvent event){
        
        AtribuirCliente(new Cliente());
        MostrarCliente();
        
    }
    
    
    @FXML
    private void Pesquisar(ActionEvent event){
        
        nome = Nome.getText();
        MostrarClientePesquisar();
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
                    dao.Excluir(a);
                    MostrarCliente();
                }
            }
            
        } catch (Exception e){
            
            Alerta("Selecione uma linha para exclusão");
            
        }
    }
    
     
    @FXML
    private void ClicarTabela(MouseEvent event){
        
        Cliente c = tabela.getSelectionModel().getSelectedItem();
        Nome.setText(c.getNome());
        Cpf.setText(c.getCpf());
        Nasc.setText(c.getNascimento());
        AtribuirCliente(c);
    }
    
    
    private void AtribuirCliente(Cliente c){
        
        codigo = c.getCodigo();
        Nome.setText(c.getNome());
        Cpf.setText(c.getCpf());
        Nasc.setText(c.getNascimento());
        
    }
    
    
    private Cliente ObterCliente(){
        
        c = new Cliente();
        c.setCodigo(codigo);
        c.setNome(Nome.getText());
        c.setCpf(Cpf.getText());
        c.setNascimento(Nasc.getText());
        
        return c;
        
    }
    
    @FXML
    public void MostrarCliente(){
        
        ObservableList<Cliente> list = dao.Todos();
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colNasc.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        
        tabela.setItems(list);
        
    }
    
    
    @FXML
    private void MostrarClientePesquisar(){
        
        ObservableList<Cliente> list = dao.Pesquisar(nome);
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colNasc.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        
        tabela.setItems(list);
    }
    
    
      @FXML
    private void Alerta(String texto){
         
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Atenção");
            alerta.setHeaderText(null);
            alerta.setContentText(texto);
            alerta.show();
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codigo = 0;
        MostrarCliente();
    }
    
}
