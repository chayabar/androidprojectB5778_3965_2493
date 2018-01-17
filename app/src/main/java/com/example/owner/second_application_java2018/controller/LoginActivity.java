package com.example.owner.second_application_java2018.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner.second_application_java2018.R;
import com.example.owner.second_application_java2018.model.backend.DBManagerFactory;
import com.example.owner.second_application_java2018.model.backend.DB_manager;

//
public class LoginActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        DB_manager manager=DBManagerFactory.getManager();
        manager.getCustomers();
        manager.getBranchs();
        manager.getCars();
        manager.getCarModels();
        manager.getOrders();
        //SystemClock.sleep(7000);

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
    private Button registButton;
    private Button guestButton;
    private Switch savePasswordSwitch;

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
        registButton = (Button)findViewById( R.id.registButton );
        guestButton = (Button)findViewById( R.id.guestButton );
        savePasswordSwitch=(Switch)findViewById( R.id.savePasswordSwitch);

        loginButton.setOnClickListener( this );
        forgotPasswordButton.setOnClickListener( this );
        registButton.setOnClickListener( this );
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
            loadSharedPreferences();
            checkSharedPreferences();
        } else if ( v == forgotPasswordButton ) {
            // Handle clicks for forgotPasswordButton
        } else if ( v == registButton ) {
            Intent intent = new Intent(LoginActivity.this,addCustomerActivity.class);
            startActivity(intent);
        } else if ( v == guestButton ) {
            enter();
        }
    }

    private void enter(){
        Intent intent = new Intent(LoginActivity.this,MainNavigationActivity.class);
        startActivity(intent);
    }

    private void checkSharedPreferences(){
       // Boolean SavePass = sharedPreferences.getBoolean("SavePass",false);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        DB_manager manager = DBManagerFactory.getManager();
        String name=userNameEditText.getText().toString();
        int id=Integer.valueOf(this.passwordEditText.getText().toString());

        if(manager.checkUsernameAndPassword(name,id)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            boolean savePass = savePasswordSwitch.getShowText();
            editor.putBoolean("savePass", savePass);
            editor.commit();
            enter();
            return;
        }
        else
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();


    }

    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean SavePass = sharedPreferences.getBoolean("SavePass",false);
        if (! SavePass )
            return;
        else {
            if (sharedPreferences.contains("NAME")) {
                userNameEditText.setText(sharedPreferences.getString("NAME", null));
                Toast.makeText(this, "load name", Toast.LENGTH_SHORT).show();
            }
            if (sharedPreferences.contains("ID")) {
                int id = sharedPreferences.getInt("ID", 0);
                passwordEditText.setText(String.valueOf(id));
                Toast.makeText(this, "load id", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

