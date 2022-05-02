package dbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Assert;

public class DbConnPostgre {
	

       private final String url = "";
       private final String user = "";
       private final String password ="";

       
       public Connection getConnection() throws SQLException {
           return DriverManager.getConnection(this.url, this.user, this.password);
       }
       
       public Connection connect() {
           Connection conn = null;
           try {
               conn = DriverManager.getConnection(url, user, password);
               System.out.println("Connected to the PostgreSQL server successfully.");
           } catch (SQLException e) {
        	   System.out.println("Conexion incorrecta a la base de datos"+e.getMessage());
        	   Assert.fail("Conexion incorrecta a la base de datos"+e.getMessage());
           }

           return conn;
       }
       
       
       
       
       public static void closeRS(ResultSet rs) {
           try {
               rs.close();
           } catch (SQLException var2) {
               var2.printStackTrace(System.out);
           }

       }

       public static void closeConection(Connection conn) {
           try {
               conn.close();
           } catch (SQLException var2) {
               var2.printStackTrace(System.out);
           }

       }

       public static void close(PreparedStatement ps) {
           try {
               ps.close();
           } catch (SQLException var2) {
               var2.printStackTrace(System.out);
           }

       }
	
}
