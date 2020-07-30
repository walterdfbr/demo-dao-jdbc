/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.Impl;

import db.DB;
import db.DBException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        PreparedStatement st = null;
                
        try {
            st = conn.prepareStatement(
            "INSERT INTO seller "
            +"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
            +"VALUES "
            +"(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
            st.setDouble(4, obj.getSalario_base());
            st.setInt(5, obj.getDepartamento().getId());
            
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                            
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DBException("Erro inesperado! nenhuma linha afetada");
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            
        }
    }

    @Override
    public void update(Vendedor obj) {
        PreparedStatement st = null;
                
        try {
            st = conn.prepareStatement(
            "UPDATE seller "
            +"SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
            +"WHERE Id = ?");
            
            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
            st.setDouble(4, obj.getSalario_base());
            st.setInt(5, obj.getDepartamento().getId());
            st.setInt(6, obj.getId());
            
            st.executeUpdate();
            
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            
        }
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
                    "SELECT seller.*, department.Name as DepName "
                    +"FROM seller INNER JOIN department "
                    +"ON seller.DepartmentId = department.Id "
                    +"WHERE seller.id = ?");
            
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Departamento dep = intantiateDepartamento (rs);
                
                Vendedor obj = intantiateVendedor (rs, dep);
                
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
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
            "SELECT seller.*, department.Name as DepName "
            +"FROM seller INNER JOIN department "
            +"ON seller.DepartmentId = department.Id "
            +"ORDER BY Name");
            
            rs = st.executeQuery();
            
            List<Vendedor> list = new ArrayList<>();
            Map <Integer, Departamento> map = new HashMap<>();
            while (rs.next()) {
               
                Departamento dep = map.get(rs.getInt("DepartmentId"));
                
                if (dep == null) {
                dep = intantiateDepartamento(rs);
                map.put(rs.getInt("DepartmentId"), dep);
                }
                
                Vendedor obj = intantiateVendedor(rs, dep);
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Vendedor intantiateVendedor(ResultSet rs, Departamento dep) throws SQLException {
                Vendedor obj = new Vendedor ();
                obj.setId(rs.getInt("Id"));
                obj.setNome(rs.getString("Name"));
                obj.setEmail(rs.getString("Email"));
                obj.setSalario_base(rs.getDouble("BaseSalary"));
                obj.setAniversario(rs.getDate("BirthDate"));
                obj.setDepartamento(dep);
                return obj;
    }

    private Departamento intantiateDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));
                return dep;
    }  

    @Override
    public List<Vendedor> findByDepartamento(Departamento departamento) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
            "SELECT seller.*, department.Name as DepName "
            +"FROM seller INNER JOIN department "
            +"ON seller.DepartmentId = department.Id "
            +"WHERE DepartmentId = ? "
            +"ORDER BY Name");
            
            st.setInt(1, departamento.getId());
            rs = st.executeQuery();
            
            List<Vendedor> list = new ArrayList<>();
            Map <Integer, Departamento> map = new HashMap<>();
            while (rs.next()) {
               
                Departamento dep = map.get(rs.getInt("DepartmentId"));
                
                if (dep == null) {
                dep = intantiateDepartamento(rs);
                map.put(rs.getInt("DepartmentId"), dep);
                }
                
                Vendedor obj = intantiateVendedor(rs, dep);
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

}
