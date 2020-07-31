/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import db.DB;
import model.dao.Impl.DepartamentoDaoJDBC;
import model.dao.Impl.VendedorDaoJDBC;

/**
 *
 * @author c051618
 */
public class DaoFactory {
        
        public static VendedorDao createVendedorDao() {
        return new VendedorDaoJDBC(DB.getConnection());
    }
      public static DepartamentoDao createDepartamentoDao () {
          return new DepartamentoDaoJDBC(DB.getConnection());
      }
        
}

