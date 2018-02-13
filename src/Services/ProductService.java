/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Product;
import Technique.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mdaghi
 */
public class ProductService {

    /*
    public void Add(Product P) throws SQLException, FileNotFoundException {
        Connection c = DataSource.getInstance().getConnection();
        String req = "INSERT INTO `product`( `name`, `type`, `brand`, `description`, `image`, `price`, `idcategory`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = c.prepareStatement(req);
        pst.setString(1, P.getName());
        pst.setString(2, P.getType());
        pst.setString(3, P.getBrand());
        pst.setString(4, P.getDescription());
        // Traitement d'image
        FileInputStream fis = new FileInputStream(P.getImage());
        pst.setBinaryStream(5, (InputStream) fis, P.getImage().length());
        // End
        pst.setFloat(6, P.getPrice());
        pst.setInt(7, P.getCat().getId());
        pst.executeUpdate();
        pst.close();
    }

    public Product GetById(Product P) throws SQLException, FileNotFoundException, IOException {
        Connection c = DataSource.getInstance().getConnection();
        String requete = " SELECT * from product where id = ?";
        PreparedStatement pst = c.prepareStatement(requete);
        pst.setInt(1, P.getId());
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            P.setId(res.getInt("id"));
            P.setName(res.getString("name"));
            P.setType(res.getString("type"));
            P.setBrand(res.getString("brand"));
            P.setDescription(res.getString("description"));
            // image
            File file = new File("picture");
            InputStream is = res.getBinaryStream("image");
            OutputStream os = new FileOutputStream(file);
            byte[] content = new byte[1024];
            int size = 0;
            while ((size = is.read(content)) != -1) {
                os.write(content, 0, size);
            }
            os.close();
            is.close();
            P.setImage(file);
            //
            P.setPrice(res.getFloat("price"));
            // category
            CategoryService SerCat = new CategoryService();
            Category cat = new Category();
            cat.setId(res.getInt("idcategory"));
            cat = SerCat.GetById(cat);
            P.setCat(cat);

        }
        return P;
    }

    public void Delete(Product p) throws SQLException {
        Connection c = DataSource.getInstance().getConnection();
        String requete = "DELETE FROM Product WHERE id = ? ";
        PreparedStatement pst = c.prepareStatement(requete);
        pst.setInt(1, p.getId());
        pst.executeUpdate();
    }

    public void Update(Product P) throws SQLException, FileNotFoundException {
        Connection c = DataSource.getInstance().getConnection();
        String req = "UPDATE `product` SET `name`= ? ,`type`= ? ,`brand`= ? ,`description`= ? ,`image`= ? ,`price`= ? ,`idcategory`= ? WHERE id = ?";
        PreparedStatement pst = c.prepareStatement(req);
        pst.setString(1, P.getName());
        pst.setString(2, P.getType());
        pst.setString(3, P.getBrand());
        pst.setString(4, P.getDescription());
        // Traitement d'image
        FileInputStream fis = new FileInputStream(P.getImage());
        pst.setBinaryStream(5, (InputStream) fis, P.getImage().length());
        // End
        pst.setFloat(6, P.getPrice());
        pst.setInt(7, P.getCat().getId());
        pst.setInt(8, P.getId());
        pst.executeUpdate();
        pst.close();
    }

    */

}
