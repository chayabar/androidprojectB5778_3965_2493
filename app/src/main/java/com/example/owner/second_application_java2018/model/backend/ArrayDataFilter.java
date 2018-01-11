package com.example.owner.second_application_java2018.model.backend;

import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.example.owner.second_application_java2018.model.entities.Branch;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by User on 11/01/2018
 */

public class ArrayDataFilter extends Filter
{
    DB_manager manager= DBManagerFactory.getManager();
    List<Branch> branches =manager.getBranchs();
    //ArrayList<Activity> activities=manager.getListOfActivities();///car
    String type;
    ArrayAdapter<String> adapter;
  /*  public ArrayDataFilter(String typeFilter, ArrayAdapter<String> adapterActivities)
    {
        type=typeFilter;
        adapter=adapterActivities;
    }*/

    @Override
    protected FilterResults performFiltering(CharSequence constraint)
    {
        String query= String.valueOf(constraint);
        FilterResults results = new FilterResults();
            // We implement here the filter logic
        if(type.compareTo("branches")==0)
        {
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = branches;
                results.count = branches.size();
            } else {
                // We perform filtering operation
                List<Branch> nBranchesList = new ArrayList<Branch>();

                for (Branch b : branches) {
                    if(b.getAddress().toString().toLowerCase().contains(query.toLowerCase())||String.valueOf(b.getParkingSpaces()).toLowerCase().contains(query.toLowerCase())||
                            String.valueOf(b.getBranchNumber()).toLowerCase().contains(query.toLowerCase()))
                        nBranchesList.add(b);
                }
                results.values = nBranchesList;
                results.count = nBranchesList.size();
            }

        }/*else {
            if (type.compareTo("travels") == 0) {
                activities = manager.getListOfActivities();
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = activities;
                    results.count = activities.size();
                } else {
                    // We perform filtering operation
                    List<Activity> nactivityList = new ArrayList<Activity>();

                    for (Activity a : activities) {
                        if (a.getActivityType().toString().toLowerCase().contains(query.toLowerCase()) || a.getActivityCountry().toLowerCase().contains(query.toLowerCase()) ||
                                a.getActivityDescription().toString().toLowerCase().contains(query.toLowerCase()) || manager.getBusinessNameById(a.getActivityBuisnessID()).toLowerCase().contains(query.toLowerCase()))
                            nactivityList.add(a);
                    }
                    results.values = nactivityList;
                    results.count = nactivityList.size();
                }
            }
        }*/
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results)
    {
        if(type.compareTo("branches")==0) {
            branches = (ArrayList<Branch>) results.values;
        }else {
            if (type.compareTo("travels") == 0) {
                branches = (ArrayList<Branch>) results.values;//activity to car
            }
        }
        // Now we have to inform the adapter about the new list filtered
        if (results.count == 0)
            adapter.notifyDataSetInvalidated();
        else
        {

            adapter.notifyDataSetChanged();
        }
    }



}
