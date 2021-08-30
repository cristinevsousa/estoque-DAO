package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidade.Pedido;
import utils.Conexao;

public class PedidoDAO implements IGerenciamentoDAO {

	private Pedido pedido;

	private Conexao conexao;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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

			PreparedStatement pst = this.conexao.getConexao().prepareStatement("INSERT INTO pedido (id) VALUES (?)", 1); // 1 significando parâmetro para retornar valor da PK

			pst.setInt(1, pedido.getId());

			pst.executeUpdate();
			
			ResultSet keys = pst.getGeneratedKeys(); // método da classe PreparedStatement que retorna pk

			keys.next();
            int key = keys.getInt(1);

			pst.close();
			
			return key;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public boolean atualizar() {
		try {

			PreparedStatement pst = this.conexao.getConexao().prepareStatement("UPDATE pedido SET id = ? WHERE id = ?");

			pst.setInt(1, pedido.getId());
			pst.setInt(2, pedido.getId());

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

			PreparedStatement pst = this.conexao.getConexao().prepareStatement("DELETE FROM pedido WHERE id = ?");

			pst.setInt(1, pedido.getId());

			pst.executeUpdate();

			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public List<Pedido> buscarPedido() {

		List<Pedido> pedidos = new ArrayList<Pedido>();

		try {

			PreparedStatement pst = this.conexao.getConexao().prepareStatement("SELECT * FROM pedido");

			ResultSet result = pst.executeQuery();

			while (result.next()) {

				Pedido pedido = new Pedido();
				pedido.setId(result.getInt("id"));

			}

			result.close();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return pedidos;
	}

	public List<Pedido> buscarPedidoId(String id) {
		List<Pedido> pedidos = new ArrayList<Pedido>();

		try {

			PreparedStatement pst = this.conexao.getConexao().prepareStatement("SELECT * FROM pedido WHERE id like ?");

			pst.setString(0, "%" + id + "%");

			ResultSet result = pst.executeQuery();

			while (result.next()) {

				Pedido pedido = new Pedido();
				pedido.setId(result.getInt("id"));

			}

			result.close();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return pedidos;
	}

}
