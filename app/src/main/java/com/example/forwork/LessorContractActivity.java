package com.example.forwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

public class LessorContractActivity extends AppCompatActivity {
    private String[] chargeRate;
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView spinner;
    private TextInputLayout contract_duration;
    private TextInputLayout contract_fee;
    private TextInputLayout contract_charge_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessor_contract);
        chargeRate = getResources().getStringArray(R.array.chargeRateList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, chargeRate);
        ;
        spinner = findViewById(R.id.filled_exposed_dropdown);
        spinner.setAdapter(adapter);
        contract_duration = findViewById(R.id.contract_duration);
        contract_fee = findViewById(R.id.contract_fee);
        contract_charge_rate = findViewById(R.id.charge_rate);
    }

    private boolean validateDuration() {
        String durationStr = contract_duration.getEditText().getText().toString().trim();
        if (durationStr.isEmpty()) {
            contract_duration.setError("Field can't be empty!");
            return false;
        } else {
            contract_duration.setError(null);
            contract_duration.setErrorEnabled(false);
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

    private boolean validateChargeRate() {
        String chargeRateStr = contract_charge_rate.getEditText().getText().toString().trim();
        if (chargeRateStr.isEmpty()) {
            contract_charge_rate.setError("Field can't be empty!");
            return false;
        } else {
            List<String> list = Arrays.asList(chargeRate);
            if (list.contains(chargeRateStr)) {
                contract_charge_rate.setError(null);
                contract_charge_rate.setErrorEnabled(false);
            } else {
                contract_charge_rate.setError("Please enter per hour/day/month!");
                return false;
            }
        }
        return true;
    }

    private void clearField() {
        contract_duration.getEditText().setText("");
        contract_fee.getEditText().setText("");
        spinner.setText("");
    }

    public void createContract(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if (validateDuration() && validateFee() && validateChargeRate()) {
            Snackbar.make(findViewById(R.id.lessor_contract_layout), getString(R.string.feedback_sent), Snackbar.LENGTH_LONG).show();
            clearField();
        }
    }
}
