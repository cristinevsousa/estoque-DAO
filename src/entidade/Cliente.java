package entidade;

import java.util.Date;

public class Cliente extends ClasseBase {

	private String nomeCliente;
	private String cpf;
	private Date nascimento;
	private Endereco endereco;
	private String email;
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "[Id: " + super.getId() + ", Nome: " + nomeCliente + ", Cpf: " + cpf + ", Email: " + email + ", Data de nascimento: " + nascimento + "]";
	}
	
}
