/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.gentree.Model.DataBase;

import com.mycompany.gentree.Controller.User;

/*
 *
 * @author 4eis
 */
public interface IDataBase {
    public Integer registrationUser(User data);
    public String createPerson(User data);
}