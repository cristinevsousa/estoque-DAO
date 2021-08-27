package main;

import java.util.List;

import dao.EnderecoDAO;
import entidade.Cliente;
import entidade.Endereco;
import servico.ClienteServico;
import servico.EnderecoServico;

public class Principal {
	
	public static void main(String args[]) {
		
		ClienteServico servico = new ClienteServico();

		Cliente cliente1 = new Cliente();
		cliente1.setId(1);
		cliente1.setNomeCliente("João da Silva");
		
		String message = servico.inserirCliente(cliente1);
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2);
		cliente2.setNomeCliente("Maria Oliveira");
		
		String message2 = servico.excluirCliente(cliente2);
		
		System.out.println(message);
		System.out.println(message2);
		
		List<Cliente> clientes = servico.buscarClientesPorNome("João");
		
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}		

		EnderecoServico servico1 = new EnderecoServico();
		
		Endereco endereco = new Endereco();
		endereco.setCidade("Indaial");
		endereco.setCep("89138-321");
		endereco.setRua("Teste");
		endereco.setBairro("Tamanduá");
		endereco.setEstado("SC");
		endereco.setNumero(12);
		
		String mensagem = servico1.inserirEndereco(endereco);
		System.out.println(mensagem);
		
		Endereco endereco1 = new Endereco();
		endereco1.setCidade("Ascurra");
		endereco1.setCep("89138-543");
		endereco1.setRua("Teste");
		endereco1.setBairro("Ribeirão");
		endereco1.setEstado("SC");
		endereco1.setNumero(36);
		endereco1.setId(6);
		
		String mensagem1 = servico1.atualizarEndereco(endereco1);
		System.out.println(mensagem1);
		
		endereco1.setId(3);
		String mensagem2 = servico1.excluirEndereco(endereco1);
		System.out.println(mensagem2);
		
		List<Endereco> abc = servico1.buscarEndereco();
		for (Endereco enderecos : abc) {
			System.out.println(abc);
		}
		
	}
	
	

}
