package com.mycompany.gentree.Model.DataBase.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;



@Entity
@Table(name = "person")
public class EPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    @SequenceGenerator(name = "person_id_seq", sequenceName = "person_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "name")
    private String name;
    @Column(name = "second_name")
    private String second_name;
    @Column(name = "father_name")
    private String father_name;
    @Column(name = "date_of_bith")
    private Date date_of_bith;
    @Column(name = "date_of_death")
    private Date date_of_death;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    
    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public Date getDate_of_birth() {
        return date_of_bith;
    }

    public void setDate_of_birth(Date date_of_bith) {
        this.date_of_bith = date_of_bith;
    }

    public Date getDate_of_death() {
        return date_of_death;
    }

    public void setDate_of_death(Date date_of_death) {
        this.date_of_death = date_of_death;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
