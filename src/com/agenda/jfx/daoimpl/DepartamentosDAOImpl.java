package com.agenda.jfx.daoimpl;

import com.agenda.jfx.dao.DepartamentoDAO;
import com.agenda.jfx.pojos.Departamentos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tayron
 */
public class DepartamentosDAOImpl extends Conexion implements DepartamentoDAO{

    @Override
    public void add(Departamentos a) throws Exception {
        String sql = "INSERT INTO departamentos (descripcion) VALUES(?);";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.setString(1, a.getDescripcion());
            
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
    public void update(Departamentos a) throws Exception {
        String sql = "UPDATE departamentos SET descripcion = ? WHERE id = ?;";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.setString(1, a.getDescripcion());
            pstm.setLong(2, a.getId());
            
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
        String sql = "DELETE FROM departamentos WHERE id = ?;";
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
    public Departamentos getOne(Long id) throws Exception {
        Departamentos dep = null;
        String sql = "SELECT id, descripcion FROM departamentos WHERE id = ?;";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);  
            pstm.setLong(1,id);
            if(pstm.execute()){
                ResultSet rst = pstm.executeQuery();
                dep = new Departamentos();
                while(rst.next()){
                     dep.setId(rst.getLong("id"));
                     dep.setDescripcion(rst.getString("descripcion"));
                }
            }
        }catch(Exception e){
            throw e;
        }finally{
            cerrar();
        }
        return dep;
    }

    @Override
    public List<Departamentos> getAll() throws Exception {
        List<Departamentos> dep_list = new ArrayList<>();
        String sql = "SELECT id, descripcion FROM departamentos ORDER BY id ASC;";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);              
            if(pstm.execute()){
                ResultSet rst = pstm.executeQuery();                
                while(rst.next()){
                    Departamentos dep = new Departamentos();
                    dep.setId(rst.getLong("id"));
                    dep.setDescripcion(rst.getString("descripcion"));
                    dep_list.add(dep);
                }
            }
        }catch(Exception e){
            throw e;
        }finally{
            cerrar();
        }
        return dep_list;
    }
    
}
