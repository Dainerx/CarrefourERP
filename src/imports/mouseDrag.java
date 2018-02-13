/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imports;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 *
 * @author Fatma Jaafar
 */

public class mouseDrag {
    
    private double x;
    private double y;
    
    public void setDragged(Parent database_parent, Stage app_stage){
        database_parent.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                x=event.getSceneX();
                y=event.getSceneY();
            }
            });

            database_parent.setOnMouseDragged(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event){
                    app_stage.setX(event.getScreenX()-x);
                    app_stage.setY(event.getScreenY()-y);
                }
            });
    }
    
}
