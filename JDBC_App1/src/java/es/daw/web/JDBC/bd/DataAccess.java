/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.daw.web.JDBC.bd;


import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw1a
 */
public class DataAccess {
    
    Connection con;
    Statement st;
    ResultSet rs;
    
    public void openConection() throws SQLException{
        String url = "jdbc:mysql://localhost/tienda";
        
        // info del user de la base de datos
        String user = "root";
        String pwd = "1234";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        con = DriverManager.getConnection(url,user,pwd);
        
        System.out.println("[openConection] conexio a base de datos: "+con);
    }
    
    public ArrayList<String> getData() throws SQLException{
        ArrayList<String> nombreProd= new ArrayList<>();
        
        try{
        st = con.createStatement();
        rs = st.executeQuery("select * from tienda.producto");
        
        while(rs.next()){
            // devuelve solo la col nombre 
            String name = rs.getNString("nombre");
            nombreProd.add(name);
        }
        }catch(SQLException ex){
            throw ex;
        }finally{
            return nombreProd;
        } 
        
    }
    
    public void closeConnection() throws SQLException{
        con.close();
    }
}
