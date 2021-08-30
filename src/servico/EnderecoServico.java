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

	public int inserirEndereco(Endereco endereco) {
		dao.setEndereco(endereco);
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

	public String atualizarEndereco(Endereco endereco) {
		dao.setEndereco(endereco);
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
		return "Endereço atualizado com sucesso!";

	}

	public String excluirEndereco(Endereco endereco) {
		dao.setEndereco(endereco);
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
