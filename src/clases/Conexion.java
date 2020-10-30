package clases;
import java.sql.*;
public class Conexion {
    //establecemos conexion a la bd
    public static Connection conectar(){
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_ds", "root", "");
            return cn;
        }catch (SQLException e){
            System.out.println("Error en la conexion local "+e);
        }
        return (null);
    }
}
