/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


import java.util.Objects;

/**
 *
 * @author ITECH
 */
public class Reclamation {
    private int id;
    private String subject;
    private String type_claim;
    private String description;
    private int status_r;
    private Client client;

    public Reclamation(int id,Client client, String subject, String type_claim, String description, int status_r) {
        this.id = id;
        this.client = client;
        this.subject = subject;
        this.type_claim = type_claim;
        this.description = description;
        this.status_r = status_r;
    }

    public Reclamation() {
    }

    public Reclamation(Client client, String subject, String type_claim, String description, int status_r) {
        this.client = client;
        this.subject = subject;
        this.type_claim = type_claim;
        this.description = description;
        this.status_r = status_r;
    }

    public Reclamation(String subject, String type_claim, String description) {
        this.subject = subject;
        this.type_claim = type_claim;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
   

   
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType_claim() {
        return type_claim;
    }

    public void setType_claim(String type_claim) {
        this.type_claim = type_claim;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus_r() {
        return status_r;
    }

    public void setStatus_r(int status_r) {
        this.status_r = status_r;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

   

   

   

}
