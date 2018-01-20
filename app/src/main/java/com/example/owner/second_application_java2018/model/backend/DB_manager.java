package com.example.owner.second_application_java2018.model.backend;

import android.content.ContentValues;

import com.example.owner.second_application_java2018.model.entities.Branch;
import com.example.owner.second_application_java2018.model.entities.Car;
import com.example.owner.second_application_java2018.model.entities.CarModel;
import com.example.owner.second_application_java2018.model.entities.Customer;
import com.example.owner.second_application_java2018.model.entities.Order;

import java.util.ArrayList;

/**
 * Created by שרה  on 30/12/2017.
 */

public interface DB_manager {
    boolean addCustomer(ContentValues newCustomer);
    boolean addCar(ContentValues newCar);
    boolean addCarModel(ContentValues newCarModel);
    boolean addBranch(ContentValues newBranch);
    boolean addOrder(ContentValues newOrder);

   /* boolean removeCustomer(long id);
    boolean removeCar(String id);
    boolean removeBranch(long id);
    boolean removeOrder(long id);

    boolean updateCustomer(int id,ContentValues values);*/
    boolean updateCar(ContentValues values);
  /*  boolean updateBranch(int id,ContentValues values);
    boolean updateOrder(int id,ContentValues values);*/


    boolean existCustomer(ContentValues customer);
   /* boolean existcar(ContentValues car);
    boolean existbranch(ContentValues branch);
    boolean existorder(ContentValues order);*/


    Order getOpenOrderByCustomer(int currentCustomer);

    boolean closeExistOrder(int orderId, float km, boolean fuelFilling, float fuellitter);

    boolean updateOrder(ContentValues values);

    Branch getBranchByBranchNumber(int BranchNumber);
    CarModel getCarModelByID(int ID);

    ArrayList<Customer> getCustomers();
    ArrayList<Car> getCars();
    ArrayList<CarModel> getCarModels();
    ArrayList<Branch> getBranchs();

    ArrayList<Order> getOrdersFromServer();

    ArrayList<Order> getOrders();
    ArrayList<Car> getAvailableCars();
    ArrayList<Car> getAvailableCarsByBranch(int branchNumber);
    ArrayList<Order> getOpenOrders();
    boolean checkUsernameAndPassword(String lastName, int ID);
    Order getOrderByID( int orderID);

}
