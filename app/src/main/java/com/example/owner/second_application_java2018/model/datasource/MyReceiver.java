package com.example.owner.second_application_java2018.model.datasource;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.owner.second_application_java2018.model.backend.DBManagerFactory;
import com.example.owner.second_application_java2018.model.backend.DB_manager;

public class MyReceiver extends BroadcastReceiver {

    DB_manager manager= DBManagerFactory.getManager();

    Context c = null;

    //filter logs by com.example.user.project_3071_1352 D/myservice1:

    public MyReceiver() {
    }

    String TAG = "Reciever";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Toast.makeText(context, "orders and cars being updated by receiver", Toast.LENGTH_LONG).show();
        manager.getOrdersFromServer();
        manager.getCarsFromServer();


    }
}

