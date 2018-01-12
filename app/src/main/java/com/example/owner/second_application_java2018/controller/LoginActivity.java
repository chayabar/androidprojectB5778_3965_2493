package com.example.owner.second_application_java2018.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.second_application_java2018.R;

//  yishar koach!
public class LoginActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();

        /*final ContentValues contentValues = new ContentValues();
        contentValues.put(RentConst.BranchConst.BRANCHNUMBER, "100");
        contentValues.put(RentConst.BranchConst.PARKINGSPACES, "3");
        contentValues.put(RentConst.BranchConst.ADDRESS, "kanfei nesharim 12, jerusalem, Israel");
        DBManagerFactory.getManager().addBranch(contentValues);*/

    }

    private EditText passwordEditText;
    private EditText userNameEditText;
    private Button loginButton;
    private TextView memberLoginTextView;
    private ImageView userImageView;
    private ImageView passwordImageView;
    private Button forgotPasswordButton;
    private Button registBeutton;
    private Button guestButton;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-01-04 19:35:38 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        passwordEditText = (EditText)findViewById( R.id.passwordEditText );
        userNameEditText = (EditText)findViewById( R.id.userNameEditText );
        loginButton = (Button)findViewById( R.id.loginButton );
        memberLoginTextView = (TextView)findViewById( R.id.memberLoginTextView );
         userImageView = (ImageView)findViewById( R.id.userImageView );
        passwordImageView = (ImageView)findViewById( R.id.passwordImageView );
        forgotPasswordButton = (Button)findViewById( R.id.forgotPasswordButton );
        registBeutton = (Button)findViewById( R.id.registBeutton );
        guestButton = (Button)findViewById( R.id.guestButton );

        loginButton.setOnClickListener( this );
        forgotPasswordButton.setOnClickListener( this );
        registBeutton.setOnClickListener( this );
        guestButton.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-01-04 19:35:38 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == loginButton ) {
            // Handle clicks for loginButton
        } else if ( v == forgotPasswordButton ) {
            // Handle clicks for forgotPasswordButton
        } else if ( v == registBeutton ) {
            // Handle clicks for registBeutton
        } else if ( v == guestButton ) {
            Intent intent = new Intent(LoginActivity.this,MainNavigationActivity.class);
            startActivity(intent);
        }
    }

}

