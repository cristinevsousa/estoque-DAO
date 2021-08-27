package servico;

import java.util.List;

import dao.EnderecoDAO;
import entidade.Endereco;
import utils.Conexao;

public class EnderecoServico {

	EnderecoDAO dao;
	Conexao conexao;
	
	public EnderecoServico() {
		dao = new EnderecoDAO();
		conexao = new Conexao();
	}
	
	public String inserirEndereco(Endereco endereco) {
		dao.setEndereco(endereco);
		dao.setConexao(conexao);
		
		try {
			conexao.conecta();
			
			if(dao.inserir()) {
				conexao.confirmarTransacao();
				System.out.println("Endereço adicionado com sucesso!");
			} else {
				conexao.cancelarTransacao();
			}
			
			conexao.fechar();
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Endereço adicionado com sucesso!";
		
	}
	
	public String atualizarEndereco(Endereco endereco) {
		dao.setEndereco(endereco);
		dao.setConexao(conexao);
		
		try {
			conexao.conecta();
			
			if(dao.atualizar()) {
				conexao.confirmarTransacao();
			} else {
				conexao.cancelarTransacao();
			}
			
			conexao.fechar();
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Endereço atualizado com sucesso!";
		
	}
	
	public String excluirEndereco(Endereco endereco) {
		dao.setEndereco(endereco);
		dao.setConexao(conexao);
		
		try {
			conexao.conecta();
			
			if(dao.excluir()) {
				conexao.confirmarTransacao();
			} else {
				conexao.cancelarTransacao();
			}
			
			conexao.fechar();
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Endereço excluido com sucesso!";
		
	}
	
	public List<Endereco> buscarEndereco() {
		dao.setConexao(conexao);
		
		try {
			conexao.conecta();
			
			List<Endereco> enderecos = dao.buscarEnderecos();
			
			conexao.fechar();
			
			return enderecos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
