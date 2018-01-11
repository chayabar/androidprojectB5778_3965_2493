package com.example.owner.second_application_java2018.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
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
import com.example.owner.second_application_java2018.model.backend.ExpandableDataFilter;
import com.example.owner.second_application_java2018.model.datasource.List_DBManager;
import com.example.owner.second_application_java2018.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 31/01/2017.
 */

public class MyexpandableListAdepter extends BaseExpandableListAdapter implements Filterable,View.OnClickListener {

    ArrayDataFilter df;
    ExpandableDataFilter edf;
    List_DBManager manager = new List_DBManager();
    private ExpandableListView expandableListView;
    private List<Branch> tempBranch;
   // private ArrayList<Activity> tempActi;
    android.app.Activity activity;
    ArrayAdapter<String> adapterActivities;
    private String mType;

    private Button b_adress;
    private Button b_phone;
    private Button b_email;
    private Button b_website;
    private TextView email;
    private TextView phone;
    private TextView adress;
    private TextView website;


    public MyexpandableListAdepter(ExpandableListView myExpandableListView, String type, android.app.Activity mactivity)
    {
        mType=type;
        expandableListView = myExpandableListView;
        activity = mactivity;
        if(mType.compareTo("branches")==0)
        {
            tempBranch = manager.getBranchs();
        }
        /*if(mType.compareTo("travels")==0)
        {
            tempActi = manager.getListOfActivities();
        }*/

    }


    @Override
    public int getGroupCount()
    {
        int returndItem=0;///delete =0
        if(mType.compareTo("agencies")==0)
        {
            returndItem= tempBranch.size();
        }
        /*else//if(mType.compareTo("travels")==0)
        {
            returndItem= tempActi.size();
        }*/
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
        if(mType.compareTo("branches")==0)
        {

            header = activity.getLayoutInflater().inflate(R.layout.header_branch, parent, false);
            Branch b = tempBranch.get(groupPosition);
            TextView address = (TextView) header.findViewById(R.id.branchAddressTextView);

            address.setText(b.getAddress());
            TextView parking = (TextView) header.findViewById(R.id.parkingNumTextView);
            parking.setText(b.getParkingSpaces());
        }
        else {
            /*if (mType.compareTo("travels") == 0) {
                header = activity.getLayoutInflater().inflate(R.layout.header_travel, parent, false);
                Activity a = tempActi.get(groupPosition);
                TextView desc = (TextView) header.findViewById(R.id.desc_travel);
                desc.setText(a.getActivityDescription());
                TextView startDate = (TextView) header.findViewById(R.id.start_date_travel);
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                startDate.setText(sdf.format(a.getActivityStartDate()));
            } else*/ {
                header = null;
            }
        }
        return header;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {

        View item;

       /* if(mType.compareTo("branches")==0)
        {

            item= activity.getLayoutInflater().inflate(R.layout.fragment_item_branch, parent, false);
            final Branch b = tempBranch.get(groupPosition);


            TextView name = (TextView) item.findViewById(R.id.name);
            name.setText(name.getText() + ": " + b.getBusinessName()+"\n");
            adress = (TextView) item.findViewById(R.id.adress);
            adress.setText(adress.getText() + ": " + b.getBusinessAdress().toString());
            phone = (TextView) item.findViewById(R.id.phone);
            phone.setText(phone.getText() + ": " + b.getBusinessPhone());
            email = (TextView) item.findViewById(R.id.email);
            email.setText(email.getText() + ": " + b.getBusinessEmail());
            website = (TextView) item.findViewById(R.id.website);
            website.setText(website.getText() + ": " + b.getBusinessWebSite());


            b_adress = (Button) item.findViewById(R.id.btn_adress);
            b_phone = (Button) item.findViewById(R.id.btn_phone);
            b_email = (Button) item.findViewById(R.id.btn_email);
            b_website = (Button) item.findViewById(R.id.btn_website);

            b_adress.setOnClickListener(this);
            b_phone.setOnClickListener(this);
            b_email.setOnClickListener(this);
            b_website.setOnClickListener(this);



            ListView listTravel = (ListView) item.findViewById(R.id.listTravels);
            final ArrayList<com.example.user.userapp.model.entities.Activity> activitiesForBusiness = manager.getActivitiesByBusiness(String.valueOf(b.getBusinessID()));
            final ArrayList<String> namesA = new ArrayList<String>();

            for (com.example.user.userapp.model.entities.Activity a : activitiesForBusiness)
                 namesA.add(a.getActivityDescription());

            final Context context = activity.getApplicationContext();
            adapterActivities = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1, namesA);


            listTravel.setAdapter(adapterActivities);
            setListViewHeightBasedOnChildren(listTravel);

        }
        else {
            if (mType.compareTo("travels") == 0) {
                item = activity.getLayoutInflater().inflate(R.layout.fragment_item_travel, parent, false);
                final Activity a = tempActi.get(groupPosition);

                TextView type = (TextView) item.findViewById(R.id.type);
                type.setText(type.getText() + ": " + a.getActivityType().toString());

                TextView country = (TextView) item.findViewById(R.id.country);
                country.setText(country.getText() + ": " + a.getActivityCountry());


                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                TextView startdate = (TextView) item.findViewById(R.id.startDate);
                startdate.setText(startdate.getText() + ": " + sdf.format(a.getActivityStartDate()));
                TextView stopdate = (TextView) item.findViewById(R.id.stopDate);
                stopdate.setText(stopdate.getText() + ": " + sdf.format(a.getActivityStopDate()));
                TextView cost = (TextView) item.findViewById(R.id.cost);
                cost.setText(cost.getText() + ": " + String.valueOf(a.getActivityCost()));
                TextView desc = (TextView) item.findViewById(R.id.description);
                desc.setText(desc.getText() + ": " + a.getActivityDescription());

                Business business=manager.getBusinessById(a.getActivityBuisnessID());
                
                TextView busiName = (TextView) item.findViewById(R.id.businessName);
                busiName.setText(busiName.getText() + ": " + business.getBusinessName());
                TextView busi_adress = (TextView) item.findViewById(R.id.busi_adress);
                busi_adress.setText(busi_adress.getText() + ": " + business.getBusinessAdress());
                TextView busi_phone = (TextView) item.findViewById(R.id.busi_phone);
                busi_phone.setText(busi_phone.getText() + ": " + business.getBusinessPhone());
                TextView busi_email = (TextView) item.findViewById(R.id.busi_email);
                busi_email.setText(busi_email.getText() + ": " + business.getBusinessEmail());
                TextView busi_website = (TextView) item.findViewById(R.id.busi_website);
                busi_website.setText(busi_website.getText() + ": " + business.getBusinessWebSite());

            } else {
                item = null;
            }
        }*/
        return item=null;//delete = null





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
            if(mType.compareTo("branches")==0)
            {
                edf = new ExpandableDataFilter("branches", this);
            }
            if(mType.compareTo("travels")==0)
            {
                edf = new ExpandableDataFilter("travels", this);
            }
        }
            return edf;
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
        if(mType.compareTo("agencies")==0)
        {
            tempBranch = edf.branches;
        }
        /*if(mType.compareTo("travels")==0)
        {
            tempActi=edf.activities;
        }*/

    }

