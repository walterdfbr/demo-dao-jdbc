/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.List;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

/**
 *
 * @author c051618
 */
public class Programa2 {
    
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        
        DepartamentoDao departamentoDao = DaoFactory.createDepartamentoDao();
        
        System.out.println("=== Teste 1 - Encontrar Departamento por Id ===");
        
        Departamento dep = departamentoDao.findById(6);
        
        System.out.println(dep);
        
        System.out.println("\n=== Test 2: Lista Departamento findAll ===");
        
        List <Departamento> list = departamentoDao.findAll();
        
        for (Departamento obj : list) {
            System.out.println(obj);
        }
        
        System.out.println("\n=== Test 3: Inserir Departamento===");
        Departamento depNovo = new Departamento(null, "Administração");
        departamentoDao.insert(depNovo);
        System.out.println(depNovo+" inserido novo departamento Id= "+depNovo.getId());
        
        System.out.println("\n=== Test 4: Atualizar Departamento===");
        dep = departamentoDao.findById(7);
        dep.setName("Admin");
        departamentoDao.update(dep);
        System.out.println(dep);
        
        System.out.println("\n=== Test 5: Deletar Departamento===");
        System.out.println("Entre com o Id do Departamento:");
        int id = sc.nextInt();
        departamentoDao.deleteById(id);
        System.out.println("Departamento deletado");
        
    }
}
