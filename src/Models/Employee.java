/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fatma Jaafar
 * 
 */
public class Employee {
    
    private int id;
    private String FirstName;
    private String LastName;
    private int cin;
    private String email;
    private String sexe;
    private LocalDate dateOfBirth;
    private String phone;
    private String address;
    private String startHire;
    private String endHire;
    private String photo;
    private String Department;
    private Employee Manager;
    private Account account;
    private Jobs job;

    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, String FirstName, String LastName, int cin, String email, String sexe, LocalDate dateOfBirth, String phone, String address, String startHire, String endHire, Jobs job) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.cin = cin;
        this.email = email;
        this.sexe = sexe;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
        this.startHire = startHire;
        this.endHire = endHire;
       
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartHire() {
        return startHire;
    }

    public void setStartHire(String startHire) {
        this.startHire = startHire;
    }

    public String getEndHire() {
        return endHire;
    }

    public void setEndHire(String endHire) {
        this.endHire = endHire;
    }

    public Employee getManager() {
        return Manager;
    }

    public void setManager(Employee Manager) {
        this.Manager = Manager;
    }



    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", cin=" + cin + ", email=" + email + ", sexe=" + sexe + ", dateOfBirth=" + dateOfBirth + ", phone=" + phone + ", address=" + address + ", startHire=" + startHire + ", endHire=" + endHire + ", Manager=" + Manager + ", job=" + job + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
}
