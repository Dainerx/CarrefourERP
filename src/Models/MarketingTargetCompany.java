/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author dainer
 */
public class MarketingTargetCompany {

    int id;
    int age;
    String field;
    String location;
    String result;

    public MarketingTargetCompany(int id, int age, String field, String location) {
        this.id = id;
        this.age = age;
        this.field = field;
        this.location = location;
    }

    public MarketingTargetCompany(int age, String field, String location) {
        this.age = age;
        this.field = field;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
