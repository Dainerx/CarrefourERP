/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Fatma Jaafar
 */
public class Jobs {
    
    private int id;
    private String name;
    private String description;
    private int nbEmpExpected;
    private float salary;
    
    

    public Jobs() {
    }

    public Jobs(int id) {
        this.id = id;
    }

    public Jobs(int id, String name, String description, int nbEmpExpected, float salary) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nbEmpExpected = nbEmpExpected;
        this.salary = salary;
    }

    public Jobs(String name, String description, int nbEmpExpected, float salary) {
        this.name = name;
        this.description = description;
        this.nbEmpExpected = nbEmpExpected;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbEmpExpected() {
        return nbEmpExpected;
    }

    public void setNbEmpExpected(int nbEmpExpected) {
        this.nbEmpExpected = nbEmpExpected;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jobs other = (Jobs) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Jobs{" + "id=" + id + ", name=" + name + ", description=" + description + ", nbEmpExpected=" + nbEmpExpected + ", salary=" + salary + '}';
    }
    
    
    
    
    
}
