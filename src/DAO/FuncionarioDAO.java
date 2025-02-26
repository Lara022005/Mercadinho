package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import Model.Funcionario;
import Util.Alerts;
import javafx.scene.control.Alert.AlertType;


public class FuncionarioDAO {

	//----------------------------------------------- Criar (Insert)----------------------------------------------
	public void create(Funcionario funcionario) {

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("insert into Funcionario values(?, ?, ?, ?, ?, ?, ?,?, ?, ?,?)");
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getEmail());
			stmt.setString(4, funcionario.getGenero());
			stmt.setString(5, funcionario.getDataNasc());
			stmt.setString(6, funcionario.getEndereco());
			stmt.setString(7, funcionario.getTelefone());
			stmt.setString(8, funcionario.getCargo());
			stmt.setString(9, funcionario.getSalario());
			stmt.setString(10, funcionario.getDataAdmissao());
			stmt.setString(11, funcionario.getSenha());


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

	// ---------------------------------------  read ler (SELECT)	------------------------------------------
	public ArrayList<Funcionario> read(){

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Funcionario> Funcionarios = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Funcionario");
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getString(1));
				funcionario.setNome(rs.getString(2));
				funcionario.setCpf(rs.getString(3));
				funcionario.setEmail(rs.getString(4));
				funcionario.setGenero(rs.getString(5));
				funcionario.setDataNasc(rs.getString(6));
				funcionario.setEndereco(rs.getString(7));
				funcionario.setTelefone(rs.getString(8));
				funcionario.setCargo(rs.getString(9));
				funcionario.setSalario(rs.getString(10));
				funcionario.setDataAdmissao(rs.getString(11));
				funcionario.setSenha(rs.getString(12));

				Funcionarios.add(funcionario);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return Funcionarios;

	}

	//---------------------------------------  update atualizar (update)--------------------------------------- 
	public void update(Funcionario funcionario) {

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("update Funcionario set nomeFuncionario = ?, cpfFuncionario = ?, emailFuncionario = ?, generoFuncionario = ?,\r\n"
					+ "enderecoFuncionario = ?, telefoneFuncionario = ?, salarioFuncionario = ?, cargo = ?,\r\n"
					+ "dataAdmissao = ?, dataNascimento = ?,senha = ? where idFuncionario = ? or cpfFuncionario = ?");
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getEmail());
			stmt.setString(4, funcionario.getGenero());
			stmt.setString(5, funcionario.getEndereco());
			stmt.setString(6, funcionario.getTelefone());
			stmt.setString(7, funcionario.getSalario());
			stmt.setString(8, funcionario.getCargo()); 
			stmt.setString(9, funcionario.getDataAdmissao());
			stmt.setString(10, funcionario.getDataNasc());
			stmt.setString(11, funcionario.getSenha());
			stmt.setString(12, funcionario.getId());
			stmt.setString(13, funcionario.getCpf());

			stmt.executeUpdate();
			System.out.println("Atualizar com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao atualizar!", e);
		} 
		finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}

	}

	// ---------------------------------------  delete apagar (DELETE) ----------------------------------------

	public void delete(Funcionario funcionario) {

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement("delete from Funcionario where idFuncionario = ? or cpfFuncionario = ?");		
			stmt.setString(1, funcionario.getId());
			stmt.setString(2, funcionario.getCpf());

			stmt.executeUpdate();
			System.out.println("Excluido com sucesso!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao excluir!", e);
		} 
		finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}

	}

	// ---------------------------------------  search pesquisar (SELECT + LIKE) ------------------------------

	public ArrayList<Funcionario>  search(Funcionario funcionario1){

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Funcionario> Funcionarios = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Funcionario where cpfFuncionario like ? or nomeFuncionario like ?");
			stmt.setString(1, funcionario1.getCpf());
			stmt.setString(2, funcionario1.getNome());
			rs = stmt.executeQuery();

			while(rs.next()) { // so ira funcionar enquanto estiver linha 				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getString(1));
				funcionario.setNome(rs.getString(2));
				funcionario.setCpf(rs.getString(3));
				funcionario.setEmail(rs.getString(4));
				funcionario.setGenero(rs.getString(5));
				funcionario.setDataNasc(rs.getString(6));
				funcionario.setEndereco(rs.getString(7));
				funcionario.setTelefone(rs.getString(8));
				funcionario.setCargo(rs.getString(9));
				funcionario.setSalario(rs.getString(10));
				funcionario.setDataAdmissao(rs.getString(11));
				funcionario.setSenha(rs.getString(12));

				Funcionarios.add(funcionario);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}
		finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return Funcionarios;

	}

	public Funcionario autenticarUser(String cpf, String senha) {
		Connection con = ConnectionDatabase.getConnection(); // conectar com banco
		PreparedStatement stmt = null; // puxar informação do banco
		ResultSet rs = null; // tras resultado do banco
		Funcionario funcionario = new Funcionario();
		
		try {						
			stmt = con.prepareStatement("select * from Funcionario where cpfFuncionario = ? and senha = ?");
			stmt.setString(1, cpf);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
			funcionario.setId(rs.getString(1));
			funcionario.setNome(rs.getString(2));
			funcionario.setCpf(rs.getString(3));
			funcionario.setEmail(rs.getString(4));
			funcionario.setGenero(rs.getString(5));
			funcionario.setDataNasc(rs.getString(6));
			funcionario.setEndereco(rs.getString(7));
			funcionario.setTelefone(rs.getString(8));
			funcionario.setCargo(rs.getString(9));
			funcionario.setSalario(rs.getString(10));
			funcionario.setDataAdmissao(rs.getString(11));
			funcionario.setSenha(rs.getString(12));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("Erro!", "Erro de conexão!", "Falha ao consultar informações no banco de dados.", AlertType.ERROR);
			throw new RuntimeException("Erro de autenticação", e);
		} finally{
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}				
		return funcionario;		
	}
	
	public String getTotalVendido(String id) {
			
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		String TotalVendido = null;
		
		try {
			stmt = con.prepareStatement("select SUM(precoTotal) as TotalVendido from Venda where FK_idFuncionario = ?;");
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TotalVendido = rs.getString(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("Erro!", "Erro de conexão!", "Falha ao consultar informações no banco de dados.", AlertType.ERROR);
			throw new RuntimeException("Erro!", e);
		} finally{
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}		
		
		return TotalVendido;
	}
	
}





