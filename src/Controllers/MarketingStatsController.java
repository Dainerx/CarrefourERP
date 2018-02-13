/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.*;
import Services.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author dainer
 */
public class MarketingStatsController implements Initializable {

    @FXML
    AnchorPane pieChartHolder;
    @FXML
    AnchorPane pieChartHolder1;
    @FXML
    private Label libTotalNewsLetter;
    @FXML
    private Label libTotalTargets;
    @FXML
    private Label libTotalProducts;
    @FXML
    private Label libTotalPromotion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        libTotalNewsLetter.setText(String.valueOf(NewsletterService.count()));
        libTotalTargets.setText(String.valueOf(MarketingTargetIndividualService.count()));
        libTotalProducts.setText(String.valueOf(2));
        libTotalPromotion.setText(String.valueOf(PromotionService.count()));
        buildPieUsers();
        buildPiePromotion();
    }

    public void buildPieUsers() {
        
        double totalUser = NewsletterService.countUsers();
        double totalSub = NewsletterService.countSubscribtions();
        totalSub = (totalSub / totalUser) * 100;
        double totalNonSub = Math.abs(100 - totalSub);
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Subscribed users", totalSub),
                        new PieChart.Data("Non Subsribed users", totalNonSub));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Subcribtion");
        pieChartHolder.getChildren().add(chart);

    }
    
    public void buildPiePromotion() {
        double totalProducts = (double) PromotionService.countProducts();
        double totalPromotions = (double) PromotionService.countPromotions();
        System.out.println(totalProducts);
        System.out.println(totalPromotions);
        System.out.println(totalPromotions / totalProducts);
        double totalPromoted = (totalPromotions / totalProducts) * 100;
        double totalNonPromoted = Math.abs(100 - totalPromoted);
        System.out.println(totalPromoted);
        System.out.println(totalNonPromoted);
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Promoted Products", totalPromoted),
                        new PieChart.Data("Non promoted Products", totalNonPromoted));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Promotions");
        pieChartHolder1.getChildren().add(chart);
    }

}
