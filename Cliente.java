package SA4;

import java.util.Date;

public class Cliente extends Endereco {

	private String nomeCliente;
	private String cpf;
	private String email;
	private Date nascimento;
	
	Cliente(String nomeCliente, String cpf, String email, Date nascimento){
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.email = email;
		this.nascimento = nascimento;
		
	}
	
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
