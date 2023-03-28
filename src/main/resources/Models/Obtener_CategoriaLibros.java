package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Obtener_CategoriaLibros {

    private Connection con;

    public Obtener_CategoriaLibros() throws SQLException,
            ClassNotFoundException {
        Conexion cone = new Conexion();
        this.con = cone.Abrir_Conexion();
    }
    public String eliminar_categoriaLibros(String id_categoria) throws SQLException{
        String resultado= "";
        int elimado=0;
        try{
            con.setAutoCommit(false);
            String sql= "DELETE FROM tb_categoria where codigocategoria='"+id_categoria+ "'";
            System.out.println("query eliminar:"+ sql);
            PreparedStatement prepare;
            prepare= con.prepareStatement(sql);
            prepare.executeUpdate();
            resultado="exito";
            con.commit();

        }catch (SQLException e){
            con.rollback();
            System.out.println("Excepcion al eliminar"+ e);
            resultado="error";
            e.printStackTrace();
        }
        return resultado;
    }
    public String insertar_Categoria(CategoriaLibros catLibr) throws SQLException, ClassNotFoundException {
        Conexion cone = new Conexion();
        this.con = cone.Abrir_Conexion();
        String resultado = "";
        int resultado_insertar = 0;
        int resultado_insertar_categoria = 0;

        try {
            String sql = "INSERT INTO public.tb_categoria(\n" +
                    "\tcodigocategoria, nombrecategoria)\n" +
                    "\tVALUES (?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, catLibr.getIdCategoriaLibro());
            st.setString(2, catLibr.getCategoriaLibro());
            resultado_insertar = st.executeUpdate();
            st.close();
            if (resultado_insertar > 0) {
                resultado = "exito";
            } else {
                resultado = "error_categoria";
            }
        } catch (SQLException e) {
            resultado = "error_excepcion";
            System.out.println("El error al insertar: " + e);
            System.out.println("El codigo rror al insertar: " + e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }

    public ResultSet consultar_categorias(Integer estado, String quien) {
        ResultSet resultSet = null;
        try {
            Conexion cone = new Conexion();
            this.con = cone.Abrir_Conexion();
            String sql = "SELECT * FROM public.tb_categoria";
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
    public ResultSet consultar_categoriasEspecificas(String quien){
     ResultSet resultSet=null;
     try{
         Conexion cone= new Conexion();
         con= cone.Abrir_Conexion();
         String sql="";
         sql = "SELECT * from tb_categoria where codigocategoria ='"+quien+"'";
         System.out.println("El SQL estados:"+sql);
         PreparedStatement ps = con.prepareStatement(sql);
         resultSet = ps.executeQuery();
     }catch (SQLException e){
         e.printStackTrace();
     }catch (ClassNotFoundException e){
         e.printStackTrace();
     }
     return resultSet;
    }
    public String actualizar_CategoriaLibro(CategoriaLibros catLib,String id_categoria) throws SQLException{
        String sql="UPDATE tb_categoria SET nombrecategoria =? where codigocategoria='"+id_categoria+"'";
        System.out.println("El id_categoria es:"+id_categoria);
        System.out.println("el sql de UPDATE es:"+sql);
        Integer resultado_actualizar = 0;
        String resultado="";
        try{
            con.setAutoCommit(false);
            PreparedStatement st= con.prepareStatement(sql);
            st.setString(1,catLib.getCategoriaLibro());
            resultado_actualizar=st.executeUpdate();
            System.out.println("resultado_actualizar:"+ resultado_actualizar);
            if (resultado_actualizar>0){
                resultado="exito";
            }else{
                resultado="error_categoria";
            }
           con.commit();
        }catch (SQLException e){
            con.rollback();
            resultado="error_excepcion";
            System.out.println("El error al actualizar:"+e);
            System.out.println("El codigo error al actualizar:"+e.getErrorCode());
            e.printStackTrace();
        }
        return resultado;
    }
}
