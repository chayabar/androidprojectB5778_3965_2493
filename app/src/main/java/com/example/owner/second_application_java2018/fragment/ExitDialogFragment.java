package com.example.owner.second_application_java2018.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

/**
 * Created by User on 27 Tevet 5778.
 */

/** This class use to show message(dialog) before user signing out of the app  */

public class ExitDialogFragment extends DialogFragment
{
    int mNum;
    static Activity actTOExit;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    public static ExitDialogFragment newInstance(int num, Activity activity) {
        ExitDialogFragment f = new ExitDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);
        actTOExit=activity;

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to exit?")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        // Exit the application
                        actTOExit.finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);



                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });


        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public int show(FragmentTransaction transaction, String tag)
    {
        return super.show(transaction, tag);
    }
}
