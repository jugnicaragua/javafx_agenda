package com.agenda.jfx.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tayron
 */
public class Conexion {
    protected Connection conexion;
    
    //JDBC driver name and database url
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/businessagenda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    //Database credentials
    private final String USER = "root";
    private final String PASS = "temporal";
    
    //Open connection to the database server
    public void conectar() throws Exception{
        try{
                Class.forName(JDBC_DRIVER);
                conexion = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);                
        }catch(ClassNotFoundException | SQLException ex){
            throw ex;
        }
    }
    
    //Close connection to the database server
    public void cerrar() throws Exception{
        if(conexion != null){
            if(!conexion.isClosed()){
                conexion.close();
            }
        }
    }
}
