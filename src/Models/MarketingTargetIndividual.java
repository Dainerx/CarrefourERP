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
public class MarketingTargetIndividual {

    int id;
    int age;
    String field;
    String maritalStatus;
    int marriagePeriod;
    String result;

    public MarketingTargetIndividual() {

    }

    public MarketingTargetIndividual(int id, int age, String field, String maritalStatus, int marriagePeriod) {
        this.id = id;
        this.age = age;
        this.field = field;
        this.maritalStatus = maritalStatus;
        this.marriagePeriod = marriagePeriod;
    }

    public MarketingTargetIndividual(int age, String field, String maritalStatus, int marriagePeriod) {
        this.age = age;
        this.field = field;
        this.maritalStatus = maritalStatus;
        this.marriagePeriod = marriagePeriod;
    }

    public MarketingTargetIndividual(int age, String field, String maritalStatus, int marriagePeriod, String result) {
        this.age = age;
        this.field = field;
        this.maritalStatus = maritalStatus;
        this.marriagePeriod = marriagePeriod;
        this.result = result;
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

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getMarriagePeriod() {
        return marriagePeriod;
    }

    public void setMarriagePeriod(int marriagePeriod) {
        this.marriagePeriod = marriagePeriod;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
