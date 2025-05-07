package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDataBase;
import Model.Venda;

public class VendaDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(Venda venda) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into Venda values(?, ?, ?, ?, GETDATE(), ?)");
			stmt.setString(1, venda.getIdCliente());
			stmt.setString(2, venda.getIdFuncionario());
			stmt.setString(3, venda.getFormaPag());	
			stmt.setString(4, venda.getDesconto());	
			stmt.setString(5, venda.getPrecoTotal());	

			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);
		}
	}

	// ---------------------------------------  read ler (SELECT)	--------------------------------------------------------
	public ArrayList<Venda> read(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Venda> venda1 = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Venda");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Venda venda = new Venda();
				venda.setId(rs.getString(1));
				venda.setIdCliente(rs.getString(2));
				venda.setIdFuncionario(rs.getString(3));				
				venda.setFormaPag(rs.getString(4));	
				venda.setDesconto(rs.getString(5));		
				venda.setDataVenda(rs.getString(6));
				venda.setPrecoTotal(rs.getString(7));	

				venda1.add(venda);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return venda1;
	}	

	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(Venda venda) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("update Venda set FK_idCliente = ?, FK_idFuncionario = ?, formaPagamento = ?, desconto = ?, dataVenda = ?, precoTotal = ? where idVenda = ?");
	//		stmt.setString(1, venda.getId());
			stmt.setString(1, venda.getIdCliente());		
			stmt.setString(2, venda.getIdFuncionario());
			stmt.setString(3, venda.getFormaPag());
			stmt.setString(4, venda.getDesconto());	
			stmt.setString(5, venda.getDataVenda());
			stmt.setString(6, venda.getPrecoTotal());
			stmt.setString(7, venda.getId());

			stmt.executeUpdate();
			System.out.println("Atualizar com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao atualizar!", e);
		} 
		finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}

	}

	// ---------------------------------------  delete apagar (DELETE) --------------------------------------- 

	public void delete(Venda venda) {

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from Venda where idVenda = ? or FK_idCliente = ?");		
			stmt.setString(1, venda.getId());
			stmt.setString(2, venda.getIdCliente());

			stmt.executeUpdate();
			System.out.println("Excluido com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao excluir!", e);
		} 
		finally {
			ConnectionDataBase.closeConnection(con, stmt);
		}

	}

	// ---------------------------------------  search pesquisar (SELECT + LIKE) --------------------------------------- 
	public ArrayList<Venda> search(Venda venda2){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Venda> Vendas = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Venda where idVenda like ? or FK_idCliente like ? ");
			stmt.setString(1, "%"+venda2.getId()+"%");
			stmt.setString(2, "%"+venda2.getIdCliente()+"%");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Venda venda = new Venda();
				venda.setId(rs.getString(1));
				venda.setIdCliente(rs.getString(2));
				venda.setIdFuncionario(rs.getString(3));				
				venda.setFormaPag(rs.getString(4));	
				venda.setDesconto(rs.getString(5));	
				venda.setDataVenda(rs.getString(6));
				venda.setPrecoTotal(rs.getString(7));			

				Vendas.add(venda);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return Vendas;

	}
	public String readID(){

		Connection con = ConnectionDataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;	
		String idVenda = null;

		try {
			stmt = con.prepareStatement("select * from Venda");			
			rs = stmt.executeQuery();

			while(rs.next()) { 								
				idVenda = rs.getString(1);			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDataBase.closeConnection(con, stmt, rs);
		}
		return idVenda;

	}

}
