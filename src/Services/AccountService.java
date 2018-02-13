/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Account;
import Technique.DataSource;
import static Test.Test.showExceptionDialog;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fatma Jaafar
 */
public class AccountService {

    Statement ste;
    Connection connexion;

    public AccountService() {
        try {
            connexion = DataSource.getInstance().getConnection();
            ste = connexion.createStatement();
        } catch (SQLException ex) {
            showExceptionDialog(ex);
        }
    }

    public static String md5(String input) {

        String md5 = null;

        if (null == input) {
            return null;
        }

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }

    public Account Login(String username, String password) {
        Account account = new Account();
        password = md5(password);
        try {
            int row = 0;

            String req = "select * from user where username='" + username + "' and password='" + password + "'";
            ResultSet res = null;
            try {
                System.out.println(req);
                res = ste.executeQuery(req);
            } catch (SQLException ex) {
                showExceptionDialog(ex);
            }

            while (res.next()) {
                row = res.getRow();

                account.setId(res.getInt("id"));
                account.setLogin(res.getString("username"));
                account.setPassword(res.getString("password"));
                account.setRole(res.getString("role"));
                account.setStatus(res.getString("status"));
            }
            if (row == 1) {
                account.setStatus_login(true);
            } else {
                account.setStatus_login(false);
            }
        } catch (SQLException ex) {
            showExceptionDialog(ex);
        }
        return account;
    }

    public void setStatus(String id) {
        try {
            String req = "update accounts set status =1 where id='" + id + "'";
            ste.executeUpdate(req);
        } catch (Exception e) {
            showExceptionDialog(e);
        }

    }

}
