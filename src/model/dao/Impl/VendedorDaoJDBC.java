/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.Impl;

import db.DB;
import db.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

/**
 *
 * @author c051618
 */
public class VendedorDaoJDBC implements VendedorDao{
    
    private Connection conn;
    
    public VendedorDaoJDBC (Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Vendedor obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Vendedor obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vendedor findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName\n"
                    +"FROM seller INNER JOIN department\n"
                    +"ON seller.DepartmentId = department.Id\n"
                    +"WHERE seller.id = ?");
            
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Departamento dep = new Departamento();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));
                
                Vendedor obj = new Vendedor ();
                obj.setId(rs.getInt("Id"));
                obj.setNome(rs.getString("Name"));
                obj.setEmail(rs.getString("Email"));
                obj.setSalario_base(rs.getDouble("BaseSalary"));
                obj.setAniversario(rs.getDate("BirthDate"));
                obj.setDepartamento(dep);
                return obj;
            }
                    return null;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Vendedor> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
