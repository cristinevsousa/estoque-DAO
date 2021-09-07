package entidade;

public class Produto extends ClasseBase {

	private String nomeProduto;
	private int qtdEstoque;
	private double preco;
	private long codigoBarra;
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public long getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(long codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	@Override
	public String toString() {
		return "[Id: " + getId() + ", Produto: " + nomeProduto + ", Preço: " + preco + ", Quantidade: " + qtdEstoque + ", Cód. de barra: " + codigoBarra + "]";
	}	
}
