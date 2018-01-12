package com.example.owner.second_application_java2018.model.backend;


//import com.example.owner.second_application_java2018.model.datasource.MySQL_DBManager;

import com.example.owner.second_application_java2018.model.datasource.MySQL_DBManager;

/**
 * Created by owner on 26/11/2017.
 */

public class DBManagerFactory {
    static DB_manager manager = null;

    public static DB_manager getManager() {
        if (manager == null)
            manager = new MySQL_DBManager(); //List_DBManager();  //
        return manager;
    }
}
