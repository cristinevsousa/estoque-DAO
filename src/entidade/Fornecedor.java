package entidade;

import java.util.Date;

public class Fornecedor extends ClasseBase {

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
	public int getQtdEntregue() {
		return qtdEntregue;
	}
	public void setQtdEntregue(int qtdEntregue) {
		this.qtdEntregue = qtdEntregue;
	}
	@Override
	public String toString() {
		return "[Id: " + getId() + "Nome Fornecedor: " + nomeFornecedor + ", Data Entrega: " + dataEntrega + ", Produto: " + produto
				+ ", Qtd. Entregue=" + qtdEntregue + "]";
	}
		
}
