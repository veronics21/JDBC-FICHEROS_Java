package es.daw.web.jdbc.bd;

import es.daw.web.jdbc.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD:
 * "Crear, Leer, Actualizar y Borrar" (del original en inglés: Create, Read, Update and Delete)
 * Se usa para referirse a las funciones básicas en bases de datos 
 * o la capa de persistencia en un software.
 * 
 * EJEMPLO DE IMPLEMENTACIÓN DEL PATRÓN DAO (DataAccess Object) CON LA TABLA PERSONAS
 * 
 * DAO abstrae del SGBC. Usado para el acceso a datos (independientemente de la fuente de los datos)
 * 
 * OFRECEMOS LOS MÉTODOS
 *
 * - select
 * - selectAll
 * - update
 * - delete
 *
 * TAMBIÉN IMPLEMENTAMOS EL PATRÓN SINGLETON
 *
 * @author daw1a
 */
public class DaoProducto implements Dao<Producto>{
    
    /***************************************************************************
     * Propiedades y métodos SINGLETON
     * 
     * Nos interesa tener solo una instancia que a su vez tiene una única 
     * instancia de la conexión
     */
    private Connection con = null;

    private static DaoProducto instance = null;
    
    private DaoProducto() throws SQLException {
        con = DBConnection.getConnection();
    }
    
    public static DaoProducto getInstance() throws SQLException {
        if (instance == null) {
            instance = new DaoProducto();
        }

        return instance;
    }
    
    public void close() throws SQLException {
        DBConnection.closeConnection();
    }
    
    /**************************************************************************
     * Propiedades y método de la clase DAO
     */
    
    
    @Override
    public Producto select(int id) throws SQLException {
        
        try(PreparedStatement ps = con.prepareStatement("SELECT * FROM tienda.productos WHERE id = ?")){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Producto result = null;

            if (rs.next()) {
                result = new Producto();
                result.setCodigo(rs.getInt("id"));
                result.setNombre(rs.getString("nombre"));
                result.setPrecio(rs.getFloat("precio"));
                result.setCodigo_fabricante(rs.getInt("codigo_fabricante"));
            }

    //        rs.close();
    //        ps.close();

            return result;
        }
    }
    

    @Override
    public List<Producto> selectAll() throws SQLException {

        try(PreparedStatement ps = con.prepareStatement("SELECT * FROM tienda.producto")){
            ResultSet rs = ps.executeQuery();

            List<Producto> result = null;

            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }

                Producto p = new Producto();
                p.setCodigo(rs.getInt("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getFloat("precio"));
                p.setCodigo_fabricante(rs.getInt("codigo_fabricante"));
                result.add(p);

            }
            return result;
        }
        //rs.close(); // no es necesario cerrarlo
        //ps.close(); // no es necesario porqeu lo cerrra el try con recursos

    }
    

    
    @Override
    public void insert(Producto p) throws SQLException {

        
        // el cod producto no tengo que incluirlo en el insert porqeu es PK y autoincremental
        try(PreparedStatement ps = con.prepareStatement("INSERT INTO tienda.producto (nombre, precio, codigo_fabricante) VALUES (?, ?, ?)")){
            //ps.setInt(1, p.getCodigo());
            ps.setString(1, p.getNombre());
            ps.setFloat(2, p.getPrecio());
            ps.setInt(3, p.getCodigo_fabricante());

            ps.executeUpdate();

        }

    }
   
    
    @Override
    public void delete(Producto p) throws SQLException{
        // hacer ps
        try(PreparedStatement ps=con.prepareStatement("DELETE FROM tienda.producto WHERE codigo=?")){
            // preparar ps
            ps.setInt(1, p.getCodigo());
            // ejecutar ps
            ps.executeUpdate();
        }
         
    }
    
    @Override
    public void delete(int id) throws SQLException{
        
    }
    
    @Override
    public void update(Producto p) throws SQLException{
        
    }
    

    
}
