package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entidade.Cliente;
import entidade.Endereco;
import entidade.Produto;
import servico.ClienteServico;
import servico.ProdutoServico;

public class Principal {

	public static void main(String args[]) throws ParseException {

		ClienteServico clienteServico = new ClienteServico();

		// Insere cliente 1

		Cliente cliente1 = new Cliente();
		cliente1.setNomeCliente("João da Silva");
		cliente1.setCpf("000.000.000.00");
		cliente1.setEmail("joao@gmail.com");
		Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse("20/02/1990");
		cliente1.setNascimento(dataNascimento);

		Endereco endereco1 = new Endereco();

		endereco1.setBairro("Centro");
		endereco1.setCep("88054-500");
		endereco1.setCidade("Florianópolis");
		endereco1.setEstado("SC");
		endereco1.setRua("Exemplo de uma rua");
		endereco1.setNumero(500);

		cliente1.setEndereco(endereco1);

		long idCliente1 = clienteServico.inserirCliente(cliente1);

		String msgCliente1Inserido = "Cliente " + cliente1.getNomeCliente() + " inserido com sucesso!";

		if (idCliente1 > 0) {
			System.out.println(msgCliente1Inserido);
		} else {
			System.out.println("Erro ao inserir cliente");
		}

		// Insere cliente 2

		Cliente cliente2 = new Cliente();
		cliente2.setNomeCliente("Maria Oliveira");
		cliente2.setCpf("100.000.000.00");
		cliente2.setEmail("maria@gmail.com");
		Date dataNascimento2 = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/1990");
		cliente2.setNascimento(dataNascimento2);

		Endereco endereco2 = new Endereco();

		endereco2.setBairro("Vargem Grande");
		endereco2.setCep("88054-000");
		endereco2.setCidade("Florianópolis");
		endereco2.setEstado("SC");
		endereco2.setRua("Exemplo de outra rua");
		endereco2.setNumero(600);

		cliente2.setEndereco(endereco2);

		long idCliente2 = clienteServico.inserirCliente(cliente2);

		String msgCliente2Inserido = "Cliente " + cliente1.getNomeCliente() + " inserido com sucesso!";

		if (idCliente2 > 0) {
			System.out.println(msgCliente2Inserido);
		} else {
			System.out.println("Erro ao inserir cliente");
		}

		// Altera cliente 2

		cliente2.setId(idCliente2);
		cliente1.setEmail("maria2@gmail.com");
		String msgCliente2Atualizado = clienteServico.alterarCliente(cliente2);

		System.out.println(msgCliente2Atualizado);

		// Exclui cliente 2

		String msgCliente2Excluido = clienteServico.excluirCliente(cliente2);

		System.out.println(msgCliente2Excluido);

		// Busca cliente por nome

		List<Cliente> clientes = clienteServico.buscarClientesPorNome("João");

		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

		ProdutoServico produtoServico = new ProdutoServico();

		// Insere produto 1

		Produto produto1 = new Produto();
		produto1.setNomeProduto("copo");
		produto1.setCodigoBarra(1564829786454L);
		produto1.setPreco(5.00);
		produto1.setQtd(3);

		long idProduto1 = produtoServico.inserirProduto(produto1);
		String msgProduto1Inserido = "Produto " + produto1.getNomeProduto() + " inserido com sucesso!";

		if (idProduto1 > 0) {
			System.out.println(msgProduto1Inserido);
		} else {
			System.out.println("Erro ao inserir produto");
		}

		// Insere produto 2

		Produto produto2 = new Produto();
		produto2.setNomeProduto("colher");
		produto2.setCodigoBarra(1864839787456L);
		produto2.setPreco(2.00);
		produto2.setQtd(6);

		long idProduto2 = produtoServico.inserirProduto(produto2);
		String msgProduto2Inserido = "Produto " + produto2.getNomeProduto() + " inserido com sucesso!";

		if (idProduto2 > 0) {
			System.out.println(msgProduto2Inserido);
		} else {
			System.out.println("Erro ao inserir produto");
		}

		// Busca produtos

		List<Produto> produtos = produtoServico.buscarProdutosPorNome("co");

		for (Produto produto : produtos) {
			System.out.println(produto);
		}

		// Altera produto 2

		produto2.setId(idProduto2);
		produto2.setPreco(2.00);
		String msgProduto2Atualizado = produtoServico.alterarProduto(produto2);

		System.out.println(msgProduto2Atualizado);

		// Exclui produto 2

		String msgProduto2Excluido = produtoServico.excluirProduto(produto2);

		System.out.println(msgProduto2Excluido);

	}

}
