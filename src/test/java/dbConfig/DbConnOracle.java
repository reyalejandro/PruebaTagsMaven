package dbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnOracle {

	
	private static Connection con = null;

	static final String DRIVER_SQL = "oracle.jdbc.driver.OracleDriver";
	static final String DATABASE_cash = "jdbc:oracle:thin:@10.120.148.118:1522:EBSUAT";
	static final String USER = "apps";
	static final String PASS = "appsebsuat";

	/**
	 * METODO PARA CREAR CONEXION CON BASE DE DATOS ORACLE 
	 **/

	public static Connection getConnOracle() {
		try {
			Class.forName(DRIVER_SQL);
			con = DriverManager.getConnection(DATABASE_cash, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * MUESTRA EL ESTADO ACTUAL DE LA CONEXION
	 **/
	
	public static void main(String[] args) {
		Connection testing = DbConnOracle.getConnOracle();
		if (testing != null) {
			System.out.println("conectado");
		} else {
			System.out.println("desconectado");
		}

	}

}




