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
public class Attendance {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Employee employee;

    public Attendance() {
    }

    public Attendance(int id, LocalDate startDate, LocalDate endDate, Employee employee) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = employee;
    }

    public Attendance(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    
    
    
}
