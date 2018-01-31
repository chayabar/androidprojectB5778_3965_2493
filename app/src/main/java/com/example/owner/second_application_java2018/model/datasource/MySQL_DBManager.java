
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
import java.util.concurrent.TimeUnit;

import static com.example.owner.second_application_java2018.model.backend.RentConst.CarToContentValues;
import static com.example.owner.second_application_java2018.model.backend.RentConst.ContentValuesToCustomer;
import static com.example.owner.second_application_java2018.model.backend.RentConst.OrderToContentValues;

/**
 * Created by owner on 22/12/2017.
 */


public class MySQL_DBManager implements DB_manager {

    private String WEB_URL = "http://avichzer.vlab.jct.ac.il/rentPHP";
    final ArrayList<CarModel> CarModelList = new ArrayList<CarModel>();
    final ArrayList<Customer> CustomerList = new ArrayList<Customer>();
    ArrayList<Car> CarList = new ArrayList<Car>();
    final ArrayList<Branch> BranchList = new ArrayList<Branch>();
    ArrayList<Order> OrderList = new ArrayList<Order>();

    @Override
    public boolean existCustomer(ContentValues newcustomer) {//Check whether the customer exists in the lists
        Customer customer = ContentValuesToCustomer(newcustomer);
        for (Customer item : getCustomers())
            if (item.getID() == customer.getID()) {
                return true;
            }
        return false;
    }

    @Override
    public boolean addCustomer(ContentValues values)//add customer to the list
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/addCustomer.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            return isDone;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    @Override
    public boolean addCarModel(ContentValues values)// add car model to the list
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/addCarModel.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            return isDone;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    @Override
    public boolean addCar(ContentValues values)// add car to the list
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/addCar.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            return isDone;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    @Override
    public boolean addBranch(ContentValues values)// add branch to the list
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/addBranch.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            return isDone;
        }
        catch (IOException e)
        {
            return false;
        }
    }


    @Override
    public boolean addOrder(final ContentValues values)//add order to the orders list
    {
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
    public ArrayList<Customer> getCustomers() //get customer list from the server
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
    public ArrayList<CarModel> getCarModels()//get car model list from the server
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

    public ArrayList<Car> getCarsFromServer()//get car list from the server
    {
        final ArrayList<Car> tempCar=new ArrayList<Car>();
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
                        tempCar.add(car);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                CarList=tempCar;
                return null;
            }
        }.execute();
        return CarList;
    }

    @Override
    public ArrayList<Car> getCars()//if car list empty get car list from the server
    {
        if(CarList.isEmpty())
        {
            getCarsFromServer();
        }
        return CarList;
    }

    @Override
    public ArrayList<Branch> getBranchs()//get branch list from the server
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
    public ArrayList<Order> getOrdersFromServer()//get order list from the server
    {

        final ArrayList<Order> tempOrder=new ArrayList<Order>();
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
                        tempOrder.add(order );
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                OrderList=tempOrder;
                return null;
            }
        }.execute();
        return OrderList;
    }


    @Override
    public ArrayList<Order> getOrders()//if order list empty get the list from the server
    {
        if(OrderList.isEmpty())
        {
            getOrdersFromServer();
        }
        return OrderList;
    }

    @Override
    public boolean updateCar(ContentValues values) {//update the car list in the server
        try
        {
            String result = PHPtools.POST(WEB_URL + "/updateCar.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            return isDone;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    @Override
    public ArrayList<Car> getAvailableCars() {//return list of all the car that available for rent
        /** This method is used to check which cars are available for rent.
         * @return list of cars.
         */
        ArrayList<Car> AvailableCars= getCars();
        for (final Order item : getOrdersFromServer())
            if (item.getOrderStatus()== Enums.OrderStatus.OPEN) {
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
        ArrayList<Car> AvailableCars=new ArrayList<Car>();
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
        ArrayList<Order> OpenOrders =new ArrayList<Order>();
        for (Order item : getOrdersFromServer())
            if (item.getOrderStatus()== Enums.OrderStatus.OPEN) {
                OpenOrders.add(item);
            }
        return OpenOrders;
    }


    @Override
    public boolean checkUsernameAndPassword(String lastName, int ID)//compare the user name to last name and password to his id
    {
        for(Customer item : getCustomers())
            if((item.getLastName().compareTo(lastName))==0 && (item.getID()==ID))
                return true;
        return false;
    }

    public Order getOrderByID( int orderID)//get order id return order
    {
        for(Order o : getOrders())
        {
            if(o.getOrderID()==orderID)
                return o;
        }
        return null;
    }

    private Car getCarByID( int carID)//get car number return car
    {
        for(Car c : getCars())
        {
            if(c.getCarNumber()==carID)
                return c;
        }
        return null;
    }

    @Override
    public Order getOpenOrderByCustomer(int currentCustomer)//get customer return his open order
    {
        getOrdersFromServer();
        if(currentCustomer== -1)
            return null;
        for( Order orderOpen : getOpenOrders())
        {
            if(currentCustomer==orderOpen.getCustomerID())
                return orderOpen;
        }
        return null;
    }

    @Override
    public boolean closeExistOrder(int orderId, float km, boolean fuelFilling, float fuellitter)//update and close order
    {
        Order order=getOrderByID(orderId);
        if (order!= null)
        {
            order.setEndMileAge(km);
            order.setEndRent(new Date());
            order.setFuelFilling(fuelFilling);
            order.setFuelLitter(fuellitter);

            float fuelCostForLitter=8;
            float KMforLitter=10;
            float chargePerMin=(float)0.1;
            float chargePerKM=(float)fuelCostForLitter/KMforLitter;
            long minutes= TimeUnit.MINUTES.convert((order.getEndRent().getTime())-(order.getStartRent().getTime()), TimeUnit.MILLISECONDS);
            float calculateCharge=chargePerMin* minutes +chargePerKM*(order.getEndMileAge()-order.getStartMileAge());
            if(fuelFilling)
                calculateCharge-=fuelCostForLitter*fuellitter;
            order.setCharge(calculateCharge);
            order.setOrderStatus(Enums.OrderStatus.CLOSE);

            Car car= getCarByID(order.getCarNumber());
            car.setMileAge(km);

            /*if(*/updateCar(CarToContentValues(car));/*)*/
                return updateOrder( OrderToContentValues(order));
        }
        return false;
    }

    @Override
    public boolean updateOrder(ContentValues values) {//update order
        try
        {
            String result = PHPtools.POST(WEB_URL + "/updateOrder.php", values);
            boolean isDone = Boolean.parseBoolean(result);
            return isDone;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    public Branch getBranchByBranchNumber(int BranchNumber){//get branch number return branch
        Branch b=null;
        for (Branch br: getBranchs())
        {
            if (br.getBranchNumber()==BranchNumber)
                b=br;
        }
        return b;
    }


    public CarModel getCarModelByID(int ID){ //get model number return car model
        for(CarModel cm : getCarModels())
        {
            if(cm.getModelCode()==ID)
                return cm;
        }
        return null;
    }

}

