package com.example.forwork;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectUserTypeFragment extends DialogFragment {
    private String userType;
    private String[] userTypeArray;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        userTypeArray = getResources().getStringArray(R.array.userType);
        int defaultSelect = 0;
        userType = userTypeArray[defaultSelect];
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle(R.string.choose_user_type)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setSingleChoiceItems(R.array.userType, defaultSelect,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                userType = userTypeArray[which];
                            }
                        })
                // Set the action buttons
                .setPositiveButton(R.string.amenities_fragment_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Activity activity = getActivity();
                        if (activity instanceof SignInActivity) {
                            ((SignInActivity) activity).getSelectedUserType(userType);
                        }
                    }
                });

        return builder.create();
    }

}