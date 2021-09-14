package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entidade.Cliente;
import entidade.Endereco;
import entidade.Fornecedor;
import servico.EnderecoServico;
import servico.FornecedorServico;
import entidade.Produto;
import servico.ClienteServico;
import servico.ProdutoServico;

public class Principal {

	static EnderecoServico enderecoServico = new EnderecoServico();
	
	static FornecedorServico fornecedorServico = new FornecedorServico();
	
	static ProdutoServico produtoServico = new ProdutoServico();

	static ClienteServico clienteServico = new ClienteServico();

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String args[]) throws ParseException {
		int escolha = 6;

		while (true) {

			if (escolha == 6) {
				escolha = menuPrincipal();
			}

			if (escolha == 0) {
				System.out.println("Programa encerrado!");
				break;
			}

			switch (escolha) {
			case 1:
				menuProduto();
				break;
			case 2:
				menuFornecedor();
				break;
			case 3:
				menuCliente();
				break;
			case 4:
				menuEndereço();
				break;
			/*
			 * case 5:
				menuPedido();
				break;
			 */
			default:
				System.out.println("Opção inválida!");
				break;
			}

			escolha = 6;
		}
	}

	public static int menuPrincipal() {

		System.out.println("Escolha uma das opções a seguir:");
		System.out.println("(1) Produto");
		System.out.println("(2) Fornecedor");
		System.out.println("(3) Cliente");
		System.out.println("(4) Endereço");
		System.out.println("(5) Realizar Pedido");
		System.out.println("(0) Sair");

		int escolha = scanner.nextInt();

		return escolha;
	}

	public static int menuCadastro() {

		System.out.println("O que você deseja fazer?");
		System.out.println("(1) Cadastrar");
		System.out.println("(2) Editar");
		System.out.println("(3) Remover");
		System.out.println("(4) Listar Todos Cadastros");
		System.out.println("(5) Voltar ao Menu principal");

		int escolha = scanner.nextInt();

		return escolha;
	}

	public static void menuProduto() {

		switch (menuCadastro()) {
		case 1:
			cadastraProduto();
			break;
		case 2:
			editaProduto();
			break;
		case 3:
			removeProduto();
			break;
		case 4:
			consultaProdutos();
			break;
		case 5:
			break;
		default:
			System.out.println("Opção inválida!");
			break;
		}
	}

	public static Produto infoProduto() {
		Produto produto = new Produto();

		System.out.println("nome:");
		String nome = scanner.nextLine();
		System.out.println("nome informado: " + nome);

		System.out.println("preço:");
		double preco = scanner.nextDouble();
		System.out.println("preço informado: " + preco);

		System.out.println("quantidade em estoque:");
		scanner.nextLine();
		int qtd = scanner.nextInt();
		System.out.println("quantidade informada: " + qtd);

		System.out.println("código de barra:");
		scanner.nextLine();
		long codBarra = scanner.nextLong();
		System.out.println("código de barra informado: " + codBarra);

		produto.setNomeProduto(nome);
		produto.setPreco(preco);
		produto.setQtdEstoque(qtd);
		produto.setCodigoBarra(codBarra);

		return produto;
	}

	public static void cadastraProduto() {

		System.out.println("Digite as informações do produto");
		scanner.nextLine();

		Produto produto = infoProduto();

		int idProduto = produtoServico.inserirProduto(produto); // variável que salva o id retornado no método

		String msgProdutoInserido = "Produto " + produto.getNomeProduto() + " inserido com sucesso!";

		if (idProduto > 0) {
			System.out.println(msgProdutoInserido);
			System.out.println();
		} else {
			System.out.println("Erro ao inserir produto");
		}
	}

	public static void editaProduto() {

		System.out.println("Digite o nome do produto que deseja editar");

		scanner.nextLine();
		String nome = scanner.nextLine();

		List<Produto> produtos = produtoServico.buscarProdutosPorNome(nome);

		for (Produto produto : produtos) {
			System.out.println(produto);
		}

		System.out.println();
		System.out.println("Digite o ID do produto para confirmar");

		int idProduto = scanner.nextInt();
		scanner.nextLine();
		System.out.println("ID informado: " + idProduto);

		System.out.println("Digite as novas informações do produto");

		Produto produto = infoProduto();

		produto.setId(idProduto);

		String msgProdutoAtualizado = produtoServico.alterarProduto(produto);

		System.out.println(msgProdutoAtualizado);
		System.out.println();

	}

	public static void removeProduto() {
		System.out.println("Digite o nome do produto que deseja remover");
		scanner.nextLine();

		String nome = scanner.nextLine();

		List<Produto> produtos = produtoServico.buscarProdutosPorNome(nome);

		for (Produto produto : produtos) {
			System.out.println(produto);
		}

		System.out.println("Digite o ID do produto para confirmar");

		int idProduto = scanner.nextInt();

		Produto produto = new Produto();

		produto.setId(idProduto);
		String msgProdutoExcluido = produtoServico.excluirProduto(produto);

		System.out.println(msgProdutoExcluido);
		System.out.println();
	}

	public static void consultaProdutos() {

		List<Produto> todosProdutos = produtoServico.buscarTodosProdutos();

		for (Produto produto : todosProdutos) {
			System.out.println(produto);
		}
		
		System.out.println();
	}

	public static void menuCliente() throws ParseException {

		switch (menuCadastro()) {
		case 1:
			cadastraCliente();
			break;
		case 2:
			editaCliente();
			break;
		case 3:
			removeCliente();
			break;
		case 4:
			consultaClientes();
			break;
		case 5:
			break;
		default:
			System.out.println("Opção inválida!");
			break;
		}
	}

	public static Cliente infoCliente() throws ParseException {
		Cliente cliente = new Cliente();

		System.out.println("nome:");
		String nome = scanner.nextLine();
		System.out.println("nome informado: " + nome);

		System.out.println("cpf:");
		String cpf = scanner.nextLine();
		System.out.println("cpf informado: " + cpf);

		System.out.println("email:");
		String email = scanner.nextLine();
		System.out.println("email informado: " + email);

		System.out.println("data de nascimento:");
		String nascimento = scanner.nextLine();
		System.out.println("data de nascimento informada: " + nascimento);

		cliente.setNomeCliente(nome);
		cliente.setCpf(cpf);
		cliente.setEmail(email);
		Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento);
		cliente.setNascimento(dataNascimento);
		
		return cliente;
	}

	public static void cadastraCliente() throws ParseException {

		System.out.println("Digite as informações do cliente");
		scanner.nextLine();

		Cliente cliente = infoCliente();

		int idCliente = clienteServico.inserirCliente(cliente);

		String msgClienteInserido = "Cliente " + cliente.getNomeCliente() + " inserido com sucesso!";

		if (idCliente > 0) {
			System.out.println(msgClienteInserido);
			System.out.println();
		} else {
			System.out.println("Erro ao inserir cliente");
		}
	}

	public static void editaCliente() throws ParseException {

		System.out.println("Digite o nome do cliente que deseja editar");

		scanner.nextLine();
		String nome = scanner.nextLine();

		List<Cliente> clientes = clienteServico.buscarClientesPorNome(nome);

		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

		System.out.println();
		System.out.println("Digite o ID do cliente para confirmar");

		int idCliente = scanner.nextInt();
		scanner.nextLine();
		System.out.println(" informado: " + idCliente);

		System.out.println("Digite as novas informações do cliente");

		Cliente cliente = infoCliente();

		cliente.setId(idCliente);

		String msgClienteAtualizado = clienteServico.alterarCliente(cliente);

		System.out.println(msgClienteAtualizado);
		System.out.println();

	}

	public static void removeCliente() {
		System.out.println("Digite o nome do cliente que deseja remover");
		scanner.nextLine();

		String nome = scanner.nextLine();

		List<Cliente> clientes = clienteServico.buscarClientesPorNome(nome);

		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

		System.out.println("Digite o ID do cliente para confirmar");

		int idCliente = scanner.nextInt();

		Cliente cliente = new Cliente();

		cliente.setId(idCliente);
		String msgClienteExcluido = clienteServico.excluirCliente(cliente);

		System.out.println(msgClienteExcluido);
		System.out.println();
	}

	public static void consultaClientes() {

		List<Cliente> todosClientes = clienteServico.buscarTodosClientes();

		for (Cliente cliente : todosClientes) {
			System.out.println(cliente);
		}
		
		System.out.println();
	}
	
	
	
	
	
	// FORNECEDOR
	
	public static void menuFornecedor() throws ParseException {

		switch (menuCadastro()) {
		case 1:
			cadastraFornecedor();
			break;
		case 2:
			editaFornecedor();
			break;
		case 3:
			removeFornecedor();
			break;
		case 4:
			consultaFornecedor();
			break;
		case 5:
			break;
		default:
			System.out.println("Opção inválida!");
			break;
		}
	}
	
	
	
	public static Fornecedor infoFornecedor() throws ParseException {
		Fornecedor fornecedor = new Fornecedor();

		System.out.println("Nome do Fornecedor:");
		String nomeFornecedor = scanner.nextLine();
		System.out.println("Nome informado: " + nomeFornecedor);

		System.out.println("Email:");
		String email = scanner.nextLine();
		System.out.println("Email informado: " + email);

		System.out.println("Telefone:");
		String telefone = scanner.nextLine();
		System.out.println("Telefone informado: " + telefone);

		fornecedor.setNomeFornecedor(nomeFornecedor);
		fornecedor.setEmail(email);
		fornecedor.setTelefone(telefone);
		
		return fornecedor;
	}

	public static void cadastraFornecedor() throws ParseException {

		System.out.println("Digite as informações do fornecedor");
		scanner.nextLine();

		Fornecedor fornecedor = infoFornecedor();

		int idFornecedor = fornecedorServico.inserirFornecedor(fornecedor);

		String msgFornecedorInserido = "Fornecedor " + fornecedor.getNome() + " inserido com sucesso!";

		if (idFornecedor > 0) {
			System.out.println(msgFornecedorInserido);
			System.out.println();
		} else {
			System.out.println("Erro ao inserir fornecedor");
		}
	}

	public static void editaFornecedor() throws ParseException {

		System.out.println("Digite o nome do fornecedor que deseja editar");

		scanner.nextLine();
		String nome = scanner.nextLine();

		List<Fornecedor> fornecedores = fornecedorServico.buscarFornecedorPorNome(nome);

		for (Fornecedor fornecedor : fornecedores) {
			System.out.println(fornecedor);
		}

		System.out.println();
		System.out.println("Digite o ID do fornecedor para confirmar");

		int idFornecedor = scanner.nextInt();
		scanner.nextLine();
		System.out.println("ID informado: " + idFornecedor);

		System.out.println("Digite as novas informações do fornecedor");

		Fornecedor fornecedor = infoFornecedor();

		fornecedor.setId(idFornecedor);

		String msgFornecedorAtualizado = fornecedorServico.alterarFornecedor(fornecedor);

		System.out.println(msgFornecedorAtualizado);
		System.out.println();

	}

	public static void removeFornecedor() {
		System.out.println("Digite o nome do fornecedor que deseja remover");
		scanner.nextLine();

		String nome = scanner.nextLine();

		List<Fornecedor> fornecedores = fornecedorServico.buscarFornecedorPorNome(nome);

		for (Fornecedor fornecedor: fornecedores) {
			System.out.println(fornecedor);
		}

		System.out.println("Digite o ID do fornecedor para confirmar");

		int idFornecedor = scanner.nextInt();

		Fornecedor fornecedor = new Fornecedor();

		fornecedor.setId(idFornecedor);
		String msgFornecedorExcluido = fornecedorServico.excluirFornecedor(fornecedor);

		System.out.println(msgFornecedorExcluido);
		System.out.println();
	}

	public static void consultaFornecedor() {

		List<Fornecedor> todosFornecedores = fornecedorServico.buscarTodosFornecedores();

		for (Fornecedor fornecedor: todosFornecedores) {
			System.out.println(fornecedor);
		}
		
		System.out.println();
	}
	
	
	// ENDEREÇO
	
	public static void menuEndereço() throws ParseException {

		switch (menuCadastro()) {
		case 1:
			cadastraEndereço();
			break;
		case 2:
			editaEndereço();
			break;
		case 3:
			removeEndereço();
			break;
		case 4:
			consultaEndereço();
			break;
		case 5:
			break;
		default:
			System.out.println("Opção inválida!");
			break;
		}
	}
	
	
	
	public static Endereco infoEndereço() throws ParseException {
		Endereco endereco = new Endereco();
		
		
		System.out.println("Informe as informações do endereço:");
		scanner.nextLine();
		System.out.println("Cidade:");
		String cidade = scanner.nextLine();
		System.out.println("Cidade informada: " + cidade);

		System.out.println("CEP:");
		String cep = scanner.nextLine();
		System.out.println("CEP informado: " + cep);

		System.out.println("Estado:");
		String estado = scanner.nextLine();
		System.out.println("Estado informado: " + estado);
		
		System.out.println("Rua:");
		String rua = scanner.nextLine();
		System.out.println("Rua informada: " + rua);
		
		System.out.println("Bairro:");
		String bairro = scanner.nextLine();
		System.out.println("Bairro informado: " + bairro);
		
		System.out.println("Número:");
		int numero = scanner.nextInt();
		System.out.println("Número informado: " + numero);
		

		endereco.setCidade(cidade);
		endereco.setCep(cep);
		endereco.setEstado(estado);
		endereco.setRua(rua);
		endereco.setBairro(bairro);
		endereco.setNumero(numero);
		
		return endereco;
	}

	public static void cadastraEndereço() throws ParseException {

		Endereco endereco = infoEndereço();

		int idEndereco = enderecoServico.inserirEndereco(endereco);
		
		String msgEndereçoInserido = "Endereço inserido com sucesso!";

		if (idEndereco > 0) {
			System.out.println(msgEndereçoInserido);
			System.out.println();
		} else {
			System.out.println("Erro ao inserir endereço");
		}
	}

	public static void editaEndereço() throws ParseException {

		System.out.println("Digite o endereço que deseja editar pela cidade:");

		scanner.nextLine();
		String cidade = scanner.nextLine();

		List<Endereco> enderecos = enderecoServico.buscarEnderecoPorCidade(cidade);

		for (Endereco endereco : enderecos) {
			System.out.println(endereco);
		}

		System.out.println();
		System.out.println("Digite o ID do endereço para confirmar");

		int idEndereco = scanner.nextInt();
		scanner.nextLine();
		System.out.println("ID informado: " + idEndereco);

		System.out.println("Digite as novas informações do endereco");

		Endereco endereco = infoEndereço();

		endereco.setId(idEndereco);

		String msgFornecedorAtualizado = enderecoServico.atualizarEndereco(endereco);

		System.out.println(msgFornecedorAtualizado);
		System.out.println();

	}

	public static void removeEndereço() {
		System.out.println("Digite o nome do endereço que deseja remover");
		scanner.nextLine();

		String cidade = scanner.nextLine();

		List<Endereco> enderecos = enderecoServico.buscarEnderecoPorCidade(cidade);

		for (Endereco endereco: enderecos) {
			System.out.println(endereco);
		}

		System.out.println("Digite o ID do endereço para confirmar");

		int idEndereco = scanner.nextInt();

		Endereco endereco = new Endereco();

		endereco.setId(idEndereco);
		String msgEnderecoExcluido = enderecoServico.excluirEndereco(endereco);

		System.out.println(msgEnderecoExcluido);
		System.out.println();
	}

	public static void consultaEndereço() {

		List<Endereco> todosEnderecos = enderecoServico.buscarTodosEnderecos();

		for (Endereco endereco: todosEnderecos) {
			System.out.println(endereco);
		}
		
		System.out.println();
	}
	
	
}

