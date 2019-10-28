package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

public class LessorContractActivity extends AppCompatActivity {
    private String[] chargeRate;
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView spinner;
    private TextInputLayout contract_min_duration;
    private TextInputLayout contract_fee;
    private TextView duration_quantifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessor_contract);/**
        chargeRate = getResources().getStringArray(R.array.chargeRateList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, chargeRate);
        spinner = findViewById(R.id.filled_exposed_dropdown);
         spinner.setAdapter(adapter);**/
        contract_min_duration = findViewById(R.id.contract_duration);
        contract_fee = findViewById(R.id.contract_fee);
        duration_quantifier = findViewById(R.id.min_duration_quantifier);
    }

    private boolean validateDuration() {
        String durationStr = contract_min_duration.getEditText().getText().toString().trim();
        if (durationStr.isEmpty()) {
            contract_min_duration.setError("Field can't be empty!");
            return false;
        } else {
            if (Integer.parseInt(durationStr) == 0) {
                contract_min_duration.setError("Minimum duration can't be zero!");
                return false;
            }
            contract_min_duration.setError(null);
            contract_min_duration.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFee() {
        String feeStr = contract_fee.getEditText().getText().toString().trim();
        if (feeStr.isEmpty()) {
            contract_fee.setError("Field can't be empty!");
            return false;
        } else {
            contract_fee.setError(null);
            contract_fee.setErrorEnabled(false);
        }
        return true;
    }

    private void clearField() {
        contract_min_duration.getEditText().setText("");
        contract_fee.getEditText().setText("");
    }

    public void createContract(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if (validateDuration() && validateFee()) {
            Snackbar.make(findViewById(R.id.lessor_contract_layout), getString(R.string.feedback_sent), Snackbar.LENGTH_LONG).show();
            clearField();
        }
    }

    public void selectPeriod(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.contract_daily:
                if (checked)
                    //implementation code
                    duration_quantifier.setText(getResources().getString(R.string.min_duration_day));
                break;
            case R.id.contract_weekly:
                if (checked)
                    duration_quantifier.setText(getResources().getString(R.string.min_duration_week));
                break;
            case R.id.contract_monthly:
                if (checked)
                    duration_quantifier.setText(getResources().getString(R.string.min_duration_month));
                break;
            default:
                break;
        }
    }
}
