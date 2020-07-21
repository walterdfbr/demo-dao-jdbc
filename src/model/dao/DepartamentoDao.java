/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Departamento;

/**
 *
 * @author c051618
 */
public interface DepartamentoDao {
    
    void insert(Departamento obj);
    void update (Departamento obj);
    void deleteById (Integer id);
    Departamento findById (Integer id);
    List <Departamento> findAll ();
    
}
