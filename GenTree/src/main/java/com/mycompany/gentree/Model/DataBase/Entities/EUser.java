package com.mycompany.gentree.Model.DataBase.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class EUser {
    @Id
    @Column(name = "id")
    private int userId;
    @Column(name = "login")
    private String userLogin;
    @Column(name = "password")
    private String userPassword;

    public EUser(int userId, String userLogin, String userPassword) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserLogin() {
        return userLogin;
    }
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
