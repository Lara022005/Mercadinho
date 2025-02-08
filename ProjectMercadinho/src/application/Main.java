package application;

import java.sql.Connection;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import DAO.ProdutoVendaDAO;
import Model.ProdutoVenda;
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

		//       -------------------        TESTAR READ / SEARCH	---------------------------------	

				ProdutoVenda produtoVenda = new ProdutoVenda();
				ProdutoVendaDAO produtoVendaDAO = new ProdutoVendaDAO();
				ArrayList<ProdutoVenda> produtoVendas = new ArrayList<>();
				produtoVenda.setId("9");
				produtoVenda.setIdVenda("13");
				produtoVendas.addAll(produtoVendaDAO.search(produtoVenda));
		
				for(int i = 0; i < produtoVendas.size(); i ++) {
					produtoVenda = produtoVendas.get(i);
					System.out.println("");
					System.out.print(produtoVenda.getId() + " | ");
					System.out.print(produtoVenda.getIdVenda() + " | ");
					System.out.print(produtoVenda.getIdProduto() + " | ");
					System.out.print(produtoVenda.getQuantidade() + " | ");
					

}

	//  -----------------------      TESTAR CREATE / UPDATE / DELETE    --------------------------------------     
		
//			ProdutoVenda produtoVenda = new ProdutoVenda();
//			produtoVenda.setIdVenda("13");
//			produtoVenda.setIdProduto("45");	
//			produtoVenda.setQuantidade("10");		
//			produtoVenda.setId("9");
		
			//		 cliente faz ligação com banco
		
//			ProdutoVendaDAO ProdutovendaDAO = new ProdutoVendaDAO();
//			ProdutovendaDAO.create(produtoVenda);
		
			launch(args);
		}

}
