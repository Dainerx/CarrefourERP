/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import imports.*;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import imports.Navigation;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author dainer
 */
public class ReclamationHomeController implements Initializable {

    Navigation nav = new Navigation();

    exit ex = new exit();
    private String username_text, email_text, nama_text;

    TrayNotification tray = new TrayNotification();

    @FXML
    private Label minimize, exit, date, timee, emailUser, Username, usernameUser, idUser, levelUser;

    @FXML
    private Button btn_home, btn_home1, btn_home11;

    @FXML
    private Button btn_user_management;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane loadPane, blur;

    @FXML
    private StackPane trans;

    @FXML
    private ImageView imageUser;

    @FXML
    private VBox rh;

    public static String menu;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private Button buttonHomerh, menu_promotion_btn, menu_mt_btn;

    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Calendar time = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        timee.setText(simpleDateFormat.format(time.getTime()));
                    }
                }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
    private void setHover2(javafx.scene.input.MouseEvent event) {
        minimize.setStyle("-fx-background-color: blue;");
    }

    @FXML
    private void setDefault2(javafx.scene.input.MouseEvent event) {
        minimize.setStyle("-fx-background-color:  #4183D7;");
    }

    @FXML
    private void imageHover(MouseEvent event) {
        imageUser.setCursor(Cursor.HAND);
    }

    @FXML
    private void handleExitClicked() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            System.exit(0);
        }
    }

    @FXML
    private void handleMinimizeClicked(MouseEvent event) {
        ex.minimizeClicked(event);
    }

    @FXML
    private void setBackgroundUserManagement(javafx.scene.input.MouseEvent event) {
        btn_home1.setStyle("-fx-background-color: #ECF0F1");
        btn_user_management.setStyle("-fx-background-color: #D2D7D3");
    }

    @FXML
    private void setBackgroundHome1(javafx.scene.input.MouseEvent event) {
        btn_home1.setStyle("-fx-background-color: #D2D7D3");
        btn_user_management.setStyle("-fx-background-color: #ECF0F1");
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Do you really want  to log out ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            tray.setNotificationType(NotificationType.CUSTOM);
            tray.setTitle("Logout Success");
            tray.setMessage("Bye " + Username.getText() + ". Stay in touch! :D ");
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(Duration.millis(1500));
            tray.setRectangleFill(Color.valueOf("#4183D7"));
            tray.setImage(new Image("/Img/iconsuser2.png"));

            Parent parent = FXMLLoader.load(getClass().getResource(nav.getLogin()));
            Scene database_scene = new Scene(parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(database_scene);
            app_stage.setTitle("Login");
            mouseDrag md = new mouseDrag();
            md.setDragged(parent, app_stage);
            app_stage.getIcons().add(nav.applicationIcon);
            app_stage.show();
        }
    }

    @FXML
    private void closeclicked(ActionEvent event) {
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
    }

    @FXML
    private void homeClicked() throws IOException {
        homeMenu();
    }

    public void homeMenu() throws IOException {
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getDashboard()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    
    @FXML
    private void reclamationsClick(ActionEvent event) throws IOException {
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getAdminReclamation()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void fidelityBackClick(ActionEvent event) throws IOException {
        System.out.println("wouah");
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getFieldlityBack()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void ajoutReclamationClick(ActionEvent event) throws IOException {
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getAjoutReclamation()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void statClick(ActionEvent event) throws IOException {
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getReclamationStat()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindToTime();
        time time = new time();
        date.setText(time.date());
        imageUser.setCursor(Cursor.HAND);
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getAdminReclamation()));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }

}
