/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;



/**
 *
 * @author Fatma Jaafar
 */
public class Account {
   
    private boolean status_login=false;
    private int id;
    private String login;
    private String password ;
    private String role ;
    private String status;

    public Account() {
    }

    public Account(int id) {
        this.id = id;
    }

   

    public boolean getStatus_login() {
        return status_login;
    }

    public void setStatus_login(boolean status_login) {
        this.status_login = status_login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
       
    
    
    
    
    
    
}
