package com.example.owner.second_application_java2018.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.owner.second_application_java2018.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourReservationFragment extends Fragment implements View.OnClickListener  {


    public YourReservationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_your_reservation, container, false);

        // Inflate the layout for this fragment
        return rootView;
    }
    private Button phoneButton;
    private ImageButton phoneImageButton;
    private Button emailButton;
    private ImageButton emailImageButton;
    private Button websiteButton;
    private ImageButton websiteImageButton;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-01-09 12:41:01 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {

    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-01-09 12:41:01 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {

    }



}