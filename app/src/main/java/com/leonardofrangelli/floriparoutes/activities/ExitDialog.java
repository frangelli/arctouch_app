package com.leonardofrangelli.floriparoutes.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import static android.app.AlertDialog.*;

/**
 * Created by frangelli on 9/6/14.
 */
public class ExitDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Builder dialog = new Builder(getActivity());
        dialog.setTitle("Sure want to exit?");
        dialog.setMessage("Are you sure that wanna exit this awsome app?");

        //actions for the dialog
        dialog.setPositiveButton("YES",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(),"Ufa... That was close!", Toast.LENGTH_SHORT).show();
            }
        });
        return dialog.create();
    }
}
