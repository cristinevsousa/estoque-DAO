package servico;

import java.util.List;

import dao.FornecedorDAO;
import entidade.Fornecedor;
import utils.Conexao;

public class FornecedorServico {

	FornecedorDAO dao;
	Conexao conexao;

	public FornecedorServico() {
		dao = new FornecedorDAO();
		conexao = new Conexao();
	}

	public int inserirFornecedor(Fornecedor fornecedor) {
		dao.setFornecedor(fornecedor);
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

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public String alterarFornecedor(Fornecedor fornecedor) {
		dao.setFornecedor(fornecedor);
		dao.setConexao(conexao);

		try {

			conexao.conecta();

			if (dao.atualizar()) {
				conexao.confirmarTransacao();
			} else {
				conexao.cancelarTransacao();
			}

			conexao.fechar();

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

		return "Fornecedor " + fornecedor.getNome() + " alterado com sucesso!";
	}

	public String excluirFornecedor(Fornecedor fornecedor) {
		dao.setFornecedor(fornecedor);
		dao.setConexao(conexao);

		try {

			conexao.conecta();

			if (dao.excluir()) {
				conexao.confirmarTransacao();
			} else {
				conexao.cancelarTransacao();
			}

			conexao.fechar();

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

		return "Fornecedor " + fornecedor.getNome() + " excluído com sucesso!";
	}

	public List<Fornecedor> buscarTodosFornecedores() {
		dao.setConexao(conexao);

		try {

			conexao.conecta();

			List<Fornecedor> fornecedores = dao.buscarFornecedores();

			conexao.fechar();

			return fornecedores;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Fornecedor> buscarFornecedorPorNome(String nomeFornecedor) {
		dao.setConexao(conexao);

		try {

			conexao.conecta();

			List<Fornecedor> fornecedores = dao.buscarFornecedores();

			conexao.fechar();

			return fornecedores;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
