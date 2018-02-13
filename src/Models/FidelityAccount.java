/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 *
 * @author ITECH
 */
public class FidelityAccount {

    private int id;
    private Client client;
    private int number_points;
    private int amount;
    private String date_creation;

    public FidelityAccount() {
    }

    public FidelityAccount(int id, Client client, int number_points, int amount, String date_creation) {
        this.id = id;
        this.client = client;
        this.number_points = number_points;
        this.amount = amount;
        this.date_creation = date_creation;
    }

    public FidelityAccount(Client client, int number_points, int amount, String date_creation) {
        this.client = client;
        this.number_points = number_points;
        this.amount = amount;
        this.date_creation = date_creation;
    }

    public FidelityAccount(int number_points, int amount, String date_creation) {
        this.number_points = number_points;
        this.amount = amount;
        this.date_creation = date_creation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getNumber_points() {
        return number_points;
    }

    public void setNumber_points(int number_points) {
        this.number_points = number_points;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "FidelityAccount{" + "id=" + id + ", client=" + client + ", number_points=" + number_points + ", amount=" + amount + ", date_creation=" + date_creation + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final FidelityAccount other = (FidelityAccount) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (this.number_points != other.number_points) {
            return false;
        }
        if (this.amount != other.amount) {
            return false;
        }
        if (!Objects.equals(this.date_creation, other.date_creation)) {
            return false;
        }
        return true;
    }

    public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String text = df.format(d);
        return text;
    }

    public java.sql.Date convert(String date) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = sdf1.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

        return sqlDate;
    }
}
