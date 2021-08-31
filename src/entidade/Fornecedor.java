package entidade;

import java.util.Date;

public class Fornecedor {

	private int id;
	private String nomeFornecedor;
	private Date dataEntrega;
	private String produto;
	private int qtdEntregue;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public int getQtdEntregue() {
		return qtdEntregue;
	}
	public void setQtdEntregue(int qtdEntregue) {
		this.qtdEntregue = qtdEntregue;
	}
}
