package Controllers;

import Models.CategoriaLibros;
import Models.Obtener_CategoriaLibros;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "RegCatLib", value = "/RegCatLib")
public class RegCatLib extends HttpServlet {
    private final String directorio = "";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("aplication/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String filtro = req.getParameter("consultar_datos");
        System.out.println("El Filtro es: " + filtro);
        HttpSession sesion = req.getSession();
        if (filtro == null){
            return;
        }
        switch (filtro){
            case "si_registro":
                JSONArray array_catLib = new JSONArray();
                JSONObject json_catLib = new JSONObject();
                String resultado_insertar = "";
                CategoriaLibros catLibrito = new CategoriaLibros();
                try {
                    Obtener_CategoriaLibros rg = new Obtener_CategoriaLibros();
                    catLibrito.setIdCategoriaLibro(req.getParameter("codigoCategoria"));
                    catLibrito.setCategoriaLibro(req.getParameter("nombreCategoria"));
                    resultado_insertar = rg.insertar_Categoria(catLibrito);
                    if (resultado_insertar == "exito"){
                        json_catLib.put("resultado", "exito");
                        json_catLib.put("nombreCategoria", catLibrito.getCategoriaLibro());
                        json_catLib.put("codigoCategoria", catLibrito.getIdCategoriaLibro());
                    }else {
                        json_catLib.put("resultado", "error");
                        json_catLib.put("resultado_insertar", resultado_insertar);
                    }
                } catch (SQLException e) {
                    json_catLib.put("resultado", "error_sql");
                    json_catLib.put("error_mostrado", e.getMessage());
                    System.out.println("Error mostrado: " + e);
                    System.out.println("Error Code error: " + e.getErrorCode());
                    System.out.println("Error exception: " + e);
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    json_catLib.put("resultado", "error_class");
                    json_catLib.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                array_catLib.put(json_catLib);
                resp.getWriter().write(array_catLib.toString());
                break;
            case "si_eliminalo":
                JSONArray array_eliminado= new JSONArray();
                JSONObject json_eliminado = new JSONObject();
                String resultado="";
                Obtener_CategoriaLibros ob = null;
                try{
                    ob= new Obtener_CategoriaLibros();
                    resultado = ob.eliminar_categoriaLibros(req.getParameter("id"));
                    if(resultado=="exito"){
                        json_eliminado.put("resultado","exito");

                    }else{
                        json_eliminado.put("resultado","error_eliminar");
                    }
                }catch (SQLException e){
                    json_eliminado.put("resultado","error_excepcion");
                    json_eliminado.put("excepcion",e.getMessage());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    json_eliminado.put("resultado","errorclass");
                    json_eliminado.put("excepcion",e.getMessage());
                    throw new RuntimeException(e);
                }
                array_eliminado.put(json_eliminado);
                resp.getWriter().write(array_eliminado.toString());
                break;
            case "si_actualizalo":
                JSONArray array_actualizar = new JSONArray();
                JSONObject json_actualizar= new JSONObject();
                CategoriaLibros cat = new CategoriaLibros();
                String resultado_actualizar="";
                try{
                    Obtener_CategoriaLibros rg = new Obtener_CategoriaLibros();
                    cat.setCategoriaLibro(req.getParameter("nombreCategoria"));
                    resultado_actualizar= rg.actualizar_CategoriaLibro(cat,req.getParameter("llave_persona"));
                    System.out.println("LA LLAVE DE PERSONA ES:"+req.getParameter("llave_persona"));
                    String id_cate_modi=req.getParameter("llave_persona");
                    System.out.println("id_cate_modi en el servlet de req.getParameter(\"llave_persona\")"+id_cate_modi);
                    if(resultado_actualizar=="exito"){
                        json_actualizar.put("resultado","exito");
                        json_actualizar.put("nombrecategoria", cat.getCategoriaLibro());
                        json_actualizar.put("codigocategoria", cat.getIdCategoriaLibro());
                    }else {
                        json_actualizar.put("resultado","error");
                        json_actualizar.put("resultado_insertar",resultado_actualizar);

                    }
                }catch (SQLException e){
                    json_actualizar.put("resultado","error_sql");
                    json_actualizar.put("error_mostrado",e.getMessage());
                    System.out.print("Error mostrado"+ e);
                    System.out.print("Error Code error"+e.getErrorCode());
                    System.out.print("Error Exception"+e);
                 throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    json_actualizar.put("resultado","error_class");
                    json_actualizar.put("error_mostrado", e);
                    throw new RuntimeException(e);
                }
                array_actualizar.put(json_actualizar);
                resp.getWriter().write(array_actualizar.toString());
                break;
            case "si_concategoria_especifica":
                JSONArray array_especifico= new JSONArray();
                JSONObject json_especifico= new JSONObject();
                Obtener_CategoriaLibros ob_cli;
                try {
                    ob_cli= new Obtener_CategoriaLibros();
                    ResultSet res_individual=ob_cli.consultar_categoriasEspecificas(req.getParameter("id"));
                    System.out.println("req.getParameter(\"id\") desde el servlet:"+ req.getParameter("id"));
                    while (res_individual.next()){
                     json_especifico.put("resultado","exito");
                     json_especifico.put("id_persona",res_individual.getString("codigocategoria"));
                     json_especifico.put("codigoCategoria",res_individual.getString("codigocategoria"));
                     json_especifico.put("nombreCategoria",res_individual.getString("nombrecategoria"));
                    }

                }catch (SQLException e){
                    json_especifico.put("resultado","error_sql");
                    json_especifico.put("el_error", e.getMessage());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    json_especifico.put("resultado","error_not_found");
                    json_especifico.put("el_eror",e.getMessage());
                    throw new RuntimeException(e);
                }
                array_especifico.put(json_especifico);
                resp.getWriter().write(array_especifico.toString()) ;
                break;
            case "si_consulta_categorias":
                JSONArray array_catLibrito = new JSONArray();
                JSONObject json_catLibrito = new JSONObject();
                String html_categorias = "";
                String el_estado = req.getParameter("estado");
                try {
                    Obtener_CategoriaLibros cl = new Obtener_CategoriaLibros();
                    ResultSet res_categorias =
                            cl.consultar_categorias(Integer.valueOf(el_estado), "todos");
                    html_categorias += "<table id=\"tabla_categorias\"" +
                            "class=\"table table-bordered dt-responsive nowrap\"" +
                            "cellspacing=\"0\" width=\"100%\">\n" +
                            "               <thead>\n" +
                            "               <tr>\n"  +
                            "               <th>Código Categoria</th>\n" +
                            "               <th>Nombre Categoria</th>\n" +
                            "               <th>Acciones</th>\n" +
                            "               </tr>\n" +
                            "               </thead>\n" +
                            "               <tbody>";
                    while (res_categorias.next()) {
                        html_categorias += "<tr>";
                        html_categorias += "<td>" + res_categorias.getString("codigoCategoria") + "</td>";
                        html_categorias += "<td>" + res_categorias.getString("nombreCategoria") + "</td>";
                                            html_categorias += "<td>";
                                            html_categorias += "<div class = 'dropdown m-b-10'>";
                                            html_categorias += "<button class = 'btn btn-secondary dropdown-toggle'" +
                                                    "type = 'button' id = 'dropdownMenuButton' data-toggle = 'dropdown' aria-haspopup = 'true'" +
                                                    "aria-expanded = 'false'>Seleccione</button>";
                                            html_categorias += "<div class = 'dropdown-menu' aria-labelledby = 'dropdownMenuButton'>";
                                            html_categorias += "<a class = 'dropdown-item btn_eliminar' data-id='"+res_categorias.getString("codigoCategoria")+ "' " +
                                                    "href='javascript:void(0)'>Eliminar</a>";
                                            html_categorias += "<a class = 'dropdown-item btn_actualizar' data-id='"+res_categorias.getString("codigoCategoria")+ "' " +
                                                    "href='javascript:void(0)'>Editar</a>";
                                                                    html_categorias +="</div>";
                                                                    html_categorias +="</div>";
                                                                    html_categorias +="</td>";
                                                                    html_categorias +="</tr>";
                    }
                    html_categorias += "</tbody>\n" +
                            "      \t\t</table>";
                    json_catLibrito.put("resultado", "exito");
                    json_catLibrito.put("tabla", html_categorias);
                } catch (SQLException e) {
                    json_catLibrito.put("resultado", "error sql");
                    json_catLibrito.put("error", e.getMessage());
                    json_catLibrito.put("code error", e.getErrorCode());
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    json_catLibrito.put("resultado", "class not found");
                    json_catLibrito.put("error", e.getMessage());
                    throw new RuntimeException(e);
                }
                array_catLibrito.put(json_catLibrito);
                resp.getWriter().write(array_catLibrito.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesion = req.getSession();
        String nombre = (String) sesion.getAttribute("nombre");
        System.out.println("Inicio sesión" + nombre);
        req.getRequestDispatcher("/modulos/catLibros/index.jsp").forward(req,resp);
    }
}
