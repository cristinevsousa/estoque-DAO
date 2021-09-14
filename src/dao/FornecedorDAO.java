package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidade.Cliente;
import entidade.Fornecedor;
import utils.Conexao;

public class FornecedorDAO implements IGerenciamentoDAO {

	private Fornecedor fornecedor;
	private Conexao conexao;

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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

			PreparedStatement pst = this.conexao.getConexao().prepareStatement(
					"INSERT INTO fornecedor (nome, email, telefone) VALUES (?, ?, ?)",
					1); // 1 significando parâmetro para retornar valor da PK

			pst.setString(1, fornecedor.getNome());
			pst.setString(2, fornecedor.getEmail());
			pst.setString(3, fornecedor.getTelefone());

			pst.executeUpdate();

			ResultSet keysFornecedor = pst.getGeneratedKeys(); // método da classe PreparedStatement que retorna pk

			keysFornecedor.next();
			int keyFornecedor = keysFornecedor.getInt(1);

			pst.close();

			return keyFornecedor;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean atualizar() {
		try {

			PreparedStatement pst = this.conexao.getConexao().prepareStatement(
					"UPDATE endereco SET nome = ?, email = ?, telefone = ? WHERE id = ?");

			pst.setString(1, fornecedor.getNome());
			pst.setString(2, fornecedor.getEmail());
			pst.setString(3, fornecedor.getTelefone());
			pst.setInt(4, fornecedor.getId());

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

			PreparedStatement pst = this.conexao.getConexao().prepareStatement("DELETE FROM fornecedor WHERE id=?");

			pst.setLong(1, fornecedor.getId());

			pst.executeUpdate();

			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public List<Fornecedor> buscarFornecedores() {
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

		try {

			PreparedStatement pst = this.conexao.getConexao().prepareStatement("SELECT * FROM fornecedor");

			ResultSet result = pst.executeQuery();

			while (result.next()) {

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(result.getInt("id"));
				fornecedor.setNomeFornecedor(result.getString("nome"));
				fornecedor.setEmail(result.getString("email"));
				fornecedor.setTelefone(result.getString("telefone"));

				fornecedores.add(fornecedor);
			}

			result.close();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return fornecedores;
	}

	public List<Fornecedor> buscarFornecedorsPorNome(String nome, String email, String telefone) {
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

		try {

			PreparedStatement pst = this.conexao.getConexao()
					.prepareStatement("SELECT * FROM fornecedor WHERE nome like ?");

			pst.setString(1, "%" + nome + "%");

			ResultSet result = pst.executeQuery();

			while (result.next()) {

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(result.getInt("id"));
				fornecedor.setNomeFornecedor(result.getString("nome"));

				fornecedores.add(fornecedor);
			}

			result.close();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return fornecedores;
	}

}
