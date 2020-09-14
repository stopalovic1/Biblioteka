package org.Biblioteka;

public class Reader {

    private int id;
    private String name;
    private String surName;
    private String jmbg;
    private String dateOfBirth;
    private String noOfId;
    private String adress;
    private String phoneNumber;

    public Reader() {
    }

    public Reader(int id, String name, String surName, String jmbg, String dateOfBirth, String noOfId, String adress, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.jmbg = jmbg;
        this.dateOfBirth = dateOfBirth;
        this.noOfId = noOfId;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNoOfId() {
        return noOfId;
    }

    public void setNoOfId(String noOfId) {
        this.noOfId = noOfId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() { return name+" "+surName; }
}
