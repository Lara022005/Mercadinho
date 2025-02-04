package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import ConnectionFactory.ConnectionDatabase;
import Model.Cliente;

public class ClienteDAO {
	
	// Criar (Insert)
	public void create(Cliente cliente) {
		
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("insert into Cliente values(?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getGenero());
			stmt.setString(5, cliente.getDataNasc());
			stmt.setString(6, cliente.getEndereco());
			stmt.setString(7, cliente.getTelefone());
			
			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);
		} 
		finally {
			 ConnectionDatabase.closeConnection(con, stmt);
		}
		
	}

}
