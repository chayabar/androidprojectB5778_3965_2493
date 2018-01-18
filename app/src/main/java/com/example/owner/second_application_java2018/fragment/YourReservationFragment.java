package com.example.owner.second_application_java2018.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.owner.second_application_java2018.R;
import com.example.owner.second_application_java2018.model.backend.DBManagerFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourReservationFragment extends Fragment implements View.OnClickListener  {


    public YourReservationFragment() {
        // Required empty public constructor
    }

    private Button closeReservationButton;
    private EditText KilometrageEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_your_reservation, container, false);

        closeReservationButton=(Button)rootView.findViewById( R.id.closeReservationButton);
        KilometrageEditText= (EditText)rootView.findViewById(R.id.KilometrageEditText);

        closeReservationButton.setOnClickListener(this);
        closeReservationButton.setEnabled(false);
        KilometrageEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0){
                    closeReservationButton.setEnabled(false);
                } else {
                    closeReservationButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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

        if(v== closeReservationButton)
        {
            DBManagerFactory.getManager().closeExistOrder(123, Float.valueOf(KilometrageEditText.getText().toString()));
        }
    }
}