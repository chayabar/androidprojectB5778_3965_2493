
package com.example.owner.second_application_java2018.model.datasource;

import android.content.ContentValues;
import android.os.AsyncTask;

import com.example.owner.second_application_java2018.model.backend.DB_manager;
import com.example.owner.second_application_java2018.model.backend.RentConst;
import com.example.owner.second_application_java2018.model.entities.Branch;
import com.example.owner.second_application_java2018.model.entities.Car;
import com.example.owner.second_application_java2018.model.entities.CarModel;
import com.example.owner.second_application_java2018.model.entities.Customer;
import com.example.owner.second_application_java2018.model.entities.Enums;
import com.example.owner.second_application_java2018.model.entities.Order;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.example.owner.second_application_java2018.model.backend.RentConst.ContentValuesToCustomer;

/**
 * Created by owner on 22/12/2017.
 */


public class MySQL_DBManager implements DB_manager {

    private String WEB_URL = "http://avichzer.vlab.jct.ac.il/rentPHP";
    final ArrayList<CarModel> CarModelList = new ArrayList<CarModel>();
    final ArrayList<Customer> CustomerList = new ArrayList<Customer>();
    final ArrayList<Car> CarList = new ArrayList<Car>();
    final ArrayList<Branch> BranchList = new ArrayList<Branch>();
    final ArrayList<Order> OrderList = new ArrayList<Order>();
    private boolean isDone;

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
    public boolean addOrder(final ContentValues values)
    {
        /*final Boolean[] isDone = new Boolean[1];
        new AsyncTask<Void,Void,Boolean>(){
            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                isDone[0] = aBoolean;
            }

            @Override
            protected Boolean  doInBackground(Void... params)
            {
                try
                {
                    String result = PHPtools.POST(WEB_URL + "/addOrder.php", values);
                    return Boolean.parseBoolean(result);
                }
                catch (IOException e)
                {
                    return false;
                }
            }
        }.execute();
        return isDone[0];*/
        try
        {
            String result = PHPtools.POST(WEB_URL + "/addOrder.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            return isDone;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    @Override
    public ArrayList<Customer> getCustomers()
    {
        if(!CustomerList.isEmpty())
        {
            return CustomerList;
        }
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void  doInBackground(Void... params)
            {
                try {
                    String str = PHPtools.GET(WEB_URL + "/customers.php");
                    JSONArray array = new JSONObject(str).getJSONArray("customers");
                    for (int i = 0; i < array.length(); i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);
                        ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                        Customer customer = RentConst.ContentValuesToCustomer(contentValues);
                        CustomerList.add(customer );
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
        return CustomerList;
    }

    @Override
    public ArrayList<CarModel> getCarModels()
    {
        if(!CarModelList.isEmpty())
        {
            return CarModelList;
        }
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void  doInBackground(Void... params)
            {
                try {
                    String str = PHPtools.GET(WEB_URL + "/carModels.php");
                    JSONArray array = new JSONObject(str).getJSONArray("carModels");
                    for (int i = 0; i < array.length(); i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);
                        ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                        CarModel carModel = RentConst.ContentValuesToCarModel(contentValues);
                        CarModelList.add(carModel);
                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
        return CarModelList;
    }

    public ArrayList<Car> getCarsFromServer()
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void  doInBackground(Void... params)
            {
                try {
                    String str = PHPtools.GET(WEB_URL + "/cars.php");
                    JSONArray array = new JSONObject(str).getJSONArray("cars");
                    for (int i = 0; i < array.length(); i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);
                        ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                        Car car = RentConst.ContentValuesToCar(contentValues);
                        CarList.add(car);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
        return CarList;
    }

    @Override
    public ArrayList<Car> getCars()
    {
        if(CarList.isEmpty())
        {
            getCarsFromServer();
        }
        return CarList;
    }

    @Override
    public ArrayList<Branch> getBranchs()
    {
        if(!BranchList.isEmpty())
        {
            return BranchList;
        }
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void  doInBackground(Void... params)
            {
                try {
                    String str = PHPtools.GET(WEB_URL + "/branchs.php");
                    JSONArray array = new JSONObject(str).getJSONArray("branchs");
                    for (int i = 0; i < array.length(); i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);
                        ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                        Branch branch = RentConst.ContentValuesToBranch(contentValues);
                        BranchList.add(branch);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
        return BranchList;
    }

    @Override
    public ArrayList<Order> getOrdersFromServer()
    {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void  doInBackground(Void... params)
            {
                try {
                    String str = PHPtools.GET(WEB_URL + "/orders.php");
                    JSONArray array = new JSONObject(str).getJSONArray("orders");
                    for (int i = 0; i < array.length(); i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);
                        ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                        Order order = RentConst.ContentValuesToOrder(contentValues);
                        OrderList.add(order );
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
        return OrderList;
    }


    @Override
    public ArrayList<Order> getOrders()
    {
        if(OrderList.isEmpty())
        {
            getOrdersFromServer();
        }
        return OrderList;
    }

    @Override
    public boolean updateCar(int id,ContentValues values) {
        /*Car car = ContentValuesToCar(values);
        car.setCarNumber(id);
        for (int i = 0; i < getCars().size(); i++)
            if (cars.get(i).getCarNumber()== id) {
                cars.set(i, car);
                return true;
            }*/
        return false;
    }

    @Override
    public ArrayList<Car> getAvailableCars() {
        /** This method is used to check which cars are available for rent.
         * @return list of cars.
         */
        ArrayList<Car> AvailableCars= getCars();
        for (final Order item : getOrdersFromServer())
            if (item.getOrderStatus()== Enums.OrderStatus.OPEN) {
                // AvailableCars.removeIf(p -> p.getCarNumber()== item.getCarNumber());///?????????????????????????????????
                Car c=getCarByID(item.getCarNumber());
                AvailableCars.remove(c);
            }
        return AvailableCars;
    }

    @Override
    public ArrayList<Car> getAvailableCarsByBranch(int branchNumber) {
        /**This method is used to check which cars are available for rent at a particular branch.
         * @return list of cars.
         */
        ArrayList<Car> AvailableCars=null;
        for (Car item : getCars())
            if (item.getHouseBranch()==branchNumber) {
                AvailableCars.add(item);
            }
        return AvailableCars;
    }

    @Override
    public ArrayList<Order> getOpenOrders() {
        /**This method is used to check which order is open,
         * ie the vehicle is still leased.
         * @return list of orders
         */
        ArrayList<Order> OpenOrders= null;
        for (Order item : getOrders())
            if (item.getOrderStatus()== Enums.OrderStatus.OPEN) {
                OpenOrders.add(item);
            }
        return OpenOrders;
    }


    @Override
    public boolean checkUsernameAndPassword(String lastName, int ID)
    {
        for(Customer item : getCustomers())
            if((item.getLastName().compareTo(lastName))==0 && (item.getID()==ID))
                return true;
        return false;
    }

    private Order getOrderByID( int orderID)
    {
        for(Order o : getOrders())
        {
            if(o.getOrderID()==orderID)
                return o;
        }
        return null;
    }

    private Car getCarByID( int carID)
    {
        for(Car c : getCars())
        {
            if(c.getCarNumber()==carID)
                return c;
        }
        return null;
    }

    public void closeExistOrder(int orderId, float km)
    {
        Order order=getOrderByID(orderId);
        if (order!= null)
        {
            order.setEndMileAge(km);
            order.setEndRent(new Date());
            float chargePerMin=(float)0.1;
            float chargePerKM=(float)0.6;
            float calculateCharge=chargePerMin* (order.getStartRent().getTime()- order.getEndRent().getTime())
                    +chargePerKM*(order.getStartMileAge()-order.getEndMileAge());
            order.setCharge(calculateCharge);
            order.setOrderStatus(Enums.OrderStatus.CLOSE);

            //updateOrder(order.getOrderID(), OrderToContentValues(order));
        }
    }

    public Branch getBranchByBranchNumber(int BranchNumber){
        Branch b=null;
        for (Branch br: getBranchs())
        {
            if (br.getBranchNumber()==BranchNumber)
                b=br;
        }
        return b;
    }

    public CarModel getCarModelByID(int ID){
        for(CarModel cm : getCarModels())
        {
            if(cm.getModelCode()==ID)
                return cm;
        }
        return null;
    }

}

