/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Account;
import Services.AccountService;
import imports.Navigation;
import imports.exit;
import imports.mouseDrag;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javax.tools.JavaFileManager;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Fatma Jaafar
 */
public class LoginFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Navigation nav = new Navigation();
    @FXML
    private AnchorPane effectFade;

    @FXML
    private Label exit;

    @FXML
    private Button login;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private void handleExitClicked() {
        exit ex = new exit();
        ex.exitClicked();
    }

    @FXML
    private void setHover(javafx.scene.input.MouseEvent event) {
        exit.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void setDefault(javafx.scene.input.MouseEvent event) {
        exit.setStyle("-fx-background-color:  #4183D7;");
    }

    @FXML
    public void HandleLogin(MouseEvent event) throws IOException {

        TrayNotification tray = new TrayNotification();
        String username_text = username.getText();
        String password_text = password.getText();
        if (username_text.equals("") || password_text.equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "Warning", null, "Please fill all the inputs!!");
            username.requestFocus();
        } else {
            Account account = new Account();
            AccountService accountService = new AccountService();

            account = accountService.Login(username_text, password_text);
            if (account.getStatus_login() == true) {
                if (account.getStatus().equals("Login")) {
                    nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Username already logged in !!");
                    username.requestFocus();
                } else {
                    tray.setNotificationType(NotificationType.CUSTOM);
                    tray.setTitle("Login Success");
                    tray.setMessage("Hello " + account.getLogin() + ". Welcome to your desktop application, have a nice day and good luck");
                    tray.setAnimationType(AnimationType.FADE);
                    tray.showAndDismiss(Duration.millis(1500));
                    tray.setRectangleFill(Color.valueOf("#4183D7"));
                    tray.setImage(new Image("/Img/iconsuser2.png"));

                    Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage2.hide();

                    FXMLLoader loader = new FXMLLoader();
                    
                    //Oussama
                    //loader.setLocation(getClass().getResource(getHomeMarketing()));
                    //Yosra
                    loader.setLocation(getClass().getResource(nav.getHomeMarketing()));
                    try {
                        loader.load();
                    } catch (Exception e) {
                    }
                    /*
                    Yosra
                    ReclamationHomeController mc = loader.getController();
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    Scene pp = new Scene(p);
                    pp.setFill(javafx.scene.paint.Color.TRANSPARENT);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(pp);
                    stage.setTitle("Home");
                    stage.getIcons().add(nav.applicationIcon);
                    mouseDrag md = new mouseDrag();
                    md.setDragged(p, stage);
                    stage.show();
                    */
                    MarketingHomeController mc = loader.getController();
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    Scene pp = new Scene(p);
                    pp.setFill(javafx.scene.paint.Color.TRANSPARENT);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(pp);
                    stage.setTitle("Home");
                    stage.getIcons().add(nav.applicationIcon);
                    mouseDrag md = new mouseDrag();
                    md.setDragged(p, stage);
                    stage.show();
                    
                }
            } else {
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Username   and password incorrect !!");
                username.setText("");
                password.setText("");
                username.requestFocus();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
