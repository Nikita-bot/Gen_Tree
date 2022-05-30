/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gentree.Model.DataBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mycompany.gentree.Controller.Person;

import com.mycompany.gentree.Model.DataBase.Entities.EPerson;
import com.mycompany.gentree.Model.DataBase.Entities.ERelative;
import com.mycompany.gentree.Model.DataBase.Entities.EUser;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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

    //@Override
    //public String[] getPersonData(Integer id) {entityManager.find(EPerson, id);}
    
    @Override
    public Integer checkPersonInDataBase(Person data) {

        System.out.println("DataBase::checkPersonInDataBas");
        Integer id = 0;
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

                EPerson ePerson = new EPerson();
                TypedQuery<EPerson> query1 = entityManager.createQuery("SELECT e FROM EPerson e WHERE e.userId = :id ", EPerson.class)
                        .setParameter("id", eUser.getUserId());

                ePerson = query1.getSingleResult();
                id = ePerson.getId();
            }
            catch(Exception e){
                System.out.println("Error when insert new User: ");
                e.printStackTrace();
            }
            System.out.println("DataBase::BeforeCommit");
            uTransaction.commit();
            System.out.println("DataBase::AfterCommit");
            return id;
        }
        catch(Exception e){
            System.out.println("Error when transaction init:");
            e.printStackTrace();
        }
        return id;

    }

    @Override
    public List<String> getRelatives(Integer id) {
        System.out.println("DataBase::getRelatives");
        List<String> rel = new ArrayList<String>() ;
        try 
        {
            //uTransaction.begin();
            try{
                TypedQuery<ERelative> query = entityManager.createQuery("SELECT r FROM ERelative r WHERE r.personId = :id", ERelative.class);
                query.setParameter("id", id);
                List<ERelative> relatives = query.getResultList();
                List<EPerson> relativeData = new ArrayList();
                for (ERelative e: relatives){
                    //System.out.println("id: " + e.getRelId() + " role: " + e.getRole());
                    TypedQuery<EPerson> personQuery = entityManager.createQuery("SELECT p FROM EPerson p WHERE p.id = :id", EPerson.class);
                    personQuery.setParameter("id", e.getRelId());
                    relativeData.add(personQuery.getSingleResult());
                }
                for(int i = 0; i < relativeData.size(); i++){
                    JSONObject obj = new JSONObject();
                    obj.put("id", Integer.toString(relativeData.get(i).getId()));
                    obj.put("role", relatives.get(i).getRole());
                    obj.put("firstName", relativeData.get(i).getName());
                    obj.put("secondName", relativeData.get(i).getSecond_name());
                    obj.put("fatherName", relativeData.get(i).getFather_name());

                    rel.add(obj.toJSONString());
                }
                
                /*
                Пример доставания данных
                for (String[] rels : rel) {
                    System.out.println("Row = " + row); //Выведет строку:  Row = [rel_id,role]
                } 
                */
            }
            catch(Exception e){
                System.out.println("Error Select QUERY");
            }
            
            //uTransaction.commit();		
        } 
        catch (Exception e) 
        {
            System.out.println("Error Select");
        } 
        return rel; 
    }


}
