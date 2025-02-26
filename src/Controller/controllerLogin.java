package Controller;

import java.io.IOException;

import DAO.FuncionarioDAO;
import Model.Funcionario;
import Util.Alerts;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class controllerLogin {

    @FXML
    private Button btLogin;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;

    public static Funcionario funcionario = new Funcionario();
    
    @FXML
    void actionLogin(ActionEvent event) throws IOException{
    	FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    	
    	funcionario = funcionarioDAO.autenticarUser(txtUser.getText(), txtPassword.getText());
    	
    	
    	if(funcionario.getCpf() == null) {
    		Alerts.showAlert("Erro!", "Erro de login", "Verifique se as informações estão corretas e tente novamente!", AlertType.ERROR);
    	}
    	else if(txtUser.getText() == "" && txtPassword.getText() == "") {
    		Alerts.showAlert("Erro!", "Erro de login", "Preencha as informações de login e senha para acessar!", AlertType.ERROR);   		
    	}
    	else if(funcionario.getCpf().equals(txtUser.getText()) && funcionario.getSenha().equals(txtPassword.getText())) {
    		Alerts.showAlert("Login bem sucedido!", "Seja bem vindo, " + funcionario.getNome(),"Se sinta a vontade para navegar no nosso sistema!", AlertType.INFORMATION); 
    		Main.TelaHome();
    	}
    	  	
    }
    
    
    
}
