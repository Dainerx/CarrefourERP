/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Label;
import javax.swing.Timer;
/**
 *
 * @author Fatma Jaafar
 */
public class time {
    

    public String date(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        return(sdf.format(date));       
    }
    public String dateQuery(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return(sdf.format(date));       
    }
    public String dateMonth(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        return(sdf.format(date));       
    }
    public String dateYear(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return(sdf.format(date));       
    }
    
    
    
}
