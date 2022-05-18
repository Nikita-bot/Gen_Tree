package com.mycompany.gentree.Controller;

import java.util.GregorianCalendar;

public class User {
    private Integer id;
    private String name;
    private String secondName;
    private String fathersName;
    private String email;
    private String password;
    private Integer userId;
    private String date_of_bith;
    private String date_of_death = "0";
    
    public String getName() {
        return name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public String getFathersName() {
        return fathersName;
    }
    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getDate_of_birth() {
        return date_of_bith;
    }
    public void setDate_of_bith(String date_of_bith) {
        this.date_of_bith = date_of_bith;
    }
    public String getDate_of_death() {
        return date_of_death;
    }
    public void setDate_of_death(String date_of_death) {
        this.date_of_death = date_of_death;
    }

}
