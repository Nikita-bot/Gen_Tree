package com.mycompany.gentree.Model.DataBase.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class EUser {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    //@SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int userId;
    @Column(name = "login")
    private String userLogin;
    @Column(name = "password")
    private String userPassword;
    
    public EUser() {
 
    }

    public EUser(int id, String userLogin, String userPassword) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userId = id;
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
