package com.example.owner.second_application_java2018.model.datasource;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.owner.second_application_java2018.model.backend.DBManagerFactory;
import com.example.owner.second_application_java2018.model.entities.Order;

import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * Created by owner on 09/01/2018.
 */

public class ForegroundServiceCarStatusChange extends IntentService {
    boolean isRun = false;

    public ForegroundServiceCarStatusChange() {
        super("ForegroundServiceCarStatusChange");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (isRun) {
            try {
                Thread.sleep(1000);
                Date myTime=new Date();
                for (Order o : DBManagerFactory.getManager().getOrders())
                {
                    if( (o.getEndRent().getTime()-myTime.getTime())<=10000)
                    {

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