/*
 * // ENDEREÇOS
 * 
 * // Insere endereço
 * 
 * Endereco endereco1 = new Endereco(); endereco1.setCidade("Indaial");
 * endereco1.setCep("89138-321"); endereco1.setRua("Teste");
 * endereco1.setBairro("Tamanduá"); endereco1.setEstado("SC");
 * endereco1.setNumero(12);
 * 
 * int idEnd1 = enderecoServico.inserirEndereco(endereco1); String
 * msgEnd1Inserido = "Endereço" + " inserido com sucesso!";
 * 
 * if (idEnd1 > 0) { System.out.println(msgEnd1Inserido); } else {
 * System.out.println("Erro ao inserir endereço"); }
 * 
 * // Exclui o endereço inserido para teste int idEndTeste =
 * enderecoServico.inserirEndereco(endereco1); endereco1.setId(idEndTeste);
 * String msgExclusaoEnd = enderecoServico.excluirEndereco(endereco1);
 * System.out.println(msgExclusaoEnd);
 * 
 * endereco1.setId(idEnd1);
 * 
 * cliente1.setEndereco(endereco1);
 * 
 * // String msgAlteraCliente1 = clienteServico.alterarCliente(cliente1); //
 * System.out.println(msgAlteraCliente1);
 * 
 * // Cria e atualiza em seguida endereço cliente 2
 * 
 * Endereco endereco2 = new Endereco(); endereco2.setCidade("Ascurra");
 * endereco2.setCep("89138-543"); endereco2.setRua("Teste");
 * endereco2.setBairro("Ribeirão"); endereco2.setEstado("SC");
 * endereco2.setNumero(36);
 * 
 * int IdEnd2 = enderecoServico.inserirEndereco(endereco2); String msgAlteraEnd
 * = enderecoServico.atualizarEndereco(endereco2);
 * System.out.println(msgAlteraEnd);
 * 
 * endereco2.setId(IdEnd2); cliente2.setEndereco(endereco2);
 * clienteServico.alterarCliente(cliente2);
 * 
 * System.out.println(msgCliente2Atualizado);
 * 
 * // Busca endereços List<Endereco> enderecos =
 * enderecoServico.buscarEndereco(); for (Endereco endereco : enderecos) {
 * System.out.println(endereco); }
 * 
 * }
 */