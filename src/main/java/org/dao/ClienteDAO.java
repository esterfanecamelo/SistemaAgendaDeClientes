package org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.model.Cliente;

public class ClienteDAO {

	private Connection connection;

	public ClienteDAO() {
		connection = FabricaDeConexao.getConexao();
	}

	public boolean cadastrarGrupo(List<Cliente> grupoDeClientes) throws SQLException {

		for (Cliente cliente : grupoDeClientes) {
			inserir(cliente);
		}

		return true;
	}

	public Cliente consultar(Integer cpf) throws SQLException {

		Cliente cliente = null;
		try {
			String sql = "SELECT * FROM Cliente WHERE cpf=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cpf);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getInt("cpf"));
				cliente.setEmail(rs.getString("email"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return cliente;
	}

	public Cliente inserir(Cliente cliente) throws SQLException {

		try {
			String sql = "INSERT INTO CLIENTE (nome,cpf,email) values (?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setInt(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.executeUpdate();
			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public Cliente atualizar(Cliente cliente) throws SQLException {

		try {
			String sql = "update Cliente set email=? where cpf=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cliente.getEmail());
			stmt.setInt(2, cliente.getCpf());
			stmt.executeUpdate();
			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public boolean deletar(Cliente cliente) throws SQLException {

		try {

			String sql = "DELETE FROM Cliente where cpf=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cliente.getCpf());

			if (stmt.executeUpdate() > 0) {
				new TelefoneDAO().limparTelefones(cliente);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return false;

	}

	public List<Cliente> pesquisarPorNome(String nome) throws SQLException {

		List<Cliente> clientes = new ArrayList<Cliente>();

		try {
			String sql = "SELECT * FROM Cliente WHERE nome like ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			Cliente cliente;
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getInt("cpf"));
				cliente.setEmail(rs.getString("email"));
				clientes.add(cliente);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return clientes;
	}

	public List<Cliente> listarTodos() throws SQLException {

		List<Cliente> clientes = new ArrayList<Cliente>();

		try {
			String sql = "SELECT * FROM Cliente";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Cliente cliente;
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getInt("cpf"));
				cliente.setEmail(rs.getString("email"));
				clientes.add(cliente);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return clientes;
	}

}
