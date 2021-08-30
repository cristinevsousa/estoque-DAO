package servico;

import java.util.List;

import dao.ClienteDAO;
import entidade.Cliente;
import utils.Conexao;

public class ClienteServico {

	ClienteDAO dao;
	Conexao conexao;
	
	public ClienteServico() {
		dao = new ClienteDAO();
		conexao = new Conexao();
	}
	
	public int inserirCliente(Cliente cliente) {
		dao.setCliente(cliente);
		dao.setConexao(conexao);
		
		try {
			
			conexao.conecta();
			
			int id = dao.inserir();
			
			if (id > 0) {
				conexao.confirmarTransacao();
			} else {
				conexao.cancelarTransacao();
			}
			
			conexao.fechar();
			
			return id;
			
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public String alterarCliente(Cliente cliente) {
		dao.setCliente(cliente);
		dao.setConexao(conexao);
		
		try {
			
			conexao.conecta();
			
			if(dao.atualizar()) {
				conexao.confirmarTransacao();
			} else {
				conexao.cancelarTransacao();
			}
			
			conexao.fechar();
			
		}catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return "Cliente " + cliente.getNomeCliente() + " alterado com sucesso!";
	}

	
	public String excluirCliente(Cliente cliente) {
		dao.setCliente(cliente);
		dao.setConexao(conexao);
		
		try {
			
			conexao.conecta();
			
			if(dao.excluir()) {
				conexao.confirmarTransacao();
			} else {
				conexao.cancelarTransacao();
			}
			
			conexao.fechar();
			
		}catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return "Cliente " + cliente.getNomeCliente() + " excluído com sucesso!";
	}
	
	public List<Cliente> buscarTodosClientes() {
		dao.setConexao(conexao);
		
		try {
			
			conexao.conecta();
			
			List<Cliente> clientes = dao.buscarClientes();
			
			conexao.fechar();
			
			return clientes;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public List<Cliente> buscarClientesPorNome(String nome) {
		dao.setConexao(conexao);
		
		try {
			
			conexao.conecta();
			
			List<Cliente> clientes = dao.buscarClientesPorNome(nome);
			
			conexao.fechar();
			
			return clientes;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
