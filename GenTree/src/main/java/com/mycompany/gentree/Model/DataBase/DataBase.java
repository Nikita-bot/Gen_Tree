/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gentree.Model.DataBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mycompany.gentree.Controller.Person;
import com.mycompany.gentree.Model.DataBase.Entities.EPerson;
import com.mycompany.gentree.Model.DataBase.Entities.EUser;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
    
    @Override
    public Integer registrationPerson(Person data){
        System.out.println("DataBase::registrationUser");
        Integer id = 0;
        try{
            uTransaction.begin();
            try{ 
                EUser eUser = new EUser();
                eUser.setUserLogin(data.getEmail());
                eUser.setUserPassword(data.getPassword());
                System.out.println(eUser.getUserLogin());
                System.out.println(eUser.getUserPassword());
                //entityManager.merge(eUser);
                
                entityManager.persist(eUser);
                
                TypedQuery<EUser> query = entityManager.createQuery("SELECT e FROM EUser e WHERE e.userLogin = :login AND e.userPassword = :password", EUser.class)
                        .setParameter("login", data.getEmail())
                        .setParameter("password", data.getPassword());
                
                eUser = query.getSingleResult();
                id = eUser.getUserId();
            }
            catch(Exception e){
                System.out.println("Error when insert new User: ");
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
        return id;
        
    }
    
    @Override
    public String createPerson(Person data) {
        System.out.println("DataBase::CreatePerson");
        try{
            uTransaction.begin();
            try{
                
                DateFormat dateFormat = new SimpleDateFormat("yy:MM:dd");
                dateFormat.setLenient(false);
                Date bith = dateFormat.parse(data.getDate_of_birth());
                //System.out.println(bith);
                EPerson ePerson = new EPerson();
                if(!data.getDate_of_death().equals("0")){
                    Date dead = dateFormat.parse(data.getDate_of_death());
                    ePerson.setDate_of_death(dead);
                }
                ePerson.setUserId(data.getUserId());
                ePerson.setDate_of_birth(bith);
                ePerson.setSecond_name(data.getSecondName());
                ePerson.setName(data.getName());
                ePerson.setFather_name(data.getFathersName());
                entityManager.persist(ePerson);
            }
            catch(Exception e){
                System.out.println("Error when insert new User: ");
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

    @Override
    public boolean checkPersonInDataBase(Person data) {
        try{
            uTransaction.begin();
            try{ 
                EUser eUser = new EUser();
                eUser.setUserLogin(data.getEmail());
                eUser.setUserPassword(data.getPassword());
                TypedQuery<EUser> query = entityManager.createQuery("SELECT e FROM EUser e WHERE e.userLogin = :login AND e.userPassword = :password", EUser.class)
                        .setParameter("login", data.getEmail())
                        .setParameter("password", data.getPassword());
                
                eUser = query.getSingleResult();
                return true;
            }
            catch(Exception e){
                System.out.println("Error when insert new User: ");
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
        return false;

    }


}
