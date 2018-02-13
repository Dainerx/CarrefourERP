/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author dainer
 */
public class Promotion {

    private int id;
    private Product product;
    private Newsletter newsletter;
    private String content;
    private LocalDate startingDate;
    private LocalDate endingDate;

    public Promotion(int id) {
        this.id = id;
    }

    public Promotion(int id, Product product, Newsletter newsletter, String content, LocalDate startingDate, LocalDate endingDate) {
        this.id = id;
        this.product = product;
        this.newsletter = newsletter;
        this.content = content;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Promotion(Product product, Newsletter newsletter, String content, LocalDate startingDate, LocalDate endingDate) {
        this.product = product;
        this.newsletter = newsletter;
        this.content = content;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Newsletter getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Newsletter newsletter) {
        this.newsletter = newsletter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

}
