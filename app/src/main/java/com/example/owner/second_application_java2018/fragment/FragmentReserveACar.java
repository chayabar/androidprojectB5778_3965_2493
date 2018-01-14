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

public class FragmentReserveACar extends Fragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener
{
    SearchView searchView;

    MyexpandableListAdepter adapterExList;
    private ExpandableListView expandableListView;
    DB_manager manager= DBManagerFactory.getManager();

    public FragmentReserveACar() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
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
        ConnectivityManager cm = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final Context context = getActivity().getApplicationContext();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reserve_a_car, container, false);
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expanded_list_cars);


        adapterExList = new MyexpandableListAdepter(expandableListView, "Cars", getActivity());

        expandableListView.setAdapter(adapterExList);

        searchView = (SearchView) rootView.findViewById(R.id.search_car);

        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        DBManagerFactory.getManager().getCarModels();
        DBManagerFactory.getManager().getBranchs();
        DBManagerFactory.getManager().getCars();


        return rootView;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {

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
        adapterExList = new MyexpandableListAdepter(expandableListView, "Cars", getActivity());
        expandableListView.setAdapter(adapterExList);
        return false;
    }

}
