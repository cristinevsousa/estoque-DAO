package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	final static String URL = "jdbc:mysql://127.0.0.1:3306/db_sa4";	
	final static String USER = "root";
	final static String PASS = "Cris123456!@";
	final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection conexao;

    public boolean conecta() throws Exception {
        try {
            if (conexao != null && !conexao.isClosed()) {
                return true;
            }
            Class.forName(DRIVER);

            conexao = DriverManager.getConnection(URL, USER, PASS);
//                    
            conexao.setAutoCommit(false);
            conexao.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
        } catch (ClassNotFoundException cnf) {
            throw new Exception("Driver nãoo encontrado!");
        } catch (SQLException sql) {
            throw new Exception("Falha ocorrida: " + sql.getMessage());
        }
        return true;
    }

    public boolean conecta(String ip, String banco, String user, String pass) throws Exception {
        try {
            if (conexao != null && !conexao.isClosed()) {
                return true;
            }
            Class.forName(DRIVER);
            StringBuilder url = new StringBuilder("jdbc:mysql://");
            url.append(ip).append("/").append(banco);
            conexao = DriverManager.getConnection(url.toString() , user, pass);
//                    
            conexao.setAutoCommit(false);
            conexao.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
        } catch (ClassNotFoundException cnf) {
            throw new Exception("Driver não encontrado!");
        } catch (SQLException sql) {
            throw new Exception("Falha ocorrida: " + sql.getMessage());
        }
        return true;
    }

    public Connection getConexao() {
        return conexao;
    }

    public void fechar() throws Exception {
        try {
            if (conexao == null || conexao.isClosed()) {
                return;
            }
            conexao.close();
        } catch (SQLException sql) {
            throw new Exception("Falha ocorrida: " + sql.getMessage());
        }
    }

    public void confirmarTransacao() throws Exception {
        try {
            if (conexao == null || conexao.isClosed()) {
                return;
            }
            conexao.commit();
        } catch (SQLException sql) {
            throw new Exception("Falha ocorrida: " + sql.getMessage());
        }
    }

    public void cancelarTransacao() throws Exception {
        try {
            if (conexao == null || conexao.isClosed()) {
                return;
            }
            conexao.rollback();
        } catch (SQLException sql) {
            throw new Exception("Falha ocorrida: " + sql.getMessage());
        }
    }
}
