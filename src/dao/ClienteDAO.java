package dao;

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
	public boolean inserir() {
		try {
			
			PreparedStatement pst = this.conexao.getConexao().prepareStatement("INSERT INTO cliente (nome) VALUES (?)");
			
			pst.setString(1, cliente.getNomeCliente());
			
			pst.executeUpdate();
			
			pst.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean atualizar() {
		try {
			
			PreparedStatement pst = this.conexao.getConexao().prepareStatement("UPDATE cliente SET nome = ? WHERE id = ?");
			
			pst.setString(1, cliente.getNomeCliente());
			pst.setLong(2, cliente.getId());
			
			pst.executeUpdate();
			
			pst.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean excluir() {
		try {
			
			PreparedStatement pst = this.conexao.getConexao().prepareStatement("DELETE FROM cliente WHERE id = ?");
			
			pst.setLong(1, cliente.getId());
			
			pst.executeUpdate();
			
			pst.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public List<Cliente> buscarClientes(){
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			
			PreparedStatement pst = this.conexao.getConexao().prepareStatement("SELECT * FROM cliente");
			
			ResultSet result = pst.executeQuery();
			
			while(result.next()) {
				
				Cliente cliente = new Cliente();
				cliente.setId(result.getLong("id"));
				cliente.setNomeCliente(result.getString("nome"));
				
				clientes.add(cliente);
			}
			
			result.close();
			pst.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return clientes;
	}
	
	public List<Cliente> buscarClientesPorNome(String nome){
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			
			PreparedStatement pst = this.conexao.getConexao()
					.prepareStatement("SELECT * FROM cliente WHERE nome like ?");
			
			pst.setString(1, "%"+nome+"%" );
			
			ResultSet result = pst.executeQuery();
			
			while(result.next()) {
				
				Cliente cliente = new Cliente();
				cliente.setId(result.getLong("id"));
				cliente.setNomeCliente(result.getString("nome"));
				
				clientes.add(cliente);
			}
			
			result.close();
			pst.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return clientes;
	}

}

