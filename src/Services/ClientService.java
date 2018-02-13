/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Client;
import Models.User;
import Technique.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ITECH
 */
public class ClientService {

    Connection conn;

    public ClientService() {
        conn = DataSource.getInstance().getConnection();
    }

    public Client getClientByUserId(User u) {
        Client client = null;
        String req = "select * from client where id_user=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, u.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setAddress(resultSet.getString("address"));
                client.setCell(resultSet.getInt("cell"));
                client.setFirst_name(resultSet.getString("first_name"));
                client.setLast_name(resultSet.getString("last_name"));
                client.setBirth_date(resultSet.getString("birth_date"));
                client.setGender(resultSet.getString("gender"));
                client.setUser(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return client;

    }

    public Client getClientById(Client c) {
        Client client = null;
        String req = "select * from client where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, c.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setAddress(resultSet.getString("address"));
                client.setCell(resultSet.getInt("cell"));
                client.setFirst_name(resultSet.getString("first_name"));
                client.setLast_name(resultSet.getString("last_name"));
                client.setBirth_date(resultSet.getString("birth_date"));
                client.setGender(resultSet.getString("gender"));
                // autre traiteent pour récupérer l'id user léna  
                // la table client contient un foreign key id user meme principe presque que reclamation et client 
                //ok
                User u = new User();
                u.setId(resultSet.getInt("id_user"));
                u = getUserById(u);
                //
                client.setUser(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return client;

    }

    public User getUserById(User u) {
        String req = "select * from user where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, u.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                u.setPassword(resultSet.getString("password"));
                u.setUsername(resultSet.getString("username"));
                u.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return u;
    }

    public User loginUser(String username, String pwd) throws NoSuchAlgorithmException {
        pwd = Hash(pwd);
        User u = new User();
        String req = "SELECT *, COUNT(*) as exist FROM user where username= ?  and password= ? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pwd);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                u.setId(resultSet.getInt("id"));
                u.setUsername(resultSet.getString("username"));
                u.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;

    }

    public String Hash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    // 
    public ResultSet readAll() throws SQLException {
        String reqSelect = "select * from client ";
        PreparedStatement preparedStatement = conn.prepareStatement(reqSelect);
        ResultSet rs = preparedStatement.executeQuery(reqSelect);//return resultset ,pour l'affichage 
        return rs;
    }

    /*
     user u = loginUser(username,password); 
     Client c = getClientByUserId(u);
     Session.setClient(c);
     */
    /*
     Session.getClient().getId() 
     fel ajout mta3 reclamation m3adesh statique iwali haka 
     */
}
