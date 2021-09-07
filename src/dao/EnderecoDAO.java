package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Cliente;
import entidade.Endereco;
import utils.Conexao;

public class EnderecoDAO implements IGerenciamentoDAO {

	private Endereco endereco;
	private Conexao conexao;

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Conexao getConexao() {
		return conexao;
	}
	
	public void setConexao(Conexao conexao) {
		this.conexao = conexao;
	}
	
	public int inserir() {
		try {
			
			PreparedStatement pst = this.conexao.getConexao().prepareStatement
					("INSERT INTO endereco (cidade, cep, rua, bairro, estado, numero) VALUES (?, ?, ?, ?, ?, ?)", 1); // 1 significando parâmetro para retornar valor da PK
			
			pst.setString(1, endereco.getCidade());
			pst.setString(2, endereco.getCep());
			pst.setString(3, endereco.getRua());
			pst.setString(4, endereco.getBairro());
			pst.setString(5, endereco.getEstado());
			pst.setInt(6, endereco.getNumero());
			
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
			
			PreparedStatement pst = this.conexao.getConexao().prepareStatement
					("UPDATE endereco SET cidade = ?, cep = ?, estado = ?, rua = ?, bairro = ?, numero = ? WHERE id = ?");
			
			pst.setString(1, endereco.getCidade());
			pst.setString(2, endereco.getCep());
			pst.setString(3, endereco.getEstado());
			pst.setString(4, endereco.getRua());
			pst.setString(5, endereco.getBairro());
			pst.setInt(6, endereco.getNumero());
			pst.setInt(7, endereco.getId());
			
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
			
			PreparedStatement pst = this.conexao.getConexao().prepareStatement("DELETE FROM endereco WHERE id = ?");
			
			pst.setInt(1, endereco.getId());
			
			pst.executeUpdate();
			
			pst.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public List<Endereco> buscarEnderecos(){
		List<Endereco> enderecos = new ArrayList<Endereco>();
		
		try {
			
			PreparedStatement pst = this.conexao.getConexao()
					.prepareStatement("SELECT * FROM endereco");
			
			ResultSet result = pst.executeQuery();
			
			while(result.next()) {
				
				Endereco endereco = new Endereco();
				endereco.setId(result.getInt("id"));
				endereco.setCidade(result.getString("cidade"));
				endereco.setCep(result.getString("cep"));
				endereco.setEstado(result.getString("estado"));
				endereco.setRua(result.getString("rua"));
				endereco.setBairro(result.getString("bairro"));
				endereco.setNumero(result.getInt("numero"));
				
				enderecos.add(endereco);
			}
			
			result.close();
			pst.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return enderecos;
	}

}
