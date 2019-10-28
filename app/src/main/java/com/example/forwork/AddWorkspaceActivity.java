package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddWorkspaceActivity extends AppCompatActivity {
    private TextInputLayout placeName;
    private TextInputLayout placeDescription;
    private TextInputLayout placeAddress;
    private TextInputLayout placeCapacity;
    private TextInputLayout placeOpeningHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workspace);
        placeName = findViewById(R.id.place_name);
        placeDescription = findViewById(R.id.place_description);
        placeAddress = findViewById(R.id.place_address);
        placeCapacity = findViewById(R.id.place_capacity);
        placeOpeningHour = findViewById(R.id.opening_hour);
    }

    private boolean validatePlaceName() {
        String nameStr = placeName.getEditText().getText().toString().trim();
        if (nameStr.isEmpty()) {
            placeName.setError("Field can't be empty!");
            return false;
        } else {
            placeName.setError(null);
            placeName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePlaceDesc() {
        String descStr = placeDescription.getEditText().getText().toString().trim();
        if (descStr.isEmpty()) {
            placeDescription.setError("Field can't be empty!");
            return false;
        } else {
            placeDescription.setError(null);
            placeDescription.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePlaceAddress() {
        String addressStr = placeAddress.getEditText().getText().toString().trim();
        if (addressStr.isEmpty()) {
            placeAddress.setError("Field can't be empty!");
            return false;
        } else {
            placeAddress.setError(null);
            placeAddress.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePlaceCapacity() {
        String capacityStr = placeCapacity.getEditText().getText().toString().trim();
        if (capacityStr.isEmpty()) {
            placeCapacity.setError("Field can't be empty!");
            return false;
        }
        try {
            int capacity = Integer.parseInt(capacityStr);
            if (capacity == 0) {
                placeCapacity.setError("Capacity can't be zero!");
                return false;
            } else {
                placeCapacity.setError(null);
                placeCapacity.setErrorEnabled(false);
            }
            return true;
        } catch (NumberFormatException nfe) {
            placeCapacity.setError("Please enter number!");
            return false;
        }
    }

    private boolean validateOpeningHour() {
        String openingHourStr = placeOpeningHour.getEditText().getText().toString().trim();
        if (openingHourStr.isEmpty()) {
            placeOpeningHour.setError("Field can't be empty!");
            return false;
        } else {
            placeOpeningHour.setError(null);
            placeOpeningHour.setErrorEnabled(false);
        }
        return true;
    }

    public void submitWorkspace(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if (validatePlaceName() && validatePlaceDesc() && validatePlaceAddress() && validatePlaceCapacity() && validateOpeningHour()) {
            Snackbar.make(findViewById(R.id.addWorkspace_layout), getString(R.string.feedback_sent), Snackbar.LENGTH_LONG).show();
            clearField();
            Intent intent = new Intent(this, LessorContractActivity.class);
            startActivity(intent);
        }
    }

    private void clearField() {
        placeOpeningHour.getEditText().setText("");
        placeCapacity.getEditText().setText("");
        placeAddress.getEditText().setText("");
        placeDescription.getEditText().setText("");
        placeName.getEditText().setText("");
    }

    public void getSelectedAmenities(ArrayList<String> mSelectedItems) {

    }

    public void selectAmenities(View view) {
        DialogFragment serviceFragment = new SelectAmenitiesFragment();
        serviceFragment.show(getSupportFragmentManager(), "Amenities");
    }
}
