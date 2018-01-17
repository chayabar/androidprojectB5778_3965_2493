package com.example.owner.second_application_java2018.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.owner.second_application_java2018.R;
import com.example.owner.second_application_java2018.model.backend.DBManagerFactory;
import com.example.owner.second_application_java2018.model.backend.RentConst;


public class addCustomerActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        findViews();
    }
    private EditText FirstNameEditText;
    private EditText LastNameEditText;
    private EditText IDEditText;
    private EditText PhoneNumEditText;
    private EditText eMailEditText;
    private EditText CreditEditText;
    private Button AddButton;

    private void findViews() {
        FirstNameEditText = (EditText)findViewById( R.id.firstNameeditText );
        LastNameEditText = (EditText)findViewById( R.id.LastNameEditText );
        IDEditText = (EditText)findViewById( R.id.IDEditText );
        PhoneNumEditText = (EditText)findViewById( R.id.PhoneNumEditText );
        eMailEditText = (EditText)findViewById( R.id.eMailEditText );
        CreditEditText = (EditText)findViewById( R.id.CreditEditText );
        AddButton = (Button)findViewById( R.id.AddButton );

        AddButton.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == AddButton ) {
            // Handle clicks for AddButton
            addCustomer();
            saveSharedPreferences();
            finish();
        }
    }
    private void addCustomer() {
        final ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(RentConst.CustomerConst.CREDITCARD, this.CreditEditText.getText().toString());
            contentValues.put(RentConst.CustomerConst.EMAIL, this.eMailEditText.getText().toString());
            contentValues.put(RentConst.CustomerConst.FIRSTNAME, this.FirstNameEditText.getText().toString());
            contentValues.put(RentConst.CustomerConst.ID, Integer.valueOf(this.IDEditText.getText().toString()));
            contentValues.put(RentConst.CustomerConst.LASTNAME, this.LastNameEditText.getText().toString());
            contentValues.put(RentConst.CustomerConst.PHONENUMBER, this.PhoneNumEditText.getText().toString());

            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected void onPostExecute(Boolean idResult) {
                    super.onPostExecute(idResult);
                    if (idResult == true)
                        Toast.makeText(getBaseContext(), "the Customer inserted: " + idResult, Toast.LENGTH_LONG).show();
                }
                @Override
                protected Boolean doInBackground(Void... params) {
                    return DBManagerFactory.getManager().addCustomer(contentValues);
                }
            }.execute();
        }
        catch (Exception e) {}
    }

    private void saveSharedPreferences() {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String name = LastNameEditText.getText().toString();
            int id = Integer.parseInt(IDEditText.getText().toString());
            editor.putString("NAME", name);
            editor.putInt("ID", id);
            editor.commit();
            Toast.makeText(this, "You are saved as a Member", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, "failed to save, Please try again", Toast.LENGTH_SHORT).show();
        }
    }

}
