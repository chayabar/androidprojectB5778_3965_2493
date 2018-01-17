package com.example.owner.second_application_java2018.fragment;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner.second_application_java2018.R;
import com.example.owner.second_application_java2018.model.backend.ArrayDataFilter;
import com.example.owner.second_application_java2018.model.backend.DBManagerFactory;
import com.example.owner.second_application_java2018.model.backend.DB_manager;
import com.example.owner.second_application_java2018.model.backend.ExpandableDataFilter;
import com.example.owner.second_application_java2018.model.backend.RentConst;
import com.example.owner.second_application_java2018.model.entities.Branch;
import com.example.owner.second_application_java2018.model.entities.Car;
import com.example.owner.second_application_java2018.model.entities.Enums;
import com.example.owner.second_application_java2018.model.entities.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 31/01/2017.
 */

public class MyexpandableListAdepter extends BaseExpandableListAdapter implements Filterable,View.OnClickListener {

    ArrayDataFilter df;
    ExpandableDataFilter edf;
    DB_manager manager = DBManagerFactory.getManager();
    private ExpandableListView expandableListView;
    private ArrayList<Branch> tempBranch;
    private ArrayList<Car> tempCar;
    android.app.Activity activity;
    ArrayAdapter<Integer> adapterCars;
    private String mType;
    Car selectedCar=null;



    private String carTag="Cars";
    private String branchTag="Branches";


    private Button b_rentCar;


    public MyexpandableListAdepter(ExpandableListView myExpandableListView, String type, android.app.Activity mactivity)
    {
        mType=type;
        expandableListView = myExpandableListView;
        activity = mactivity;
        if(mType.compareTo(branchTag)==0)
        {
            tempBranch = manager.getBranchs();
        }
        if(mType.compareTo(carTag)==0)
        {
            tempCar= manager.getAvailableCars();
        }

    }


