package entidade;

public class Produto {

	private Long id;
	private String nomeProduto;
	private double preco;
	private int qtd;
	private long codigoBarra;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public long getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(long l) {
		this.codigoBarra = l;
	}
	@Override
	public String toString() {
		return "Produto [Produto=" + nomeProduto + ", preco=" + preco + ", qtd=" + qtd + "]";
	}
	
}
