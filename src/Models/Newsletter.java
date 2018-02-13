/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

/**
 *
 * @author dainer
 */
public class Newsletter extends RecursiveTreeObject<Newsletter> {

    private int id;
    private String subject;
    private String content;
    private String image;
    private int numberSub;

    public Newsletter(int id, String subject, String content, String image, int numberSub) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.image = image;
        this.numberSub = numberSub;
    }

    public Newsletter(int id, String subject, String content, String image) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.image = image;
    }

    public Newsletter(String subject, String content, String image) {
        this.subject = subject;
        this.content = content;
        this.image = image;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNumberSub() {
        return numberSub;
    }

    public void setNumberSub(int numberSub) {
        this.numberSub = numberSub;
    }

    @Override
    public String toString() {
        return subject;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Newsletter other = (Newsletter) obj;
        return true;
    }

}
