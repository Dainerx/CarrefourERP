package Services;

import Models.*;
import static Services.NewsletterService.connexion;
import static Services.NewsletterService.statement;
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
public class PromotionService {

    static PreparedStatement statement;
    static Connection connexion;

    public PromotionService() {
    }

    public static void add(Promotion promotion) {
        connexion = DataSource.getInstance().getConnection();
        try {
            statement = connexion.prepareStatement("INSERT into promotion (id_product,id_newsletter,content,starting_date,ending_date) VALUES (?,?,?,?,?)");
            statement.setInt(1, promotion.getProduct().getId());
            statement.setInt(2, promotion.getNewsletter().getId());
            statement.setString(3, promotion.getContent());
            Date startingDate = Date.valueOf(promotion.getStartingDate());
            Date endingDate = Date.valueOf(promotion.getEndingDate());
            statement.setDate(4, startingDate);
            statement.setDate(5, endingDate);
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error occured");
        }
    }

    public static void update(Promotion promotion) {
        connexion = DataSource.getInstance().getConnection();
        try {
            statement = connexion.prepareStatement("UPDATE promotion SET id_product=?, id_newsletter=?, content=?, starting_date=?, ending_date=? where id=?");
            statement.setInt(1, promotion.getProduct().getId());
            statement.setInt(2, promotion.getNewsletter().getId());
            statement.setString(3, promotion.getContent());
            Date startingDate = Date.valueOf(promotion.getStartingDate());
            Date endingDate = Date.valueOf(promotion.getEndingDate());
            statement.setDate(4, startingDate);
            statement.setDate(5, endingDate);
            statement.setInt(6, promotion.getId());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void delete(Promotion promotion) {
        connexion = DataSource.getInstance().getConnection();
        try {
            statement = connexion.prepareStatement("DELETE FROM  promotion where id=?");
            statement.setInt(1, promotion.getId());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error occured");
        }
    }

    public static List<Promotion> getAll() {
        connexion = DataSource.getInstance().getConnection();
        List<Promotion> promotions = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from promotion");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Promotion promotion = new Promotion(result.getInt("id"), getOneProduct(result.getInt("id_product")), NewsletterService.getOneById(result.getInt("id_newsletter")), result.getString("content"), result.getDate("starting_date").toLocalDate(), result.getDate("ending_date").toLocalDate());
                promotions.add(promotion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return promotions;
    }

    public static List<Promotion> getAllLimited() {
        connexion = DataSource.getInstance().getConnection();
        List<Promotion> promotions = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from promotion limit 10");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Promotion promotion = new Promotion(result.getInt("id"), getOneProduct(result.getInt("id_product")), NewsletterService.getOneById(result.getInt("id_newsletter")), result.getString("content"), result.getDate("starting_date").toLocalDate(), result.getDate("ending_date").toLocalDate());
                promotions.add(promotion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return promotions;
    }

    public static Promotion getOneById(int id) {
        connexion = DataSource.getInstance().getConnection();
        try {
            statement = connexion.prepareStatement("SELECT p.id as idProduct, pro.id as idPromotion, pro.content as content, pro.starting_date as starting_date, pro.ending_date as ending_date , n.id as idNewsletter from promotion pro inner join product p on pro.id_product=p.id inner join newsletter n on pro.id_newsletter=n.id where pro.id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Promotion promotion = new Promotion(result.getInt("idPromotion"), getOneProduct(result.getInt("idProduct")), NewsletterService.getOneById(result.getInt("idNewsletter")), result.getString("content"), result.getDate("starting_date").toLocalDate(), result.getDate("ending_date").toLocalDate());
                return promotion;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<Promotion> getByAny(String entry) {
        connexion = DataSource.getInstance().getConnection();
        List<Promotion> promotions = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from promotion where content like ?");
            statement.setString(1, "%" + entry + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Promotion promotion = new Promotion(result.getInt("id"), new Product(17), NewsletterService.getOneById(result.getInt("id_newsletter")), result.getString("content"), result.getDate("starting_date").toLocalDate(), result.getDate("ending_date").toLocalDate());
                promotions.add(promotion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return promotions;
    }

    public static List<Product> getAllProducts() {
        connexion = DataSource.getInstance().getConnection();
        List<Product> products = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from product");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Product product = new Product(result.getInt("id"), result.getString("name"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public static Product getOneProduct(int id) {
        connexion = DataSource.getInstance().getConnection();
        Product product = null;
        try {
            statement = connexion.prepareStatement("SELECT * from product where id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                product = new Product(result.getInt("id"), result.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public static List<Product> getAllProductsLimited() {
        connexion = DataSource.getInstance().getConnection();
        List<Product> products = new ArrayList<>();
        try {
            statement = connexion.prepareStatement("SELECT * from product limit 10");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Product product = new Product();
                product.setName(result.getString("name"));
                product.setType(result.getString("type"));
                product.setBrand(result.getString("brand"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public static int countProducts() {
        int count = 0;
        try {
            statement = connexion.prepareStatement("SELECT COUNT(*) as total from product");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                count = result.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsletterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static int countPromotions() {
        int count = 0;
        try {
            statement = connexion.prepareStatement("SELECT COUNT(*) as total from promotion");
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
            statement = connexion.prepareStatement("SELECT COUNT(*) as total from promotion");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
