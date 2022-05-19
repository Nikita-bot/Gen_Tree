package com.mycompany.gentree.Model.DataBase.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "relative")
public class ERelative{
    
    @Id
    @Column(name = "person_id")
    Integer personId;
    @Column(name = "rel_id")
    Integer relId;
    @Column(name = "role")
    String role;
    public Integer getPersonId() {
        return personId;
    }
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
    public Integer getRelId() {
        return relId;
    }
    public void setRelId(Integer relId) {
        this.relId = relId;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}