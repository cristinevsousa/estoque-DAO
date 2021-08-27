package main;

import java.util.List;

import entidade.Cliente;
import servico.ClienteServico;

public class Principal {
	
	public static void main(String args[]) {
		
		ClienteServico servico = new ClienteServico();
		
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNomeCliente("João da Silva");
		
		String message = servico.inserirCliente(cliente1);
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNomeCliente("Maria Oliveira");
		
		String message2 = servico.excluirCliente(cliente2);
		
		System.out.println(message);
		System.out.println(message2);
		
		List<Cliente> clientes = servico.buscarClientesPorNome("João");
		
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}		
		
	}
	
	

}
