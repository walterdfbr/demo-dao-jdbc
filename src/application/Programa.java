/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.Date;
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
        Departamento obj = new Departamento(1, "Books");
        Vendedor vendedor = new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 3000.00, obj);
        
        VendedorDao vendedorDao = DaoFactory.createVendedorDao();
        
        System.out.println(vendedor);
    }
    
}
