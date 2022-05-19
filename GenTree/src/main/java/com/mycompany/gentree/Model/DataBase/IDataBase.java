/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.gentree.Model.DataBase;

import com.mycompany.gentree.Controller.Person;

/*
 *
 * @author 4eis
 */
public interface IDataBase {
    public Integer registrationPerson(Person data);
    public String createPerson(Person data);
    public boolean checkPersonInDataBase(Person data);
}