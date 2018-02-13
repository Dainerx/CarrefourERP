/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imports;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 *
 * @author Fatma Jaafar
 */
public class Navigation {

    private final String login = "/Views/LoginFXML.fxml";
    private final String homeMarketing = "/Views/marketingHome.fxml";
    private final String dashboard = "/Views/dashboard.fxml";
    private final String overViewMarketing = "/Views/overViewMarketing.fxml";
    private final String promotion = "/Views/promotion.fxml";
    private final String newsletter = "/Views/newsletter.fxml";
    private final String marketingStats = "/Views/marketingStats.fxml";
    private final String marketingTargetIndividual = "/Views/marketingTargetIndividual.fxml";

    private final String homeReclamation = "/Views/reclamationHome.fxml";
    private final String adminReclamation = "/Views/adminReclamation.fxml";
    private final String fiedlityBack = "/Views/fidelityBack.fxml";
    private final String reclamationStatistics = "/Views/reclamationStatistics.fxml";
    private final String ajoutReclamation = "/Views/ajoutReclamation.fxml";

    private final String jobManagement = "/Views/jobManagement.fxml";
    private final String editjob = "/Views/editjob.fxml";
    private final String addjob = "/Views/addjob.fxml";
    private final String empManagement = "/Views/employeeManagement.fxml";

    public Image applicationIcon = new Image(getClass().getResourceAsStream("/Img/car-logo.png"));

    public String getLogin() {
        return login;
    }

    public String getHomeMarketing() {
        return homeMarketing;
    }

    public String getDashboard() {
        return dashboard;
    }

    public String getJobManagement() {
        return jobManagement;
    }

    public String getEditJob() {
        return editjob;
    }

    public String getAddJob() {
        // System.out.println("dddddddddd"+addjob);
        return addjob;
    }

    public String getEmpManagement() {
        return empManagement;
    }

    public String getOverViewMarketing() {
        return overViewMarketing;
    }

    public String getPromotion() {
        return promotion;
    }

    public String getNewsletter() {
        return newsletter;
    }

    public String getMarketingTargetIndividual() {
        return marketingTargetIndividual;
    }

    public String getMarketingStats() {
        return marketingStats;
    }

    public String getHomeReclamation() {
        return homeReclamation;
    }

    public String getAdminReclamation() {
        return adminReclamation;
    }

    public String getFieldlityBack() {
        return fiedlityBack;
    }

    public String getReclamationStat() {
        return reclamationStatistics;
    }

    public String getAjoutReclamation() {
        return ajoutReclamation;
    }

    public void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void animationFade(Node e) {
        FadeTransition x = new FadeTransition(new Duration(1000), e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.LINEAR);
        x.play();
    }

    public void numbersCheck(TextField text) {
        text.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (!text.getText().matches("[0-9]*")) {
                    showAlert(Alert.AlertType.WARNING, "Warning", null, " input supports numbers only!!");
                    text.setText("");
                    text.requestFocus();
                }
            }
        });

    }

}
