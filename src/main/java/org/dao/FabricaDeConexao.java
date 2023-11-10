package org.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaDeConexao {

	public static Connection getConexao() {
		try {
			String url = "jdbc:sqlite:C:\\temp\\BancoAtvBD\\banco.db";
			return DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