    @Override
    public void notifyDataSetInvalidated()
    {
        //no agency founs as matching the query
        super.notifyDataSetInvalidated();
        if(mType.compareTo("agencies")==0)
        {
            tempBranch = edf.branches;
        }
        /*if(mType.compareTo("travels")==0)
        {
            tempActi=edf.activities;
        }*/
        Toast.makeText(this.activity,"no found results", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        if (v == b_adress) {
            // intent action maps
            String s1=adress.getText().toString().replace("adress: ","");

            Intent implicitIntent = new Intent(Intent.ACTION_VIEW);
            implicitIntent.setData(Uri.parse("geo:0,0?q=" + s1.replace(" ","+").replace("\n","+")));
            if (implicitIntent.resolveActivity(this.activity.getPackageManager()) != null) {
                this.activity.startActivity(implicitIntent);
            }
        }
        if (v == b_phone) {
            // intent action phone
            String s2=phone.getText().toString().replace("phone: ","");

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + s2));
            if (intent.resolveActivity(this.activity.getPackageManager()) != null) {
                this.activity.startActivity(intent);
            }
        }
        if (v == b_email)
        {
            // intent action email
            String to = email.getText().toString().replace("email: ", "");
            //???d
            Log.i("Send email", "");
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("*/*");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});

            try {
                this.activity.startActivity(Intent.createChooser(emailIntent, "Send message..."));
                Log.i("Finished sending email", "");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this.activity, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == b_website)
        {
                // intent action website
                String s4=website.getText().toString().replace("website: ","");

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+s4));
                this.activity.startActivity(browserIntent);
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
