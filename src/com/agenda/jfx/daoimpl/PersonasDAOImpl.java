package com.agenda.jfx.daoimpl;

import com.agenda.jfx.dao.PersonasDAO;
import com.agenda.jfx.pojos.Personas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tayron
 */
public class PersonasDAOImpl extends Conexion implements PersonasDAO{

    @Override
    public void add(Personas a) throws Exception {
        String sql = "INSERT INTO personas (nombres,apellidos,cedula,telefono,email,direccion,id_departamento) VALUES(?,?,?,?,?,?,?);";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.setString(1, a.getNombres());
            pstm.setString(2, a.getApellidos());
            pstm.setString(3, a.getCedula());
            pstm.setString(4, a.getTelefono());
            pstm.setString(5, a.getEmail());
            pstm.setString(6, a.getDireccion());
            pstm.setLong(7, a.getDepartamentos().getId());
            if(pstm.execute()){                
                conexion.commit();
            }
        }catch(Exception e){
            throw e;
        }finally{
            cerrar();
        }
    }

    @Override
    public void update(Personas a) throws Exception {
        String sql = "UPDATE personas SET nombres = ?, apellidos = ?, cedula = ?, telefono = ?, email = ?, direccion = ?, id_departamento = ? WHERE id = ?;";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.setString(1, a.getNombres());
            pstm.setString(2, a.getApellidos());
            pstm.setString(3, a.getCedula());
            pstm.setString(4, a.getTelefono());
            pstm.setString(5, a.getEmail());
            pstm.setString(6, a.getDireccion());
            pstm.setLong(7, a.getDepartamentos().getId());
            pstm.setLong(8, a.getId());
            if(pstm.execute()){                
                conexion.commit();
            }
        }catch(Exception e){
            throw e;
        }finally{
            cerrar();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        String sql = "DELETE FROM personas WHERE id = ?;";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.setLong(1, id);
            if(pstm.execute()){                
                conexion.commit();
            }
        }catch(Exception e){
            throw e;
        }finally{
            cerrar();
        }
    }

    @Override
    public Personas getOne(Long id) throws Exception {
        Personas persona = null;
        String sql = "SELECT id,nombres,apellidos,cedula,telefono,email,direccion,id_departamento FROM personas WHERE id = ?;";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.setLong(1, id);
            if(pstm.execute()){
                ResultSet rst = pstm.executeQuery();
                persona = new Personas();
                while(rst.next()){
                    persona.setId(rst.getLong("id"));
                    persona.setNombres(rst.getString("nombres"));
                    persona.setApellidos(rst.getString("apellidos"));
                    persona.setCedula(rst.getString("cedula"));
                    persona.setTelefono(rst.getString("telefono"));
                    persona.setEmail(rst.getString("email"));
                    persona.setDireccion(rst.getString("direccion"));
                    DepartamentosDAOImpl dep = new DepartamentosDAOImpl();
                    persona.setDepartamentos(dep.getOne(rst.getLong("id_departamento")));
                }
            }
        }catch(Exception e){
            throw e;
        }finally{
            cerrar();
        }
        return persona;
    }

    @Override
    public List<Personas> getAll() throws Exception {
        List<Personas> personas_list = new ArrayList<>();
        String sql = "SELECT id,nombres,apellidos,cedula,telefono,email,direccion,id_departamento FROM personas ORDER BY id ASC;";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            if(pstm.execute()){
                ResultSet rst = pstm.executeQuery();                
                while(rst.next()){
                    Personas persona = new Personas();
                    persona.setId(rst.getLong("id"));
                    persona.setNombres(rst.getString("nombres"));
                    persona.setApellidos(rst.getString("apellidos"));
                    persona.setCedula(rst.getString("cedula"));
                    persona.setTelefono(rst.getString("telefono"));
                    persona.setEmail(rst.getString("email"));
                    persona.setDireccion(rst.getString("direccion"));
                    DepartamentosDAOImpl dep = new DepartamentosDAOImpl();
                    persona.setDepartamentos(dep.getOne(rst.getLong("id_departamento")));
                    personas_list.add(persona);
                }
            }
        }catch(Exception e){
            throw e;
        }finally{
            cerrar();
        }
        return personas_list;
    }
    
}
