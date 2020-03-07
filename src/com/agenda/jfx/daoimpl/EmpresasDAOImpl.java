package com.agenda.jfx.daoimpl;

import com.agenda.jfx.dao.EmpresasDAO;
import com.agenda.jfx.pojos.Empresas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tayron
 */
public class EmpresasDAOImpl extends Conexion implements EmpresasDAO{

    @Override
    public void add(Empresas a) throws Exception {
        String sql = "INSERT INTO empresas (razonsocial,ruc,telefono,email,direccion,id_departamento) VALUES(?,?,?,?,?,?);";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.setString(1, a.getRazonsocial());
            pstm.setString(2, a.getRuc());
            pstm.setString(3, a.getTelefono());
            pstm.setString(4, a.getEmail());
            pstm.setString(5, a.getDireccion());
            pstm.setLong(6, a.getDepartamentos().getId());
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
    public void update(Empresas a) throws Exception {
        String sql = "UPDATE empresas SET razonsocial = ?, ruc = ?, telefono = ?, email = ?, direccion = ?, id_departamento = ? WHERE id = ?;";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.setString(1, a.getRazonsocial());
            pstm.setString(2, a.getRuc());
            pstm.setString(3, a.getTelefono());
            pstm.setString(4, a.getEmail());
            pstm.setString(5, a.getDireccion());
            pstm.setLong(6, a.getDepartamentos().getId());
            pstm.setLong(7, a.getId());
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
        String sql = "DELETE FROM empresas WHERE id = ?;";
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
    public Empresas getOne(Long id) throws Exception {
        Empresas emp = null;
        String sql = "SELECT id,razonsocial,ruc,telefono,email,direccion,id_departamento FROM empresas WHERE id = ?;";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            pstm.setLong(1, id);
            if(pstm.execute()){
                ResultSet rst = pstm.executeQuery();
                emp = new Empresas();
                while(rst.next()){
                    emp.setId(rst.getLong("id"));
                    emp.setRazonsocial(rst.getString("razonsocial"));
                    emp.setRuc(rst.getString("ruc"));
                    emp.setTelefono(rst.getString("telefono"));
                    emp.setEmail(rst.getString("email"));
                    emp.setDireccion(rst.getString("direccion"));
                    DepartamentosDAOImpl depdaoimpl = new DepartamentosDAOImpl();
                    emp.setDepartamentos(depdaoimpl.getOne(rst.getLong("id_departamento")));
                }
            }
        }catch(Exception e){
            throw e;
        }finally{
            cerrar();
        }
        return emp;
    }

    @Override
    public List<Empresas> getAll() throws Exception {
        List<Empresas> emp_list = new ArrayList<>();
        String sql = "SELECT id,razonsocial,ruc,telefono,email,direccion,id_departamento FROM empresas ORDER BY id ASC;";
        try{
            conectar();
            PreparedStatement pstm = conexion.prepareStatement(sql);
            if(pstm.execute()){
                ResultSet rst = pstm.executeQuery();                
                while(rst.next()){
                    Empresas emp = new Empresas();
                    emp.setId(rst.getLong("id"));
                    emp.setRazonsocial(rst.getString("razonsocial"));
                    emp.setRuc(rst.getString("ruc"));
                    emp.setTelefono(rst.getString("telefono"));
                    emp.setEmail(rst.getString("email"));
                    emp.setDireccion(rst.getString("direccion"));
                    DepartamentosDAOImpl depdaoimpl = new DepartamentosDAOImpl();
                    emp.setDepartamentos(depdaoimpl.getOne(rst.getLong("id_departamento")));
                    emp_list.add(emp);
                }
            }
        }catch(Exception e){
            throw e;
        }finally{
            cerrar();
        }
        return emp_list;
    }
    
}
