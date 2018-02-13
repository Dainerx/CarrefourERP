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
public class MarketingTargetCompanyService {

    PreparedStatement statement;
    Connection connexion;

    public MarketingTargetCompanyService() {
        connexion = DataSource.getInstance().getConnection();
    }

    public void add(MarketingTargetCompany mtc) {
        try {
            statement = connexion.prepareStatement("INSERT into marketing_target_company (age,field,location) VALUES (?,?,?)");
            statement.setInt(1, mtc.getAge());
            statement.setString(2, mtc.getField());
            statement.setString(3, mtc.getLocation());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error occured");
        }
    }

    public void update(MarketingTargetCompany mtc) {
        try {
            statement = connexion.prepareStatement("UPDATE marketing_target_company SET age=?, field=?, location=? where id=?");
            statement.setInt(1, mtc.getAge());
            statement.setString(2, mtc.getField());
            statement.setString(3, mtc.getLocation());
            statement.setInt(4, mtc.getId());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error occured");
        }
    }

    public void delete(MarketingTargetCompany mtc) {
        try {
            statement = connexion.prepareStatement("DELETE FROM  marketing_target_company where id=?");
            statement.setInt(1, mtc.getId());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error occured");
        }
    }

    public List<MarketingTargetCompany> getAll() {
        List<MarketingTargetCompany> mtcs = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from marketing_target_company");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                MarketingTargetCompany mtc = new MarketingTargetCompany(result.getInt("id"), result.getInt("age"), result.getString("field"), result.getString("location"));
                mtc.setResult(result.getString("result"));
                mtcs.add(mtc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mtcs;
    }

    public List<MarketingTargetCompany> getByAll(String entry) {
        List<MarketingTargetCompany> mtcs = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from marketing_target_company where age like ? or field like ? or location like ? or result like ?");
            statement.setString(1, "%" + entry + "%");
            statement.setString(2, "%" + entry + "%");
            statement.setString(3, "%" + entry + "%");
            statement.setString(4, "%" + entry + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                MarketingTargetCompany mtc = new MarketingTargetCompany(result.getInt("id"), result.getInt("age"), result.getString("field"), result.getString("location"));
                mtc.setResult(result.getString("result"));
                mtcs.add(mtc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mtcs;
    }

}
