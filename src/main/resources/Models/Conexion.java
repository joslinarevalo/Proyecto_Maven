package Models;

import java.sql.*;

public class Conexion {
    public PreparedStatement ps;
    public ResultSet rs;
    public String SQL;
    public Connection con = null;

    public Connection Abrir_Conexion() throws ClassNotFoundException, SQLException {
      // String url = "jdbc:postgresql://localhost:5432/db_libro";
        String url = "jdbc:sqlserver://DESKTOP-VQ7VS5O\\SQLEXPRESS;databaseName=db_libro";
       // String user = "postgres";
        String user = "sa";  //usuario de base de datos
        //String password = "root";
        String password = "claudia2023";  //Contraseña de base de datos

        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver funciona correctamente.");
            System.out.println("Conexion exitosa");
            return con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la BD " + e.getMessage() + " Error code: " +
                    e.getErrorCode());
            throw new SQLException(String.valueOf(e.getMessage()));
        }

    }


}
