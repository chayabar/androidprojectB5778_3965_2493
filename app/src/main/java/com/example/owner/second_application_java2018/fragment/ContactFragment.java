package com.example.owner.second_application_java2018.fragment;


import android.content.Intent;
import android.net.Uri;
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
public class ContactFragment extends Fragment implements View.OnClickListener  {


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_contact, container, false);
        phoneButton = (Button)rootView.findViewById( R.id.phoneButton );
        phoneImageButton = (ImageButton)rootView.findViewById( R.id.phoneImageButton );
        emailButton = (Button)rootView.findViewById( R.id.emailButton );
        emailImageButton = (ImageButton)rootView.findViewById( R.id.emailImageButton );
        websiteButton = (Button)rootView.findViewById( R.id.websiteButton );
        websiteImageButton = (ImageButton)rootView.findViewById( R.id.websiteImageButton );

        phoneButton.setOnClickListener( this );
        phoneImageButton.setOnClickListener( this );
        emailButton.setOnClickListener( this );
        emailImageButton.setOnClickListener( this );
        websiteButton.setOnClickListener( this );
        websiteImageButton.setOnClickListener( this );
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
        phoneButton = (Button)getActivity().findViewById( R.id.phoneButton );
        phoneImageButton = (ImageButton)getActivity().findViewById( R.id.phoneImageButton );
        emailButton = (Button)getActivity().findViewById( R.id.emailButton );
        emailImageButton = (ImageButton)getActivity().findViewById( R.id.emailImageButton );
        websiteButton = (Button)getActivity().findViewById( R.id.websiteButton );
        websiteImageButton = (ImageButton)getActivity().findViewById( R.id.websiteImageButton );

        phoneButton.setOnClickListener( this );
        phoneImageButton.setOnClickListener( this );
        emailButton.setOnClickListener( this );
        emailImageButton.setOnClickListener( this );
        websiteButton.setOnClickListener( this );
        websiteImageButton.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-01-09 12:41:01 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == phoneButton ) {
            phoneCall();
        } else if ( v == phoneImageButton ) {
            phoneCall();
        } else if ( v == emailButton ) {
            sendingMail();
        } else if ( v == emailImageButton ) {
            sendingMail();
        }
          else if ( v == websiteButton ) {
            websiteView();
        } else if ( v == websiteImageButton ) {
            websiteView();
        }
    }

public void phoneCall()
{
    Uri number=Uri.parse("tel:0525857646");
    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
    startActivity(callIntent);
    return;
}

public void sendingMail()
{
    String to="sarush1111@gmail.com";

    Intent email = new Intent(Intent.ACTION_SEND);
    email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});

    //need this to prompts email client only
    email.setType("message/rfc822");

    startActivity(Intent.createChooser(email, "Choose an Email client :"));
}

public void websiteView()
{
    Intent intent=new Intent(getActivity(),WebViewActivity.class);
    startActivity(intent);
}

}
