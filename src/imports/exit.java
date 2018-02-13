/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imports;

import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Fatma Jaafar
 */
public class exit {
    public void exitClicked(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to exit ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.exit(0);
        } 
    }
    
    public void minimizeClicked(MouseEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}
