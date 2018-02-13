/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.*;
import Services.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import imports.FadeInTransition;
import imports.mouseDrag;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class OverViewMarketingController implements Initializable {

    @FXML
    private HBox groupHolder;
    @FXML
    private Group groupRegistered;
    @FXML
    private Group groupTarget;
    @FXML
    private Group groupGents;
    @FXML
    private Group groupLadies;
    @FXML
    private JFXListView<String> departmentList;
    private ObservableList<String> departments;

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
    private TableColumn<MarketingTargetIndividual, Integer> _maritalPeriod;
    @FXML
    private TableColumn<MarketingTargetIndividual, String> _result;

    private ObservableList<MarketingTargetIndividual> list_mti;

    @FXML
    private TableView<Promotion> tablePromotions;
    @FXML
    private TableColumn<Product, String> _product;
    @FXML
    private TableColumn<Promotion, LocalDate> _startingDate;
    @FXML
    private TableColumn<Promotion, LocalDate> _endingDate;

    private ObservableList<Promotion> list_promotion;

    @FXML
    private TableView<Product> tableProducts;
    @FXML
    private TableColumn<Product, String> _product_name;
    @FXML
    private TableColumn<Product, String> _product_type;
    @FXML
    private TableColumn<Product, String> _product_brand;

    private ObservableList<Product> list_product;

    @FXML
    private Label libTotalNewsLetter;
    @FXML
    private Label libTotalTargets;
    @FXML
    private Label libTotalProducts;
    @FXML
    private Label libTotalPromotion;

    @FXML
    private Button mtiButton;
    @FXML
    private Button promotionBtn;
    @FXML
    private Button btnAddDepartment;

    @FXML
    private StackPane deptStackPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        libTotalNewsLetter.setText(String.valueOf(NewsletterService.count()));
        libTotalTargets.setText(String.valueOf(MarketingTargetIndividualService.count()));
        libTotalProducts.setText(String.valueOf(10));
        libTotalPromotion.setText(String.valueOf(PromotionService.count()));
        buildAll();
    }

    private void setRipples() {
        JFXRippler rippler1 = new JFXRippler(groupRegistered);
        JFXRippler rippler2 = new JFXRippler(groupGents);
        JFXRippler rippler3 = new JFXRippler(groupLadies);
        JFXRippler rippler4 = new JFXRippler(groupTarget);
        rippler1.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler2.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler3.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler4.setMaskType(JFXRippler.RipplerMask.RECT);

        rippler1.setRipplerFill(Paint.valueOf("#1564C0"));
        rippler2.setRipplerFill(Paint.valueOf("#00AACF"));
        rippler3.setRipplerFill(Paint.valueOf("#00B3A0"));
        rippler4.setRipplerFill(Paint.valueOf("#F87951"));

        groupHolder.getChildren().addAll(rippler1, rippler2, rippler3, rippler4);
    }

    public void buildAll() {
        list_mti = FXCollections.observableArrayList();
        MarketingTargetIndividualService.getAllLimited().forEach(e -> {
            list_mti.add(e);
        });

        _id.setCellValueFactory(new PropertyValueFactory<>("id"));
        _age.setCellValueFactory(new PropertyValueFactory<>("age"));
        _field.setCellValueFactory(new PropertyValueFactory<>("field"));
        _maritalStatus.setCellValueFactory(new PropertyValueFactory<>("maritalStatus"));
        _maritalPeriod.setCellValueFactory(new PropertyValueFactory<>("maritalPeriod"));
        _result.setCellValueFactory(new PropertyValueFactory<>("result"));
        tableMti.getItems().clear();
        tableMti.getItems().addAll(list_mti);

        list_promotion = FXCollections.observableArrayList();
        PromotionService.getAllLimited().forEach(e -> {
            list_promotion.add(e);
        });

        _product.setCellValueFactory(new PropertyValueFactory<>("Product"));
        _startingDate.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
        _endingDate.setCellValueFactory(new PropertyValueFactory<>("endingDate"));

        tablePromotions.getItems().clear();
        tablePromotions.getItems().addAll(list_promotion);

        _product_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        _product_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        _product_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        list_product = FXCollections.observableArrayList();
        PromotionService.getAllProductsLimited().forEach(e -> {
            list_product.add(e);
        });
        tableProducts.getItems().clear();
        tableProducts.getItems().addAll(list_product);

    }

    @FXML
    private void mtiButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        Scene pp = new Scene(p);
        pp.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(pp);
        stage.setTitle("Home");
        //stage.getIcons().add(nav.applicationIcon);
        mouseDrag md = new mouseDrag();
        md.setDragged(p, stage);
        stage.show();
    }

    @FXML
    private void promotionBtnClicked() {

    }

    /*
    private void setUpDepartments() {
        departmentList.getItems().clear();
        try {
            connection = handler.getConnection();

            String query = "SELECT department.`name` FROM department";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                rs = pst.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("name");
                    departments = FXCollections.observableArrayList(name);
                    departmentList.getItems().addAll(departments);
                }
            }
            rs.close();

        } catch (Exception ex) {
            Logger.getLogger(OverviewController.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

    }


    @FXML
    private void addDepartment(ActionEvent event) {
        //Body Input text
        txtDepartment = new JFXTextField();
        txtDepartment.setPromptText("Enter new department name");
        txtDepartment.setLabelFloat(false);
        txtDepartment.setPrefSize(150, 50);
        txtDepartment.setPadding(new Insets(10, 5, 10, 5));
        txtDepartment.setStyle("-fx-font-size:13px; -fx-font-weight:bold;-fx-text-fill:#00B3A0");
        // Heading text
        Text t = new Text("Add New Department");
        t.setStyle("-fx-font-size:14px;");

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(t);
        dialogLayout.setBody(txtDepartment);

        JFXDialog dialog = new JFXDialog(deptStackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        // close button
        JFXButton closeButton = new JFXButton("Dismiss");
        closeButton.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;");
        //Add button
        JFXButton addBtn = new JFXButton("Add");
        addBtn.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;"
                + "");
        closeButton.setOnAction((ActionEvent event1) -> {
            dialog.close();
        });       
        addBtn.setOnAction((ActionEvent event1) -> {
            System.out.println(txtDepartment.getText());
            saveDepartment();
            setUpDepartments();
            dialog.close();
        });
        
        HBox box=new HBox();
        box.setSpacing(20);
        box.setPrefSize(200, 50);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.getChildren().addAll(addBtn,closeButton);

        dialogLayout.setActions(box);
        
        dialog.show();
    }

    private void saveDepartment() {
        String name = txtDepartment.getText().trim();
        try {
            if (name.isEmpty() || name.equals("")) {
                return;
            }
            connection = handler.getConnection();
            String query = "INSERT INTO department(name) VALUES (?) ";
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setString(1, name);
            ps.executeUpdate();
            System.out.println(query);
            System.out.println(name);
                       
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + ex.getCause());
        }

    }
     */
}
