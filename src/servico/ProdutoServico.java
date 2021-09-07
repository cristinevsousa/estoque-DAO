package servico;

import java.util.List;

import dao.ProdutoDAO;
import entidade.Produto;
import utils.Conexao;

public class ProdutoServico {

	ProdutoDAO dao;
	Conexao conexao;
	
	public ProdutoServico() {
		dao = new ProdutoDAO();
		conexao = new Conexao();
	}
	
	public int inserirProduto(Produto produto) {
		dao.setProduto(produto);
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
	
	public String alterarProduto(Produto produto) {
		dao.setProduto(produto);
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
		
		return "Produto " + produto.getNomeProduto() + " alterado com sucesso!";
	}

	
	public String excluirProduto(Produto produto) {
		dao.setProduto(produto);
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
		
		return "Produto excluído com sucesso!";
	}
	
	public List<Produto> buscarTodosProdutos() {
		dao.setConexao(conexao);
		
		try {
			
			conexao.conecta();
			
			List<Produto> produtos = dao.buscarProdutos();
			
			conexao.fechar();
			
			return produtos;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public List<Produto> buscarProdutosPorNome(String nome) {
		dao.setConexao(conexao);
		
		try {
			
			conexao.conecta();
			
			List<Produto> produtos = dao.buscarProdutosPorNome(nome);
			
			conexao.fechar();
			
			return produtos;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
