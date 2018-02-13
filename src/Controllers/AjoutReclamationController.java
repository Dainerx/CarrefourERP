/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Reclamation;
import Services.ReclamationServicee;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ITECH
 */
public class AjoutReclamationController implements Initializable {

    @FXML
    private Button addbutton;
    @FXML
    private TextArea decription;
    private TextField type;
    @FXML
    private TextField subject;
    @FXML
    private TabPane tabPaneClaim;
    @FXML
    private TableView<Reclamation> tableviewclaim;
    @FXML
    private TableColumn<Reclamation, String> viewsubject;
    @FXML
    private TableColumn<Reclamation, String> typeview;
    @FXML
    private TableColumn<Reclamation, String> descriptionview;
    @FXML
    private TableColumn<Reclamation, String> statusview;

    ObservableList<Reclamation> data = FXCollections.observableArrayList();
    @FXML
    private Button updatebutton;
    @FXML
    private Button resetbutton;
    @FXML
    private AnchorPane typecombo;
     private ObservableList<String> Options = FXCollections.observableArrayList("Foreign Body", "Defective Product", "Defective Packaging", "Error Quantitative");
    @FXML
    private ComboBox<String> combo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            RemplirTable();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onclick(ActionEvent event) throws SQLException {

        Reclamation r1 = new Reclamation(subject.getText(),combo.getValue(), decription.getText());
               
               
        ReclamationServicee ser = new ReclamationServicee();
        if (!(subject.getText().equals(""))  && !(combo.getValue().equals("")) && !(decription.getText().equals(""))) {
            ser.ajouterReclamation(r1);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successfully added ");
            alert.setHeaderText("Adding a succesful claim");
            Optional<ButtonType> result = alert.showAndWait();
            //
            data = FXCollections.observableArrayList();
            tableviewclaim.setItems(data);
            RemplirTable();
            //
            reset();
        } else {
            Alert alertE = new Alert(Alert.AlertType.INFORMATION);
            alertE.setTitle("Addition Error ");
            alertE.setHeaderText("No Addition of Claim");
            Optional<ButtonType> result = alertE.showAndWait();
        }

    }

    public void RemplirTable() throws SQLException {
     //   viewid.setCellValueFactory(new PropertyValueFactory<>("id"));
        viewsubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        combo.setItems(Options);
       typeview.setCellValueFactory(new PropertyValueFactory<>("type_claim"));
        descriptionview.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusview.setCellValueFactory(new PropertyValueFactory<>("status_r"));
        ReclamationServicee Ser = new ReclamationServicee();
        List<Reclamation> list = Ser.readAll();
        for (Reclamation r : list) {
            data.add(r);
        }
        tableviewclaim.setItems(data);
    }

    @FXML
    private void SelectItem(MouseEvent event) {
        //OnMouseClicked Action on  va selectionner une entity de type reclamation à partir du TW 
        if (tableviewclaim.getSelectionModel().getSelectedItem() != null) {
            Reclamation r = tableviewclaim.getSelectionModel().getSelectedItem();
            subject.setText(r.getSubject());
            combo.setValue(r.getType_claim());
            decription.setText(r.getDescription());

        }
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        // condition des test !!!! 
        if (tableviewclaim.getSelectionModel().getSelectedItem() != null) {
            Reclamation r = tableviewclaim.getSelectionModel().getSelectedItem();
            r.setSubject(subject.getText());
            r.setDescription(decription.getText());
            r.setType_claim(combo.getValue());
            ReclamationServicee ser = new ReclamationServicee();
            ser.update(r);
            //
            data = FXCollections.observableArrayList();
            tableviewclaim.setItems(data);
            RemplirTable();
            //
            reset();
        }// copie yosra ay nafsha deja copit menhna 
    }
    // esma3 3amla delete rani ama fi interface admin ahaya esma3 just ki read ana bech nahiha chnrodha reset  

    @FXML
    public void reset() {
        subject.clear();
       combo.setValue("");
        decription.clear();
    }
}

/*
 etape 1 : lazem el interface de login :( awel 7aja 
 etape 2 : lazem crypthage md5  
 etape 3 : test login true or false  ( c'est bon )
 etape 4 : get client by id user ( c'est bon )
 etape 5 : creation de la session ( c'est bon )
 etape 6 : redirection vers l'interface mta3ek  
 */
