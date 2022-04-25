/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.daw.JDBC;

import es.daw.web.JDBC.db.*;
import es.daw.web.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daw1a
 */
@WebServlet(name = "JDBC", urlPatterns = {"/JDBC"})

public class JDBC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //------------ 
       DataAccess da = new DataAccess();
       ArrayList<Fabricante> fab=null;
       ArrayList<Producto> prod=null;
       ArrayList<String> cabProd = null;
       ArrayList<String> cabFabric = null;
        try {
            // abrimos la conexion
            da.openConnection();
            
            // cargamos satos
            fab=da.getFabricantes();
            prod=da.getProductos();
            cabFabric=da.getCabecerasTabla("fabricante");
            cabProd=da.getCabecerasTabla("producto");
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           try {
               da.closeConnection();
           } catch (SQLException ex) {
               Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
       
        
        
        
        
        //-----------------
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Trabla del Serblet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            // tabla 1
            out.println("<h1> Tabla de Fabricantes</h1>");
            out.println("<table>");
                out.println("<tr>");
                for(String cabecera : cabFabric){
                    out.println("<th>"+cabecera.toUpperCase()+"</th>");
                }
//                    out.println("<th>Código</th>");
//                    out.println("<th>Nombre</th>");
                out.println("</tr>");
                if(fab!=null){
                    for(Fabricante f : fab){
                        out.println("<tr>");
                            out.println("<td>"+f.getCodigo()+"</td>");
                            out.println("<td>"+f.getNombre()+"</td>");
                        out.println("</tr>");
                    }
                }


            out.println("</table>");
            
            // tabla 2
            out.println("<h1> Tabla de Productos</h1>");
            out.println("<table>");
                out.println("<tr>");
                    for(String cabecera : cabProd){
                        out.println("<th>"+cabecera.toUpperCase()+"</th>");
                    }
//                    out.println("<th>Código</th>");
//                    out.println("<th>Nombre</th>");
//                    out.println("<th>Precio</th>");
//                    out.println("<th>Cod_Fab</th>");
                out.println("</tr>"); 
                if(fab!=null){
                    for(Producto p : prod){
                        out.println("<tr>");
                            out.println("<td>"+p.getCodigo()+"</td>");
                            out.println("<td>"+p.getNombre()+"</td>");
                            out.println("<td>"+p.getPrecio()+"</td>");
                            out.println("<td>"+p.getCod_frabricante()+"</td>");
                        out.println("</tr>");
                    }
                }
                out.println("<tr>");
                    
                out.println("</tr>");
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
