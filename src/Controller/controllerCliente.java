package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.ClienteDAO;
import Model.Cliente;
import Util.Alerts;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class controllerCliente implements Initializable{	

	@FXML
	private Button btCadastrar;

	@FXML
	private Button btCliente;

	@FXML
	private Button btEditar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btFornecedor;

	@FXML
	private Button btFuncionario;

	@FXML
	private Button btMain;

	@FXML
	private Button btPesquisar;

	@FXML
	private Button btProduto;

	@FXML
	private Button btRegistrarVenda;

	@FXML
	private Button btSair;

	@FXML
	private TableColumn<Cliente, String> columnCPF;

	@FXML
	private TableColumn<Cliente, String> columnNome;

	@FXML
	private TableColumn<Cliente, String> columnDataNasc;
	@FXML
	private TableColumn<Cliente, String> columnEmail;

	@FXML
	private TableColumn<Cliente, String> columnEndereco;

	@FXML
	private TableColumn<Cliente, String> columnGenero;

	@FXML
	private TableColumn<Cliente, String> columnIndice;

	@FXML
	private TableColumn<Cliente, String> columnTelefone;

	@FXML
	private TableView<Cliente> tableClientes;

	@FXML
	private TextField txtPesquisar;

	@FXML
	private Label txtUser;

	@FXML
	void actionSair(ActionEvent event) {

	}

	@FXML
	void telaCliente(ActionEvent event) {

	}

	@FXML
	void telaFornecedor(ActionEvent event) {

	}

	@FXML
	void telaFuncionario(ActionEvent event) {

	}

	@FXML
	void telaMain(ActionEvent event) throws IOException {
		Main.TelaHome();
	}

	@FXML
	void telaProduto(ActionEvent event) {

	}

	@FXML
	void telaVenda(ActionEvent event) {

	}

	@FXML
	void actionCadastrar(ActionEvent event) {

	}

	@FXML
	void actionEditar(ActionEvent event) {

	}

	@FXML
	void actionExcluir(ActionEvent event) {

		int i = tableClientes.getSelectionModel().getSelectedIndex(); // valor clicado na tela
		if(i == -1) {
			Alerts.showAlert("ERRO!", "Falha ao concluir", "Selecione um cliente", AlertType.ERROR);   		
		}else {
			Cliente cliente = new Cliente();
			cliente = tableClientes.getItems().get(i);

			Alert confirmation = new Alert (AlertType.CONFIRMATION);
			confirmation.setContentText("Deseja realmente excluir o(a) cliente? \n   " + cliente.getNome());

			Optional<ButtonType> resultado = confirmation.showAndWait();

			if(resultado.isPresent() && resultado.get() == ButtonType.OK) {
				ClienteDAO clienteDAO = new ClienteDAO();
				clienteDAO.delete(cliente);

				Alerts.showAlert("Sucesso!", "Cliente excluído", "O cliente "+cliente.getNome()+", foi excluido com sucesso", AlertType.INFORMATION);
				CarregarTableCliente();
			}
		}
	}

	@FXML
	void actionPesquisar(ActionEvent event) {
		PesquisarTableCliente();
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		CarregarTableCliente();
		txtUser.setText(controllerLogin.funcionario.getNome());
	}

	private ObservableList<Cliente> ArrayClientes;

	public void CarregarTableCliente() {
		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayClientes = FXCollections.observableArrayList(clienteDAO.read());

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));	
		columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

		tableClientes.setItems(ArrayClientes);						
	}    
	public void PesquisarTableCliente() {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente();
		cliente.setCpf(txtPesquisar.getText());
		cliente.setNome(txtPesquisar.getText());

		ArrayClientes = FXCollections.observableArrayList(clienteDAO.search(cliente));

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));	
		columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

		tableClientes.setItems(ArrayClientes);

	}    

}
