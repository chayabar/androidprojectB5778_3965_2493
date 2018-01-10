/*
package com.example.owner.second_application_java2018.model.datasource;

import android.content.ContentValues;

import com.example.owner.second_application_java2018.model.backend.DB_manager;
import com.example.owner.second_application_java2018.model.backend.RentConst;
import com.example.owner.second_application_java2018.model.entities.Branch;
import com.example.owner.second_application_java2018.model.entities.Car;
import com.example.owner.second_application_java2018.model.entities.CarModel;
import com.example.owner.second_application_java2018.model.entities.Customer;
import com.example.owner.second_application_java2018.model.entities.Order;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.owner.second_application_java2018.model.backend.RentConst.ContentValuesToCustomer;

*/
/**
 * Created by owner on 22/12/2017.
 *//*


public class MySQL_DBManager implements DB_manager {

    private String WEB_URL = "http://avichzer.vlab.jct.ac.il/rentPHP";

    @Override
    public boolean existCustomer(ContentValues newcustomer) {
        Customer customer = ContentValuesToCustomer(newcustomer);
        for (Customer item : getCustomers())
            if (item.getID() == customer.getID()) {
                return true;
            }
        return false;
    }

    @Override
    public boolean addCustomer(ContentValues values)
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/addCustomer.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            //if (id == true)
            //    SetUpdate();
            //printLog("addStudent:\n" + result);
            return isDone;
        }
        catch (IOException e)
        {
            //printLog("addStudent Exception:\n" + e);
            return false;
        }
    }

    @Override
    public boolean addCarModel(ContentValues values)
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/addCarModel.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            //if (id == true)
            //    SetUpdate();
            //printLog("addStudent:\n" + result);
            return isDone;
        }
        catch (IOException e)
        {
            //printLog("addStudent Exception:\n" + e);
            return false;
        }
    }

    @Override
    public boolean addCar(ContentValues values)
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/addCar.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            //if (id == true)
            //    SetUpdate();
            //printLog("addStudent:\n" + result);
            return isDone;
        }
        catch (IOException e)
        {
            //printLog("addStudent Exception:\n" + e);
            return false;
        }
    }

    @Override
    public boolean addBranch(ContentValues values)
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/addBranch.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            //if (id == true)
            //    SetUpdate();
            //printLog("addStudent:\n" + result);
            return isDone;
        }
        catch (IOException e)
        {
            //printLog("addStudent Exception:\n" + e);
            return false;
        }
    }

    @Override
    public boolean addOrder(ContentValues values)
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/addOrder.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            //if (id == true)
            //    SetUpdate();
            //printLog("addStudent:\n" + result);
            return isDone;
        }
        catch (IOException e)
        {
            //printLog("addStudent Exception:\n" + e);
            return false;
        }
    }


    @Override
    public List<Customer> getCustomers()
    {    
        List<Customer> result = new ArrayList<Customer>();
        try {
            String str = PHPtools.GET(WEB_URL + "/customers.php"); 
            JSONArray array = new JSONObject(str).getJSONArray("customers");
            for (int i = 0; i < array.length(); i++)
            {            
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Customer customer = RentConst.ContentValuesToCustomer(contentValues);
                result.add(customer );
            }        
            return result;  
        } 
        catch (Exception e) {         
            e.printStackTrace(); 
        }  
        return null;
    }

    @Override
    public List<CarModel> getCarModels()
    {
        List<CarModel> result = new ArrayList<CarModel>();
        try {
            String str = PHPtools.GET(WEB_URL + "/carModels.php");
            JSONArray array = new JSONObject(str).getJSONArray("carModels");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                CarModel carModel = RentConst.ContentValuesToCarModel(contentValues);
                result.add(carModel);
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<Car> getCars()
    {
        List<Car> result = new ArrayList<Car>();
        try {
            String str = PHPtools.GET(WEB_URL + "/cars.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Car car = RentConst.ContentValuesToCar(contentValues);
                result.add(car);
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Branch> getBranchs()
    {
        List<Branch> result = new ArrayList<Branch>();
        try {
            String str = PHPtools.GET(WEB_URL + "/branchs.php");
            JSONArray array = new JSONObject(str).getJSONArray("branchs");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Branch branch = RentConst.ContentValuesToBranch(contentValues);
                result.add(branch);
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getOrders()
    {
        List<Order> result = new ArrayList<Order>();
        try {
            String str = PHPtools.GET(WEB_URL + "/orders.php");
            JSONArray array = new JSONObject(str).getJSONArray("orders");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Order order = RentConst.ContentValuesToOrder(contentValues);
                result.add(order );
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
}
*/
