package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entidade.Cliente;
import entidade.Endereco;
import servico.EnderecoServico;
import entidade.Produto;
import servico.ClienteServico;
import servico.ProdutoServico;

public class Principal {

	public static void main(String args[]) throws ParseException {

		// CLIENTES
		ClienteServico clienteServico = new ClienteServico();

		// Insere cliente 1 com endereço

		Cliente cliente1 = new Cliente();

		cliente1.setNomeCliente("João da Silva");
		cliente1.setCpf("000.000.000.00");
		cliente1.setEmail("joao@gmail.com");
		Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse("20/02/1990");
		cliente1.setNascimento(dataNascimento);

		Endereco enderecoCliente1 = new Endereco();

		enderecoCliente1.setBairro("Centro");
		enderecoCliente1.setCep("88054-500");
		enderecoCliente1.setCidade("Florianópolis");
		enderecoCliente1.setEstado("SC");
		enderecoCliente1.setRua("Exemplo de uma rua");
		enderecoCliente1.setNumero(500);

		EnderecoServico enderecoServico = new EnderecoServico();

		int idEndCliente1 = enderecoServico.inserirEndereco(enderecoCliente1);

		enderecoCliente1.setId(idEndCliente1);

		cliente1.setEndereco(enderecoCliente1);

		int idCliente1 = clienteServico.inserirCliente(cliente1); // variável que salva o id retornado no método

		String msgCliente1Inserido = "Cliente " + cliente1.getNomeCliente() + " inserido com sucesso!";

		if (idCliente1 > 0) { // se o id for maior que 0 é porque foi inserido no banco
			System.out.println(msgCliente1Inserido);
		} else {
			System.out.println("Erro ao inserir cliente");
		}

		// Insere cliente 2 com endereço

		Cliente cliente2 = new Cliente();

		cliente2.setNomeCliente("Maria Oliveira");
		cliente2.setCpf("100.000.000.00");
		cliente2.setEmail("maria@gmail.com");
		Date dataNascimento2 = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/1990");
		cliente2.setNascimento(dataNascimento2);

		Endereco enderecoCliente2 = new Endereco();

		enderecoCliente2.setBairro("Vargem Grande");
		enderecoCliente2.setCep("88054-000");
		enderecoCliente2.setCidade("Florianópolis");
		enderecoCliente2.setEstado("SC");
		enderecoCliente2.setRua("Exemplo de outra rua");
		enderecoCliente2.setNumero(600);

		int idEndCliente2 = enderecoServico.inserirEndereco(enderecoCliente2);

		enderecoCliente2.setId(idEndCliente2);

		cliente2.setEndereco(enderecoCliente2);

		int idCliente2 = clienteServico.inserirCliente(cliente2); // variável que salva o id retornado no método

		String msgCliente2Inserido = "Cliente " + cliente2.getNomeCliente() + " inserido com sucesso!";

		if (idCliente2 > 0) { // se o id for maior que 0 é porque foi inserido no banco
			System.out.println(msgCliente2Inserido);
		} else {
			System.out.println("Erro ao inserir cliente");
		}

		// Altera cliente 2

		cliente2.setId(idCliente2); // seta o id para o método que busca (pelo id) o cliente que será alterado
		cliente2.setEmail("maria2@gmail.com");
		String msgCliente2Atualizado = clienteServico.alterarCliente(cliente2);

		System.out.println(msgCliente2Atualizado);

		// Busca cliente por nome

		List<Cliente> clientes = clienteServico.buscarClientesPorNome("o");

		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

		// Exclui cliente 2

		String msgCliente2Excluido = clienteServico.excluirCliente(cliente2);

		System.out.println(msgCliente2Excluido);

		// Busca clientes

		List<Cliente> todosClientes = clienteServico.buscarTodosClientes();

		for (Cliente cliente : todosClientes) {
			System.out.println(cliente);
		}

		// ENDEREÇOS

		// Insere endereço

		Endereco endereco1 = new Endereco();
		endereco1.setCidade("Indaial");
		endereco1.setCep("89138-321");
		endereco1.setRua("Teste");
		endereco1.setBairro("Tamanduá");
		endereco1.setEstado("SC");
		endereco1.setNumero(12);

		int idEnd1 = enderecoServico.inserirEndereco(endereco1); // variável que salva o id retornado no método
		String msgEnd1Inserido = "Endereço" + " inserido com sucesso!";

		if (idEnd1 > 0) { // se o id for maior que 0 é porque foi inserido no banco
			System.out.println(msgEnd1Inserido);
		} else {
			System.out.println("Erro ao inserir endereço");
		}

		// Exclui o endereço inserido para teste
		int idEndTeste = enderecoServico.inserirEndereco(endereco1);
		endereco1.setId(idEndTeste);
		String msgExclusaoEnd = enderecoServico.excluirEndereco(endereco1);
		System.out.println(msgExclusaoEnd);

		// Altera endereço de cliente já cadastrado antes

		endereco1.setId(idEnd1); // seta o id para o método que busca (pelo id) o cliente que terá o endereço
									// alterado
		cliente1.setEndereco(endereco1);

		//String msgAlteraCliente1 = clienteServico.alterarCliente(cliente1);
		//System.out.println(msgAlteraCliente1);

		// Cria e atualiza em seguida endereço cliente 2

		Endereco endereco2 = new Endereco();
		endereco2.setCidade("Ascurra");
		endereco2.setCep("89138-543");
		endereco2.setRua("Teste");
		endereco2.setBairro("Ribeirão");
		endereco2.setEstado("SC");
		endereco2.setNumero(36);

		int IdEnd2 = enderecoServico.inserirEndereco(endereco2);
		String msgAlteraEnd = enderecoServico.atualizarEndereco(endereco2);
		System.out.println(msgAlteraEnd);

		endereco2.setId(IdEnd2); // seta o id para o método que busca (pelo id) o cliente que terá o endereço
									// alterado
		cliente2.setEndereco(endereco2);
		clienteServico.alterarCliente(cliente2);

		System.out.println(msgCliente2Atualizado);

		// Busca endereços
		List<Endereco> enderecos = enderecoServico.buscarEndereco();
		for (Endereco endereco : enderecos) {
			System.out.println(endereco);
		}

		// PRODUTOS
		ProdutoServico produtoServico = new ProdutoServico();

		// Insere produto 1

		Produto produto1 = new Produto();
		produto1.setNomeProduto("copo");
		produto1.setCodigoBarra(1564829786454L);
		produto1.setPreco(5.00);
		produto1.setQtdEstoque(3);

		int idProduto1 = produtoServico.inserirProduto(produto1); // variável que salva o id retornado no método
		String msgProduto1Inserido = "Produto " + produto1.getNomeProduto() + " inserido com sucesso!";

		if (idProduto1 > 0) { // se o id for maior que 0 é porque foi inserido no banco
			System.out.println(msgProduto1Inserido);
		} else {
			System.out.println("Erro ao inserir produto");
		}

		// Insere produto 2

		Produto produto2 = new Produto();
		produto2.setNomeProduto("colher");
		produto2.setCodigoBarra(1864839787456L);
		produto2.setPreco(2.00);
		produto2.setQtdEstoque(6);

		int idProduto2 = produtoServico.inserirProduto(produto2); // variável que salva o id retornado no método
		String msgProduto2Inserido = "Produto " + produto2.getNomeProduto() + " inserido com sucesso!";

		if (idProduto2 > 0) { // se o id for maior que 0 é porque foi inserido no banco
			System.out.println(msgProduto2Inserido);
		} else {
			System.out.println("Erro ao inserir produto");
		}

		// Altera produto 2

		produto2.setId(idProduto2); // seta o id para o método que busca (pelo id) o produto que será alterado
		produto2.setPreco(2.00);
		String msgProduto2Atualizado = produtoServico.alterarProduto(produto2);

		System.out.println(msgProduto2Atualizado);

		// Busca produto por nome

		List<Produto> produtos = produtoServico.buscarProdutosPorNome("co");

		for (Produto produto : produtos) {
			System.out.println(produto);
		}

		// Exclui produto 2

		String msgProduto2Excluido = produtoServico.excluirProduto(produto2);

		System.out.println(msgProduto2Excluido);

		// Busca produtos

		List<Produto> todosProdutos = produtoServico.buscarTodosProdutos();

		for (Produto produto : todosProdutos) {
			System.out.println(produto);
		}

	}

}
