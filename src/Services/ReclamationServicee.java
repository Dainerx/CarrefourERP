/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Client;
import Models.Reclamation;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ITECH
 */
public class ReclamationServicee {

    Connection conn;

    public ReclamationServicee() {
        conn = DataSource.getInstance().getConnection();
    }

    public List<Reclamation> readAll() throws SQLException {
        List<Reclamation> list = new ArrayList<>();

        String reqSelect = "select * from reclamation  ";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(reqSelect);//return resultset ,pour l'affichage 
        Reclamation r = null;

        while (rs.next()) {
            // chaque Reclamation possede un client ici on va récupéré le client by id 
            Client c = new Client();
            // remplissage de l'id client from resulatset id_client foreign key 
            c.setId(rs.getInt("id_client"));
            // creation d'un service client pour qu'on puisse utilisé ces méthodes 
            ClientService serClient = new ClientService();
            // récupération du client qui posséde cette reclmation by id c.getId()
            c = serClient.getClientById(c);
            // affectation du l'entité client c à la réclamation remplie avec tout les attributs 
            r = new Reclamation(rs.getInt("id"), c, rs.getString("subject"), rs.getString("type_claim"), rs.getString("description"), rs.getInt("status_r"));
            list.add(r);
        }
        return list;
    }

    public void ajouterReclamation(Reclamation r) {
        try {
            String req = "INSERT INTO reclamation(id_client,subject,type_claim,description,status_r) VALUES (?,?,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(req);//creer un statment ou prepare statment 
            pre.setInt(1, 2);
            // oui hakéka kif t5ademha ista3melha , 

            // pre.setInt(1,28);
            pre.setString(2, r.getSubject());
            pre.setString(3, r.getType_claim());
            pre.setString(4, r.getDescription());
            pre.setInt(5, 0);
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Reclamation r) {
        String req = "update reclamation set id_client=?,subject=?,type_claim=?,description=?,status_r=? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, r.getClient().getId());
            preparedStatement.setString(2, r.getSubject());
            preparedStatement.setString(3, r.getType_claim());
            preparedStatement.setString(4, r.getDescription());
            preparedStatement.setInt(5, r.getStatus_r());
            preparedStatement.setInt(6, r.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void remove(Integer id) {
        String req = "delete from reclamation where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    }
