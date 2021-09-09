package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidade.Cliente;
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
	public int inserir() {
		try {

			PreparedStatement pst = this.conexao.getConexao()
					.prepareStatement("INSERT INTO cliente (nome, cpf, email, nascimento) VALUES (?,?,?,?)", 1); // 1 significando parâmetro para retornar valor da PK

			java.sql.Date dt = new Date(cliente.getNascimento().getYear(), cliente.getNascimento().getMonth(),
					cliente.getNascimento().getDay());

			pst.setString(1, cliente.getNomeCliente());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getEmail());
			pst.setDate(4, dt);

			pst.executeUpdate();
			
			ResultSet keysCliente = pst.getGeneratedKeys(); // método da classe PreparedStatement que retorna pk

			keysCliente.next();
            int keyCliente = keysCliente.getInt(1);

			pst.close();
			
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
					.prepareStatement("UPDATE cliente SET nome=?, cpf=?, email=?, nascimento=? WHERE id = ?");

			java.sql.Date dt = new Date(cliente.getNascimento().getYear(), cliente.getNascimento().getMonth(),
					cliente.getNascimento().getDay());
			
			pst.setString(1, cliente.getNomeCliente());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getEmail());
			pst.setDate(4, dt);
			pst.setInt(5, cliente.getId());

			pst.executeUpdate();

			pst.close();

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
				cliente.setId(result.getInt("id"));
				cliente.setNomeCliente(result.getString("nome"));
				cliente.setCpf(result.getString("cpf"));
				cliente.setNascimento(result.getDate("nascimento"));
				cliente.setEmail(result.getString("email"));

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
				cliente.setId(result.getInt("id"));
				cliente.setNomeCliente(result.getString("nome"));
				cliente.setCpf(result.getString("cpf"));
				cliente.setNascimento(result.getDate("nascimento"));
				cliente.setEmail(result.getString("email"));

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
