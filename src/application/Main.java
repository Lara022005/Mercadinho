package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Stage stage;
	private static Scene login;
	private static Scene main;
	private static Scene cliente;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			stage = primaryStage;
			primaryStage.setTitle("Mercadinho - Login");
			
			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/View/viewLogin.fxml"));
			login = new Scene(fxmlLogin);
			
//			Parent fxmlMain = FXMLLoader.load(getClass().getResource("/View/viewMain.fxml"));
//			main = new Scene(fxmlMain);
			
			
			primaryStage.setScene(login);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void changeScreen(String tela) {
		if(tela.equals("main")) {
			stage.setScene(main);
			stage.centerOnScreen();
			stage.setTitle("Menu principal");
		}
		
	}
	
	public static void TelaHome() throws IOException {
		FXMLLoader fxmlHome = new FXMLLoader();
		fxmlHome.setLocation(Main.class.getResource("/View/viewMain.fxml"));
		Parent TelaHome = fxmlHome.load();
		main = new Scene(TelaHome);
		
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
		
	}
	public static void TelaCliente() throws IOException {
		FXMLLoader fxmlHome = new FXMLLoader();
		fxmlHome.setLocation(Main.class.getResource("/View/viewCliente.fxml"));
		Parent TelaCliente = fxmlHome.load();
		cliente = new Scene(TelaCliente);
		
		stage.setScene(cliente);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
		
	}
	
	private static Stage cadCliente;
	public static void TelaCadastroCliente() throws IOException {
		FXMLLoader fxmlCadastroCliente = new FXMLLoader();
		fxmlCadastroCliente.setLocation(Main.class.getResource("/View/viewCadastrarCliente.fxml"));
		Parent cadastroCliente = fxmlCadastroCliente.load();
		Scene scene2 = new Scene(cadastroCliente);
		
		cadCliente = new Stage();
		cadCliente.setTitle("Cadastro/Edição de Cliente - Mercadinho do João");
		cadCliente.initModality(Modality.WINDOW_MODAL);
		cadCliente.setScene(scene2);
		cadCliente.centerOnScreen();
		cadCliente.showAndWait();
		
	}
	
	public static void main(String[] args) {

			

		launch(args);
	}

}
