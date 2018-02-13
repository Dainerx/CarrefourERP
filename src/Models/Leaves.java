/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author Fatma Jaafar
 */
public class Leaves {
    
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String typeLeave;
    private String state;
    private int nbDays;
    private Employee employee;

    public Leaves() {
    }

    public Leaves(int id) {
        this.id = id;
    }

    public Leaves(int id, LocalDate startDate, LocalDate endDate, String typeLeave, String state, int nbDays, Employee employee) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.typeLeave = typeLeave;
        this.state = state;
        this.nbDays = nbDays;
        this.employee = employee;
    }

    public Leaves(int id, LocalDate startDate, LocalDate endLeave, String typeLeave, String state) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endLeave;
        this.typeLeave = typeLeave;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNbDays() {
        return nbDays;
    }

    public void setNbDays(int nbDays) {
        this.nbDays = nbDays;
    }

  

    public String getTypeLeave() {
        return typeLeave;
    }

    public void setTypeLeave(String typeLeave) {
        this.typeLeave = typeLeave;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Leaves{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", typeLeave=" + typeLeave + ", state=" + state + ", nbDays=" + 
                nbDays + ", employee=" + employee + '}';
    }
    
    
    
}
