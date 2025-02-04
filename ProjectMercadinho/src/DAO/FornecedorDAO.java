package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnectionFactory.ConnectionDatabase;
import Model.Fornecedor;

public class FornecedorDAO {
	
	
	public void create(Fornecedor fornecedor) {
	
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("insert into Fornecedor values(?, ?, ?, ?)");
			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getCnpj());
			stmt.setString(3, fornecedor.getEndereco());
			stmt.setString(4, fornecedor.getTelefone());
			
			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!");
		}
	
	}
	
}
