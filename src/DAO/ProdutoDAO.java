package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import Model.Produto;

public class ProdutoDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(Produto produto) {

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("insert into Produto values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
			stmt.setString(1, produto.getIdFornecedor());
			stmt.setString(2, produto.getNome());
			stmt.setString(3, produto.getCodBarra());
			stmt.setString(4, produto.getLote());
			stmt.setString(5, produto.getDataFab());
			stmt.setString(6, produto.getDataVal());
			stmt.setString(7, produto.getMarca());
			stmt.setString(8, produto.getCategoria());
			stmt.setString(9, produto.getUniMedida());
			stmt.setString(10, produto.getPrecoUni());
			stmt.setString(11, produto.getEstoque());									

			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!", e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}

	}

	// ---------------------------------------  read ler (SELECT)	--------------------------------------------------------
	public ArrayList<Produto> read(){

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> produtos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Produto");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Produto produto = new Produto();
				produto.setId(rs.getString(1));
				produto.setIdFornecedor(rs.getString(2));
				produto.setNome(rs.getString(3));
				produto.setCodBarra(rs.getString(4));
				produto.setLote(rs.getString(5));
				produto.setDataFab(rs.getString(6));
				produto.setDataVal(rs.getString(7));
				produto.setMarca(rs.getString(8));
				produto.setCategoria(rs.getString(9));
				produto.setUniMedida(rs.getString(10));
				produto.setPrecoUni(rs.getString(11));
				produto.setEstoque(rs.getString(12));


				produtos.add(produto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return produtos;

	}

	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(Produto produto) {

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("update Produto set idFornecedor = ?, nomeProduto = ?, codBarra = ? , lote = ?, dataFab = ?,dataVal = ?, marca = ?, categoria = ?, \r\n"
					+ "unidadeDeMedida = ?, precoUnitario = ?, estoque = ? where idProduto = ? or nomeProduto = ? or codBarra = ?");

			stmt.setString(1, produto.getIdFornecedor());
			stmt.setString(2, produto.getNome());
			stmt.setString(3, produto.getCodBarra());
			stmt.setString(4, produto.getLote());
			stmt.setString(5, produto.getDataFab());
			stmt.setString(6, produto.getDataVal());
			stmt.setString(7, produto.getMarca());
			stmt.setString(8, produto.getCategoria());
			stmt.setString(9, produto.getUniMedida());
			stmt.setString(10, produto.getPrecoUni());
			stmt.setString(11, produto.getEstoque());	
			stmt.setString(12, produto.getId());
			stmt.setString(13, produto.getIdFornecedor());
			stmt.setString(14, produto.getCodBarra());


			stmt.executeUpdate();
			System.out.println("Atualizar com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao atualizar!", e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}

	}

	// ---------------------------------------  delete apagar (DELETE) --------------------------------------- 	
	public void delete(Produto produto) {

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from Produto where idProduto = ? or codBarra = ?");		
			stmt.setString(1, produto.getId());
			stmt.setString(2, produto.getCodBarra());

			stmt.executeUpdate();
			System.out.println("Excluido com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao excluir!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}

	}
	// ---------------------------------------  search pesquisar (SELECT + LIKE) --------------------------------------- 
	public ArrayList<Produto> search(Produto produto1){

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> produtos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Produto where nomeProduto like ? or codBarra like ?");			
			stmt.setString(1,"%" +produto1.getNome() +"%");
			stmt.setString(2, "%"+produto1.getCodBarra()+"%");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Produto produto = new Produto();
				produto.setId(rs.getString(1));
				produto.setIdFornecedor(rs.getString(2));
				produto.setNome(rs.getString(3));
				produto.setCodBarra(rs.getString(4));
				produto.setLote(rs.getString(5));
				produto.setDataFab(rs.getString(6));
				produto.setDataVal(rs.getString(7));
				produto.setMarca(rs.getString(8));
				produto.setCategoria(rs.getString(9));
				produto.setUniMedida(rs.getString(10));
				produto.setPrecoUni(rs.getString(11));
				produto.setEstoque(rs.getString(12));


				produtos.add(produto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return produtos;

	}

	public ArrayList<Produto> searchID(Produto produto1){

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> produtos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Produto where nomeProduto like ? or codBarra like ?");		
			stmt.setString(1,"%" +produto1.getNome() +"%");
			stmt.setString(2, "%"+produto1.getCodBarra()+"%");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Produto produto = new Produto();
				produto.setId(rs.getString(1));
				produto.setIdFornecedor(rs.getString(2));
				produto.setNome(rs.getString(3));
				produto.setCodBarra(rs.getString(4));
				produto.setLote(rs.getString(5));
				produto.setDataFab(rs.getString(6));
				produto.setDataVal(rs.getString(7));
				produto.setMarca(rs.getString(8));
				produto.setCategoria(rs.getString(9));
				produto.setUniMedida(rs.getString(10));
				produto.setPrecoUni(rs.getString(11));
				produto.setEstoque(rs.getString(12));


				produtos.add(produto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return produtos;

	}

	//	--------------------- retorna estoque baixo ---------------------

	public ArrayList<Produto> getByEstoque(){

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> produtos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select*from Produto where estoque < 15");
			rs = stmt.executeQuery();

			int i = 1;
			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Produto produto = new Produto();
				produto.setId(""+ i);
				produto.setIdFornecedor(rs.getString(2));
				produto.setNome(rs.getString(3));
				produto.setCodBarra(rs.getString(4));
				produto.setLote(rs.getString(5));
				produto.setDataFab(rs.getString(6));
				produto.setDataVal(rs.getString(7));
				produto.setMarca(rs.getString(8));
				produto.setCategoria(rs.getString(9));
				produto.setUniMedida(rs.getString(10));
				produto.setPrecoUni(rs.getString(11));
				produto.setEstoque(rs.getString(12));


				produtos.add(produto);
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return produtos;

	}
	public ArrayList<String> readProdutoByNome() {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<String> produtos = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT nomeProduto FROM Produto");
			rs = stmt.executeQuery();

			while (rs.next()) {
				String nome;
				nome = rs.getString(1);
				produtos.add(nome);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler os produtos!", e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return produtos;
	}

}