    @Override
    public int getGroupCount()
    {
        int returndItem;
        if(mType.compareTo(branchTag)==0)
        {
            returndItem= tempBranch.size();
        }
        else if(mType.compareTo(carTag)==0)
        {
            returndItem= tempCar.size();
        }
        else
            returndItem=0;
        return returndItem;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        View header;
        if(mType.compareTo(branchTag)==0)
        {

            header = activity.getLayoutInflater().inflate(R.layout.header_branch, parent, false);
            Branch b = tempBranch.get(groupPosition);
            TextView address = (TextView) header.findViewById(R.id.branchAddressTextView);

            address.setText(b.getAddress());
            TextView parking = (TextView) header.findViewById(R.id.parkingNumTextView);
            parking.setText(String.valueOf(b.getParkingSpaces()));
        }
        else if (mType.compareTo(carTag) == 0) {
            header = activity.getLayoutInflater().inflate(R.layout.header_car, parent, false);
            Car c = tempCar.get(groupPosition);
            TextView branchAddress = (TextView) header.findViewById(R.id.branchAddressTextView);
            String bAddress=manager.getBranchByBranchNumber(c.getHouseBranch()).getAddress();
            branchAddress.setText(bAddress);
            TextView companyName = (TextView) header.findViewById(R.id.companyNameTextView);
            String cName=manager.getCarModelByID(c.getModelCode()).getCompanyName();
            companyName.setText(cName);
        }else {
            header = null;
        }
        return header;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {

        View item=null;

        if(mType.compareTo(branchTag)==0)
        {

            item= activity.getLayoutInflater().inflate(R.layout.fragment_item_branch, parent, false);
            final Branch b = tempBranch.get(groupPosition);


            TextView branchNumber;
            TextView Address;
            TextView parkingSpaces;

            branchNumber= (TextView) item.findViewById(R.id.branchNumber);
            branchNumber.setText(branchNumber.getText() + ": " + b.getBranchNumber());
            Address = (TextView) item.findViewById(R.id.Address);
            Address.setText(Address.getText() + ": " + b.getAddress().toString());
            parkingSpaces = (TextView) item.findViewById(R.id.parkingSpaces);
            parkingSpaces.setText(parkingSpaces.getText() + ": " + b.getParkingSpaces());

            /*ListView listCars = (ListView) item.findViewById(R.id.listCars);
            final ArrayList<Car> CarsInBranch = manager.getAvailableCarsByBranch(b.getBranchNumber());
            final ArrayList<Integer> namesA = new ArrayList<Integer>();

            for (Car a : CarsInBranch)
                namesA.add(a.getModelCode());

            final Context context = activity.getApplicationContext();
            adapterCars = new ArrayAdapter<Integer>(context,android.R.layout.simple_list_item_1, namesA);


            listCars.setAdapter(adapterCars);
            setListViewHeightBasedOnChildren(listCars);*/

        }
        else {
            if (mType.compareTo(carTag) == 0) {
                item = activity.getLayoutInflater().inflate(R.layout.fragment_item_car, parent, false);
                selectedCar = tempCar.get(groupPosition);

                TextView branchAddress = (TextView) item.findViewById(R.id.branchAddress);
                String bAddress=manager.getBranchByBranchNumber(selectedCar.getHouseBranch()).getAddress();
                branchAddress.setText(branchAddress.getText() + " : " + bAddress);

                TextView companyName = (TextView) item.findViewById(R.id.companyName);
                String cName=manager.getCarModelByID(selectedCar.getModelCode()).getCompanyName();
                companyName.setText(companyName.getText() + " : " + cName);

                TextView mileAge =(TextView) item.findViewById(R.id.mileAge);
                mileAge.setText(mileAge.getText() + " : " + String.valueOf(selectedCar.getMileAge()));

                TextView carNumber =(TextView) item.findViewById(R.id.carNumber);
                carNumber.setText(carNumber.getText() + " : " + String.valueOf(selectedCar.getCarNumber()));

                b_rentCar = (Button) item.findViewById(R.id.buttonRent);
                b_rentCar.setOnClickListener(this);

            } else {
                item = null;
            }
        }
        return item;//delete = null

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Filter getFilter()
    {
        if (edf == null)
        {
            if(mType.compareTo(branchTag)==0)
            {
                edf = new ExpandableDataFilter(branchTag, this);
            }
            if(mType.compareTo(carTag)==0)
            {
                edf = new ExpandableDataFilter(carTag, this);
            }
        }
        return edf;
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
        if(mType.compareTo(branchTag)==0)
        {
            tempBranch = edf.branches;
        }
        if(mType.compareTo(carTag)==0)
        {
            tempCar=edf.cars;
        }

    }

    @Override
    public void notifyDataSetInvalidated()
    {
        //no agency founs as matching the query
        super.notifyDataSetInvalidated();
        if(mType.compareTo(branchTag)==0)
        {
            tempBranch = edf.branches;
        }
        if(mType.compareTo(carTag)==0)
        {
            tempCar=edf.cars;
        }
        Toast.makeText(this.activity,"no results", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {

        if (v == b_rentCar) {
            Toast.makeText(this.activity, "we opened for you reservation, you can see it in 'your reservation'", Toast.LENGTH_LONG).show();
            // rent the car
            Order valuesOrder=new Order();
            valuesOrder.setOrderStatus(Enums.OrderStatus.OPEN);
            valuesOrder.setCharge(0);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
            int CustomerID=sharedPreferences.getInt("ID", 0);

            valuesOrder.setCustomerID(CustomerID);
            valuesOrder.setCarNumber(selectedCar.getCarNumber());
            valuesOrder.setStartMileAge(selectedCar.getMileAge());

            Date d=Calendar.getInstance().getTime();
            valuesOrder.setStartRent(d);
            valuesOrder.setEndRent(d);
            valuesOrder.setEndMileAge(selectedCar.getMileAge());
            valuesOrder.setFuelFilling(false);
            final ContentValues cv=RentConst.OrderToContentValues(valuesOrder);
            //manager.addOrder(cv);

            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected void onPostExecute(Boolean idResult) {
                    super.onPostExecute(idResult);
                    //if (idResult == true)
                        //Toast.makeText( "the order inserted: " + idResult, Toast.LENGTH_LONG).show();
                }
                @Override
                protected Boolean doInBackground(Void... params) {
                    return manager.addOrder(cv);
                }
            }.execute();
            //tempCar= manager.getAvailableCars();
        }
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
