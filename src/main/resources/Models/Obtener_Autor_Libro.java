package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Obtener_Autor_Libro {
    private Connection con;

    public Obtener_Autor_Libro() throws SQLException,
            ClassNotFoundException {
        Conexion cone = new Conexion();
        this.con = cone.Abrir_Conexion();
    }

    public ResultSet consultar_autoresyLibros() {
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            this.con = cone.Abrir_Conexion();
           /* String sql = "SELECT\n" +
                    "\ttb_autor.codigoautor, \n" +
                    "\tconcat_ws(' ', nombreautor,apellidoautor) AS nombre\n" +
                    "FROM\n" +
                    "\ttb_autor\n" +
                    "\tINNER JOIN\n" +
                    "\ttb_libro\n" +
                    "\tON \n" +
                    "\t\ttb_autor.codigoautor = tb_libro.codigoautor\n" +
                    "GROUP BY\n" +
                    "\ttb_autor.codigoautor";*/
            String sql = "SELECT\n" +
                    "\tdbo.tb_autor.codigoautor, \n" +
                    "\tconcat_ws(' ', dbo.tb_autor.apellidoautor,\tdbo.tb_autor.codigoautor) AS nombre\n" +
                    "FROM\n" +
                    "\tdbo.tb_autor\n" +
                    "\tINNER JOIN\n" +
                    "\tdbo.tb_libro\n" +
                    "\tON \n" +
                    "\t\tdbo.tb_autor.codigoautor = dbo.tb_libro.codigoautor";

            System.out.println("El SQL estados: " + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet consultar_autoresylibrosEspecificos( String quien) {
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            this.con = cone.Abrir_Conexion();
            String sql="";
            String el_where_id=quien;
           /* sql = "SELECT\n" +
                    "    tb_libro.titulolibro,\n" +
                    "    tb_libro.precio,\n" +
                    "\t\ttb_libro.existencia\n" +
                    "    FROM\n" +
                    "    tb_libro\n" +
                    "    WHERE tb_libro.codigoautor= '"+el_where_id+"'";*/
            sql = "SELECT\n" +
                    "\tdbo.tb_libro.titulolibro, \n" +
                    "\tdbo.tb_libro.precio, \n" +
                    "\tdbo.tb_libro.existencia\n" +
                    "FROM\n" +
                    "\tdbo.tb_libro\n" +
                    "WHERE\n" +
                    "\tdbo.tb_libro.codigolibro = '"+el_where_id+"'";

            System.out.println("El SQL estados: " + sql);
            PreparedStatement ps = con.prepareStatement(sql);
            resultSet = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
