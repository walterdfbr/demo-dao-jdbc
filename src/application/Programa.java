/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.Date;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

/**
 *
 * @author c051618
 */
public class Programa {
    
    public static void main(String[] args) {
        
        
        
        VendedorDao vendedorDao = DaoFactory.createVendedorDao();
        
        System.out.println("=== Test 1: Vendedor findById ===");
        
        Vendedor vendedor = vendedorDao.findById(3);
        
        System.out.println(vendedor);
        
        System.out.println("=== Test 2: Lista Vendedor findByDepartmentId ===");
        
        Departamento departamento = new Departamento(2, null);
        List<Vendedor> list = vendedorDao.findByDepartamento(departamento);
        
        for (Vendedor obj : list) {
        System.out.println(obj);
        }
        
        System.out.println("=== Test 3: Lista Vendedor findAll ===");
        
        list = vendedorDao.findAll();
        
        for (Vendedor obj : list) {
            System.out.println(obj);
        }
        
        System.out.println("=== Test 4: Lista Vendedor Inserção ===");
        
        Vendedor novo_vendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.00, departamento);
        vendedorDao.insert(novo_vendedor);
        System.out.println("Inserido novo id= "+novo_vendedor.getId());
    
    }
}
