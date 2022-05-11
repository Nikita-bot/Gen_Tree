/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gentree.Model.DataBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mycompany.gentree.Controller.User;
import com.mycompany.gentree.Model.DataBase.Entities.EPerson;
import com.mycompany.gentree.Model.DataBase.Entities.EUser;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;

/**
 *
 * @author 4eis
 */
public class DataBase implements IDataBase{
    @Resource
    private UserTransaction uTransaction;
    @PersistenceContext(unitName = "GenTree_Unit")
    private EntityManager entityManager;

    public DataBase() {
    }
    
    //@Override
    public String registration(User data) {
        System.out.println("DataBase::Registration");
        try{
            uTransaction.begin();
            try{
                // EUser eUser = new EUser();
                // eUser.setUserLogin(data.getEmail());
                // eUser.setUserPassword(data.getPassword());
                // entityManager.merge(eUser);
                DateFormat dateFormat = new SimpleDateFormat("yy:MM:dd");
                dateFormat.setLenient(false);
                Date bith = dateFormat.parse(data.getDate_of_bith());
                System.out.println(bith);
                Date dead = dateFormat.parse(data.getDate_of_death());
                System.out.println(dead);

                EPerson ePerson = new EPerson();
                ePerson.setDate_of_death(dead);
                ePerson.setDate_of_bith(bith);
                ePerson.setUserId(data.getUserId());
                ePerson.setSecond_name(data.getSecondName());
                ePerson.setName(data.getName());
                ePerson.setFather_name(data.getFathersName());
                entityManager.persist(ePerson);
            }
            catch(Exception e){
                System.out.println("Error when incert new User: ");
                e.printStackTrace();
            }
            System.out.println("DataBase::BeforeCommit");
            uTransaction.commit();
            System.out.println("DataBase::AfterCommit");
        }
        catch(Exception e){
            System.out.println("Error when transaction init:");
            e.printStackTrace();
        }
        return null;
    }

}
