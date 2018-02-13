/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.*;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dainer
 */
public class NewsletterService {

    static PreparedStatement statement;
    static Connection connexion;

    public static void add(Newsletter newsletter) {
        connexion = DataSource.getInstance().getConnection();
        try {
            statement = connexion.prepareStatement("INSERT into newsletter (subject,content,image) VALUES (?,?,?)");
            statement.setString(1, newsletter.getSubject());
            statement.setString(2, newsletter.getContent());
            statement.setString(3, newsletter.getImage());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error occured");
        }
    }

    public static void update(Newsletter newsletter) {
        connexion = DataSource.getInstance().getConnection();

        try {
            statement = connexion.prepareStatement("UPDATE newsletter SET subject=?, content=? where id=?");
            statement.setString(1, newsletter.getSubject());
            statement.setString(2, newsletter.getContent());
            statement.setInt(3, newsletter.getId());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error occured");
        }
    }

    public static void delete(Newsletter newsletter) {
        connexion = DataSource.getInstance().getConnection();

        try {
            statement = connexion.prepareStatement("DELETE FROM  newsletter where id=?");
            statement.setInt(1, newsletter.getId());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error occured");
        }
    }

    public static List<Newsletter> getAll() {
        connexion = DataSource.getInstance().getConnection();

        List<Newsletter> newsletters = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from newsletter");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Newsletter newsletter = new Newsletter(result.getInt("id"), result.getString("subject"), result.getString("content"), result.getString("image"));
                newsletters.add(newsletter);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newsletters;
    }

    public static Newsletter getOneById(int id) {
        connexion = DataSource.getInstance().getConnection();

        try {
            statement = connexion.prepareStatement("SELECT n.*, COUNT(s.id) as total from newsletter n inner join subscribe_newsletter s on n.id=s.id_newsletter where n.id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Newsletter newsletter = new Newsletter(result.getInt("id"), result.getString("subject"), result.getString("content"), result.getString("image"), result.getInt("total"));
                return newsletter;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<Newsletter> getBySubject(String entry) {
        connexion = DataSource.getInstance().getConnection();
        List<Newsletter> newsletters = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from newsletter where subject like ?");
            statement.setString(1, "%" + entry + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Newsletter newsletter = new Newsletter(result.getInt("id"), result.getString("subject"), result.getString("content"), result.getString("image"));
                newsletters.add(newsletter);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newsletters;
    }

    public static List<Newsletter> getByContent(String entry) {
        connexion = DataSource.getInstance().getConnection();
        List<Newsletter> newsletters = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from newsletter where content like ?");
            statement.setString(1, "%" + entry + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Newsletter newsletter = new Newsletter(result.getInt("id"), result.getString("subject"), result.getString("content"), result.getString("image"));
                newsletters.add(newsletter);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newsletters;
    }

    public static List<String> getUsersSubscribed(int id)
    {
        List<String> emails = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT u.email as emails from subscribe_newsletter sc inner join user u on sc.id_user=u.id where sc.id_newsletter=?");
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                emails.add(result.getString("emails"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emails;
    }
    
    public static int countUsers() {
        int count=0;
        try {
            statement = connexion.prepareStatement("SELECT COUNT(*) as total from user");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                count = result.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static int countSubscribtions() {
        int count=0;
        try {
            statement = connexion.prepareStatement("SELECT COUNT(*) as total from subscribe_newsletter");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                count = result.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static int count() {
        connexion = DataSource.getInstance().getConnection();
        try {
            statement = connexion.prepareStatement("SELECT COUNT(*) as total from newsletter");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
