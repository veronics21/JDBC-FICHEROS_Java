package es.daw.web.jdbc;

import es.daw.web.jdbc.bd.DaoProducto;
import es.daw.web.jdbc.model.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daw1a
 */
@WebServlet(name = "ServletDAO_2", urlPatterns = {"/ServletDAO_2"})
public class ServletDAO_2 extends HttpServlet {

    private DaoProducto daoP = null;
    
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
        
        // --------------------
        //1. Recoger datos enviados desde el formulario --> por name="parametro"
        // getParameter siempre devuelve String
        String nombre = request.getParameter("nombre");
        String codigo = request.getParameter("codigo");
        String precio = request.getParameter("precio");
        String codigo_fabricante = request.getParameter("codigo_fabricante");
        String operacion = request.getParameter("operacion");
        
        System.out.println("*********operación:"+operacion);
        
        //2. Crear el producto
        Producto p = new Producto();
        if (!codigo.equals("")){
            p.setCodigo(Integer.parseInt(codigo));
        }
        if(!nombre.equals(""))p.setNombre(nombre);
        if(!precio.equals(""))p.setPrecio(Float.parseFloat(precio));
        if(codigo_fabricante.equals(""))p.setCodigo_fabricante(Integer.parseInt(codigo_fabricante));
        
        //3. Crear el DAO del producto para acceso a B.D
        boolean error = false;
        String mensajeResultado="";
        try {
            daoP = DaoProducto.getInstance();
            switch (operacion){
                case "insert":
                    daoP.insert(p);
                    System.out.println("[processRequest] Se han cargado los datos de los productos en la colección");
                    mensajeResultado="[processRequest] Se han cargado los datos de los productos en la colección";
                    break;
                case "update":
                    break;
                case "delete":
                    daoP.delete(p);
                    System.out.println("[processRequest] Se han borrado el producto con codigo: "+p.getCodigo());
                    mensajeResultado="[processRequest] Se han borrado el producto con codigo: "+p.getCodigo();
                    break;
                    
                  
            }

        } catch (SQLException e) {
            System.err.println("[processRequest][ERROR REALIZAR CONSULTA]" + e.getMessage());
            error = true;
        }
        
        
        // --------------------
        
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletDAO_2</title>");            
            out.println("</head>");
            out.println("<body>");
            if (error)
                out.println("<h1>Error al crear el nuevo producto</h1>");
            else
                out.println("<h1>"+mensajeResultado+"</h1>");
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

    @Override
    public void destroy() {
        super.destroy();
        
        try {
            daoP.close();
        } catch (SQLException ex) {
            System.err.println("[processRequest][ERROR AL CERRA LA CONEXIÓN]" + ex.getMessage());
        }
        
    }     
}
