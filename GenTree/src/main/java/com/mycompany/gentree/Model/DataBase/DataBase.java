/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gentree.Model.DataBase;

import com.mycompany.gentree.Controller.User;
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
                EUser eUser = new EUser();
                eUser.setUserLogin(data.getEmail());
                eUser.setUserPassword(data.getPassword());
                entityManager.merge(eUser);
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
