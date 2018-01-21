package com.example.owner.second_application_java2018.model.datasource;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.owner.second_application_java2018.model.backend.DBManagerFactory;
import com.example.owner.second_application_java2018.model.backend.DB_manager;
import com.example.owner.second_application_java2018.model.entities.Order;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by owner on 09/01/2018.
 */

public class ForegroundServiceCarStatusChange extends IntentService {
    boolean isRun = true;

    public ForegroundServiceCarStatusChange() {
        super("ForegroundServiceCarStatusChange");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (isRun) {
            try {
                //Toast.makeText(this, "this is service", Toast.LENGTH_SHORT).show();
                DB_manager manager=DBManagerFactory.getManager();
                ArrayList<Order> oldOpenOrders=manager.getOpenOrders();
                Thread.sleep(1000);
                ArrayList<Order> newOpenOrders=manager.getOpenOrders();
                Boolean sendToUpdate=false;
                for (Order myOrder : oldOpenOrders)
                {
                    if(! newOpenOrders.contains(myOrder))
                    {
                        //old order is close now, update cars- more cars available
                        sendToUpdate=true;
                        Intent intent1 = new Intent();
                        intent1.setAction("INVITATION_SET");
                        ForegroundServiceCarStatusChange.this.sendBroadcast(intent1);
                    }
                }
                if (!sendToUpdate) {
                    for (Order myOrder : newOpenOrders) {
                        if (!oldOpenOrders.contains(myOrder)) {
                            //new order is opened, update cars- less cars available
                            Intent intent1 = new Intent("com.example.owner.second_application_java2018");
                            sendBroadcast(intent1);
                        }
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "Service print ...");
        }
    }

    @Override
    public void onDestroy() {
        isRun = false;
        super.onDestroy();
    }

}
