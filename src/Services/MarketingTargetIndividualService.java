package Services;

import Models.*;
import Technique.DataSource;
import java.sql.Connection;
import java.sql.Date;
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
public class MarketingTargetIndividualService {

    static PreparedStatement statement;
    static Connection connexion;

    public MarketingTargetIndividualService() {
    }

    public static void add(MarketingTargetIndividual mti) {
        connexion = DataSource.getInstance().getConnection();
        try {
            statement = connexion.prepareStatement("INSERT into marketing_target_individual (age,field,marital_status,marriage_period, result) VALUES (?,?,?,?,?)");
            statement.setInt(1, mti.getAge());
            statement.setString(2, mti.getField());
            statement.setString(3, mti.getMaritalStatus());
            statement.setInt(4, mti.getMarriagePeriod());
            statement.setString(5, mti.getResult());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error occured");
        }
    }

    public static void update(MarketingTargetIndividual mti) {
        connexion = DataSource.getInstance().getConnection();
        try {
            statement = connexion.prepareStatement("UPDATE marketing_target_individual SET age=?, field=?, marital_status=?, marriage_period=? where id=?");
            statement.setInt(1, mti.getAge());
            statement.setString(2, mti.getField());
            statement.setString(3, mti.getMaritalStatus());
            statement.setInt(4, mti.getMarriagePeriod());
            statement.setInt(5, mti.getId());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error occured");
        }
    }

    public static void delete(MarketingTargetIndividual mti) {
        connexion = DataSource.getInstance().getConnection();
        try {
            statement = connexion.prepareStatement("DELETE FROM  marketing_target_individual where id=?");
            statement.setInt(1, mti.getId());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error occured");
        }
    }

    public static List<MarketingTargetIndividual> getAll() {
        connexion = DataSource.getInstance().getConnection();
        List<MarketingTargetIndividual> mtis = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from marketing_target_individual");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                MarketingTargetIndividual mti = new MarketingTargetIndividual(result.getInt("id"), result.getInt("age"), result.getString("field"), result.getString("marital_status"), result.getInt("marriage_period"));
                mti.setResult(result.getString("result"));
                mtis.add(mti);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingTargetIndividualService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mtis;
    }

    public static List<MarketingTargetIndividual> getAllLimited() {
        connexion = DataSource.getInstance().getConnection();
        List<MarketingTargetIndividual> mtis = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from marketing_target_individual limit 10");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                MarketingTargetIndividual mti = new MarketingTargetIndividual(result.getInt("id"), result.getInt("age"), result.getString("field"), result.getString("marital_status"), result.getInt("marriage_period"));
                mti.setResult(result.getString("result"));
                mtis.add(mti);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingTargetIndividualService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mtis;
    }

    public static MarketingTargetIndividual getOneById(int id) {
        connexion = DataSource.getInstance().getConnection();
        try {
            statement = connexion.prepareStatement("SELECT * from marketing_target_individual where id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                MarketingTargetIndividual mti = new MarketingTargetIndividual(result.getInt("id"), result.getInt("age"), result.getString("field"), result.getString("marital_status"), result.getInt("marriage_period"));
                mti.setResult(result.getString("result"));
                return mti;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingTargetIndividualService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<MarketingTargetIndividual> getByAll(String entry) {
        connexion = DataSource.getInstance().getConnection();
        List<MarketingTargetIndividual> mtis = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from promotion where age like ? or field like ? or marital_status like ? or marriage_period like ? or result like ?");
            statement.setString(1, "%" + entry + "%");
            statement.setString(2, "%" + entry + "%");
            statement.setString(3, "%" + entry + "%");
            statement.setString(4, "%" + entry + "%");
            statement.setString(5, "%" + entry + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                MarketingTargetIndividual mti = new MarketingTargetIndividual(result.getInt("id"), result.getInt("age"), result.getString("field"), result.getString("marital_status"), result.getInt("marriage_period"));
                mti.setResult(result.getString("result"));
                mtis.add(mti);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingTargetIndividualService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mtis;
    }

    public static int count() {
        connexion = DataSource.getInstance().getConnection();
        try {
            statement = connexion.prepareStatement("SELECT COUNT(*) as total from marketing_target_individual");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingTargetIndividualService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
