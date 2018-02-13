/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.NewsletterService;
import Models.Newsletter;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Optional;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class NewsLetterController implements Initializable {

    private int idHodler;

    @FXML
    private Label fabEdit;
    @FXML
    private AnchorPane fabPane;
    @FXML
    private TableView<Newsletter> tableNewsletters;
    @FXML
    private TableColumn<Newsletter, Integer> _id;
    @FXML
    private TableColumn<Newsletter, String> _subject;
    @FXML
    private TableColumn<Newsletter, String> _content;

    private ObservableList<Newsletter> list_newsletter;

    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private Label lblName;
    @FXML
    private Label libSubject;
    @FXML
    private JFXTextField textSubject;
    @FXML
    private Label libContent;
    @FXML
    private JFXTextArea textContent;
    @FXML
    private Label libsubscribed;
    @FXML
    private ToggleGroup filter;

    @FXML
    private JFXTextField filterText;
    @FXML
    private JFXRadioButton filterSubject;
    @FXML
    private JFXRadioButton filterContent;

    @FXML
    private ImageView image;

    boolean selected = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //setRipples();
        buildNewsletterTable();

        tableNewsletters.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends Newsletter> observable,
                        Newsletter oldValue,
                        Newsletter newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    getNewsletterInfo(newValue.getId());

                });

    }

    private void setRipples() {
        JFXRippler fXRippler = new JFXRippler(fabEdit);
        fXRippler.setMaskType(JFXRippler.RipplerMask.CIRCLE);
        fXRippler.setRipplerFill(Paint.valueOf("#FF80AF"));
        fabPane.getChildren().add(fXRippler);

    }

    private void buildNewsletterTable() {
        list_newsletter = FXCollections.observableArrayList();
        NewsletterService.getAll().forEach(e -> {
            list_newsletter.add(e);
        });

        _id.setCellValueFactory(new PropertyValueFactory<>("id"));
        _subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        _content.setCellValueFactory(new PropertyValueFactory<>("content"));

        tableNewsletters.getItems().clear();
        tableNewsletters.getItems().addAll(list_newsletter);
    }

    private void getNewsletterInfo(Integer id) {
        selected = true;
        idHodler = id;
        Newsletter result = NewsletterService.getOneById(id);
        image.setImage(new Image(result.getImage()));
        lblName.setVisible(false);
        textSubject.setText(result.getSubject());
        textContent.setText(result.getContent());
        libsubscribed.setText(String.valueOf(result.getNumberSub()));
    }

    @FXML
    private void searchNewsletter() {
        if (filterText.getText().isEmpty()) {
            buildNewsletterTable();
        }

        if (filterSubject.isSelected()) {
            list_newsletter = FXCollections.observableArrayList();
            NewsletterService.getBySubject(filterText.getText()).forEach(e -> {
                list_newsletter.add(e);
            });
            _id.setCellValueFactory(new PropertyValueFactory<>("id"));
            _subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
            _content.setCellValueFactory(new PropertyValueFactory<>("content"));

            tableNewsletters.getItems().clear();
            tableNewsletters.getItems().addAll(list_newsletter);
        } else {
            list_newsletter = FXCollections.observableArrayList();
            NewsletterService.getByContent(filterText.getText()).forEach(e -> {
                list_newsletter.add(e);
            });
            _id.setCellValueFactory(new PropertyValueFactory<>("id"));
            _subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
            _content.setCellValueFactory(new PropertyValueFactory<>("content"));

            tableNewsletters.getItems().clear();
            tableNewsletters.getItems().addAll(list_newsletter);
        }
    }

    @FXML
    private void modifyNewsletter() {
        if (selected == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No Object selected");
            alert.setContentText("Please, select a newsletter to modify.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Modify");
            alert.setContentText("Do you really want to modify this newsletter?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                NewsletterService.update(new Newsletter(idHodler, textSubject.getText(), textContent.getText(), ""));
                buildNewsletterTable();
            }
        }
    }

    @FXML
    private void deleteNewsLetter() {
        if (selected == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No Object selected");
            alert.setContentText("Please, select a newsletter to delete.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete");
            alert.setContentText("Do you really want to delete this newsletter?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                NewsletterService.delete(new Newsletter(idHodler, textSubject.getText(), textContent.getText(), ""));
                buildNewsletterTable();
            }

        }
    }

    @FXML
    private void extratPDF() throws SQLException, FileNotFoundException, DocumentException {

        Document pdfReport = new Document();
        PdfWriter.getInstance(pdfReport, new FileOutputStream("pdf_Projet.pdf"));
        pdfReport.open();
        pdfReport.add(new Paragraph("List of newsletters"));
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);
        PdfPTable my_report_table = new PdfPTable(4);

        PdfPCell tableCellColumn = new PdfPCell(new Phrase("id"));
        my_report_table.addCell(tableCellColumn);
        tableCellColumn = new PdfPCell(new Phrase("Subject"));
        my_report_table.addCell(tableCellColumn);
        tableCellColumn = new PdfPCell(new Phrase("Content"));
        my_report_table.addCell(tableCellColumn);
        tableCellColumn = new PdfPCell(new Phrase("Number of subscribers"));
        my_report_table.addCell(tableCellColumn);

        Services.NewsletterService.getAll().forEach(e -> {
            String id = "" + e.getId();
            PdfPCell tableCell = new PdfPCell(new Phrase(id));
            my_report_table.addCell(tableCell);

            tableCell = new PdfPCell(new Phrase(e.getSubject()));
            my_report_table.addCell(tableCell);

            tableCell = new PdfPCell(new Phrase(e.getContent()));
            my_report_table.addCell(tableCell);

            tableCell = new PdfPCell(new Phrase("" + e.getNumberSub()));
            my_report_table.addCell(tableCell);
        });
        /* Attach report table to PDF */
        pdfReport.add(my_report_table);
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);
        pdfReport.add(Chunk.NEWLINE);

        pdfReport.add(new Paragraph("-Carrefour ERP"));

        pdfReport.close();

        Alert alertReservation = new Alert(Alert.AlertType.INFORMATION);
        alertReservation.setTitle("Extraction en PDF");
        alertReservation.setHeaderText(null);
        alertReservation.setContentText("PDF report has been created.\nYou'll find "
                + "the file under:\n/home/dainer/NetBeansProjects/CarrefourJava");
        alertReservation.showAndWait();
    }

}
