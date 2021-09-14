package entidade;

public class Fornecedor extends ClasseBase {

	private String nome;
	private String email;
	private String telefone;
	
	public String getNome() {
		return nome;
	}
	public void setNomeFornecedor(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public String toString() {
		return "[Id: " + getId() + "Nome Fornecedor: " + nome + "E-Mail: " + email
				+ ", Telefone =" + telefone + "]";
	}
		
}
