package com.example.owner.second_application_java2018.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.owner.second_application_java2018.R;
import com.example.owner.second_application_java2018.model.backend.DBManagerFactory;
import com.example.owner.second_application_java2018.model.backend.DB_manager;

/**
 * this fragment shows available cars for rent using MyExpandableListAdaptor class
 * also have the option to search in results
 * Created by User on 10/01/2018.
 */

public class FragmentReserveACar extends Fragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener
{
    SearchView searchView;

    MyexpandableListAdepter adapterExList;
    private ExpandableListView expandableListView;
    DB_manager manager= DBManagerFactory.getManager();
    int currentCustomer;

    public FragmentReserveACar() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        //on start, check for connectivity
        super.onStart();
        try
        {
            if (!isNetworkConnected())
                throw new Exception("The application must have internet connection");
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkConnected() {
        //check internet connection
        ConnectivityManager cm = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        currentCustomer = getArguments().getInt("current customer");  //get customer ID
        View rootView = inflater.inflate(R.layout.fragment_reserve_a_car, container, false);
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expanded_list_cars);
        //set new adaptor with tag "Cars"
        adapterExList = new MyexpandableListAdepter(expandableListView, "Cars", this, currentCustomer);

        expandableListView.setAdapter(adapterExList);

        searchView = (SearchView) rootView.findViewById(R.id.search_car);  //define the search view, listener for text change

        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

        return rootView;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        //if text changes, get the new results
        adapterExList.getFilter().filter(newText);
        expandableListView.setAdapter(adapterExList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }

    @Override
    public boolean onClose()
    {
        //prepare for future use
        adapterExList = new MyexpandableListAdepter(expandableListView, "Cars", this, currentCustomer);
        expandableListView.setAdapter(adapterExList);
        return false;
    }


}
