package org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.model.Cliente;
import org.model.Telefone;

public class TelefoneDAO {

	private Connection connection;

	public TelefoneDAO() {
		connection = FabricaDeConexao.getConexao();
	}

	public Telefone consultar(Integer id) throws SQLException {

		Telefone telefone = null;
		try {
			String sql = "SELECT * FROM Telefone WHERE ID=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				telefone = new Telefone();
				telefone.setNumero(rs.getInt("numero"));
				telefone.setId(rs.getInt("ID"));
				telefone.setCliente(new ClienteDAO().consultar(rs.getInt("cpf_cliente")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return telefone;
	}

	public Telefone inserir(Telefone telefone) throws SQLException {

		try {
			String sql = "INSERT INTO Telefone (numero,cpf_cliente) values (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, telefone.getNumero());
			stmt.setInt(2, telefone.getCliente().getCpf());
			stmt.executeUpdate();
			return telefone;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public List<Telefone> buscar(Cliente cliente) throws SQLException {
		List<Telefone> telefones = new ArrayList<Telefone>();

		try {
			String sql = "SELECT * FROM Telefone WHERE cpf_cliente = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cliente.getCpf());
			ResultSet rs = stmt.executeQuery();
			Telefone telefone;
			while (rs.next()) {
				telefone = new Telefone();
				telefone.setNumero(rs.getInt("numero"));
				telefone.setId(rs.getInt("ID"));
				telefone.setCliente(new ClienteDAO().consultar(rs.getInt("cpf_cliente")));
				telefones.add(telefone);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return telefones;
	}

	public Telefone atualizar(Telefone telefone) throws SQLException {

		try {
			String sql = "update Telefone set numero=? where cpf_cliente=? and id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, telefone.getNumero());
			stmt.setInt(2, telefone.getCliente().getCpf());
			stmt.setInt(3, telefone.getId());
			stmt.executeUpdate();
			return telefone;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public boolean limparTelefones(Cliente cliente) throws SQLException {

		try {

			String sql = "DELETE FROM Telefone where cpf_cliente=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cliente.getCpf());

			if (stmt.executeUpdate() > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return false;

	}

	public boolean removerTelefone(int id) throws SQLException {

		try {

			String sql = "DELETE FROM Telefone WHERE ID=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);

			if (stmt.executeUpdate() != 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return false;

	}

}
