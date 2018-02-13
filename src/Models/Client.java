/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author ITECH
 */
public class Client {
        
    private int id;
    private String first_name;
    private String last_name;
    private int cell;
    private String birth_date;
    private String gender;
    private String address;
    private User user;

    public Client() {
    }

    public Client(int id, String first_name, String last_name, int cell, String birth_date, String gender, String address, User user) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.cell = cell;
        this.birth_date = birth_date;
        this.gender = gender;
        this.address = address;
        this.user = user;
    }

    public Client(String first_name, String last_name, int cell, String birth_date, String gender, String address, User user) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.cell = cell;
        this.birth_date = birth_date;
        this.gender = gender;
        this.address = address;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", cell=" + cell + ", birth_date=" + birth_date + ", gender=" + gender + ", address=" + address + ", user=" + user + '}';
    }
    
    
}
