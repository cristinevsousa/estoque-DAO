package entidade;

import java.util.Date;

public class Fornecedor {

	private int id;
	private String nomeFornecedor;
	private Date dataEntrega;
	private String produto;
	private int qtdEntregue;
	
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
	public int getQtd() {
		return qtdEntregue;
	}
	public void setQtd(int qtdEntregue) {
		this.qtdEntregue = qtdEntregue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
