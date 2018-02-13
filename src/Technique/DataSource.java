/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technique;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Fatma Jaafar
 */
public class DataSource {
    
    private String url;
    private String login;
    private String password;
    private Connection connection;
    private Properties properties;
    private static DataSource instance;
   
    private DataSource(){
            try {
            properties = new Properties();
            properties.load(new FileInputStream(new File("configuration.properties")));
            url = properties.getProperty("url");
            login = properties.getProperty("login");
            password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connection Etablie !");
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    private static class DataSourceHolder{
        
        private final static DataSource instance = new DataSource();
    
    }
    
    public static DataSource getInstance(){
        return DataSourceHolder.instance;
    }
    public Connection getConnection(){
        return this.connection;
    }
    
    
    
}
