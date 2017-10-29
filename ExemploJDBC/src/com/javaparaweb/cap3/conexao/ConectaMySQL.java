package com.javaparaweb.cap3.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMySQL {
	public static void main(String[] args) {
		Connection conexao = null;
		try {
			// Registrando a classe JDBC, e os parámetros de conexão em tempo de execução
			String url = "jdbc:mysql://localhost/agenda";
			String usuario = "adminjose";
			String senha = "adminadmin";

			conexao = DriverManager.getConnection(url, usuario, senha); //(_1_)
			System.out.println("Conectou!");
			conexao.close();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao criar a conexão. Erro: " + e.getMessage());
		}
	}
}
