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
 * Created by User on 10/01/2018.
 */

/**
 * fragment FragmentBranches shows list of all branches in bussiness
 * the handling is via class MyExpandableListAdaptor
 * also there is the option to search in the results
 */

public class FragmentBranches extends Fragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener
{
    //some variable for future use
    SearchView searchView;

    MyexpandableListAdepter adapterExList;
    private ExpandableListView expandableListView;
    DB_manager manager= DBManagerFactory.getManager();
    int currentCustomer=-1;

    public FragmentBranches() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {  //on each start of this fragment check internet connectivity
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

    private boolean isNetworkConnected() {  //check the internet connection
        ConnectivityManager cm = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
            //on create initilize all needed parts
    {
        final Context context = getActivity().getApplicationContext();
        // Inflate the layout for this fragment
        currentCustomer = getArguments().getInt("current customer");  //get current customer ID

        View rootView = inflater.inflate(R.layout.fragment_branches, container, false);
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expanded_list_branches);  //the view of list branches


        adapterExList = new MyexpandableListAdepter(expandableListView, "Branches", this, currentCustomer);  //set the adaptor with tag "Branche"

        expandableListView.setAdapter(adapterExList);

        searchView = (SearchView) rootView.findViewById(R.id.search_branch);  //define the search view

        searchView.setOnQueryTextListener(this);  //set listener for th etext change and close
        searchView.setOnCloseListener(this);



        return rootView;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {

        adapterExList.getFilter().filter(newText);  //get the result according the search text
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
        //on close, initalize for future use
        adapterExList = new MyexpandableListAdepter(expandableListView, "Branches", this, currentCustomer);
        expandableListView.setAdapter(adapterExList);
        return false;
    }

}
