package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidade.Fornecedor;
import utils.Conexao;

public class FornecedorDAO implements IGerenciamentoDAO {

	private Fornecedor fornecedor;
	private Conexao conexao;

public Fornecedor getFornecedor(){
	return fornecedor;
}

public void setFornecedor(Fornecedor fornecedor){
	this.fornecedor = fornecedor;
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
		
		PreparedStatement pst = this.conexao.getConexao().prepareStatement
				("INSERT INTO fornecedor (nomeFornecedor, dataEntrega, produto, qtdEntregue) VALUES (?, ?, ?, ?)");
		
		pst.setString(1, fornecedor.getNomeFornecedor());
		pst.setString(2, fornecedor.getDataEntrega());
		pst.setString(3, fornecedor.getProduto());
		pst.setString(4, fornecedor.getQtdEntregue());
		
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
		
		PreparedStatement pst = this.conexao.getConexao().prepareStatement
				("UPDATE endereco SET nomeFornecedor = ?, dataEntrega = ?, produto = ?, qtdEntregue = ?, bairro = ?, numero = ? WHERE id = ?");
		
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
	// TODO Auto-generated method stub
	return false;
}

}

