package com.example.owner.second_application_java2018.controller;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.owner.second_application_java2018.R;
import com.example.owner.second_application_java2018.fragment.ContactFragment;
import com.example.owner.second_application_java2018.fragment.ExitDialogFragment;
import com.example.owner.second_application_java2018.fragment.FragmentBranches;
import com.example.owner.second_application_java2018.fragment.FragmentReserveACar;
import com.example.owner.second_application_java2018.fragment.YourReservationFragment;
import com.example.owner.second_application_java2018.model.backend.DBManagerFactory;
import com.example.owner.second_application_java2018.model.backend.DB_manager;
import com.example.owner.second_application_java2018.model.datasource.ForegroundServiceCarStatusChange;
import com.example.owner.second_application_java2018.model.datasource.MyReceiver;

/**
 * this is the main navigation class
 * the navigation have 5 option
 * if the customer logged in with his account all the option enables, if not cant order a car
 */

public class MainNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    IntentFilter intent;  //variable for intent
    MyReceiver reciver;  //variable for receiver
    boolean doubleBackToExitPressedOnce = false;

    int currentCustomer=-1;  //default value, if not logged in
    DB_manager manager=DBManagerFactory.getManager();  //get implementation of DBManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {  //method OnCreate is activated on the creation of this navigation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intent= new IntentFilter();  //set the service and receiver
        reciver= new MyReceiver();
        intent.addAction("INVITATION_SET");
        Intent serviceIntent=new Intent(getBaseContext(), ForegroundServiceCarStatusChange.class);
        startService(serviceIntent);
        registerReceiver(reciver, intent);

        currentCustomer = getIntent().getIntExtra("EXTRA_USER_ID", -1);  //set user ID with default of -1

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);  //define the action for the fab button (send mail)
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                String to="sarush1111@gmail.com";
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                //need this to prompts email client only
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    /**This method is used to back to main navigation or
     * if was duble click move to login activity.
     * @return void
     */
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);  //2 clicks in time frame of 2 min
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_current_reservation) {
            yourReservation();

        }else if (id==R.id.action_exit){
            showDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ft;
        Fragment fragment;
        Bundle bundle = new Bundle();   //define bundle with the user ID, used to send to fragments
        bundle.putInt("current customer", currentCustomer);

        if (id == R.id.nav_contactUs) {
            //show fragment of contact us
            fragment = new ContactFragment();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content, fragment);
            ft.commit();

        } else if (id == R.id.nav_branches) {
            //show fragment of all branches
            fragment = new FragmentBranches();
            fragment.setArguments(bundle);
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content, fragment);
            ft.commit();

        } else if (id == R.id.nav_reserveAcar) {
            //show fragment reserve a car, only if customer logged in and do not have order already
            if (currentCustomer==-1)
            {
                Toast.makeText(this, "In order to add a reservation, please log in", Toast.LENGTH_LONG).show();
            }
            else if(manager.getOpenOrderByCustomer(currentCustomer)==null) {
                fragment = new FragmentReserveACar();
                fragment.setArguments(bundle);
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.commit();
            }
            else Toast.makeText(this, "you already have a reservation", Toast.LENGTH_LONG).show();
        }
        else if (id == R.id.nav_yourReservation) {
            //show fragment "your reservation", function handle the replacement
            yourReservation();
        } else if (id == R.id.nav_disconnect) {
            //exit the program
            showDialog();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    void showDialog()// for exit from the application
    {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = ExitDialogFragment.newInstance(/*mStackLevel*/0,this);
        newFragment.show(ft, "dialog");
    }

    public void yourReservation(){
        //if customer have open reservation show the fragment
        if(manager.getOpenOrderByCustomer(currentCustomer)!=null ) {
            FragmentTransaction ft;
            Fragment fragment;
            Bundle bundle = new Bundle();  //send budle with customer reservation
            bundle.putInt("current customer", currentCustomer);
            fragment = new YourReservationFragment();
            fragment.setArguments(bundle);
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content, fragment);
            ft.commit();
        }
        else
            //in case the customer do not have open reservation
            Toast.makeText(this, "there is no open reservation", Toast.LENGTH_LONG).show();
    }
}
