package com.example.owner.second_application_java2018.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.owner.second_application_java2018.R;
import com.example.owner.second_application_java2018.model.backend.DBManagerFactory;
import com.example.owner.second_application_java2018.model.backend.DB_manager;
import com.example.owner.second_application_java2018.model.entities.Order;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourReservationFragment extends Fragment implements View.OnClickListener  {


    public YourReservationFragment() {
        // Required empty public constructor
    }

    int currentCustomer=-1;
    final DB_manager manager=DBManagerFactory.getManager();
    private Button closeReservationButton;
    private EditText KilometrageEditText;
    private CheckBox fuelFillingCheckBox;
    private EditText fuelLitterEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        currentCustomer = getArguments().getInt("current customer");
        final float startMileAge=manager.getOpenOrderByCustomer(currentCustomer).getStartMileAge();

        Order nowOrder=manager.getOpenOrderByCustomer(currentCustomer);
        View rootView= inflater.inflate(R.layout.fragment_your_reservation, container, false);
        TextView helloUser= (TextView) rootView.findViewById(R.id.reservationInfo);
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        helloUser.setText("hello, your order number "+nowOrder.getOrderID()+"\nfrom date "+df.format("yyyy-MM-dd hh:mm:ss",
                nowOrder.getStartRent()).toString()+"\nwith start mile age of "+nowOrder.getStartMileAge());
        closeReservationButton=(Button)rootView.findViewById( R.id.closeReservationButton);
        KilometrageEditText= (EditText)rootView.findViewById(R.id.KilometrageEditText);
        fuelFillingCheckBox= (CheckBox)rootView.findViewById(R.id.FuelFillingCheckBox);
        fuelLitterEditText= (EditText)rootView.findViewById(R.id.FuelLitterEditText);

        closeReservationButton.setOnClickListener(this);
        closeReservationButton.setEnabled(false);
        fuelLitterEditText.setEnabled(false);
        KilometrageEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0 || Float.valueOf(s.toString().trim())<startMileAge){
                    closeReservationButton.setEnabled(false);
                }
                else{
                    closeReservationButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fuelFillingCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //recording the click of the checkbox
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //testing if it is checked or unchecked
                if (isChecked) {
                    //if it is check
                    fuelLitterEditText.setEnabled(true);

                } else {
                    fuelLitterEditText.setEnabled(false);

                }
            }
        });
        return rootView;
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-01-09 12:41:01 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {

        if (v == closeReservationButton) {
            final int orderID = manager.getOpenOrderByCustomer(currentCustomer).getOrderID();
            final float kilometrage = Float.valueOf(KilometrageEditText.getText().toString());
            StringBuffer result = new StringBuffer();
            result.append(fuelFillingCheckBox.isChecked());
            final boolean fuelFilling = Boolean.valueOf(result.toString());
            final float fuelLitter;
            if (fuelFilling)
                fuelLitter = Float.valueOf(fuelLitterEditText.getText().toString());
            else
                fuelLitter = 0;
            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected void onPostExecute(Boolean idResult) {
                    super.onPostExecute(idResult);
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    return manager.closeExistOrder(orderID, kilometrage, fuelFilling, fuelLitter);

                }
            }.execute();

            manager.getOrdersFromServer();
            SystemClock.sleep(500);
            Order order=manager.getOrderByID(orderID);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("order closed");
            builder.setMessage("Final payment: " + order.getCharge() + " Thank You for choosing Take&Go");
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            });
            builder.create().show();

            FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
            ft.remove(this);
            ft.commit();
        }
    }
}