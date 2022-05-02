package dbConfig;



import java.sql.*;


public class DbConsultas {

    public DbConsultas() {

    }

    public void select(){
        Connection conexion = null;
        DbConnPostgre conn = new DbConnPostgre();
        ResultSet rs = null;
        try {
            conexion = conn.connect();
            Statement stmt = conexion.createStatement();
            rs = stmt.executeQuery("select");
        
                if (rs.next() == false){
                    System.out.println("La respuesta de la base de datos viene vacia");
                    System.out.println("==============================================");
                    System.out.println();
                }else if (rs.wasNull()!=true){
                    boolean index1 = rs.getBoolean(1);   
                    System.out.println("Informacion recibida desde la base de datos: " + index1);
                    System.out.println("======================================================"); 
                    if(index1==false){
                    	 System.out.println("Es false se realiza el cambio a true");	 
                    }
                }
                
            stmt.close();
            rs.close();
            conexion.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
   
    }


    public void update(){
        Connection conexion = null;
        DbConnPostgre conn = new DbConnPostgre();
        int rs;
        try {
            conexion = conn.connect();
            Statement stmt = conexion.createStatement();
            rs = stmt.executeUpdate("UPDATE");
            
                if (rs >0){
                   System.out.println("Se realiza el cambio exitosamente a true");
                   System.out.println("======================================================");
                }else{	
                   System.out.println("La respuesta de la base de datos es 0");
                   System.out.println("==============================================");
                   System.out.println();
                }
               
            stmt.close();
            conexion.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
   
    }


    public void delete(String rut){
    	Connection conexion = null;
        DbConnPostgre conn = new DbConnPostgre();
        int rs;
        try {
        	 conexion = conn.connect();
            Statement stmt = conexion.createStatement();
            rs = stmt.executeUpdate("delete');");
            if (rs >0){
                System.out.println("Se elimina correctamente el registro del usuario en la blacklist");
                System.out.println("======================================================");
             }else{	
                System.out.println("La respuesta de la base de datos es 0 para la tabla blacklist");
                System.out.println("==============================================");
                System.out.println();
             }
                
                
            stmt.close();
            conexion.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }    
    } 
    
    
    
    
    
}

