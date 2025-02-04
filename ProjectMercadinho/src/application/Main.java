package application;
	
import java.sql.Connection;

import ConnectionFactory.ConnectionDatabase;
import DAO.ClienteDAO;
import Model.Cliente;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
			Connection con = ConnectionDatabase.getConnection();
			ConnectionDatabase.closeConnection(con);
			
			// cliente receber usuario
			Cliente cliente = new Cliente();
			cliente.setNome("Lara Cristina");
			cliente.setCpf("02598563519");
			cliente.setDataNasc("2005-02-26");
			cliente.setEmail("teste@gmail.com");
			cliente.setEndereco("araguaina sul");
			cliente.setTelefone("63992693908");
			cliente.setGenero("Feminino");
			
			// cliente faz ligação com banco
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.create(cliente);
		
		launch(args);
	}
}
