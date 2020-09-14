package org.Biblioteka;

import java.util.Objects;

public class Worker {
    private int id;
    private String name;
    private String surName;
    private String jmbg;
    private String DateOfBirth;
    private String NoOfId;
    private String adress;
    private String phoneNumber;
    private String username;
    private String password;

    public Worker() {
    }

    public Worker(int id, String name, String surName, String jmbg, String dateOfBirth, String noOfId, String adress, String phoneNumber, String username, String password) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.jmbg = jmbg;
        DateOfBirth = dateOfBirth;
        NoOfId = noOfId;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
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
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getNoOfId() {
        return NoOfId;
    }

    public void setNoOfId(String noOfId) {
        NoOfId = noOfId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return id == worker.id &&
                Objects.equals(name, worker.name) &&
                Objects.equals(surName, worker.surName) &&
                Objects.equals(jmbg, worker.jmbg) &&
                Objects.equals(DateOfBirth, worker.DateOfBirth) &&
                Objects.equals(NoOfId, worker.NoOfId) &&
                Objects.equals(adress, worker.adress) &&
                Objects.equals(phoneNumber, worker.phoneNumber) &&
                Objects.equals(username, worker.username);
    }

}
