
package es.daw.web.JDBC;

import es.daw.web.JDBC.bd.DataAccess;
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
@WebServlet(name = "JDBCServlet", urlPatterns = {"/JDBCServlet"})
public class JDBCServlet extends HttpServlet {

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
        boolean err=false;
        DataAccess da= new DataAccess();
        
        ArrayList<String> names;
        
        try {
            // 1º establecer conexion
            da.openConection();
            // 2º hacer consulta y extraer datos
            names = da.getData();
            //3º cerrar la conexion 
            da.closeConnection();
            
        } catch (SQLException ex) {
            System.err.println("[ERROR] "+ ex);
            Logger.getLogger(JDBCServlet.class.getName()).log(Level.SEVERE, null, ex);
            err=true;
            names=null;
        }
        
        
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet JDBCServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            if(err){
                out.println("Error al conectar a la base de datos");
            }
            else{
                out.println("<h1>Conectado Correctamente </h1>");
                
                for(String n : names)out.print("<p>"+ n +"</p>");
                
            }
            
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
