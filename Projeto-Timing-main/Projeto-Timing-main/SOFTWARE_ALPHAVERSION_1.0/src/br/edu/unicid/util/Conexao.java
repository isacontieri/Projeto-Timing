package br.edu.unicid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
	public Connection conectaBD() throws ClassNotFoundException {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/DB?user=root&password=";
			conn = DriverManager.getConnection(url);
		} catch (SQLException erro) {

			JOptionPane.showMessageDialog(null, "ConexaoDAO" + erro.getMessage());
		}
		return conn;
	}
}
