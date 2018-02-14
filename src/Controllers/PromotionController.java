/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.*;
import Models.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class PromotionController implements Initializable {

    private int idHodler;

    @FXML
    private Label fabEdit;
    @FXML
    private AnchorPane fabPane;
    @FXML
    private TableView<Promotion> tablePromotions;
    @FXML
    private TableColumn<Promotion, Integer> _id;
    @FXML
    private TableColumn<Product, String> _product;
    @FXML
    private TableColumn<Newsletter, String> _newsletter;
    @FXML
    private TableColumn<Promotion, String> _content;
    @FXML
    private TableColumn<Promotion, LocalDate> _startingDate;
    @FXML
    private TableColumn<Promotion, LocalDate> _endingDate;

    private ObservableList<Promotion> list_promotion;

    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private Label libProduct;
    @FXML
    private JFXComboBox cbProduct;
    @FXML
    private JFXComboBox cbProduct1;
    @FXML
    private Label libNewsletter;
    @FXML
    private JFXComboBox<Newsletter> cbNewsletter;
    @FXML
    private JFXComboBox<Newsletter> cbNewsletter1;
    @FXML
    private Label libContent;
    @FXML
    private JFXTextArea textContent;
    @FXML
    private JFXTextArea textContent1;
    @FXML
    private Label libStartingDate;
    @FXML
    private Label libEndingDate;
    @FXML
    private DatePicker cbStartingDate;
    @FXML
    private DatePicker cbEndingDate;

    @FXML
    private ToggleGroup filter;

    @FXML
    private VBox modifyVbox;

    @FXML
    private VBox addVbox;

    @FXML
    private Button add_btn;

    @FXML
    private JFXTextField filterText;

    boolean selected = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addVbox.setVisible(false);
        //setRipples();
        buildPromotionTable();
        setCbValues();
        cbProduct.getSelectionModel().select(0);
        cbNewsletter.getSelectionModel().select(0);
        cbProduct1.getSelectionModel().select(0);
        cbNewsletter1.getSelectionModel().select(0);

        tablePromotions.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends Promotion> observable,
                        Promotion oldValue,
                        Promotion newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    getPromotionInfo(newValue.getId());
                });

    }

    private void setCbValues() {

        ObservableList<Product> list_product = FXCollections.observableArrayList();
        PromotionService.getAllProducts().forEach(e -> {
            list_product.add(e);
        });

        cbProduct.setItems(list_product);
        cbProduct1.setItems(list_product);

        ObservableList<Newsletter> list_news = FXCollections.observableArrayList();
        NewsletterService.getAll().forEach(e -> {
            list_news.add(e);
        });
        cbNewsletter.setItems(list_news);
        cbNewsletter1.setItems(list_news);
        
    }

    private void setRipples() {
        JFXRippler fXRippler = new JFXRippler(fabEdit);
        fXRippler.setMaskType(JFXRippler.RipplerMask.CIRCLE);
        fXRippler.setRipplerFill(Paint.valueOf("#FF80AF"));
        fabPane.getChildren().add(fXRippler);

    }

    private void buildPromotionTable() {
        list_promotion = FXCollections.observableArrayList();
        PromotionService.getAll().forEach(e -> {
            list_promotion.add(e);
        });

        _id.setCellValueFactory(new PropertyValueFactory<>("id"));
        _product.setCellValueFactory(new PropertyValueFactory<>("Product"));
        _newsletter.setCellValueFactory(new PropertyValueFactory<>("Newsletter"));
        _content.setCellValueFactory(new PropertyValueFactory<>("content"));
        _startingDate.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
        _endingDate.setCellValueFactory(new PropertyValueFactory<>("endingDate"));

        tablePromotions.getItems().clear();
        tablePromotions.getItems().addAll(list_promotion);
    }

    private void getPromotionInfo(Integer id) {
        selected = true;
        idHodler = id;
        Promotion result = PromotionService.getOneById(id);
        cbProduct.getSelectionModel().select(result.getProduct().getId());
        cbNewsletter.getSelectionModel().select(result.getNewsletter().getId());
        textContent.setText(result.getContent());
        libStartingDate.setText(result.getStartingDate().toString());
        libEndingDate.setText(result.getEndingDate().toString());
    }

    @FXML
    private void modifyPromotion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Modify");
        alert.setContentText("Do you really want to modify this newsletter?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            PromotionService.update(new Promotion(idHodler, (Product) cbProduct.getValue(), (Newsletter) cbNewsletter.getValue(), textContent.getText(), LocalDate.now(), LocalDate.now()));
            buildPromotionTable();
        }
    }

    @FXML
    private void deletePromotion() {
        if (selected == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No Object selected");
            alert.setContentText("Please, select a promotion to modify.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete");
            alert.setContentText("Do you really want to delete this promotion?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                PromotionService.delete(new Promotion(idHodler));
                buildPromotionTable();
            }
        }

    }

    @FXML
    private void searchPromotion() {
        if (filterText.getText().isEmpty()) {
            buildPromotionTable();
        }

        list_promotion = FXCollections.observableArrayList();
        PromotionService.getByAny(filterText.getText()).forEach(e -> {
            list_promotion.add(e);
        });
        _id.setCellValueFactory(new PropertyValueFactory<>("id"));
        _product.setCellValueFactory(new PropertyValueFactory<>("Product"));
        _newsletter.setCellValueFactory(new PropertyValueFactory<>("Newsletter"));
        _content.setCellValueFactory(new PropertyValueFactory<>("content"));
        _startingDate.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
        _endingDate.setCellValueFactory(new PropertyValueFactory<>("endingDate"));

        tablePromotions.getItems().clear();
        tablePromotions.getItems().addAll(list_promotion);
    }

    @FXML
    private void goAdd() {
        modifyVbox.setVisible(false);
        addVbox.setVisible(true);
    }

    @FXML
    private void goCancel() {
        addVbox.setVisible(false);
        modifyVbox.setVisible(true);
    }

    @FXML
    private void addPromotion() {
        if (cbEndingDate.getValue().compareTo(cbStartingDate.getValue()) > 0) {
            Newsletter news = (Newsletter) cbNewsletter1.getValue();
            Promotion promotion = new Promotion((Product) cbProduct1.getValue(), news, textContent1.getText(), cbStartingDate.getValue(), cbEndingDate.getValue());
            PromotionService.add(promotion);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String[] to = new String[(int)NewsletterService.getUsersSubscribed(news.getId()).stream().count()];
            int i=0;
            for (String email : NewsletterService.getUsersSubscribed(news.getId())) {
                to[i]=email;
                i++;
            }
            System.out.println(Arrays.toString(to));
            sendFromGMail("email", "password", to, "New promotion added", promotion.getContent());
            buildPromotionTable();
            alert.setTitle("Information Dialog");
            alert.setHeaderText("An addition has occured successfully");
            alert.setContentText("Promotion added successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Input entered does not match the criteria");
            alert.setContentText("Please, make sure each input entry do match criteria.");
            alert.showAndWait();
        }

    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

}
