package entidade;

public class Endereco extends ClasseBase {

	private String cidade;
	private String cep;
	private String estado;
	private String rua;
	private int numero;
	private String bairro;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public  String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	@Override
	public String toString() {
		return "Endereco [id=" + super.getId() + ", cidade=" + cidade + ", cep=" + cep + ", estado=" + estado + ", rua=" + rua
				+ ", numero=" + numero + ", bairro=" + bairro + "]";
	}
	
}
