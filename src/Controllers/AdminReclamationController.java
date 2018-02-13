/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Client;
import Models.FidelityAccount;
import Models.Reclamation;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import Services.*;

/**
 * FXML Controller class
 *
 * @author ITECH
 */
public class AdminReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tableviewClaimAdmin;
    @FXML
    private TableColumn<?, ?> subjectClaimAdmin;
    @FXML
    private TableColumn<?, ?> typeClaimAdmin;
    @FXML
    private TableColumn<?, ?> descriptionClaimAdmin;
    @FXML
    private TableColumn<Reclamation, Integer> statusClaimAdmin;

    ObservableList<Reclamation> data = FXCollections.observableArrayList();
    @FXML
    private Button DeleteButton;
    @FXML
    private TextField email;
    @FXML
    private Button replayButton;
    @FXML
    private TextArea message;
    @FXML
    private Button treatmentButton;

    ObservableList<String> listid = FXCollections.observableArrayList();
    @FXML
    private PieChart piechart;
    @FXML
    private TextField lastname;
    @FXML
    private TextField firstname;

    @FXML
    private ComboBox<String> idClientCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            RemplirTable();
        } catch (SQLException ex) {
            Logger.getLogger(AdminReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void RemplirTable() throws SQLException {
        //   idClaimAdmin.setCellValueFactory(new PropertyValueFactory<>("id"));
        subjectClaimAdmin.setCellValueFactory(new PropertyValueFactory<>("subject"));
        typeClaimAdmin.setCellValueFactory(new PropertyValueFactory<>("type_claim"));
        descriptionClaimAdmin.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusClaimAdmin.setCellValueFactory(new PropertyValueFactory<>("status_r"));
        ReclamationServicee Ser = new ReclamationServicee();
        List<Reclamation> list = Ser.readAll();
        System.out.println(list);
        for (Reclamation r : list) {
            data.add(r);
        }
        tableviewClaimAdmin.setItems(data);
    }

    @FXML
    private void selectIterm(MouseEvent event) {
        //OnMouseClicked Action on  va selectionner une entity de type reclamation à partir du TW 
        if (tableviewClaimAdmin.getSelectionModel().getSelectedItem() != null) {
            Reclamation r = tableviewClaimAdmin.getSelectionModel().getSelectedItem();
            firstname.setText(r.getClient().getFirst_name());
            lastname.setText(r.getClient().getLast_name());
            email.setText(r.getClient().getUser().getEmail());
        }
    }

    @FXML
    private void DeleteOnClick(ActionEvent event) throws SQLException {
        if (tableviewClaimAdmin.getSelectionModel().getSelectedItem() == null) {
            Notifications notificationBuilder = Notifications.create()
                    .title("")
                    .text("Please Select a row from the tableView  ")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);

            notificationBuilder.showError();
            return;
        }
        Reclamation r = tableviewClaimAdmin.getSelectionModel().getSelectedItem();
        ReclamationServicee Ser = new ReclamationServicee();
        Ser.remove(tableviewClaimAdmin.getSelectionModel().getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successfully Deleted ");
        alert.setHeaderText("Deleting a Claim");
        Optional<ButtonType> result = alert.showAndWait();
        tableviewClaimAdmin.getItems().clear();

        RemplirTable();

        //
    }

    @FXML
    private void replay() {
        //////////////////////////////////////////////////////

        Reclamation r = tableviewClaimAdmin.getSelectionModel().getSelectedItem();

        //email.setText(r.getClient().getUser().getEmail());
        /////////////////////////////////////////////////////
        //SendMail.send(r.getClient().getUser().getEmail(), "Traitement de la reclamation", message.getText(), "tarhouniyosra11@gmail.com", "yesmine95");
        System.out.println("aaaaaa");
    }

    public void reset() {
        email.clear();
        message.clear();
        firstname.clear();
        lastname.clear();
    }

    @FXML
    private void traiter(ActionEvent event) throws SQLException {
        Reclamation r = tableviewClaimAdmin.getSelectionModel().getSelectedItem();
        if (tableviewClaimAdmin.getSelectionModel().getSelectedItem().getStatus_r() == 0) {
            ReclamationServicee Ser = new ReclamationServicee();
            r.setStatus_r(1);
            Ser.update(r);
            tableviewClaimAdmin.getItems().clear();
            RemplirTable();

        }

    }

//private ObservableList<PieChart.Data> data3;
//    private void buildchartt() {
//        
//        data3 = FXCollections.observableArrayList();
//        ReclamationServicee Ser = new ReclamationServicee();
//        Ser.statistic();
//        data3.add(new PieChart.Data());
//       // piechart.setTitle("title");
//        piechart.setData(data3);
//
//    }
    private ObservableList<PieChart.Data> data3 = FXCollections.observableArrayList();

//    public void statistic() {
//        Connection conn;
//        conn = Datasource.getInstance();
//        String req = "SELECT `type_claim`,count(*) as total FROM `reclamation` GROUP BY type_claim";
//        PreparedStatement preparedStatement;
//        try {
//            ReclamationServicee ser = new ReclamationServicee();
//            preparedStatement = conn.prepareStatement(req);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                String type = resultSet.getString("type_caim");
//                data3.add(new PieChart.Data(type, resultSet.getInt("total")));
//                // piechart.setTitle("title");
//                piechart.setData(data3);
//
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//    }
}
