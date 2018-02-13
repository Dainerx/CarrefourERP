/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Client;
import Models.FidelityAccount;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ITECH
 */
public class FidelityAccountService {

    Connection conn;

    public FidelityAccountService() {
        conn = DataSource.getInstance().getConnection();
    }

    public void add(FidelityAccount c) throws ParseException {
        String req = "insert into account_fidelity (id_client,number_points,amount,date_creation) values (?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, c.getClient().getId());
            preparedStatement.setInt(2, c.getNumber_points());
            preparedStatement.setInt(3, c.getAmount());
            preparedStatement.setDate(4, c.convert(c.getDate_creation()));
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void update(FidelityAccount c) {
        String req = "update account_fidelity set number_points=?,amount=?,date_creation=? where id = ?";
        PreparedStatement preparedStatement;
        try {
            //////////////////////////////////////////////////////
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, c.getNumber_points());
            preparedStatement.setInt(2, c.getAmount());
            preparedStatement.setDate(3, c.convert(c.getDate_creation()));
            preparedStatement.setInt(4, c.getId());
            ////////////////////////////////////////////////////////
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(FidelityAccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Integer id) {
        String req = "delete from account_fidelity where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FidelityAccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//////////////////////////////////////////////////////////////////////////////////

    public FidelityAccount findById(Integer id) {
        FidelityAccount f = new FidelityAccount();
        String req = "select * from account_fidelity where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ClientService ser = new ClientService();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client c = new Client();
                c.setId(resultSet.getInt(2));
                c = ser.getClientById(c);
                f.setId(resultSet.getInt(1));
                f.setClient(c);
                f.setNumber_points(resultSet.getInt(3));
                f.setAmount(resultSet.getInt(4));
                f.setDate_creation(f.convert(resultSet.getDate(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return f;
    }

////////////////////////////////////////////////////////////////////////////////////
    public List<FidelityAccount> getAll() {
        List<FidelityAccount> list = new ArrayList<>();
        String req = "select * from account_fidelity";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FidelityAccount f = new FidelityAccount(resultSet.getInt("number_points"), resultSet.getInt("amount"), FidelityAccount.convert(resultSet.getDate("date_creation")));
                Client c = new Client();
                c.setId(resultSet.getInt("id_client"));
                ClientService cs = new ClientService();
                c = cs.getClientById(c);
                f.setClient(c);
                f.setId(resultSet.getInt("id"));

                list.add(f);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////// 
    public FidelityAccount findByIdClient(Integer id) {
        FidelityAccount f = new FidelityAccount();
        String req = "select * from account_fidelity where id_client =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ClientService ser = new ClientService();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client c = new Client();
                c.setId(resultSet.getInt(2));
                c = ser.getClientById(c);
                f.setId(resultSet.getInt(1));
                f.setClient(c);
                f.setNumber_points(resultSet.getInt(3));
                f.setAmount(resultSet.getInt(4));
//                f.setDate_creation(f.convert(resultSet.getDate(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return f;
    }
}
