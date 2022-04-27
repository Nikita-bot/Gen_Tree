package com.mycompany.gentree.resources.DataBase;

import com.mycompany.gentree.resources.User;

public interface IDB {
    public int checkUserInBase(User u);
    public void registration(User u);
}
