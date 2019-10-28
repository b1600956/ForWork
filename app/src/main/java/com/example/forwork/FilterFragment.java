package com.example.forwork;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends DialogFragment {
    private ArrayList<String> mSelectedItems;
    private String[] locationArray;
    private String[] amenitiesArray;
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView area_spinner;
    private LinearLayout filter_layout;
    private RadioGroup radioGroup;
    private String period;
    private String area;
    private double max_fee;
    private TextInputLayout filter_fee;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItems = new ArrayList<>();  // Where we track the selected items
        locationArray = getResources().getStringArray(R.array.location_list);
        amenitiesArray = getResources().getStringArray(R.array.amenitiesList);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogLayout = getActivity().getLayoutInflater().inflate(R.layout.fragment_filter, null);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, locationArray);
        area_spinner = dialogLayout.findViewById(R.id.filter_area_dropdown);
        area_spinner.setAdapter(adapter);
        filter_layout = dialogLayout.findViewById(R.id.filter_layout);
        radioGroup = dialogLayout.findViewById(R.id.filter_period_group);
        filter_fee = dialogLayout.findViewById(R.id.filter_fee);
        for (String amenity : amenitiesArray) {
            CheckBox checkbox = new CheckBox(getContext());
            checkbox.setText(amenity);
            checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((CheckBox) v).isChecked()) {
                        mSelectedItems.add(((CheckBox) v).getText().toString());
                    } else {
                        if (mSelectedItems.contains(((CheckBox) v).getText().toString())) {
                            mSelectedItems.remove(((CheckBox) v).getText().toString());
                        }
                    }
                }
            });
            filter_layout.addView(checkbox);
        }
        // Set the dialog title
        builder.setTitle(R.string.filter_title)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                // Set the action buttons
                .setView(dialogLayout)
                .setPositiveButton(R.string.filter_apply, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        period = getPeriod();
                        max_fee = getMaxFee();
                        area = getArea();
                        Activity activity = getActivity();
                        if (activity instanceof MyContractsActivity) {
                            //Intent intent = new Intent(getActivity(),MainActivity.class);
                            //startActivity(intent);
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

    private String getArea() {
        String areaStr = area_spinner.getText().toString().trim();
        if (areaStr.isEmpty())
            return null;
        else
            return areaStr;
    }

    private double getMaxFee() {
        String feeStr = filter_fee.getEditText().getText().toString().trim();
        if (feeStr.isEmpty())
            return 0;
        else
            return Double.parseDouble(feeStr);
    }

    private String getPeriod() {
        int id = radioGroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.filter_daily:
                return getResources().getString(R.string.workspace_daily);
            case R.id.filter_weekly:
                return getResources().getString(R.string.workspace_weekly);
            case R.id.filter_monthly:
                return getResources().getString(R.string.work_space_monthly);
            default:
                return null;
        }
    }

}
