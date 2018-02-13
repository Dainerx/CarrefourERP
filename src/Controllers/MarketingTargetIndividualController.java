/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.*;
import Models.*;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class MarketingTargetIndividualController implements Initializable {

    private int idHodler;

    @FXML
    private Label fabEdit;
    @FXML
    private AnchorPane fabPane;
    @FXML
    private TableView<MarketingTargetIndividual> tableMti;
    @FXML
    private TableColumn<MarketingTargetIndividual, Integer> _id;
    @FXML
    private TableColumn<MarketingTargetIndividual, Integer> _age;
    @FXML
    private TableColumn<MarketingTargetIndividual, String> _field;
    @FXML
    private TableColumn<MarketingTargetIndividual, String> _maritalStatus;
    @FXML
    private TableColumn<MarketingTargetIndividual, Integer> _marriage_period;
    @FXML
    private TableColumn<MarketingTargetIndividual, String> _result;

    private ObservableList<MarketingTargetIndividual> list_mti;

    @FXML
    private AnchorPane holderAnchor;

    @FXML
    private Label libAge;
    @FXML
    private Label libField;
    @FXML
    private Label libMaritalStatus;
    @FXML
    private Label libMariagePeriod;
    @FXML
    private Label libResult;

    @FXML
    private JFXTextField textAge;
    @FXML
    private JFXTextField textMariagePeriod;
    @FXML
    private JFXComboBox cbField;
    @FXML
    private JFXComboBox cbMarital;

    @FXML
    private ToggleGroup filter;

    @FXML
    private JFXTextField filterText;

    @FXML
    private VBox modifyVbox;

    @FXML
    private VBox addVbox;

    @FXML
    private Button add_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addVbox.setVisible(false);
        ObservableList<String> list_fields = FXCollections.observableArrayList();
        list_fields.add("Computer engineering");
        list_fields.add("Law");
        list_fields.add("Medical Studies");
        cbField.setItems(list_fields);
        ObservableList<String> list_marital = FXCollections.observableArrayList();
        list_marital.add("Single");
        list_marital.add("Engaged");
        list_marital.add("Married");
        cbMarital.setItems(list_marital);

        //setRipples();
        buildMtiTable();
        tableMti.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends MarketingTargetIndividual> observable,
                        MarketingTargetIndividual oldValue,
                        MarketingTargetIndividual newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    getMtiInfo(newValue.getId());

                });
    }

    private void setRipples() {
        JFXRippler fXRippler = new JFXRippler(fabEdit);
        fXRippler.setMaskType(JFXRippler.RipplerMask.CIRCLE);
        fXRippler.setRipplerFill(Paint.valueOf("#FF80AF"));
        fabPane.getChildren().add(fXRippler);

    }

    private void buildMtiTable() {
        list_mti = FXCollections.observableArrayList();
        MarketingTargetIndividualService.getAll().forEach(e -> {
            list_mti.add(e);
        });

        _id.setCellValueFactory(new PropertyValueFactory<>("id"));
        _age.setCellValueFactory(new PropertyValueFactory<>("age"));
        _field.setCellValueFactory(new PropertyValueFactory<>("field"));
        _maritalStatus.setCellValueFactory(new PropertyValueFactory<>("maritalStatus"));
        _marriage_period.setCellValueFactory(new PropertyValueFactory<>("marriagePeriod"));
        _result.setCellValueFactory(new PropertyValueFactory<>("result"));
        tableMti.getItems().clear();
        tableMti.getItems().addAll(list_mti);
    }

    private void getMtiInfo(Integer id) {
        idHodler = id;
        MarketingTargetIndividual result = MarketingTargetIndividualService.getOneById(id);
        libAge.setText("");
        libAge.setText(libAge.getText() + " " + String.valueOf(result.getAge()));
        libField.setText("");
        libField.setText(libField.getText() + " " + result.getField());
        libMaritalStatus.setText("");
        libMaritalStatus.setText(libMaritalStatus.getText() + " " + result.getMaritalStatus());
        libMariagePeriod.setText("");
        libMariagePeriod.setText(libMariagePeriod.getText() + " " + String.valueOf(result.getMarriagePeriod()));
        libResult.setText("");
        libResult.setText(libResult.getText() + " " + result.getResult());
    }

    @FXML
    private void searchMti() {
        if (filterText.getText().isEmpty()) {
            buildMtiTable();
        } else {
            list_mti = FXCollections.observableArrayList();

            System.out.println(MarketingTargetIndividualService.getAll());
            MarketingTargetIndividualService.getAll().stream().filter(e -> e.getMaritalStatus().startsWith(filterText.getText()) || e.getField().startsWith(filterText.getText()))
                    .forEach(
                            e
                            -> {
                        list_mti.add(e);
                    }
                    );

            _id.setCellValueFactory(new PropertyValueFactory<>("id"));
            _age.setCellValueFactory(new PropertyValueFactory<>("age"));
            _field.setCellValueFactory(new PropertyValueFactory<>("field"));
            _maritalStatus.setCellValueFactory(new PropertyValueFactory<>("maritalStatus"));
            _marriage_period.setCellValueFactory(new PropertyValueFactory<>("_marriage_period"));
            _result.setCellValueFactory(new PropertyValueFactory<>("result"));
            tableMti.getItems().clear();
            tableMti.getItems().addAll(list_mti);
        }
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

    private String getAIResponse(Map<String, String> inputFlux) throws MalformedURLException, java.net.ProtocolException, IOException {
        String output = "";
        URL url = new URL("http://127.0.0.1:5000/predict_mti");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);
        String stage = "{\"age\":\"" + inputFlux.get("age") + "\",\"field\":\"" + inputFlux.get("field") + "\",\"marital_status\":\"" + inputFlux.get("marital_status") + "\",\"marriage_period\":\"" + inputFlux.get("marriage_period") + "\"}";
        byte[] out = stage.getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try (DataOutputStream out_x = new DataOutputStream(http.getOutputStream())) {
            out_x.write(out);
        }
        DataInputStream dis = new DataInputStream(http.getInputStream());
        String inputLine;
        while ((inputLine = dis.readLine()) != null) {
            output += inputLine;
        }
        dis.close();
        return output;
    }

    @FXML
    private void addMti() {

        String output = "";
        Map<String, String> inputFlux = new HashMap<>();
        inputFlux.put("age", textAge.getText());

        String field = (String) cbField.getValue();
        if (field.equalsIgnoreCase("Computer engineering")) {
            field = "0";
        } else if (field.equalsIgnoreCase("Law")) {
            field = "1";
        } else {
            field = "2";
        }
        inputFlux.put("field", field);

        String marital_status = (String) cbMarital.getValue();
        if (marital_status.equalsIgnoreCase("Single")) {
            marital_status = "0";
        } else if (marital_status.equalsIgnoreCase("Engaged")) {
            marital_status = "1";
        } else {
            marital_status = "2";
        }
        inputFlux.put("marital_status", marital_status);

        inputFlux.put("marriage_period", textMariagePeriod.getText());

        String finalOutput = "";
        try {
            output = getAIResponse(inputFlux);
            finalOutput = output.substring(output.indexOf("[") + 2, output.indexOf(",") - 3);
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.CUSTOM);
            tray.setTitle("Carrefour Brain");
            tray.setMessage("Our machine learning algorithm finished processing.");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(1500));
            tray.setRectangleFill(Color.valueOf("#4183D7"));
            tray.setImage(new Image("/Img/iconsuser2.png"));
        } catch (java.net.ProtocolException ex) {
            Logger.getLogger(MarketingTargetIndividualController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MarketingTargetIndividualController.class.getName()).log(Level.SEVERE, null, ex);
        }
        MarketingTargetIndividual mti = new MarketingTargetIndividual(Integer.valueOf(textAge.getText()), (String) cbField.getValue(), (String) cbMarital.getValue(), Integer.valueOf(textMariagePeriod.getText()), finalOutput);
        MarketingTargetIndividualService.add(mti);
        buildMtiTable();
    }

    @FXML
    private void extractCSV() throws SQLException, FileNotFoundException, DocumentException, IOException {
        File file = new File("/home/dainer/NetBeansProjects/CarrefourJava/mti.csv");
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (MarketingTargetIndividual e : MarketingTargetIndividualService.getAll()) {
                String text = e.getId() + "," + e.getField() + "," + e.getMaritalStatus() + "," + e.getMarriagePeriod() + "," + e.getResult() + "\n";
                try {
                    writer.write(text);
                } catch (IOException ex) {
                    Logger.getLogger(MarketingTargetIndividualController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MarketingTargetIndividualController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.flush();
            writer.close();
        }

        Alert alertReservation = new Alert(Alert.AlertType.INFORMATION);
        alertReservation.setTitle("Extraction en PDF");
        alertReservation.setHeaderText(null);
        alertReservation.setContentText("CSV report has been created.\nYou'll find "
                + "the file under:\n/home/dainer/NetBeansProjects/CarrefourJava");
        alertReservation.showAndWait();
    }

}
