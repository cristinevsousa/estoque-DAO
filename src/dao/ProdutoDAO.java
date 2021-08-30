package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidade.Produto;
import utils.Conexao;

public class ProdutoDAO implements IGerenciamentoDAO {

	private Produto produto;
	
	private Conexao conexao;
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
			
			PreparedStatement pst = this.conexao.getConexao().prepareStatement("INSERT INTO produto (nome, preco, qtd, codigo_barra) VALUES (?,?,?,?)", 1); // 1 significando parâmetro para retornar valor da PK
			
			pst.setString(1, produto.getNomeProduto());
			pst.setDouble(2, produto.getPreco());
			pst.setInt(3, produto.getQtdEstoque());
			pst.setLong(4, produto.getCodigoBarra());
			
			pst.executeUpdate();
			
			ResultSet keys = pst.getGeneratedKeys(); // método da classe PreparedStatement que retorna pk

			keys.next();
            int key = keys.getInt(1);
			
			pst.close();
			
			return key;
			
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public boolean atualizar() {
		try {
			
			PreparedStatement pst = this.conexao.getConexao().prepareStatement("UPDATE produto SET nome = ? WHERE id = ?");
			
			pst.setString(1, produto.getNomeProduto());
			pst.setLong(2, produto.getId());
			
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
			
			PreparedStatement pst = this.conexao.getConexao().prepareStatement("DELETE FROM produto WHERE id = ?");
			
			pst.setLong(1, produto.getId());
			
			pst.executeUpdate();
			
			pst.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public List<Produto> buscarProdutos(){
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			
			PreparedStatement pst = this.conexao.getConexao().prepareStatement("SELECT * FROM produto");
			
			ResultSet result = pst.executeQuery();
			
			while(result.next()) {
				
				Produto produto = new Produto();
				produto.setId(result.getInt("id"));
				produto.setNomeProduto(result.getString("nome"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQtdEstoque(result.getInt("qtd"));
				
				produtos.add(produto);
			}
			
			result.close();
			pst.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return produtos;
	}
	
	public List<Produto> buscarProdutosPorNome(String nome){
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			
			PreparedStatement pst = this.conexao.getConexao()
					.prepareStatement("SELECT * FROM produto WHERE nome like ?");
			
			pst.setString(1, "%"+nome+"%" );
			
			ResultSet result = pst.executeQuery();
			
			while(result.next()) {
				
				Produto produto = new Produto();
				produto.setId(result.getInt("id"));
				produto.setNomeProduto(result.getString("nome"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQtdEstoque(result.getInt("qtd"));
				
				produtos.add(produto);
			}
			
			result.close();
			pst.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return produtos;
	}

	
}
