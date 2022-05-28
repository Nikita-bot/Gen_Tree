/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gentree.Model;

import com.mycompany.gentree.Controller.Person;
import com.mycompany.gentree.Model.DataBase.IDataBase;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.List;

/**
 *
 * @author 4eis
 */
public class App implements IApp{
    
    @Inject
    private IDataBase db;
    
    public void init(String login, String pass){
    }
    
    public String registration(String data){
        Jsonb jb = JsonbBuilder.create();
        Person person = jb.fromJson(data, Person.class);

        if(db.checkPersonInDataBase(person) == 0){
            Integer id = db.registrationPerson(person);
            if(id != 0){
                person.setUserId(id);
            }
            db.createPerson(person);
            return "Registration Complete";
        }
        else{
            return "Such User already exist";
        }
    }
    
    public String loginization(String data){
        Jsonb jb = JsonbBuilder.create();
        Person person = jb.fromJson(data, Person.class);
        Integer user_id = db.checkPersonInDataBase(person);
        if(user_id != 0){
            String token = Token.generateToken(person.getEmail() + " " + Integer.toString(user_id));
            Token.checkToken(token);
            return token;
        }
        else{
            return "Wrong mail or password";
        }
        
    }
    
    public String personalData(String data){
        String login = Token.checkToken(data);
        Integer id = Integer.parseInt(login.substring(login.indexOf(' ') + 1, login.length()));
        List<String[]> relatives = db.getRelatives(id);
        for (String[] relative : relatives) {
            //System.out.println(relative[0] + " " + relative[1] + " " + relative[2] + " " + relative[3]);
        }
        StringBuilder sb = new StringBuilder();
        for (String[] s : relatives){
            sb.append(String.join(",", s));
            sb.append("\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
