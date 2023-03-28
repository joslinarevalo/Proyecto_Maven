package Controllers;

import Models.CategoriaLibros;
import Models.Obtener_Autor_Libro;
import Models.Obtener_CategoriaLibros;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Mostrar_Autor_Libro", value = "/Mostrar_Autor_Libro")
public class Mostrar_Autor_Libro extends HttpServlet {
    private final String directorio="";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String nombre = (String) sesion.getAttribute("nombre");
        System.out.println("Inicio sesión" + nombre);
        request.getRequestDispatcher("/modulos/vista_autor_libro/autor_libro.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("aplication/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String filtro = request.getParameter("consultar_datos");
        System.out.println("El Filtro es: " + filtro);
        //HttpSession sesion = request.getSession();
        if (filtro == null){
            return;
        }
        switch (filtro){
            case "si_consulta_autoresconlibros":
                JSONArray array_json_autores= new JSONArray();
                JSONObject json_autores= new JSONObject();
                Obtener_Autor_Libro ob_claDAO=null;
                try {
                    ob_claDAO= new Obtener_Autor_Libro();
                    ResultSet res=ob_claDAO.consultar_autoresyLibros();
                    String html_autores=""; //combox
                    while (res.next()){
                        html_autores+="<option value="+res.getString("codigoautor")+">"+res.getString("nombre")+"</option>";
                    }
                 json_autores.put("resultado","exito");
                    json_autores.put("opciones",html_autores);
                    System.out.println("entrar al filtro");
                }catch (SQLException e){
                    json_autores.put("resultado","error_sql");
                    json_autores.put("el_error", e.getMessage());
                    throw new RuntimeException(e);
                }catch (ClassNotFoundException e){
                    json_autores.put("resultado","error_not_found");
                    json_autores.put("el_eror",e.getMessage());
                    throw new RuntimeException(e);
                }
                array_json_autores.put(json_autores);
                response.getWriter().write(array_json_autores.toString()) ;
                break;
            case "si_consulta_autoreslibros_especifico":
                JSONArray array_autorlibros = new JSONArray();
                JSONObject json_autorlibros = new JSONObject();
                String html_deptos_autores = "";
                String  html_autoresconlibros="";
                String el_id_autor = request.getParameter("id_autor");
                try {
                    Obtener_Autor_Libro libAut = new  Obtener_Autor_Libro();
                    ResultSet res_personas_autores =libAut.consultar_autoresylibrosEspecificos(el_id_autor);

                    html_autoresconlibros += "<table id=\"tabla_autores\"" +
                            "class=\"table table-bordered dt-responsive nowrap\"" +
                            "cellspacing=\"0\" width=\"100%\">\n" +
                            "               <thead>\n" +
                            "               <tr>\n"  +
                            "               <th>Titulo Libro</th>\n" +
                            "               <th>Existencias</th>\n" +
                            "               <th>Precio</th>\n"+
                            "               </tr>\n" +
                            "               </thead>\n" +
                            "               <tbody>";
                    while (res_personas_autores.next()) {
                        html_autoresconlibros += "<tr>";
                        html_autoresconlibros += "<td>" + res_personas_autores.getString("tituloLibro") + "</td>";
                        html_autoresconlibros += "<td>" + res_personas_autores.getString("existencia") + "</td>";
                        html_autoresconlibros += "<td>" + res_personas_autores.getString("precio") + "</td>";
                        html_autoresconlibros += "<td>";
                        html_autoresconlibros +="</div>";
                        html_autoresconlibros +="</div>";
                        html_autoresconlibros +="</td>";
                        html_autoresconlibros +="</tr>";
                    }
                    html_autoresconlibros += "</tbody>\n" +
                            "      \t\t</table>";
                    json_autorlibros.put("resultado", "exito");
                    json_autorlibros.put("tabla", html_autoresconlibros);
                } catch (SQLException e) {
                    json_autorlibros.put("resultado", "error sql");
                    json_autorlibros.put("error", e.getMessage());
                    json_autorlibros.put("code error", e.getErrorCode());
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    json_autorlibros.put("resultado", "class not found");
                    json_autorlibros.put("error", e.getMessage());
                    throw new RuntimeException(e);
                }
                array_autorlibros.put(json_autorlibros);
                response.getWriter().write(array_autorlibros.toString());
                break;
        }
    }
}
