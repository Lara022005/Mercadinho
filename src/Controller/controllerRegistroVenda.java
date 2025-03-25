package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.ClienteDAO;
import DAO.ProdutoDAO;
import DAO.ProdutoVendaDAO;
import DAO.VendaDAO;
import Model.Cliente;
import Model.Produto;
import Model.ProdutoVenda;
import Model.Venda;
import Util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class controllerRegistroVenda implements Initializable{

	@FXML
	private Button btAdicionar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btRegistrar;

	@FXML
	private TableColumn<Produto, String> columnEmail;

	@FXML
	private TableColumn<Produto, String> columnIndice;

	@FXML
	private TableColumn<Produto, String> columnNome;

	@FXML
	private TableColumn<Produto, String> columnPrecoTotal;

	@FXML
	private TableColumn<Produto, String> columnPrecoUni;

	@FXML
	private TableColumn<Produto, String> columnQuantidade;

	@FXML
	private TableView<Produto> tableProdutos;

	@FXML
	private ChoiceBox<String> choiceFormaPag;

	@FXML
	private TextField txtCPF;

	@FXML
	private TextField txtDesconto;

	@FXML
	private TextField txtCliente;

	@FXML
	private TextField txtVendedor;

	@FXML
	private TextField txtPrecoUni;

	@FXML
	private TextField txtProduto;

	@FXML
	private TextField txtQuantidade;

	@FXML
	private TextField txtPrecoTotal;

	@FXML
	private TextField txtTotalVenda;

	@FXML
	private TextField txtCodBarra;

	private static Produto produtoVenda = new Produto();
	double totalVenda;
	double desconto;
	private ArrayList<Produto> ArrayProdutos = new ArrayList<>();
	
	String[] nomesProdutos = new String[200];
	String[] quantidade = new String[200];

	@FXML
	void actionAdicionar(ActionEvent event) {

		if(txtCPF.getText() == "" || txtCliente.getText() == "" || txtVendedor.getText() == "" || txtPrecoTotal.getText() == "" || 
				txtProduto.getText() == "" || txtQuantidade.getText() == "" ||txtCodBarra.getText() == "" ||txtPrecoUni.getText() == "") {
    		Alerts.showAlert("Erro!", "Campos inválidos"," Verifique se os campos estãos preenchidos e tente novamente!", AlertType.ERROR);  
    		
    	} else {
		produtoVenda.setNome(txtProduto.getText());
		produtoVenda.setEstoque(txtQuantidade.getText());
		produtoVenda.setPrecoUni(txtPrecoUni.getText());
		produtoVenda.setPrecoTotal(txtPrecoTotal.getText());
		produtoVenda.setId("" + ArrayProdutos.size());
		String valor = txtPrecoTotal.getText();
		valor = valor.replace(",", ".");
		double precoTotal = Double.parseDouble(valor);
		totalVenda = totalVenda + precoTotal;
		valor = String.format("%.2f", totalVenda);
		txtTotalVenda.setText(valor);
		
		valor = txtDesconto.getText();
		valor = valor.replace(",", ".");
		double valorDesconto = Double.parseDouble(valor);
		desconto = desconto + valorDesconto;
		
		nomesProdutos[ArrayProdutos.size()] = txtProduto.getText();
		quantidade[ArrayProdutos.size()] = txtQuantidade.getText();
		
		ArrayProdutos.add(produtoVenda);
		carregarTableProdutos(ArrayProdutos);	
		
		Alerts.showAlert("Sucesso!", "Parabéns","Produto adicionado com sucesso", AlertType.INFORMATION);		
    	}
	}

	@FXML
	void actionCancelar(ActionEvent event) {
		
		txtCPF.setText("");
		txtCliente.setText("");
		txtVendedor.setText("");
		txtTotalVenda.setText("");
		txtPrecoTotal.setText("");
		txtPrecoUni.setText("");
		txtProduto.setText("");
		txtQuantidade.setText("");
		txtDesconto.setText("");
		txtCodBarra.setText("");
		choiceFormaPag.setValue(null);    	   	

		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();
		
			
	}  

	@FXML
	void actionRegistrar(ActionEvent event) {
		
		if(choiceFormaPag.getValue() == null || txtTotalVenda.getText() == ""){
    		Alerts.showAlert("Erro!", "Campo inválido"," Verifique se o campo de pagamento está preenchido e tente novamente!", AlertType.ERROR);      		
    	} else {
		Venda venda = new Venda();
		VendaDAO vendaDAO = new VendaDAO();
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();		
		ProdutoDAO produtoDAO = new ProdutoDAO();		
		ProdutoVendaDAO produtoVendaDAO = new ProdutoVendaDAO();
		ArrayList<Cliente> clientes = new ArrayList<>();		
		cliente.setCpf(txtCPF.getText());
		clientes = clienteDAO.search(cliente);
		cliente = clientes.get(0);

		venda.setIdFuncionario(controllerLogin.funcionario.getId());
		venda.setIdCliente(cliente.getId());
		venda.setFormaPag(choiceFormaPag.getValue().toString());
		venda.setDesconto("" + desconto);
		venda.setPrecoTotal(txtPrecoTotal.getText());
		
		String valor = txtTotalVenda.getText();
		valor = valor.replace(",", ".");
		double precoTotal = Double.parseDouble(valor);
		venda.setPrecoTotal("" + precoTotal);
		
		vendaDAO.create(venda);

		for(int i =0; i < ArrayProdutos.size(); i++) { // size tamanho do array
			Produto produto = new Produto();
			ProdutoVenda produtoVenda = new ProdutoVenda();
			ArrayList<Produto> produtos = new ArrayList<>();
			produto.setNome(nomesProdutos[i]);		
			produtos = produtoDAO.searchID(produto);
			produto = produtos.get(0);			
			produtoVenda.setIdProduto(produto.getId());
			produtoVenda.setIdVenda(vendaDAO.readID());
			produtoVenda.setQuantidade(quantidade[i]);
			produtoVendaDAO.create(produtoVenda);

			txtCPF.setText(null);
			txtCliente.setText(null);		
			txtTotalVenda.setText(null);
			txtPrecoTotal.setText(null);
			txtPrecoUni.setText(null);
			txtProduto.setText(null);
			txtQuantidade.setText(null);
			txtDesconto.setText(null);
			txtCodBarra.setText(null);
			choiceFormaPag.setValue(null);    
					
			Alerts.showAlert("Sucesso!", "Parabéns","Venda realizada com sucesso", AlertType.INFORMATION);
			}
		}
		ArrayProdutos = new ArrayList<Produto>();
		carregarTableProdutos(ArrayProdutos);
	}

	@FXML
	void actionCPFclick(MouseEvent event) {
		if(txtCliente.getText().length() > 3) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setNome(txtCliente.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();		
			clientes = clienteDAO.search(cliente);
			cliente = clientes.get(0);
			txtCPF.setText(cliente.getCpf());
		}
	}

	@FXML
	void actionCPFtype(KeyEvent event) throws Exception {
		if(txtCliente.getText().length() > 3) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setNome(txtCliente.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();		
			clientes = clienteDAO.search(cliente);
			cliente = clientes.get(0);
			txtCPF.setText(cliente.getCpf());						
		}else {
			txtCPF.setText("");
		}
	}

	@FXML
	void actionCodclick(MouseEvent event) {
		if(txtProduto.getText().length() > 3) {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = new Produto();
			produto.setNome(txtProduto.getText());
			ArrayList<Produto> produtos = new ArrayList<>();		
			produtos = produtoDAO.search(produto);
			produto = produtos.get(0);
			txtCodBarra.setText(produto.getCodBarra());

			String precoUn;
			precoUn = produto.getPrecoUni();
			double valorUn = Double.parseDouble(precoUn);
			precoUn = String.format("%.2f", valorUn);

			txtPrecoUni.setText("R$ "+precoUn);
		}
	}

	@FXML
	void actionCodtype(KeyEvent event)throws Exception {

		if(txtProduto.getText().length() > 3) {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = new Produto();
			produto.setNome(txtProduto.getText());
			ArrayList<Produto> produtos = new ArrayList<>();		
			produtos = produtoDAO.search(produto);
			produto = produtos.get(0);
			txtCodBarra.setText(produto.getCodBarra());

			String precoUn;
			precoUn = produto.getPrecoUni();
			double valorUn = Double.parseDouble(precoUn);
			precoUn = String.format("%.2f", valorUn);	

			txtPrecoUni.setText("R$ "+precoUn);
		}else {
			txtCodBarra.setText("");
			txtPrecoUni.setText("");
		}
	}

	@FXML
	void actionDesconto(KeyEvent event) {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = new Produto();
		produto.setNome(txtProduto.getText());
		ArrayList<Produto> produtos = new ArrayList<>();		
		produtos = produtoDAO.search(produto);
		produto = produtos.get(0);
		double quantidade = Double.parseDouble(txtQuantidade.getText());
		double precoUni = Double.parseDouble(produto.getPrecoUni());
		if(quantidade >= 15) {
			double desconto = (precoUni * quantidade) * 0.05;
			double precoTotal = precoUni * quantidade - desconto;
			txtDesconto.setText("" + String.format("%.2f", desconto));
			txtPrecoTotal.setText("" + String.format("%.2f", precoTotal));

		}else if(quantidade < 15){
			double precoTotal = precoUni * quantidade;
			txtDesconto.setText("0.00");
			txtPrecoTotal.setText("" + String.format("%.2f", precoTotal));

		}else {
			txtDesconto.setText(null);
			txtPrecoTotal.setText(null);
			txtPrecoUni.setText(null);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		choiceFormaPag.getItems().add("Débito");
		choiceFormaPag.getItems().add("Dinheiro");
		choiceFormaPag.getItems().add("Pix");
		txtVendedor.setText(controllerLogin.funcionario.getNome());

		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<String> nomesProdutos = new ArrayList<String>();
		nomesProdutos = produtoDAO.readProdutoByNome();
		String[] produto = new String[nomesProdutos.size()];

		for (int i = 0; i < nomesProdutos.size(); i++) {
			produto[i] = nomesProdutos.get(i);
		} 

		TextFields.bindAutoCompletion(txtProduto, produto); // ------------ refrente ao controlsfx(sugestoes da barra de pesquisa)


		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<String> nomesClientes = new ArrayList<String>();
		nomesClientes = clienteDAO.readClienteByNome();
		String[] cliente = new String[nomesClientes.size()];

		for (int i = 0; i < nomesClientes.size(); i++) {
			cliente[i] = nomesClientes.get(i);
		}
		TextFields.bindAutoCompletion(txtCliente, cliente);				
	}

	private void carregarTableProdutos(ArrayList<Produto> ArrayProdutos) {

		ObservableList<Produto> produtosVendidos = FXCollections.observableArrayList(ArrayProdutos);

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("estoque"));	
		columnPrecoUni.setCellValueFactory(new PropertyValueFactory<>("precoUni"));
		columnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));

		tableProdutos.setItems(produtosVendidos);

	}	

}
