/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviews;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author AGORA
 */
public class Jobstable {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty description;
    private final StringProperty nb;
    private final StringProperty salary;

    public Jobstable(String id, String name, String description, String nb, String salary) {
        this.id = new SimpleStringProperty(id);
        this.name =new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.nb = new SimpleStringProperty(nb);
        this.salary = new SimpleStringProperty(salary);
    }
    
     public String getId(){
        return id.get();
    }

  

    public String getName() {
        return name.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getNb() {
        return nb.get();
    }

    public String getSalary() {
        return salary.get();
    }
     
     public void setId(String Value){
        id.set(Value);
    }
     public void setName(String Value){
        name.set(Value);
    }
      public void setDescription(String Value){
        description.set(Value);
    }
       public void setNb(String Value){
        nb.set(Value);
    }
        public void setSalary(String Value){
        salary.set(Value);
    }
        
    
    
     public StringProperty idProperty(){
        return id;
    }
     
      public StringProperty nameProperty(){
        return name;
    }
       public StringProperty descriptionProperty(){
        return description;
    }
        public StringProperty nbProperty(){
        return nb;
    }
         public StringProperty salaryProperty(){
        return salary;
    }
    
    
}
