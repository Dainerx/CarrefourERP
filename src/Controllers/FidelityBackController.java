/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Client;
import Models.FidelityAccount;
import Services.ClientService;
import Services.FidelityAccountService;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author dainer
 */
public class FidelityBackController implements Initializable {

    ObservableList<FidelityAccount> data1 = FXCollections.observableArrayList();
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;

    ObservableList<String> listid = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> idClientCombo;
    @FXML
    private TextField number;
    @FXML
    private TextField amount;
    @FXML
    private DatePicker date;
    @FXML
    private Button SubmitButton1;
    @FXML
    private Button editButton1;
    @FXML
    private Button DeleteButton1;
    @FXML
    private TableView<FidelityAccount> TWFidelity;
    @FXML
    private TableColumn<FidelityAccount, Integer> numberAdmin1;
    @FXML
    private TableColumn<FidelityAccount, Integer> amountAdmin1;
    @FXML
    private TableColumn<?, ?> dateAdmin1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RemplirTable2();
        try {
            initcombo();
        } catch (SQLException ex) {
            Logger.getLogger(FidelityBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void RemplirTable2() {
        // idclient.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        numberAdmin1.setCellValueFactory(new PropertyValueFactory<>("number_points"));
        amountAdmin1.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateAdmin1.setCellValueFactory(new PropertyValueFactory<>("date_creation"));

        FidelityAccountService Ser = new FidelityAccountService();
        List<FidelityAccount> list = Ser.getAll();
        for (FidelityAccount f : list) {
            data1.add(f);
        }
        TWFidelity.setItems(data1);
    }

    @FXML
    private void submit() throws ParseException {
        Client c = new Client();
        ClientService serClient = new ClientService();
        c.setId(Integer.valueOf(idClientCombo.getValue()));
        c = serClient.getClientById(c);
        LocalDate a = date.getValue();
        java.sql.Date d = java.sql.Date.valueOf(a);
        FidelityAccount r = new FidelityAccount();
        String de = r.convert(d);
        FidelityAccount f = new FidelityAccount(c, Integer.valueOf(number.getText()), Integer.valueOf(amount.getText()), de);
        FidelityAccountService ser = new FidelityAccountService();
        if (!(idClientCombo.getValue().equals("")) && !(number.getText().equals("")) && !(amount.getText().equals("")) && !(date.getEditor().getText().equals(""))) {
            FidelityAccount fid = ser.findByIdClient(Integer.parseInt(idClientCombo.getValue()));
            System.out.println(fid);
            if (fid.getId() == 0) {
                ser.add(f);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Successfully added ");
                alert.setHeaderText("Adding a succesful claim");
                Optional<ButtonType> result = alert.showAndWait();

                data1 = FXCollections.observableArrayList();
                TWFidelity.setItems(data1);
                RemplirTable2();
                //
                reset();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Client already exists");
                alert.show();
            }
        } else {
            Alert alertE = new Alert(Alert.AlertType.INFORMATION);
            alertE.setTitle("Addition Error ");
            alertE.setHeaderText("No Addition of Claim");
            Optional<ButtonType> result = alertE.showAndWait();
        }

    }

    @FXML
    private void modif(ActionEvent event) {
        if (TWFidelity.getSelectionModel().getSelectedItem() != null) {
            FidelityAccount f = TWFidelity.getSelectionModel().getSelectedItem();
            f.setNumber_points(Integer.valueOf(number.getText()));
            f.setAmount(Integer.valueOf(amount.getText()));

            LocalDate a = date.getValue();
            java.sql.Date d = java.sql.Date.valueOf(a);
            FidelityAccount r = new FidelityAccount();
            String de = r.convert(d);
            System.out.println("-----------------");
            System.out.println(de);
            System.out.println("-----------------");
            f.setDate_creation(de);
            FidelityAccountService serv = new FidelityAccountService();
            serv.update(f);
            //
            data1 = FXCollections.observableArrayList();
            TWFidelity.setItems(data1);
            RemplirTable2();
            //
            reset();
        }
    }

    public void reset() {
        firstName.clear();
        lastName.clear();
    }

    @FXML
    private void deleteAccount(ActionEvent event) throws SQLException {
        if (TWFidelity.getSelectionModel().getSelectedItem() == null) {
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
        FidelityAccount f = TWFidelity.getSelectionModel().getSelectedItem();
        FidelityAccountService Ser1 = new FidelityAccountService();
        Ser1.remove(TWFidelity.getSelectionModel().getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successfully Deleted ");
        alert.setHeaderText("Deleting an Account Fidelity ");
        Optional<ButtonType> result = alert.showAndWait();
        TWFidelity.getItems().clear();

        RemplirTable2();

        //
    }

    @FXML
    private void selecter(MouseEvent event) throws ParseException {
        if (TWFidelity.getSelectionModel().getSelectedItem() != null) {

            FidelityAccount f = TWFidelity.getSelectionModel().getSelectedItem();
            System.out.println(f);
            firstName.setText(f.getClient().getFirst_name());
            lastName.setText(f.getClient().getLast_name());
            number.setText(f.getNumber_points() + "");
            amount.setText(f.getAmount() + "");
            String dateString = f.getDate_creation();
            System.out.println(f.getDate_creation());
            System.out.println(LocalDate.parse(dateString));
            date.setValue(LocalDate.parse(dateString));
        }
    }

    public void initcombo() throws SQLException {
        ClientService ser = new ClientService();
        ResultSet res = ser.readAll();
        while (res.next()) {
            listid.add(res.getInt("id") + "");
        }
        idClientCombo.setItems(listid);
    }

    @FXML
    private void resetaccount(ActionEvent event) {
        number.clear();
        amount.clear();
        idClientCombo.setValue("");
        date.getEditor().clear();
        firstName.clear();
        lastName.clear();
    }

}
