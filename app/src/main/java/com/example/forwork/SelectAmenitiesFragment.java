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
public class SelectAmenitiesFragment extends DialogFragment {
    private ArrayList<String> mSelectedItems;
    private String[] amenitiesArray;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItems = new ArrayList<>();  // Where we track the selected items
        amenitiesArray = getResources().getStringArray(R.array.amenitiesList);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle(R.string.amenities_title)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(R.array.amenitiesList, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(amenitiesArray[which]);
                                } else if (mSelectedItems.contains(amenitiesArray[which])) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(amenitiesArray[which]);
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton(R.string.amenities_fragment_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Activity activity = getActivity();
                        if (activity instanceof AddWorkspaceActivity) {
                            ((AddWorkspaceActivity) activity).getSelectedAmenities(mSelectedItems);
                        }
                    }
                })
                .setNegativeButton(R.string.amenities_fragment_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

}
