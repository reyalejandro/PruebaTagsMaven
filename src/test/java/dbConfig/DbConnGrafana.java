package dbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnGrafana {
	// Librería de MySQL
	public static String driver = "com.mysql.cj.jdbc.Driver";
	// Nombre de la base de datos
	public static String database = "grafanahakalab";
	// Host
	public static String hostname = "localhost";
	// Puerto
	public static String port = "3306";
	// Ruta de nuestra base de datos (desactivamos el uso de SSL con
	// "?useSSL=false")
	public static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
	// Nombre de usuario
	public static String username = "grafana";
	// Clave de usuario
	public static String password = "grafana";
	
	
	/**
	 * CADENA DE CONEXION CON MYSQL PARA USO DE GRAFANA
	 * 
	 */

	public static Connection conectarMySQL() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * MUESTRA EL ESTADO ACTUAL DE LA CONEXION
	 * 
	 */
	
public static void main(String[]args) {
Connection testing = DbConnGrafana.conectarMySQL();
	if(testing!=null) {
		System.out.println("conectado");
	}else {
		System.out.println("desconectado");
	
	}
	
	}
}
