package servico;

import java.util.List;

import dao.PedidoDAO;
import entidade.Pedido;
import utils.Conexao;

public class PedidoServico {
	
		PedidoDAO dao;
		Conexao conexao;
		
		public PedidoServico() {
			dao = new PedidoDAO();
			conexao = new Conexao();
		}
		
		public String inserirPedido(Pedido pedido) {
			dao.setPedido(pedido);
			dao.setConexao(conexao);
			
			try {
				
				conexao.conecta();
				
				if(dao.inserir()) {
					conexao.confirmarTransacao();
				} else {
					conexao.cancelarTransacao();
				}
				
				conexao.fechar();
				
			}catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
			
			return "Pedido " + pedido.getId() + " inserido com sucesso!";
		}
		
		public String alterarPedido(Pedido pedido) {
			dao.setPedido(pedido);
			dao.setConexao(conexao);
			
			try {
				
				conexao.conecta();
				
				if(dao.atualizar()) {
					conexao.confirmarTransacao();
				} else {
					conexao.cancelarTransacao();
				}
				
				conexao.fechar();
				
			}catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
			
			return "Pedido " + pedido.getId() + " alterado com sucesso!";
		}

		
		public String excluirPedido(Pedido pedido) {
			dao.setPedido(pedido);
			dao.setConexao(conexao);
			
			try {
				
				conexao.conecta();
				
				if(dao.excluir()) {
					conexao.confirmarTransacao();
				} else {
					conexao.cancelarTransacao();
				}
				
				conexao.fechar();
				
			}catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
			
			return "Pedido " + pedido.getId() + " excluído com sucesso!";
		}
		
		public List<Pedido> buscarTodosPedidos() {
			dao.setConexao(conexao);
			
			try {
				
				conexao.conecta();
				
				List<Pedido> pedidos = dao.buscarPedido();
				
				conexao.fechar();
				
				return pedidos;
				
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}

		public List<Pedido> buscarPedidoId(String id) {
			dao.setConexao(conexao);
			
			try {
				
				conexao.conecta();
				
				List<Pedido> pedido = dao.buscarPedidoId(id);
				
				conexao.fechar();
				
				return pedido;
				
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
	}