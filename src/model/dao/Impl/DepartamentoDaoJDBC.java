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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

/**
 *
 * @author c051618
 */
public class DepartamentoDaoJDBC implements DepartamentoDao {

    private Connection conn;
    
    public DepartamentoDaoJDBC (Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insert(Departamento obj) {
        
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement(
            "INSERT INTO department "
            +"(Name) "
            +"VALUES "
            +"(?)", Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, obj.getName());
            
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
    public void update(Departamento obj) {
        
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement(
                    "UPDATE department "
                    +"SET Name = ? "
                    +"WHERE Id = ?");
            
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            
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
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement(
            "DELETE FROM department "
            + "WHERE Id = ?");
            
            st.setInt(1, id);
            
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
    public Departamento findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
            "SELECT * FROM department "
            +"WHERE Id = ?");
            
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if (rs.next()) {
                Departamento dep = new Departamento();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                
                return dep;
                
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
    public List<Departamento> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
            "SELECT * FROM department "
            +"ORDER BY Name");
            
            rs = st.executeQuery();
            
            List <Departamento> list = new ArrayList<>();
            
            
            while (rs.next()) {
                Departamento dep = new Departamento();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                list.add(dep);
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
