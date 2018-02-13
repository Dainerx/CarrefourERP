/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Account;
import Models.Employee;
import Models.Jobs;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fatma Jaafar
 */
public class EmployeeService {
     Statement ste;
    Connection connexion;

    public EmployeeService() {
        try {
             connexion =  DataSource.getInstance().getConnection();
            ste = connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void add(Employee employee){
      String req=" INSERT INTO `employees`( `idJob`, `departement`, `idAccount`, `firstName`, `lastName`, `cin`, `email`, `sexe`,"
              + " `dateOfBirth`, `phone`, `address`, `idManager`, photo) "
              + "VALUES  ("+employee.getJob().getId()+",'"+employee.getDepartment()+"',"+employee.getAccount().getId()+","
              + "'"+employee.getFirstName()+"','"+employee.getLastName()+"',"+employee.getCin()+",'"+employee.getEmail()+"',"
              + "'"+employee.getSexe()+"','"+employee.getDateOfBirth()+"',"+employee.getPhone()+",'"+employee.getAddress()+"',"
              + ""+employee.getManager().getId()+",'"+employee.getAddress()+"')";
           System.out.println(req);
         try {
             ste.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
         }
  }
 
     public void Delete(Employee employee)
    {
        //idea  from myself to myself :D : 
        //create before delete trigger (create a view of deleted employees , in ze view :endHire is current date) 
        // cuz why not !! :p
        // and cuz it's so late , don't forget to delete all the stuff related tothe emp when deleting'em
        try {
            String sql = "DELETE FROM  employees where id='"+employee.getId()+"'";
             ste.executeUpdate(sql);

            System.out.println("deleted");
            System.out.println(sql);
        } catch (SQLException ex) {
            System.out.println("nope !!!");
        }
    }
     //add this 
//SELECT e.lastName as lastEmp, e.firstName as firstEmp,  m.`firstName` as firstMan, m.`lastName` as lastMan  , e.`id`, e.`idManager`, m.`id`, j.name, j.salary FROM `employees` e INNER 
     //   JOIN employees m on e.`idManager`=m.id INNER JOIN jobs j on j.id= e.`idJob` WHERE e.`idManager` and e.departement='humain resources'
     public Employee findEmployee(int id){
       String req="Select * FROM employees WHERE id=?";
           Employee emp ;
        try {
            PreparedStatement ps = this.connexion.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet res  = ps.executeQuery();
            res.first();
            
      
           emp= new Employee();
          // SELECT `id`, `idJob`, `departement`, `idAccount`, 
          // `firstName`, `lastName`, `cin`, `email`, `sexe`, `dateOfBirth`,
         //  `phone`, `address`, `startHire`, `endHire`, `idManager
            emp.setId(res.getInt("id"));
            emp.setAccount(new Account(res.getInt("idAccount")));
            emp.setFirstName(res.getString("firstName"));
            //complete all attributs
                    
                  
   
            
                return emp;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       emp=new Employee(-1);
        return emp;
     
     }
      public Employee findEmployeeByAccountID(int id){
       String req="SELECT e.* , a.role as role FROM employees e INNER JOIN user a on e.idAccount=a.id WHERE a.id=?";
           Employee emp ;
        try {
            PreparedStatement ps = this.connexion.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet res  = ps.executeQuery();
            res.first();
            
      
           emp= new Employee();
          // SELECT `id`, `idJob`, `departement`, `idAccount`, 
          // `firstName`, `lastName`, `cin`, `email`, `sexe`, `dateOfBirth`,
         //  `phone`, `address`, `startHire`, `endHire`, `idManager
            emp.setId(res.getInt("id"));
            emp.setAccount(new Account(res.getInt("idAccount")));
            emp.setFirstName(res.getString("firstName"));
            emp.setLastName(res.getString("lastName"));
            emp.setEmail(res.getString("email"));
            emp.getAccount().setRole(res.getString("role"));
            //complete all attributs
                    
                  
   
            
                return emp;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       emp=new Employee(-1);
        return emp;
     
     }
     
    
    
    
}
