package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

import entidade.Cliente;
import entidade.Endereco;
import utils.Conexao;

public class ClienteDAO implements IGerenciamentoDAO {

	private Cliente cliente;

	private Conexao conexao;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Conexao getConexao() {
		return conexao;
	}

	public void setConexao(Conexao conexao) {
		this.conexao = conexao;
	}

	@Override
	public long inserir() {
		try {

			PreparedStatement pst = this.conexao.getConexao().prepareStatement(
					"INSERT INTO endereco (estado, cidade, cep, rua, bairro, numero) VALUES (?,?,?,?,?,?)", 1); // 1 significando parâmetro para retornar valor da PK

			pst.setString(1, cliente.getEndereco().getEstado());
			pst.setString(2, cliente.getEndereco().getCidade());
			pst.setString(3, cliente.getEndereco().getCep());
			pst.setString(4, cliente.getEndereco().getRua());
			pst.setString(5, cliente.getEndereco().getBairro());
			pst.setLong(6, cliente.getEndereco().getNumero());

			pst.executeUpdate();

			ResultSet keysEnd = pst.getGeneratedKeys(); // método da classe PreparedStatement que auto gera pk

			keysEnd.next();
            long keyEnd = keysEnd.getLong(1);

			pst.close();

			PreparedStatement pst2 = this.conexao.getConexao()
					.prepareStatement("INSERT INTO cliente (nome, cpf, email, nascimento, endereco_id) VALUES (?,?,?,?,?)", 1);

			java.sql.Date dt = new Date(cliente.getNascimento().getYear(), cliente.getNascimento().getMonth(),
					cliente.getNascimento().getDay());

			pst2.setString(1, cliente.getNomeCliente());
			pst2.setString(2, cliente.getCpf());
			pst2.setString(3, cliente.getEmail());
			pst2.setDate(4, dt);
			pst2.setLong(5, keyEnd);

			pst2.executeUpdate();
			
			ResultSet keysCliente = pst2.getGeneratedKeys();

			keysCliente.next();
            long keyCliente = keysCliente.getLong(1);

			pst2.close();
			
			return keyCliente;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public boolean atualizar() {
		try {

			PreparedStatement pst = this.conexao.getConexao()
					.prepareStatement("UPDATE cliente SET nome=?, cpf=?, email=?, nascimento=?");

			java.sql.Date dt = new Date(cliente.getNascimento().getYear(), cliente.getNascimento().getMonth(),
					cliente.getNascimento().getDay());
			
			pst.setString(1, cliente.getNomeCliente());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getEmail());
			pst.setDate(4, dt);

			pst.executeUpdate();

			pst.close();

			PreparedStatement pst2 = this.conexao.getConexao().prepareStatement(
					"UPDATE endereco SET estado=?, cidade=?, cep=?, rua=?, bairro=?, numero=? WHERE id=?");

			pst2.setString(1, cliente.getEndereco().getEstado());
			pst2.setString(2, cliente.getEndereco().getCidade());
			pst2.setString(3, cliente.getEndereco().getCep());
			pst2.setString(4, cliente.getEndereco().getRua());
			pst2.setString(5, cliente.getEndereco().getBairro());
			pst2.setLong(6, cliente.getEndereco().getNumero());
			pst2.setLong(7, cliente.getId());

			pst2.executeUpdate();

			pst2.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean excluir() {
		try {

			PreparedStatement pst = this.conexao.getConexao().prepareStatement("DELETE FROM cliente WHERE id=?");

			pst.setLong(1, cliente.getId());

			pst.executeUpdate();

			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public List<Cliente> buscarClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();

		try {

			PreparedStatement pst = this.conexao.getConexao().prepareStatement("SELECT * FROM cliente");

			ResultSet result = pst.executeQuery();

			while (result.next()) {

				Cliente cliente = new Cliente();
				cliente.setId(result.getLong("id"));
				cliente.setNomeCliente(result.getString("nome"));

				clientes.add(cliente);
			}

			result.close();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return clientes;
	}

	public List<Cliente> buscarClientesPorNome(String nome) {
		List<Cliente> clientes = new ArrayList<Cliente>();

		try {

			PreparedStatement pst = this.conexao.getConexao()
					.prepareStatement("SELECT * FROM cliente WHERE nome like ?");

			pst.setString(1, "%" + nome + "%");

			ResultSet result = pst.executeQuery();

			while (result.next()) {

				Cliente cliente = new Cliente();
				cliente.setId(result.getLong("id"));
				cliente.setNomeCliente(result.getString("nome"));

				clientes.add(cliente);
			}

			result.close();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return clientes;
	}

}
